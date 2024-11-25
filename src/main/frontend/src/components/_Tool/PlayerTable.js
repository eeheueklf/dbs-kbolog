import React from "react";
import styles from "./Table.module.css";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus } from "@fortawesome/free-solid-svg-icons";

export function getPosition(pPosition) {
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
export default function PlayerTable({ pId, pName, pNum, pPosition}) {
    const navigate = useNavigate();

    // 테이블 행 클릭 시 실행되는 함수
    const handleRowClick = (pId) => {
        navigate(`/log/${pId}`);
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
                            <FontAwesomeIcon className={styles.ii} icon={faPlus} />
                            ㅤ관심 선수 추가
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
                </tr>
                </tbody>
            </table>
        </div>
    );
}
