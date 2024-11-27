import styles from "./Mypage.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";

import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faMarker} from "@fortawesome/free-solid-svg-icons";
import {useNavigate} from "react-router-dom";

export default function Mypage() {
    const [teams, setTeams] = useState([]);
    const [sponsor, setSponsor] = useState("");
    const navigate = useNavigate();
    // 응원 팀 선택
    useEffect(() => {
        fetch("/api/my/team") // 팀 목록을 가져오는 API 엔드포인트
            .then((res) => res.json())
            .then((data) => {
                setTeams(data); // 가져온 데이터를 상태에 저장
            })
            .catch((err) => {
                console.error("Failed to fetch teams:", err);
            });
    }, []);

    // 팀 선택 핸들러
    const handleTeamSelect = (e) => {
        const sponsor = e.target.value;
        setSponsor(sponsor);
    };

    // 폼 제출 핸들러
    const handleSubmit = (e) => {
        e.preventDefault();

        // 선택한 팀 데이터를 단순히 문자열로 서버에 전송
        fetch(`/api/my/selectTeam`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: sponsor,
        })
        .then((res) => {
            alert("팀 선택 완료!");
            navigate('/profile');
        })
    };


    return (
        <div className={styles.default}>
            <div className={styles.cropping}>
            </div>
            <div className={styles.inner}>
                <div>
                    <p className={styles.emoji}>💟</p>
                    <p className={styles.title}>마이페이지</p>
                    <form onSubmit={handleSubmit}>
                        <div className={styles.formGroup}>
                            {teams.length > 0 ? (
                                <select
                                    name="sponsor"
                                    value={sponsor}
                                    onChange={handleTeamSelect}
                                    required
                                    className={styles.input}
                                >
                                    <option value="" disabled>
                                        응원할 팀을 선택하세요
                                    </option>
                                    {teams.map((team) => (
                                        <option key={team.sponsor} value={team.sponsor}>
                                            {team.teamName}
                                        </option>
                                    ))}
                                </select>
                            ) : (
                                <p>팀 목록을 불러오는 중입니다...</p>
                            )}
                        </div>
                        <button type="submit" className={styles.submitButton}>
                            팀 선택
                        </button>
                    </form>

                    <p className={styles.content}></p>
                </div>
            </div>
            <Footer/>
        </div>
    );
}