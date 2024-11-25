import React from "react";
import styles from "./Table.module.css";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {faMagnifyingGlass, faPlus,faFontAwesomeFlag} from "@fortawesome/free-solid-svg-icons";
import {faCompass,faFontAwesome, faCalendar} from "@fortawesome/free-regular-svg-icons";

export function getTeamClass(teamName) {
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

    return styles.defaultTeam;
}
export default function Table({ id, title, date, location, homeTeam, awayTeam, homeSponsor, awaySponsor }) {
    const navigate = useNavigate();

    // 테이블 행 클릭 시 실행되는 함수
    const handleRowClick = (id) => {
        navigate(`/log/${id}`);
    };

    const handleWriteLog = () => {
        navigate('/write');
    }
    if (id === 0) {
        return (
            <div className={styles.tableWrapper}>
                <table className={styles.table}>
                    <tbody>
                    <tr className={styles.tr}>
                        <td className={styles.tdNav}>
                            <FontAwesomeIcon className={styles.ii} icon={faMagnifyingGlass}/> 제목
                        </td>
                        <td className={styles.tdNav}>
                            <FontAwesomeIcon className={styles.ii} icon={faCalendar}/> 날짜
                        </td>
                        <td className={styles.tdNav}>
                            <FontAwesomeIcon className={styles.ii} icon={faCompass}/> 위치
                        </td>
                        <td className={styles.tdNav}>
                            <FontAwesomeIcon className={styles.ii} icon={faFontAwesome}/> 홈팀
                        </td>
                        <td className={styles.tdNav}>
                            <FontAwesomeIcon className={styles.ii} icon={faFontAwesomeFlag}/> 원정팀
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        );
    }
    if (id === -1) {
        return (
            <div className={styles.tableWrapper}>
                <table className={styles.table}>
                    <tbody>
                    <tr className={styles.tr}
                        onClick={handleWriteLog}>
                        <td className={styles.tdPlus}>
                            <FontAwesomeIcon className={styles.ii} icon={faPlus} />
                            ㅤ새 기록
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        );
    }

    return (
        <div className={styles.tableWrapper}>
            <table className={styles.table}>
                <tbody>
                <tr className={styles.tr}
                    onClick={() => handleRowClick(id)}>
                    <td className={styles.td}>{title}</td>
                    <td className={styles.td}>{date}</td>
                    <td className={styles.td}>{location}</td>
                    <td className={styles.td}>
                            <span className={`${styles.highlight} ${getTeamClass(homeSponsor)}`}>
                                {homeTeam}
                            </span>
                    </td>
                    <td className={styles.td}>
                            <span className={`${styles.highlight} ${getTeamClass(awaySponsor)}`}>
                                {awayTeam}
                            </span>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    );
}