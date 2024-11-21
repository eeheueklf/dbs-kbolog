import React from "react";
import styles from "./Table.module.css";

function getTeamClass(teamName) {
    if (teamName === "기아") return styles.kia;
    if (teamName === "LG") return styles.lg;
    if (teamName === "두산") return styles.doosan;
    if (teamName === "키움") return styles.kiwoom;
    if (teamName === "SSG") return styles.ssg;
    if (teamName === "삼성") return styles.samsung;
    if (teamName === "한화") return styles.hanwha;
    if (teamName === "롯데") return styles.lotte;
    if (teamName === "NC") return styles.nc;
    if (teamName === "KT") return styles.kt;

    return styles.defaultTeam; // 기본 스타일
}

export default function Table({ title, date, location, homeTeam, awayTeam }) {

    return (
        <div className={styles.tableWrapper}>
            <table className={styles.table}>
                <tbody>
                    <tr className={styles.tr}>
                        <td className={styles.td}>{title}</td>
                        <td className={styles.td}>{date}</td>
                        <td className={styles.td}>{location}</td>
                        <td className={styles.td}>
                            <span className={`${styles.highlight} ${getTeamClass(homeTeam)}`}>
                                {homeTeam}
                            </span>
                        </td>
                        <td className={styles.td}>
                            <span className={`${styles.highlight} ${getTeamClass(awayTeam)}`}>
                                {awayTeam}
                            </span>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
}
