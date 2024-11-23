import styles from "./Log.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import { useParams } from 'react-router-dom';

export default function Log() {
    const [watchingContent, setContent] = useState(null);
    const { id } = useParams();

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
    console.log(watchingContent)

    return (
        <div className={styles.default}>
            <div className={styles.cropping}>
            </div>
            <div className={styles.inner}>
                {watchingContent ? (
                    <div>
                        <h1>{watchingContent.title}</h1>
                        <p>{watchingContent.content}</p> {/* content 출력 */}
                        <p>Location: {watchingContent.location}</p>
                        <p>Game: {watchingContent.game.homeTeam.teamName} vs {watchingContent.game.awayTeam.teamName}</p>
                    </div>
                ) : (
                    <p>Loading...</p>  // 데이터가 로딩 중일 때
                )}

            </div>
            <Footer/>
        </div>
    );
}
