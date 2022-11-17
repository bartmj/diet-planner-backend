// import './App.css';
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import React, { useState, useEffect, useCallback } from 'react';
import {useDispatch, useSelector} from "react-redux";
import { logout } from "./slices/auth";
import EventBus from "./common/EventBus";
import Home from './components/Home';
import Login from './components/Login';
import Register from './components/Register';
import BoardAdmin from "./components/BoardAdmin";
import BoardModerator from "./components/BoardModerator";
import BoardUser from "./components/BoardUser";
import Profile from "./components/Profile";

const App = () => {
    const [showModeratorBoard, setModeratorBoard] = useState(false);
    const [showAdminBoard, setShowAdminBoard] = useState(false);

    const {user: currentUser} = useSelector((state) => state.auth)
    const dispatch = useDispatch();

    const logOut = useCallback(() => {
        dispatch(logout());
    }, [dispatch]);

    useEffect(() => {
        if (currentUser) {
            setModeratorBoard(currentUser.roles.includes("ROLE_MODERATOR"));
            setShowAdminBoard(currentUser.roles.includes("ROLE_ADMIN"));
        } else {
            setShowAdminBoard(false);
            setModeratorBoard(false);
        }

        EventBus.on("logout", () => {
            logOut();
        });

        return () => {
            EventBus.remove("logout");
        };
    }, [currentUser, logOut]);


  return (
          <BrowserRouter>
              <div>
                  <nav>
                      <Link to={"/"}>
                          what
                      </Link>
                      <div>
                          <li>
                              <Link to={"/home"}>
                                  Home
                              </Link>
                          </li>

                          {showModeratorBoard && (
                              <li>
                                  <Link to={"/mod"}>
                                      Moderator Board
                                  </Link>
                              </li>
                          )}

                          {showAdminBoard && (
                              <li>
                                  <Link to={"/admin"}>
                                      Admin Board
                                  </Link>
                              </li>
                          )}

                          {currentUser ? (
                              <div>
                                  <li>
                                      <Link to={"/profile"}>
                                          {currentUser.username}
                                      </Link>
                                  </li>
                                  <li>
                                      <a href="/login" onClick={logOut}>
                                          LogOut
                                      </a>
                                  </li>
                              </div>
                          ) : (
                              <div>
                                  <li>
                                      <Link to={"/login"}>
                                          Login
                                      </Link>
                                  </li>
                                  <li>
                                      <Link to={"register"}>
                                          Sign Up
                                      </Link>
                                  </li>
                              </div>
                          )}
                      </div>
                  </nav>
                  <div>
                      <Routes>
                          <Route path="/" element={<Home/>} />
                          <Route path="/home" element={<Home/>} />
                          <Route path="/login" element={<Login/>} />
                          <Route path="/register" element={<Register/>} />
                          <Route path="/profile" element={<Profile/>} />
                          <Route path="/user" element={<BoardUser/>} />
                          <Route path="/mod" element={<BoardModerator/>} />
                          <Route path="/admin" element={<BoardAdmin/>} />
                      </Routes>
                  </div>
              </div>
          </BrowserRouter>

      // <div className="App">
    //     <BrowserRouter>
    //         <Routes>
    //             <Route path="/" element={<UserPanel />} />
    //             <Route path="/signup" element={<UserSignupPage />} />
    //             <Route path="/signin" element={<UserLoginPage />} />
    //         </Routes>
    //     </BrowserRouter>
    // </div>
  );
}

export default App;
