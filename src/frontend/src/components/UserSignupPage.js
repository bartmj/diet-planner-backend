import React, { useState } from "react";

const UserSignupPage = (props) => {
    const [form, setForm] = useState({
        username: '',
        password: '',
        passwordRepeat: ''
    })

    const onClickSignup = () => {
        const user = {
            username: form.username,
            password: form.password
        };

    };

    return (
        <div className="container">
            <h1 className="text-center">Sign Up</h1>
            <div className="col-12 mb-3">
            </div>
            <div className="col-12 mb-3">
                <label>username</label>
                <input
                    name="username"
                    placeholder="Your username"
                />
            </div>
            <div className="col-12 mb-3">
                <label>password</label>
                <input
                    name="password"
                    placeholder="Your password"
                    type="password"
                />
            </div>
            <div className="col-12 mb-3">
                <label>password Repeat</label>
                <input
                    name="passwordRepeat"
                    placeholder="Repeat your password"
                    type="password"
                />
            </div>
            <div className="text-center">
                <button
                    className="add-button"
                    onClick={onClickSignup}>
                    Add food
                </button>
            </div>
        </div>
    );
}

export default UserSignupPage;

