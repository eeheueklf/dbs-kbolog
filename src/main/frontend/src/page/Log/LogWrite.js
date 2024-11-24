import React, { useState } from "react";
import styles from "./Log.module.css";
import { useNavigate } from "react-router-dom";


export default function LogWrite() {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        title: "",
        content: "",
        gameId: "",  // gameId를 저장하는 상태
        location: "",
    });

    const [games, setGames] = useState([]); // 날짜에 따른 경기 목록 저장

    // 날짜 입력 핸들러
    const handleDateChange = (e) => {
        const selectedDate = e.target.value;
        setFormData((prevData) => ({ ...prevData, date: selectedDate }));

        // 서버에서 해당 날짜의 경기 목록을 가져옴
        fetch(`/api/game?date=${selectedDate}`)
            .then((res) => res.json())
            .then((data) => {
                setGames(data); // 경기 목록을 상태에 저장
                console.log('game', data)
            })
            .catch((err) => {
                console.error("Failed to fetch games:", err);
                setGames([]); // 에러 시 빈 목록
            });
    };

    // 기타 입력 핸들러
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    // 게임 선택 핸들러
    const handleGameSelect = (e) => {
        const selectedGameId = e.target.value;
        console.log(e.target.value)
        setFormData((prevData) => ({
            ...prevData,
            gameId: selectedGameId,  // 게임 ID를 상태에 저장
        }));
    };

    // 폼 제출 핸들러
    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("formData:", formData); // formData를 확인

        // 선택된 경기 정보를 서버로 전송
        fetch("/api/log/write", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: formData.title,
                content: formData.content,
                gameId: formData.gameId,  // gameId를 함께 전송
                location: formData.location,
            }),
        })
            .then((res) => {
                if (res.ok) {
                    alert("작성 완료");
                    navigate('/main');
                } else {
                    throw new Error("Failed to save data");
                }
            })
            .catch((err) => {
                console.error("API save error:", err);
            });
    };

    return (
        <div className={styles.default}>
            <div className={styles.inner}>
                <h1 className={styles.title}>관람 기록 작성</h1>
                <form onSubmit={handleSubmit}>
                    <div className={styles.formGroup}>
                        <label>제목</label>
                        <input
                            type="text"
                            name="title"
                            value={formData.title}
                            onChange={handleInputChange}
                            required
                            className={styles.input}
                        />
                    </div>
                    <div className={styles.formGroup}>
                        <label>날짜</label>
                        <input
                            type="date"
                            name="date"
                            value={formData.date}
                            onChange={handleDateChange}
                            required
                            className={styles.input}
                        />
                    </div>
                    <div className={styles.formGroup}>
                        <label>경기</label>
                        {games.length > 0 ? (
                            <select
                                name="gameId"
                                value={formData.gameId}
                                onChange={handleGameSelect}
                                required
                                className={styles.input}
                            >
                                <option value="" disabled>
                                    경기 선택
                                </option>
                                {games.map((game) => (
                                    <option key={game.gameId} value={game.gameId}>
                                        {`${game.homeTeam.teamName} vs ${game.awayTeam.teamName}`}
                                    </option>

                                ))}
                            </select>
                        ) : (
                            <p>해당 날짜의 경기가 없습니다.</p>
                        )}
                    </div>
                    <div className={styles.formGroup}>
                        <label>위치</label>
                        <input
                            type="text"
                            name="location"
                            value={formData.location}
                            onChange={handleInputChange}
                            required
                            className={styles.input}
                        />
                    </div>
                    <div className={styles.formGroup}>
                        <label>내용</label>
                        <textarea
                            name="content"
                            value={formData.content}
                            onChange={handleInputChange}
                            rows="5"
                            required
                            className={styles.textarea}
                        ></textarea>
                    </div>
                    <button type="submit" className={styles.submitButton}>
                        작성하기
                    </button>
                </form>
            </div>
        </div>
    );
}
