import styles from "./Playerpage.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import HeaderImg from "../../image/nail.png";
import PlayerTable from "../../components/_Tool/PlayerTable";

export default function Playerpage() {
    const [player, setPlayer] = useState([]);

    useEffect(() => {
        fetch("/api/cheerplayer")
            .then(res=>res.json())
            .then(data=> {
                setPlayer(data);
                console.log(data);
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
                <div className={styles.title}>관심 선수</div>
                <br/><br/>
                {player.map(data => (
                    <PlayerTable
                        pId={data.playerId}
                        pName={data.playerName}
                        pNum={data.playerNumber}
                        pPosition={data.playerPosition}
                    />
                ))}
                <PlayerTable pId={-1}/>

            </div>
            <Footer/>
        </div>
    );
}
