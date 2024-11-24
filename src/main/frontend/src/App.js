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


function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/main" element={<Main />} />
                    <Route path="/signin" element={<Signin />}/>
                    <Route path="/signup" element={<Signup />}/>
                    <Route path="/log/:id" element={<Log />} />
                    <Route path="/write" element={<LogWrite/>} />
                    <Route path="/edit/:id" element={<LogEdit/>}/>/>
                    <Route path="/mypage" element={<Mypage/>} />
                    <Route path="/my/edit" element={<MypageEdit/>} />
                    <Route path="/teampage" element={<Teampage/>} />
                    <Route path="/playerpage" element={<Playerpage/>} />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;

