import styles from "./Players.module.css";
import React, { useEffect, useState } from "react";
import Footer from "../../components/_Layout/Footer";
import HeaderImg from "../../image/playerHeader.jpg";
import PlayerRank from "../../components/_Table/_PlayerRank";

export default function PlayersFanRank() {
    const [players, setPlayers] = useState([]);
    const [view, setView] = useState("pitcher");

    useEffect(() => {
        fetch(`/api/player/rank`)
            .then(res => res.json())
            .then((data) => {
                setPlayers(data);
                console.log(data)
            })
            .catch((err) => {
                console.error("API fetch error:", err);
            });
    }, []);

    return (
        <div className={styles.default}>
            <div className={styles.cropping}>
                <img className={styles.headerImg} src={HeaderImg} alt="Header" />
            </div>
            <div className={styles.inner}>
                <div className={styles.title}>인기 선수</div>
                <br/>

                <PlayerRank pId={0} pPosition={view === "pitcher" ? 1 : 0}/>
                {players.map((player, index ) => (
                    <PlayerRank
                        key={player.playerId}
                        index={index}
                        pName={player.playerName}
                        pTeam={player.teamName}
                        pSponsor={player.teamSponsor}
                        fanCount={player.fanCount}
                        pPosition={player.playerPosition}
                    />
                ))}

            </div>
            <Footer/>
        </div>
    );
}
