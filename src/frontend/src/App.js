// import './App.css';
// import UserPanel from './components/UserPanel';
// import UserSignupPage from './components/UserSignupPage';
// import UserLoginPage from './components/UserLoginPage';
// import {TopBar} from "./components/TopBar";
// import { BrowserRouter, Routes, Route } from "react-router-dom";
import React from 'react';

function App() {

  return (
    <div className="App">
        <BrowserRouter>
            <TopBar/>
            <Routes>
                <Route path="/" element={<UserPanel />} />
                <Route path="/signup" element={<UserSignupPage />} />
                <Route path="/signin" element={<UserLoginPage />} />
            </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
