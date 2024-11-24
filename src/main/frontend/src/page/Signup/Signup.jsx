import React, {useState} from "react";
import styles from "./Signup.module.css"
import axios from "axios"

export default function Signup() {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSignUp = () => {
        const data = {
            username,
            password,
        };

        axios
            .post("/api/signup", data)
            .then((res) => {
                console.log("회원가입 성공", data, res);
            })
            .catch((err) => {
                console.log(`${err} :: 회원가입 실패`);
            });


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
                <button  className="text-wrapper-7, div-wrapper">중복확인</button>
                </div>
                <div className={styles.inputArea}>

                <input value={password}
                       onChange={(e)=>{setPassword(e.target.value)}} type="password"
                       placeholder="비밀번호를 입력해주세요." />
                </div>
                <button onClick={handleSignUp} className={styles.signin}>회원가입</button>

            </div>
        </div>
    );

};