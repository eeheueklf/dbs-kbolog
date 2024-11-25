import styles from "./PlayerDetail.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import {useNavigate, useParams} from 'react-router-dom';
import SingleTable from "../../components/_Table/SingleTable";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPenToSquare,faCircleXmark} from "@fortawesome/free-regular-svg-icons";

export default function PlayerDetail() {
    const [wLog, setWLog] = useState(null);
    const { pId } = useParams();

    useEffect(() => {
        fetch(`/api/player/${pId}`)
            .then(res=>res.json())
            .then((data) => {
                setWLog(data);
                console.log(data)
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
                        {/* <p className={styles.emoji}>{wLog.title.slice(0, 2)}</p>*/}
                        {/*<p className={styles.title}>{wLog.title.slice(2)}</p>*/}

                        {/*<SingleTable*/}
                        {/*    iconName={"faBaseball"}*/}
                        {/*    type={"팀"}*/}
                        {/*    data={`${wLog.homeTeam}vs${wLog.awayTeam}`}*/}
                        {/*/>*/}
                        {/*<SingleTable*/}
                        {/*    iconName={"faTicket"}*/}
                        {/*    type={"위치"}*/}
                        {/*    data={wLog.location}*/}
                        {/*/>*/}
                        {/*<p className={styles.content}>{wLog.content}</p>*/}
                    </div>
                ) : (
                    <p>Loading...</p>  // 로딩 중
                )}

            </div>
            <Footer/>
        </div>
    );
}
