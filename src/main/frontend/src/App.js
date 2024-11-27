import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Main from './page/mainpage/Mainpage';
import Home from './page/Home/Home';
import Signin from './page/Signin/Signin';
import Signup from "./page/Signup/Signup";
import Log from "./page/Log/Log";
import LogWrite from "./page/Log/LogWrite";
import LogEdit from "./page/Log/LogEdit";
import Mypage from "./page/Mypage/Mypage"
import MypageEdit from "./page/Mypage/MypageEdit"
import Teampage from "./page/Teampage/Teampage"
import Playerpage from "./page/Player/Playerpage";
import PlayerDetail from "./page/Player/PlayerDetail";
import Players from "./page/Players/Players";

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/dashboard" element={<Main />} />
                    <Route path="/signin" element={<Signin />}/>
                    <Route path="/signup" element={<Signup />}/>
                    <Route path="/log/detail/:id" element={<Log />} />
                    <Route path="/log/create" element={<LogWrite/>} />
                    <Route path="/log/edit/:id" element={<LogEdit/>}/>/>
                    <Route path="/profile" element={<Mypage/>} />
                    <Route path="/profile/edit" element={<MypageEdit/>} />
                    <Route path="/team/fav" element={<Teampage/>} />                    <Route path="/players/fav" element={<Playerpage/>} />
                    <Route path="/players/" element={<Players/>} />
                    <Route path="/players/fav" element={<Playerpage/>} />
                    <Route path="/players/detail/:pId" element={<PlayerDetail />} />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;

