import styles from "./Playerpage.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import HeaderImg from "../../image/nail.png";
import Table from "../../components/_Tool/Table"

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
                    <Table
                        id={data.playerId}
                        title={data.playerName}
                    />
                ))}
                <Table id={-1}/>

            </div>
            <Footer/>
        </div>
    );
}
