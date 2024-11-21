import React from "react";
import styles from "./Table.module.css";

export default function Table({ title, date, homeTeam, awayTeam }) {

    return (
        <div className={styles.tableWrapper}>
            <table className={styles.table}>
                <tbody>
                        <tr className={styles.tr}>
                            <td className={styles.td}>{title}</td>
                            <td className={styles.td}>{date}</td>
                            <td className={styles.td}>{homeTeam}</td>
                            <td className={styles.td}>{awayTeam}</td>
                        </tr>

                </tbody>
            </table>
        </div>
    );
}
