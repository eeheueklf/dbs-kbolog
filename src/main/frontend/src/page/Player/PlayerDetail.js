import styles from "./PlayerDetail.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import { useParams} from 'react-router-dom';
import SingleTable from "../../components/_Table/SingleTable";
import {getPosition} from '../../components/_Table/PlayerTable'

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart as faHeartFull} from "@fortawesome/free-solid-svg-icons";
import { faHeart as faHeartEmpty} from "@fortawesome/free-regular-svg-icons";
import PlayerScore from "../../components/_List/PlayerScore";

export default function PlayerDetail() {
    const [player, setPlayer] = useState(null);
    const { pId } = useParams();
    const [pData, setPData] = useState(null);
    const [isFavorite, setIsFavorite] = useState(false);

    useEffect(() => {
        // 선수 상세 정보 가져오기
        fetch(`/api/player/${pId}`)
            .then(res => res.json())
            .then((data) => {
                setPlayer(data);
                setPData({
                    ...data,
                    scoreData: data.playerPosition === 1
                        ? {
                            era: data.era,
                            ip: data.ip,
                            whip: data.whip,
                        }
                        : {
                            avg: data.avg,
                            ops: data.ops,
                            war: data.war,
                        },
                });

                // 해당 선수가 관심 선수 목록에 포함되어 있는지 체크
                fetch(`/api/player/isFav/${pId}`)
                    .then((res) => res.json())
                    .then((isFavorite) => {
                        setIsFavorite(isFavorite);
                    })
                    .catch((err) => {
                        console.error("Error checking favorite status:", err);
                    });
            })
            .catch((err) => {
                console.error("API fetch error:", err);
            });
    }, [pId]);

    const toggleFavorite = async () => {
        try {
            const response = await fetch(`/api/player/select/${pId}`, {
                method: isFavorite ? "DELETE" : "POST",  // 관심 설정: POST, 해제: DELETE
                headers: {
                    "Content-Type": "application/json",
                },
            });

            if (!response.ok) {
                throw new Error("관심 선수 설정/해제에 실패했습니다.");
            }

            // 상태 업데이트
            setIsFavorite((prev) => !prev);
            console.log(
                isFavorite
                    ? `${player.playerName}를 관심 선수에서 해제했습니다.`
                    : `${player.playerName}를 관심 선수로 설정했습니다.`
            );
        } catch (error) {
            console.error("Error:", error);
        }
    };

    return (
        <div className={styles.default}>
            <div className={styles.cropping}>
            </div>
            <div className={styles.inner}>
                {player ? (
                    <div>
                        {/* 관심 선수 아이콘 */}
                        <div onClick={toggleFavorite} className={styles.favoriteIcon}>
                            <FontAwesomeIcon
                                className={styles.ii}
                                icon={isFavorite ? faHeartFull : faHeartEmpty}
                                title={isFavorite ? "관심 선수 해제" : "관심 선수 설정"}
                            />
                        </div>
                        {player.playerPosition === 1 ? (
                            <p className={styles.emoji}>🧢</p>
                        ) : <p className={styles.emoji}>⚾️</p>}
                        <p className={styles.title}>{player.playerName}</p>

                        <SingleTable
                            iconName={"faBaseball"}
                            type={"등번호"}
                            data={player.playerNumber}
                        />
                        <SingleTable
                            iconName={"faTicket"}
                            type={"포지션"}
                            data={getPosition(player.playerPosition)}
                        />
                        {pData ?
                            (
                                <PlayerScore
                                    position={pData.playerPosition}
                                    scoreData={pData.scoreData}
                                />
                            ) : <p>Loading...</p>
                        }

                    </div>
                ) : (
                    <p>Loading...</p>  // 로딩 중
                )}

            </div>
            <Footer/>
        </div>
    );
}
