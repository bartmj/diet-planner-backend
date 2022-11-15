import React, { useState } from "react";
import * as apiCalls from '../api/apiCalls';

const UserSignupPage = (props) => {
    const [form, setForm] = useState({
        username: '',
        email: '',
        password: '',
        passwordRepeat: '',
    })
    const [errors, setErrors] = useState({});
    const [info, setInfo] = useState('');

    const onClickSignup = () => {
        if (form.password !== form.passwordRepeat) {
            setErrors(() => {
                return {passwordMatch: 'Password fields have different values!'};
            })
            return;
        }
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
                }
            })
            setInfo(() => {
                return response.data
            })
        }).catch(apiError => {
            if (apiError.response.data && apiError.response.data.validationErrors) {
                setErrors(apiError.response.data.validationErrors);
                console.log("Elo")
                console.log(apiError.response)
            }
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

    function handleFocus() {
        setErrors(() => {
            return '';
        })
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
                    onChange={handleChange}
                    value={form.username}
                    onFocus={handleFocus}
                />
                <p className="error">{errors.username}</p>
            </div>
            <div className="col-12 mb-3">
                <label>email</label>
                <input
                    name="email"
                    onChange={handleChange}
                    value={form.email}
                    onFocus={handleFocus}
                />
                <p className="error">{errors.email}</p>
            </div>
            <div className="col-12 mb-3">
                <label>password</label>
                <input
                    name="password"
                    type="password"
                    onChange={handleChange}
                    value={form.password}
                    onFocus={handleFocus}
                />
                <p className="error">{errors.password}</p>
            </div>
            <div className="col-12 mb-3">
                <label>repeat password</label>
                <input
                    name="passwordRepeat"
                    type="password"
                    onChange={handleChange}
                    value={form.passwordRepeat}
                    onFocus={handleFocus}
                />
            </div>
            <div className="text-center">
                <button
                    className="add-button"
                    onClick={onClickSignup}>
                    Sign up
                </button>
            </div>
            <p className="success">{info}</p>
            <p className="error">{errors.passwordMatch}</p>
        </div>
    );
}

export default UserSignupPage;

