import React from "react";
import styles from "./_PTable.module.css";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCrosshairs, faSitemap } from "@fortawesome/free-solid-svg-icons";
import { faHeart } from "@fortawesome/free-regular-svg-icons";
import { getTeamClass } from "./Table";
import { getPosition } from "./PlayerTable";

export default function _PTable({ pId, pName, pPosition, pTeam, pSponsor, avg, ops, war, era, ip, whip }) {
    const navigate = useNavigate();

    // 테이블 행 클릭 시 실행되는 함수
    const handleRowClick = (pId) => {
        navigate(`/players/detail/${pId}`);
    };

    // 첫 번째 조건: 헤더가 필요할 때 (pId === 0일 경우)
    if (pId === 0) {
        return (
            <div className={styles.tableWrapper}>
                <table className={styles.table}>
                    <tbody>
                    <tr className={styles.tr}>
                        <td className={styles.tdNav}>
                            <FontAwesomeIcon className={styles.ii} icon={faSitemap} /> 소속팀
                        </td>
                        <td className={styles.tdNav}>
                            <FontAwesomeIcon className={styles.ii} icon={faHeart} /> 이름
                        </td>
                        <td className={styles.tdNav}>
                            <FontAwesomeIcon className={styles.ii} icon={faCrosshairs} /> 포지션
                        </td>
                        {pPosition === 1 ? (
                            <>
                                <td className={styles.tdNav}>이닝</td>
                                <td className={styles.tdNav}>평균자책점</td>
                                <td className={styles.tdNav}>WHIP</td>
                            </>
                        ) : (
                            <>
                                <td className={styles.tdNav}>타율</td>
                                <td className={styles.tdNav}>OPS</td>
                                <td className={styles.tdNav}>WAR</td>
                            </>
                        )}
                    </tr>
                    </tbody>
                </table>
            </div>
        );
    }

    // 두 번째 조건: 실제 데이터 행
    if (pPosition !== 1) { // 타자일 때
        return (
            <div className={styles.tableWrapper}>
                <table className={styles.table}>
                    <tbody>
                    <tr className={styles.tr} onClick={() => handleRowClick(pId)}>
                        <td className={styles.td}>
                                <span className={`${styles.highlight} ${getTeamClass(pSponsor)}`}>
                                    {pTeam}
                                </span>
                        </td>
                        <td className={styles.td}>{pName}</td>
                        <td className={styles.td}>{getPosition(pPosition)}</td>
                        <td className={styles.td}>{avg}</td>
                        <td className={styles.td}>{ops}</td>
                        <td className={styles.td}>{war}</td>
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
                <tr className={styles.tr} onClick={() => handleRowClick(pId)}>
                    <td className={styles.td}>
                            <span className={`${styles.highlight} ${getTeamClass(pSponsor)}`}>
                                {pTeam}
                            </span>
                    </td>
                    <td className={styles.td}>{pName}</td>
                    <td className={styles.td}>{getPosition(pPosition)}</td>
                    <td className={styles.td}>{ip}</td>
                    <td className={styles.td}>{era}</td>
                    <td className={styles.td}>{whip}</td>
                </tr>
                </tbody>
            </table>
        </div>
    );
}
