import { useNavigate } from "react-router-dom";
import React, {useState} from "react";
import styles from "./Signin.module.css"
import axios from "axios";

export default function Signin() {
    const navigate = useNavigate();

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSignIn = async () => {
        const data = {
            username,
            password,
        };


        try {
            const response = await axios.post("/api/signin", data);

            // 로그인 성공
            console.log("로그인 성공:", response.data);
            navigate('/main'); //로그인 페이지로

        } catch (err) {
            if (err.response) {
                // 서버가 응답을 반환한 경우 (HTTP 상태 코드 포함)
                if (err.response.status === 404) {
                    console.error("에러: 사용자 정보를 찾을 수 없습니다.");
                } else if (err.response.status === 401) {
                    console.error("에러: 비밀번호가 틀렸습니다.");
                } else {
                    console.error(`에러: ${err.response.status} - ${err.response.data}`);
                }
            } else {
                // 서버에 요청이 도달하지 않았거나 네트워크 문제
                console.error("네트워크 오류 또는 서버 문제:", err.message);
            }
        }


    };



    return (
        <div className={styles.signupPage}>
            <div>
                <div className={styles.title}>크보로그</div>
                <div className={styles.inputArea}>
                    <input className={styles.inputId}
                           value={username}
                           onChange={(e)=>{setUsername(e.target.value)}}
                           type="text"
                           placeholder="ID를 입력해주세요." />
                </div>
                <div className={styles.inputArea}>

                    <input value={password}
                           onChange={(e)=>{setPassword(e.target.value)}} type="password"
                           placeholder="비밀번호를 입력해주세요." />
                </div>
                <button onClick={handleSignIn} className={styles.signin}>로그인</button>

            </div>
        </div>
    );

};