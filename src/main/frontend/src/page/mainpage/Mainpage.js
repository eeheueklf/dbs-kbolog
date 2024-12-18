import styles from "./Mainpage.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import HeaderImg from "../../image/stadium.jpg";
import Table from "../../components/_Table/Table"

export default function Mainpage() {
    const [record, setRecord] = useState([]);
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
                <br/>
                <Table id={0}/>
                {record.map(data => (
                    <Table
                        id={data.watchingId}
                        title={data.title}
                        date={(() => {
                            const gameDate = new Date(data.watchingDate);
                            const year = gameDate.getFullYear();
                            const month = String(gameDate.getMonth() + 1).padStart(2, '0');  // getMonth() returns 0-11
                            const day = String(gameDate.getDate()).padStart(2, '0');
                            return `${year}년 ${month}월 ${day}일`;
                        })()}
                        location={data.location}
                        homeTeam={data.homeTeam}
                        awayTeam={data.awayTeam}
                        homeSponsor={data.homeTeamSponsor}
                        awaySponsor={data.awayTeamSponsor}
                    />
                ))}
                <Table id={-1}/>

            </div>
            <Footer />
        </div>
    );
}