import React from "react";
import styles from "./PlayerTable.module.css";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCrosshairs, faSitemap, faCaretUp } from "@fortawesome/free-solid-svg-icons";
import { faHeart, faSquarePlus, faUser } from "@fortawesome/free-regular-svg-icons";
import {getTeamClass} from "./Table"
import {getPosition} from "./PlayerTable"

export default function PlayerRank({ pId, pName, pPosition, pTeam, pSponsor, fanCount, index}) {
    const navigate = useNavigate();

    // 테이블 행 클릭 시 실행되는 함수
    const handleRowClick = (pId) => {
        navigate(`/players/detail/${pId}`);
    };

    const handleWriteLog = () => {
        navigate('/players');
    }
    if (pId === -1) {
        return (
            <div className={styles.tableWrapper}>
                <table className={styles.table}>
                    <tbody>
                    <tr className={styles.tr}
                        onClick={handleWriteLog}>
                        <td className={styles.tdPlus}>
                            <FontAwesomeIcon className={styles.ii} icon={faSquarePlus}/>
                            <span> 선수 탐색 하기</span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        );
    }
    if (pId === 0) {
        return (
            <div className={styles.tableWrapper}>
                <table className={styles.table}>
                    <tbody>
                    <tr className={styles.tr}>
                        <td className={styles.tdNav}>
                            <FontAwesomeIcon className={styles.ii} icon={faCaretUp}/> 순위
                        </td>
                        <td className={styles.tdNav}>
                            <FontAwesomeIcon className={styles.ii} icon={faHeart}/> 이름
                        </td>
                        <td className={styles.tdNav}>
                            <FontAwesomeIcon className={styles.ii} icon={faCrosshairs}/> 포지션
                        </td>
                        <td className={styles.tdNav}>
                            <FontAwesomeIcon className={styles.ii} icon={faSitemap}/> 소속팀
                        </td>
                        <td className={styles.tdNav}>
                            <FontAwesomeIcon className={styles.ii} icon={faUser}/> 팬 수
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
                    onClick={() => handleRowClick(pId)}>
                    <td className={styles.td}>{index + 1}</td>
                    <td className={styles.td}>{pName}</td>
                    <td className={styles.td}>{getPosition(pPosition)}</td>
                    <td className={styles.td}>
                            <span className={`${styles.highlight} ${getTeamClass(pSponsor)}`}>
                                {pTeam}
                            </span>
                    </td>
                    <td className={styles.td}>{fanCount}</td>
                </tr>
                </tbody>
            </table>
        </div>
    );
}
