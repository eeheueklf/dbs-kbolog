import React, {useState} from "react";
import styles from "./Signup.module.css"
import axios from "axios"
import {useNavigate} from "react-router-dom";

export default function Signup() {

    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [checkDup, setCheckDup] = useState(false);

    const handleSignUp = () => {
        if (password.length < 8 || password.length > 30) {
            alert("비밀번호는 8자 이상, 30자 이하로 입력해주세요.");
            return;
        }
        const data = { username, password };
        axios.post('/api/signup', data)
            .then(response => {
                alert("회원가입 성공!");
                navigate('/');
            })
            .catch(error => {
                alert("회원가입에 실패하였습니다");
            });
    };

    const checkDupUser = () => {
        if (username.length < 5 || username.length > 20) {
            alert("아이디는 5자 이상, 20자 이하로 입력해주세요.");
            return;
        }
        axios
            .post("/api/checkDupUser", { username }) // 서버에서 JSON으로 처리하도록 수정
            .then((res) => {
                if (res.data.isAvailable) {
                    setCheckDup(true);
                    alert("사용 가능한 아이디입니다!");
                } else {
                    setCheckDup(false);
                    alert("이미 사용 중인 아이디입니다.");
                }
            })
            .catch((err) => {
                setCheckDup(false);
                console.log(`${err} :: 중복 체크 실패`);
            });
    };

    return (
        <div className={styles.signupPage}>
            <div className={styles.signupContainer}>
                <div className={styles.title}>크보로그</div>
                <div className={styles.inputGroup}>
                    <input
                        className={styles.inputField}
                        value={username}
                        onChange={(e) => {
                            setUsername(e.target.value);
                            setCheckDup(false); // ID 변경 시 다시 중복 체크 필요
                        }}
                        type="text"
                        placeholder="ID"
                    />
                    <button
                        onClick={checkDupUser}
                        className={styles.duplicateCheckButton}
                    >
                        중복확인
                    </button>
                </div>
                <div className={styles.inputGroup}>
                    <input
                        className={styles.inputField}
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        type="password"
                        placeholder="비밀번호"
                    />
                </div>
                <button
                    onClick={handleSignUp}
                    className={styles.signupButton}
                    disabled={!checkDup || !password} // 조건에 따라 버튼 활성화
                >
                    회원가입
                </button>
            </div>
        </div>
    );


};