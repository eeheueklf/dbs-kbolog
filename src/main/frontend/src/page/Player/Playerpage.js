import styles from "./Playerpage.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import HeaderImg from "../../image/playerHeader.jpg";
import PlayerTable from "../../components/_Table/PlayerTable";

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
                <br/>
                <PlayerTable pId={0}/>
                {player.map(data => (
                    <PlayerTable
                        pId={data.playerId}
                        pName={data.playerName}
                        pNum={data.playerNumber}
                        pPosition={data.playerPosition}
                        pTeam={data.team.teamName}
                        pSponsor={data.team.sponsor}
                    />
                ))}
                <PlayerTable pId={-1}/>

            </div>
            <Footer/>
        </div>
    );
}
