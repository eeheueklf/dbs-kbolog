import React from "react";
import styles from "./PlayerTable.module.css";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faListOl, faCrosshairs, faSitemap } from "@fortawesome/free-solid-svg-icons";
import { faHeart, faSquarePlus, faCalendar } from "@fortawesome/free-regular-svg-icons";
import {getTeamClass} from "./Table"

function getPosition(pPosition) {
    if (pPosition === 1) return "투수";
    if (pPosition === 2) return "포수";
    if (pPosition === 3) return "1루수";
    if (pPosition === 4) return "2루수";
    if (pPosition === 5) return "3루수";
    if (pPosition === 6) return "유격수";
    if (pPosition === 7) return "좌익수";
    if (pPosition === 8) return "중견수";
    if (pPosition === 9) return "우익수";
    if (pPosition === 10) return "지명타자";

    return "";
}
export default function PlayerTable({ pId, pName, pNum, pPosition, pTeam, pSponsor, cheerDate}) {
    const navigate = useNavigate();

    // 테이블 행 클릭 시 실행되는 함수
    const handleRowClick = (pId) => {
        navigate(`/about/player/${pId}`);
    };

    const handleWriteLog = () => {
        navigate('/write');
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
                            <span> 관심 선수 추가</span>
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
                            <FontAwesomeIcon className={styles.ii} icon={faHeart}/> 이름
                        </td>
                        <td className={styles.tdNav}>
                            <FontAwesomeIcon className={styles.ii} icon={faListOl}/> 등번호
                        </td>
                        <td className={styles.tdNav}>
                            <FontAwesomeIcon className={styles.ii} icon={faCrosshairs}/> 포지션
                        </td>
                        <td className={styles.tdNav}>
                            <FontAwesomeIcon className={styles.ii} icon={faSitemap}/> 소속팀
                        </td>
                        <td className={styles.tdNav}>
                            <FontAwesomeIcon className={styles.ii} icon={faCalendar}/> 관심일
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
                    <td className={styles.td}>{pName}</td>
                    <td className={styles.td}>{pNum}</td>
                    <td className={styles.td}>{getPosition(pPosition)}</td>
                    <td className={styles.td}>
                            <span className={`${styles.highlight} ${getTeamClass(pSponsor)}`}>
                                {pTeam}
                            </span>
                    </td>
                    <td className={styles.td}>{cheerDate}</td>
                </tr>
                </tbody>
            </table>
        </div>
    );
}
