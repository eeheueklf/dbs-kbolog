import styles from "./Mypage.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import SingleTable from "../../components/_Table/SingleTable";

import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import { faArrowRightFromBracket, faUserXmark} from "@fortawesome/free-solid-svg-icons";
import {faPenToSquare} from "@fortawesome/free-regular-svg-icons";
import {useNavigate} from "react-router-dom";
import axios from "axios";

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

    const handleLogout = async () => {
        try {
            await axios.post("/api/signout", {}, { withCredentials: true });
            sessionStorage.removeItem("username"); // 세션에서 사용자 정보 제거
            alert("로그아웃 되었습니다.")
            navigate(`/`);
        } catch (error) {
            console.error("로그아웃 실패:", error);
        }
    }

    const handleDeleteAccount = async () => {
        const confirmDelete = window.confirm("정말로 탈퇴하시겠습니까? 이 작업은 되돌릴 수 없습니다.");
        if (!confirmDelete) return;

        try {
            const response = await axios.delete("/api/resign", { withCredentials: true });
            console.log("탈퇴 성공:", response.data.message);

            sessionStorage.removeItem("username");
            navigate(`/`);
        } catch (error) {
            console.error("탈퇴 실패:", error);
            alert("탈퇴 과정에서 문제가 발생했습니다.");
        }
    };

    return (
        <div className={styles.default}>
            <div className={styles.cropping}>
            </div>
            <div className={styles.inner}>
                <div>
                    <FontAwesomeIcon className={styles.ii} icon={faArrowRightFromBracket} onClick={handleLogout}/>
                    <FontAwesomeIcon className={styles.ii} icon={faUserXmark} onClick={handleDeleteAccount}/>
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
                    <div className={styles.caption} onClick={handleLogEdit}>
                        <FontAwesomeIcon className={styles.capii} icon={faPenToSquare} />
                        <span >팀 변경</span>
                    </div>
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

