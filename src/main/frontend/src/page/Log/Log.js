import styles from "./Log.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import {useNavigate, useParams} from 'react-router-dom';
import SingleTable from "../../components/_Table/SingleTable";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPenToSquare,faCircleXmark} from "@fortawesome/free-regular-svg-icons";

export default function Log() {
    const [wLog, setWLog] = useState(null);
    const { id } = useParams();
    const navigate = useNavigate();
    const handleLogEdit = (id) => {
        navigate(`/Edit/${id}`);
    };

    const handleLogDelete = () => {
        /* eslint-disable no-restricted-globals */
        if(confirm("삭제하면 복구할 수 없습니다. 정말 삭제하시겠습니까?")===true){
        fetch(`/api/log/delete/${id}`, {
            method: "DELETE",
        })
            .then((res) => {
                if (res.ok) {
                    alert("로그가 삭제되었습니다.");
                    navigate("/main"); // 삭제 후 메인 페이지로 이동
                } else {
                    alert("삭제 실패: " + res.statusText);
                }
            })
            .catch((err) => {
                console.error("Error deleting log:", err);
            });
        }
        /* eslint-disable no-restricted-globals */
    };

    useEffect(() => {
        fetch(`/api/log/${id}`)
            .then(res=>res.json())
            .then((data) => {
                setWLog(data);
            })
            .catch((err) => {
                console.error("API fetch error:", err);
            });
    }, []);

    return (
        <div className={styles.default}>
            <div className={styles.cropping}>
            </div>
            <div className={styles.inner}>
                {wLog ? (
                    <div>
                        <FontAwesomeIcon className={styles.ii} icon={faPenToSquare}  onClick={() => handleLogEdit(id)}/>
                        <FontAwesomeIcon className={styles.ii} icon={faCircleXmark} onClick={() => handleLogDelete(id)}/>
                        <p className={styles.emoji}>{wLog.title.slice(0, 2)}</p>
                        <p className={styles.title}>{wLog.title.slice(2)}</p>
                        <SingleTable
                            iconName={"faCalendarCheck"}
                            type={"날짜"}
                            data={(() => {
                                const gameDate = new Date(wLog.watchingDate);
                                const year = gameDate.getFullYear();
                                const month = String(gameDate.getMonth() + 1).padStart(2, '0');  // getMonth() returns 0-11
                                const day = String(gameDate.getDate()).padStart(2, '0');
                                return `${year}년 ${month}월 ${day}일`;
                            })()}
                        />
                        <SingleTable
                            iconName={"faBaseball"}
                            type={"팀"}
                            data={`${wLog.homeTeam}vs${wLog.awayTeam}`}
                        />
                        <SingleTable
                            iconName={"faTicket"}
                            type={"위치"}
                            data={wLog.location}
                        />
                        <p className={styles.content}>{wLog.content}</p>
                    </div>
                ) : (
                    <p>Loading...</p>  // 로딩 중
                )}

            </div>
            <Footer/>
        </div>
    );
}
