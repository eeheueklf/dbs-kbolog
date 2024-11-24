import React from "react";
import styles from "./SingleTable.module.css"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faLocationPin, faCalendarCheck, faBaseball } from "@fortawesome/free-solid-svg-icons";
import { faCircleUser  } from "@fortawesome/free-solid-svg-icons";

// 아이콘 매핑
const iconMap = {
    faTicket: faLocationPin,
    faCalendarCheck: faCalendarCheck,
    faBaseball: faBaseball,
    faUser: faCircleUser,
};

export default function SingleTable({ iconName, type, data }) {
    console.log(data)
    return (
        <div className={styles.tableWrapper}>
            <table className={styles.table}>
                <tbody>
                <tr className={styles.tr}>
                    <td className={styles.td}>
                        <FontAwesomeIcon className={styles.ii} icon={iconMap[iconName]}/>
                    </td>
                    <td className={styles.td}><p className={styles.grey}>{type}</p></td>
                    <td className={styles.td}>{data}</td>
                </tr>
                </tbody>
            </table>
        </div>
    );
}
