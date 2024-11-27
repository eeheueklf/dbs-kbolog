import styles from "./Teampage.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import Calendar from "react-calendar";
import "./calendar.css";


export default function Teampage() {
    const [team, setTeam] = useState([]);
    const [gameData, setGameData] = useState([]);
    const [selectedDate, setSelectedDate] = useState(new Date());

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

    }, [team.rootTeamSponsor]);

    const teamSponsorColors = {
        기아: "#F73600",
        두산: "#131230",
        롯데: "#041E42",
        삼성: "#074CA1",
        한화: "#F73600",
        KIA: "#EA0029",
        LG: "#C30452",
        SSG: "#CE0E2D",
        NC:"#315288",
        KT: "#000000",
    };

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
        const teamColor = teamSponsorColors[team.rootTeamSponsor] || "#f0f0f0";
        document.documentElement.style.setProperty('--team-color', teamColor);
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
