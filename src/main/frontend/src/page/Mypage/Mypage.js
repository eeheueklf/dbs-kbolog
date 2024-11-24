import styles from "./Mypage.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import {useNavigate, useParams} from 'react-router-dom';
import SingleTable from "../../components/_Tool/SingleTable";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMarker } from "@fortawesome/free-solid-svg-icons";

export default function Mypage() {
    const [user, setUser] = useState([]);
    const [rootTeam, setRootTeam] = useState([]);

    useEffect(() => {
        fetch("/api/my")
            .then(res=>res.json())
            .then(res=> {
                setUser(res);
                setRootTeam(res.rootTeam.teamName)
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
                <div>
                    <FontAwesomeIcon className={styles.ii} icon={faMarker} />
                    <p className={styles.emoji}>💟</p>
                    <p className={styles.title}>마이페이지</p>
                    <SingleTable
                        iconName={"faUser"}
                        type={"아이디"}
                        data={user.username}
                    />
                    <SingleTable
                        iconName={"faBaseball"}
                        type={"응원 팀"}
                        data={rootTeam}
                    />
                    <SingleTable
                        iconName={"faCalendarCheck"}
                        type={"응원 날짜"}
                        data={user.rootdate}
                    />
                    <p className={styles.content}></p>
                </div>
            </div>
            <Footer/>
        </div>
    );
}
