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
    function getTeamClass(teamName) {
        if (teamName === "KIA") return styles.kia;
        if (teamName === "LG") return styles.lg;
        if (teamName === "두산") return styles.doosan;
        if (teamName === "키움") return styles.kiwoom;
        if (teamName === "SSG") return styles.ssg;
        if (teamName === "삼성") return styles.samsung;
        if (teamName === "한화") return styles.hanwha;
        if (teamName === "롯데") return styles.lotte;
        if (teamName === "NC") return styles.nc;
        if (teamName === "KT") return styles.kt;

        return styles.defaultTeam;
    }
    useEffect(() => {
        // 선수 상세 정보 가져오기
        fetch(`/api/player/${pId}`)
            .then(res => {
                if (!res.ok) { // 상태 코드가 2xx가 아닐 경우 오류 처리
                    throw new Error(`HTTP error! status: ${res.status}`);
                }
                return res.json();
            })
            .then((data) => {
                setPlayer(data);
                setPData({
                    ...data,
                    scoreData: data.playerType === "Pitcher"
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
                        <div onClick={toggleFavorite} className={styles.favoriteIcon}>
                            <FontAwesomeIcon
                                className={styles.ii}
                                icon={isFavorite ? faHeartFull : faHeartEmpty}
                                title={isFavorite ? "관심 선수 해제" : "관심 선수 설정"}
                            />
                        </div>
                        {player.playerType === "Pitcher" ? (
                            <p className={styles.emoji}>🧢</p>
                        ) : <p className={styles.emoji}>⚾️</p>}
                        <p className={styles.info}>{getPosition(player.playerPosition)} / {player.hander}{player.battingHand}</p>
                        <p className={styles.title}>
                            <span
                                className={`${styles.teamColor} ${getTeamClass(player.teamSponsor)}`}>{player.playerNumber}</span>
                            {player.playerName}
                        </p>
                        {pData ?
                            (
                                <PlayerScore
                                    playerType={pData.playerType}
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
