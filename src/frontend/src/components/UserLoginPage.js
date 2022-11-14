import * as apiCalls from '../api/apiCalls';
import React, {useState} from "react";

const UserLoginPage = () => {
    const [form, setForm] = useState({
        username: '',
        password: ''
    })

    const handleChange = (e) => {
        const { value, name } = e.target;

        setForm((previousForm) => {
            return {
                ...previousForm,
                [name]: value
            };
        });
    }

    function onClickSignIn() {
        let user = {
            username: form.username,
            password: form.password
        }
        apiCalls.signIn(user).then((res) => console.log(res))
    }

    return (
        <div className="container">
            <h1 className="text-center">Log In</h1>
            <div className="col-12 mb-3">
            </div>
            <div className="col-12 mb-3">
                <label>username</label>
                <input
                    name="username"
                    onChange={handleChange}
                    value={form.username}
                />
            </div>
            <div className="col-12 mb-3">
                <label>password</label>
                <input
                    name="password"
                    type="password"
                    onChange={handleChange}
                    value={form.password}
                />
            </div>
            <div className="text-center">
                <button
                    className="add-button"
                    onClick={onClickSignIn}>
                    Log in
                </button>
            </div>
        </div>
    );
}

export default UserLoginPage;