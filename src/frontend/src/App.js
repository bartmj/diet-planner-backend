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
import UserPanel from "./components/UserPanel";

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
                          Logo
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

                          {currentUser && (
                              <li>
                                  <Link to={"/user"}>
                                      User
                                  </Link>
                              </li>
                          )}

                          {currentUser && (
                              <li>
                                  <Link to={"/calc"}>
                                      Macro Calc
                                  </Link>
                              </li>
                          )}

                          {currentUser ? (
                              <div>
                                  <li>
                                      <Link to={"/profile"}>
                                          Profile
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
                          <Route path="/calc" element={<UserPanel/>} />
                          <Route path="/mod" element={<BoardModerator/>} />
                          <Route path="/admin" element={<BoardAdmin/>} />
                      </Routes>
                  </div>
              </div>
          </BrowserRouter>
  );
}

export default App;
