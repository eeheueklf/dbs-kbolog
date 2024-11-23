import React, { useState, useEffect } from "react";
import styles from "./Log.module.css";
import { useNavigate, useParams } from "react-router-dom";  // useParams 추가

export default function LogEdit() {
    const navigate = useNavigate();
    const { id } = useParams();

    const [formData, setFormData] = useState({
        title: "",
        content: "",
        gameId: "",
        location: "",
    });

    const [games, setGames] = useState([]); // 날짜에 따른 경기 목록 저장

    // 수정할 데이터 불러오기
    useEffect(() => {
        fetch(`/api/log/${id}`)
            .then((res) => res.json())
            .then((data) => {
                setFormData(data);  // 가져온 데이터를 상태에 저장
            })
            .catch((err) => {
                console.error("Failed to fetch log data:", err);
            });
    }, [id]);  // logId가 변경될 때마다 데이터 가져오기

    // 날짜 입력 핸들러
    const handleDateChange = (e) => {
        const selectedDate = e.target.value;
        setFormData((prevData) => ({ ...prevData, date: selectedDate }));

        // 서버에서 해당 날짜의 경기 목록을 가져옴
        fetch(`/api/game?date=${selectedDate}`)
            .then((res) => res.json())
            .then((data) => {
                setGames(data);
                console.log('game', data);
            })
            .catch((err) => {
                console.error("Failed to fetch games:", err);
                setGames([]);
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
        console.log(e.target.value);
        setFormData((prevData) => ({
            ...prevData,
            gameId: selectedGameId,  // 게임 ID를 상태에 저장
        }));
    };

    // 폼 제출 핸들러
    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("formData:", formData);

        // 수정된 데이터를 서버로 전송
        fetch(`/api/log/edit/${id}`, {  // 수정 API 엔드포인트
            method: "PUT",  // PUT 메소드 사용
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: formData.title,
                content: formData.content,
                gameId: formData.gameId,
                location: formData.location,
            }),
        })
            .then((res) => {
                if (res.ok) {
                    alert("수정 완료");
                    navigate('/main');
                } else {
                    throw new Error("Failed to update data");
                }
            })
            .catch((err) => {
                console.error("API save error:", err);
            });
    };

    return (
        <div className={styles.default}>
            <div className={styles.inner}>
                <h1 className={styles.title}>관람 기록 수정</h1>
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
                        수정하기
                    </button>
                </form>
            </div>
        </div>
    );
}
