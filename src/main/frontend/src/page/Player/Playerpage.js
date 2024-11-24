import styles from "./Playerpage.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";


export default function Playerpage() {
    const [team, setTeam] = useState([]);
    const [backgroundColor, setBackgroundColor] = useState("#f0f0f0");
    const [gameData, setGameData] = useState([]);
    const [selectedDate, setSelectedDate] = useState(new Date());
    const [gamesForSelectedDate, setGamesForSelectedDate] = useState([]);

    const formatDate = (date) => {
        const d = new Date(date);
        const year = d.getFullYear();
        const month = String(d.getMonth() + 1).padStart(2, '0');
        const day = String(d.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    };


    useEffect(() => {
        fetch("/api/my")
            .then(res => res.json())
            .then(res => {
                setTeam(res.rootTeam)
            })
            .catch((err) => {
                console.error("API fetch error:", err); // 에러 처리
            });

        switch (team.sponsor) {
            case "기아":
                setBackgroundColor("#F73600"); // 기아의 배경색
                break;
            case "두산":
                setBackgroundColor("#131230"); // 두산의 배경색
                break;
            case "삼성":
                setBackgroundColor("#074CA1"); // 삼성의 배경색
                break;
            default:
                setBackgroundColor("#f0f0f0"); // 기본 배경색
        }
    }, [team.sponsor]);


    return (
        <div className={styles.default}>
            <div className={styles.cropping}
                 style={{backgroundColor: backgroundColor}}></div>
            <div className={styles.inner}>
                <div className={styles.title}>관심 선수</div>


            </div>
            <Footer/>
        </div>
            );
            }
