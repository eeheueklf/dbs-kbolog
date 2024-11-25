import React from "react";
import styles from "./PlayerScore.module.css"


export default function PlayerScore({ position ,scoreData }) {
    console.log(position, scoreData)

    return (
        <div className={styles.playerScore}>
            {position === 1 ? (
                <ul>
                    <li>
                        <span>ERA</span>
                        <strong>{scoreData.innings}</strong>
                    </li>
                    <li>
                        <span>승</span>
                        <strong>1313</strong>
                    </li>
                    <li>
                        <span>패</span>
                        <strong>1313</strong>
                    </li>
                    <li>
                        <span>세이브</span>
                        <strong>1313</strong>
                    </li>
                    <li>
                        <span>탈삼진</span>
                        <strong>1313</strong>
                    </li>
                </ul>
            ) : (

            <ul>
                <li>
                    <span>d</span>
                    <strong>{scoreData.obp}</strong>
                </li>
                <li>
                    <span>득점</span>
                    <strong>{scoreData.avg}</strong>
                </li>
                <li>
                    <span>안타</span>
                    <strong>{scoreData.slg}</strong>
                </li>
                <li>
                    <span>홈런</span>
                    <strong></strong>
                </li>
                <li>
                    <span>타율</span>
                    <strong></strong>
                </li>
            </ul>
            )}
        </div>
    );
}