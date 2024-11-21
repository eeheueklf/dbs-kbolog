import { useNavigate } from 'react-router-dom';
import styles from "./Home.module.css"

export default function Home() {
    const navigate = useNavigate();

    //환경 변수 설정 필요 - 설정 완료 .env 파일 참조
    const clientId = process.env.REACT_APP_clientId;
    const redirectUri = process.env.REACT_APP_redirectUri;

    // 로그인 버튼 클릭 핸들러
    const handleLogin = () => {
        navigate("/Signin");
    };

    // 회원가입 페이지로 이동하는 함수
    const navigateToSignup = () => {
        navigate("/Signup");
    };

    return (
        <div className={styles.loginPage}>
            <div>
                <div className={styles.title}>크보로그</div>
                <div className={styles.flex}>
                    <button className={styles.signin} onClick={handleLogin}>로그인</button>
                    <button className={styles.signin} onClick={navigateToSignup}>회원가입</button>
                </div>
            </div>
        </div>
    );
}