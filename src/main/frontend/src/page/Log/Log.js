import styles from "./Log.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import {useNavigate, useParams} from 'react-router-dom';
import SingleTable from "../../components/_Tool/SingleTable";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {faMarker, faTrashCan} from "@fortawesome/free-solid-svg-icons";

export default function Log() {
    const [watchingContent, setContent] = useState(null);
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
                setContent(data);  // 관람 기록 데이터를 상태에 저장
            })
            .catch((err) => {
                console.error("API fetch error:", err); // 에러 처리
            });
    }, []);

    return (
        <div className={styles.default}>
            <div className={styles.cropping}>
            </div>
            <div className={styles.inner}>
                {watchingContent ? (
                    <div>
                        <FontAwesomeIcon className={styles.ii} icon={faMarker}  onClick={() => handleLogEdit(id)}/>
                        <FontAwesomeIcon className={styles.ii} icon={faTrashCan} onClick={() => handleLogDelete(id)}/>
                        <p className={styles.emoji}>{watchingContent.title.slice(0, 2)}</p>
                        <p className={styles.title}>{watchingContent.title.slice(2)}</p>
                        <SingleTable
                            iconName={"faCalendarCheck"}
                            type={"날짜"}
                            data={(() => {
                                const gameDate = new Date(watchingContent.game.gameDate);
                                const year = gameDate.getFullYear();
                                const month = String(gameDate.getMonth() + 1).padStart(2, '0');  // getMonth() returns 0-11
                                const day = String(gameDate.getDate()).padStart(2, '0');
                                return `${year}년 ${month}월 ${day}일`;
                            })()}
                        />
                        <SingleTable
                            iconName={"faBaseball"}
                            type={"팀"}
                            data={`${watchingContent.game.homeTeam.teamName}vs${watchingContent.game.awayTeam.teamName}`}
                        />
                        <SingleTable
                            iconName={"faTicket"}
                            type={"위치"}
                            data={watchingContent.location}
                        />
                        <p className={styles.content}>{watchingContent.content}</p>
                    </div>
                ) : (
                    <p>Loading...</p>  // 로딩 중
                )}

            </div>
            <Footer/>
        </div>
    );
}
