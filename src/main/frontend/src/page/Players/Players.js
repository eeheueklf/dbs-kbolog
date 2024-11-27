import styles from "./Players.module.css";
import React, { useEffect, useState } from "react";
import Footer from "../../components/_Layout/Footer";
import HeaderImg from "../../image/playerHeader.jpg";
import PlayerTable from "../../components/_Table/_PTable";

export default function Players() {
    const [players, setPlayers] = useState([]);
    const [view, setView] = useState("pitcher");

    useEffect(() => {
        fetch(`/api/player`)
            .then(res => res.json())
            .then((data) => {
                setPlayers(data);
            })
            .catch((err) => {
                console.error("API fetch error:", err);
            });
    }, []);

    const filterPlayers = (position) => {
        if (position === "pitcher") {
            return players.filter(player => player.playerPosition === 1); // 투수만 필터
        } else {
            return players.filter(player => player.playerPosition !== 1); // 타자만 필터
        }
    };
    const [toolbarStatus, setToolbarStatus] = useState(false);  // 초기 상태는 OFF

    const toggleToobarStatus = () => {
        setToolbarStatus(!toolbarStatus);
        if(view==="hitter") setView("pitcher")
        else setView("hitter")
    };
    return (
        <div className={styles.default}>
            <div className={styles.cropping}>
                <img className={styles.headerImg} src={HeaderImg} alt="Header" />
            </div>
            <div className={styles.inner}>
                <div className={styles.title}>선수 목록</div>
                <br/>
                <div className={styles.checkboxSwitch}>
                    <input
                        type="checkbox"
                        checked={toolbarStatus}  // 상태에 맞게 체크박스 상태 결정
                        onChange={toggleToobarStatus}  // 체크박스를 클릭할 때 상태 변경
                        value="1"
                        name="status"
                        className={styles.inputCheckbox}
                        id={styles.toolbarActive}
                    />
                    <div className={styles.checkboxAnimate}>
                        <span className={styles.checkboxOff}>투수</span>
                        <span className={styles.checkboxOn}>타자</span>
                    </div>
                </div>

                <br/>

                {/* 헤더는 pPosition에 따라 동적으로 렌더링되므로 */}
                <PlayerTable pId={0} pPosition={view === "pitcher" ? 1 : 0}/>
                {filterPlayers(view).map(data => {
                    if (view === "hitter") {
                        return (
                            <PlayerTable
                                key={data.playerId}
                                pId={data.playerId}
                                pName={data.playerName}
                                pPosition={data.playerPosition}
                                pTeam={data.teamName}
                                pSponsor={data.teamSponsor}
                                avg={data.avg}
                                ops={data.ops}
                                war={data.war}
                            />
                        );
                    } else {
                        return (
                            <PlayerTable
                                key={data.playerId}
                                pId={data.playerId}
                                pName={data.playerName}
                                pPosition={data.playerPosition}
                                pTeam={data.teamName}
                                pSponsor={data.teamSponsor}
                                era={data.era}
                                ip={data.ip}
                                whip={data.whip}
                            />
                        );
                    }
                })}

            </div>
            <Footer/>
        </div>
    );
}
