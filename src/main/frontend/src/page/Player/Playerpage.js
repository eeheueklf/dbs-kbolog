import styles from "./Playerpage.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import HeaderImg from "../../image/playerHeader.jpg";
import PlayerTable from "../../components/_Table/PlayerTable";
import Calendar from "react-calendar";
import {useNavigate} from "react-router-dom";

export default function Playerpage() {
    const [player, setPlayer] = useState([]);
    const navigate = useNavigate();
    useEffect(() => {
        fetch("/api/player/cheer")
            .then(res=>res.json())
            .then(data=> {
                setPlayer(data);
            })
            .catch((err) => {
                console.error("API fetch error:", err); // 에러 처리
            });
    }, []);


    return (
        <div className={styles.default}>
            <div className={styles.cropping}>
                <img className={styles.headerImg} src={HeaderImg} alt="Header" />
            </div>
            <div className={styles.inner}>
                <div className={styles.title}>관심 선수
                    <input
                        type="button"
                        value="💕팬 랭킹"
                        className={styles.rankBtn}
                        onClick={() => navigate('/players/rank')}
                    />
                </div>
                <br/>
                <PlayerTable pId={0}/>

                {player.map(data => {
                    // cheerDate를 Date 객체로 변환
                    const cheerDate = new Date(data.cheerDate);
                    const today = new Date();

                    // 밀리초 단위로 차이 계산 후, 일 단위로 변환
                    const diffInMs = today - cheerDate; // 밀리초 차이
                    const diffInDays = Math.floor(diffInMs / (1000 * 60 * 60 * 24)); // 일 단위

                    return (
                        <PlayerTable
                            pId={data.playerId}
                            pName={data.playerName}
                            pNum={data.playerNumber}
                            pPosition={data.playerPosition}
                            pTeam={data.teamName}
                            pSponsor={data.teamSponsor}
                            cheerDate={`${diffInDays}일`}
                        />
                    );
                })
                }
                <PlayerTable pId={-1}/>

            </div>
            <Footer/>
        </div>
    );
}
