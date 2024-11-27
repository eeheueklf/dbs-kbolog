import styles from "./Teampage.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import Calendar from "react-calendar";
import "./calendar.css";


export default function Teampage() {
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
                setTeam(res);
            })
            .catch((err) => {
                console.error("API fetch error:", err); // 에러 처리
            });

        switch (team.rootTeamSponsor) {
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
    }, [team.rootTeamSponsor]);

    useEffect(() => {
        const selectedDate = new Date();
        const year = selectedDate.getFullYear();
        const month = selectedDate.getMonth();
        const startOfMonth = formatDate(new Date(year, month, 1));
        const endOfMonth = formatDate(new Date(year, month + 1, 0));
        fetch(`/api/game/team`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                teamId: team.rootTeamId,
                start: startOfMonth,
                end: endOfMonth,
            })})
            .then((res) => res.json())
            .then((data) => {
                if (Array.isArray(data)) {
                    setGameData(data);
                } else {
                    setGameData([]);
                }
            })
            .catch((err) => console.error("Failed to fetch game data:", err));
    }, [selectedDate, team.id]);

    const gamesByDate = gameData.reduce((acc, game) => {
        const gameDate = formatDate(game.gameDate); // Assuming the game date is in `game.date`
        if (!acc[gameDate]) acc[gameDate] = [];
        acc[gameDate].push(game);
        return acc;
    }, {});

    const tileContent = ({ date, view }) => {
        const formattedDate = formatDate(date);
        const gamesOnDate = gamesByDate[formattedDate];

        return gamesOnDate ? (
            <div className={styles.gameInfo}>
                {gamesOnDate.map((game, index) => {
                    return (
                        <div key={index} className={styles.game}>
                            {game.awayTeamSponsor} vs {game.homeTeamSponsor}
                        </div>
                    );
                })}
            </div>
        ) : null;
    };

    return (
        <div className={styles.default}>
            <div className={styles.cropping}></div>
                <div className={styles.inner}>
                    {team.rootTeamName ? (
                        <>
                            <div className={styles.title}>{team.rootTeamName} 경기 일정</div>
                            <div className={styles.calendarContainer}>
                                <Calendar
                                    onChange={setSelectedDate}
                                    value={selectedDate}
                                    tileContent={tileContent}
                                    formatDay={(locale, date) => date.toLocaleString("en", { day: "numeric" })}
                                    calendarType="gregory"
                                    locale="en"
                                    showNavigation={false}
                                />
                            </div>
                        </>
                    ) : (
                        <p>응원하는 팀이 없습니다</p>
                    )}


                </div>
                <Footer/>
            </div>
            );
            }
