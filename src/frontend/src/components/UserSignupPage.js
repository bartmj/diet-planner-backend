import React, { useState } from "react";
import * as apiCalls from '../api/apiCalls';

const UserSignupPage = (props) => {
    const [form, setForm] = useState({
        username: '',
        email: '',
        password: '',
        passwordRepeat: '',
        info: ''
    })

    const onClickSignup = () => {
        let user = {
            username: form.username,
            email: form.email,
            password: form.password,
            role: ['user'],
        };
        apiCalls.signup(user).then(response => {
            // console.log(response)
            setForm(() => {
                return {
                    username: '',
                    email: '',
                    password: '',
                    passwordRepeat: '',
                    info: response.data
                }
            })
        })
    }

    const handleChange = (e) => {
        const { value, name } = e.target;

        setForm((previousForm) => {
            return {
                ...previousForm,
                [name]: value
            };
        });
    }

    return (
        <div className="container">
            <h1 className="text-center">Sign Up</h1>
            <div className="col-12 mb-3">
            </div>
            <div className="col-12 mb-3">
                <label>username</label>
                <input
                    name="username"
                    // placeholder="Your username"
                    onChange={handleChange}
                    value={form.username}
                />
            </div>
            <div className="col-12 mb-3">
                <label>email</label>
                <input
                    name="email"
                    // placeholder="Your email"
                    onChange={handleChange}
                    value={form.email}
                />
            </div>
            <div className="col-12 mb-3">
                <label>password</label>
                <input
                    name="password"
                    // placeholder="Your password"
                    type="password"
                    onChange={handleChange}
                    value={form.password}
                />
            </div>
            <div className="col-12 mb-3">
                <label>repeat password</label>
                <input
                    name="passwordRepeat"
                    // placeholder="Repeat your password"
                    type="password"
                    onChange={handleChange}
                    value={form.passwordRepeat}
                />
            </div>
            <div className="text-center">
                <button
                    className="add-button"
                    onClick={onClickSignup}>
                    Sign up
                </button>
            </div>
            <p className="success">{form.info}</p>
        </div>
    );
}

export default UserSignupPage;

