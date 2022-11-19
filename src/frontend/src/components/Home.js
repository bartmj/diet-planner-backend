import React, { useState, useEffect } from "react";

import UserService from '../services/userService';

const Home = () => {
    const [content, setContent] = useState("");

    useEffect(() => {
        UserService.getPublicContent().then(
            (response) => {
                setContent(response.data)
            },
            (error) => {
                const _content = (error.response && error.response.data) ||
                    error.message ||
                    error.toString()

                setContent(_content);
            }
        );
    }, []);

    return (
        <div className="container">
            <header>
                <h3>{content}</h3>
                <p>This is a high performant Macro Calculator created for people who want to optimize their diet.</p>
            </header>
        </div>
    )
}

export default Home;