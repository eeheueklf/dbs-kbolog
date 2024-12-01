import React from "react";
import styles from "./PlayerScore.module.css"


export default function PlayerScore({ playerType ,scoreData }) {
    return (
        <div className={styles.playerScore}>
            {playerType === "Pitcher" ? (
                <ul>
                    <li>
                        <span>ERA</span>
                        <strong>{scoreData.era}</strong>
                    </li>
                    <li>
                        <span>이닝</span>
                        <strong>{scoreData.ip}</strong>
                    </li>
                    <li>
                        <span>WHIP</span>
                        <strong>{scoreData.whip}</strong>
                    </li>
                </ul>
            ) : (

            <ul>
                <li>
                    <span>타율</span>
                    <strong>{scoreData.avg}</strong>
                </li>
                <li>
                    <span>OPS</span>
                    <strong>{scoreData.ops}</strong>
                </li>
                <li>
                    <span>WAR</span>
                    <strong>{scoreData.war}</strong>
                </li>
            </ul>
            )}
        </div>
    );
}