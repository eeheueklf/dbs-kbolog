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
                    <Route path="/my" element={<Mypage/>} />
                    <Route path="/my/edit" element={<MypageEdit/>} />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;

