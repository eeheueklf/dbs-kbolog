import styles from "./Mypage.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import SingleTable from "../../components/_Table/SingleTable";

import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import { faPenToSquare} from "@fortawesome/free-regular-svg-icons";
import {useNavigate} from "react-router-dom";

export default function Mypage() {
    const [user, setUser] = useState([]);
    const [rootTeam, setRootTeam] = useState([]);
    const navigate = useNavigate();
    const handleLogEdit = () => {
        navigate(`/profile/edit`);
    };
    useEffect(() => {
        fetch("/api/my")
            .then(res => res.json())
            .then(res => {
                setUser(res);
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
                    <FontAwesomeIcon className={styles.ii} icon={faPenToSquare} onClick={handleLogEdit}/>
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
                    data={user.rootTeamName}
                    />
                    <SingleTable
                        iconName={"faCalendarCheck"}
                        type={"응원 날짜"}
                        data={user.rootDate}
                    />
                    <p className={styles.content}></p>
                </div>
            </div>
            <Footer/>
        </div>
    );
}

