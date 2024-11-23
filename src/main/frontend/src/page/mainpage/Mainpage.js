import styles from "./Mainpage.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import HeaderImg from "../../image/nail.png";
import Table from "../../components/_Tool/Table"

export default function Mainpage() {
    const [record, setRecord] = useState([]);

    console.log("rrrr",localStorage.getItem("username"))
    useEffect(() => {
        fetch("/api/watching")
            .then(res=>res.json())
            .then(res=> {
                setRecord(res);
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
                <div className={styles.title}>2024 KBO</div>
                <br/>다가오는 경기 - 추후 기능 추가<br/><br/>
                {record.map(data => (
                    <Table
                        id={data.watchingId}
                        title={data.title}
                        date={(() => {
                            const gameDate = new Date(data.game.gameDate);
                            const year = gameDate.getFullYear();
                            const month = String(gameDate.getMonth() + 1).padStart(2, '0');  // getMonth() returns 0-11
                            const day = String(gameDate.getDate()).padStart(2, '0');
                            return `${year}년 ${month}월 ${day}일`;
                        })()}
                        location={data.location}
                        homeTeam={data.game.homeTeam.teamName}
                        awayTeam={data.game.awayTeam.teamName}
                    />
                ))}


            </div>
            <Footer />
        </div>
    );
}
