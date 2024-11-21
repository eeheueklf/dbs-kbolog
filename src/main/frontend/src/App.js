import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Main from './page/mainpage/Mainpage';
import Home from './page/Home/Home';
import Signin from './page/Signin/Signin';
import Signup from "./page/Signup/Signup"

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/main" element={<Main />} />
                    <Route path="/signin" element={<Signin />}/>

                    <Route path="/signup" element={<Signup />}/>
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;

