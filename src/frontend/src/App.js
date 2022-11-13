import './App.css';
import UserPanel from './components/UserPanel';
import UserSignupPage from './components/UserSignupPage';
import UserLoginPage from './components/UserLoginPage';
import {TopBar} from "./components/TopBar";
import { BrowserRouter, Routes, Route } from "react-router-dom";


function App() {

  return (
    <div className="App">
      {/*  <title>Diet Planner - Project</title>*/}
      {/*  <TopBar/>*/}
      {/*<header className="App-header">*/}
      {/*  <div className="container">*/}
      {/*    <h1>Diet Planner</h1>*/}
      {/*  </div>*/}
      {/*</header>*/}
      {/*<section className="container">*/}
      {/*  <UserPanel />*/}
      {/*</section>*/}
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
