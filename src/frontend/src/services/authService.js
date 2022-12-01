import axios from "axios";
import { API_URL } from '../Constants';

const url = API_URL + "api/auth/";

const register = (username, email, password) => {
    return axios.post(url + "signup", {
        username,
        email,
        password,
    });
};

const login = (username, password) => {
    return axios
        .post(url + "signin", {
            username,
            password,
        })
        .then((response) => {
            if (response.data.token) {
                localStorage.setItem("user", JSON.stringify(response.data));
            }

            return response.data;
        });
};

const logout = () => {
    localStorage.removeItem("user");
};

const authService = {
    register,
    login,
    logout,
};

export default authService;