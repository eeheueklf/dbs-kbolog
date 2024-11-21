import styles from "./Log.module.css";
import React, {useEffect, useState} from "react";
import Footer from "../../components/_Layout/Footer";
import { useParams } from 'react-router-dom';

export default function Log() {
    const [record, setRecord] = useState([]);


    let { id } = useParams();
    localStorage.setItem('logId', id);
    console.log("logId : "+ id)

    useEffect(() => {
        fetch(`/api/log/${id}`)
            .then(res=>res.json())
            .then(res=> {
                setRecord(res);
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
                <div className={styles.title}>2024 KBO</div>



            </div>
            <Footer />
        </div>
    );
}
