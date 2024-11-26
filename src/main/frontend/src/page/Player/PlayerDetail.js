import styles from "./PlayerDetail.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import { useParams} from 'react-router-dom';
import SingleTable from "../../components/_Table/SingleTable";
import {getPosition} from '../../components/_Table/PlayerTable'

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPenToSquare,faCircleXmark} from "@fortawesome/free-regular-svg-icons";
import PlayerScore from "../../components/_List/PlayerScore";

export default function PlayerDetail() {
    const [player, setPlayer] = useState(null);
    const { pId } = useParams();
    const [pData, setPData] = useState(null);

    useEffect(() => {
        fetch(`/api/player/${pId}`)
            .then(res=>res.json())
            .then((data) => {
                setPlayer(data);
                setPData({
                    ...data,
                    scoreData:
                        data.playerPosition === 1
                            ? {
                                bb: data.bb,
                                era: data.era,
                            }
                            : {
                                avg: data.avg,
                                obp: data.obp,
                                slg: data.slg,
                            },
                });

                console.log(pData.scoreData)
            })
            .catch((err) => {
                console.error("API fetch error:", err);
            });
    }, [pId]);

    return (
        <div className={styles.default}>
            <div className={styles.cropping}>
            </div>
            <div className={styles.inner}>
                {player ? (
                    <div>
                        <p className={styles.emoji}>🧢</p>
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
