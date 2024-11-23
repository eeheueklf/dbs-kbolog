import React from "react";
import styles from "./SingleTable.module.css";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faLocationPin, faCalendarCheck, faCircleInfo } from "@fortawesome/free-solid-svg-icons";

// 아이콘 매핑
const iconMap = {
    faTicket: faLocationPin,
    faCalendar: faCalendarCheck,
    faCircleInfo: faCircleInfo,
};

export default function SingleTable({ iconName, type, data }) {
    const navigate = useNavigate();

    return (
        <div className={styles.tableWrapper}>
            <table className={styles.table}>
                <tbody>
                <tr className={styles.tr}>
                    <td className={styles.td}>
                        <FontAwesomeIcon className={styles.ii} icon={iconMap[iconName]} />
                    </td>
                    <td className={styles.td}><p className={styles.grey}>{type}</p></td>
                    <td className={styles.td}>{data}</td>
                </tr>
                </tbody>
            </table>
        </div>
    );
}
