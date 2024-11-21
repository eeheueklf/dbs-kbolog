import styles from "./Mainpage.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import HeaderImg from "../../image/nail.png";
import Table from "../../components/_Tool/Table"

export default function Mainpage() {
    const [data, setData] = useState([]);
    const columns = [
        { Header: "팀", accessor: "user_id" },
        { Header: "날짜", accessor: "title" },
        { Header: "상대", accessor: "content" },
    ];

    useEffect(() => {
        fetch("/api/watching")
            .then((response) => response.json())
            .then((data) => setData(data))
            .catch((err) => console.error("API fetch error:", err));
    }, []);
    return (
        <div className={styles.default}>
            <div className={styles.cropping}>
                <img className={styles.headerImg} src={HeaderImg} alt="Header" />
            </div>
            <div className={styles.inner}>
                <div className={styles.title}>2024 KBO</div>
                다가오는 경기
                <Table columns={columns} data={data} />
            </div>
            <Footer />
        </div>
    );
}
