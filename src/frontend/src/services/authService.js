import axios from "axios";
import {API_URL} from '../Constants';


const register = (username, email, password) => {
    return axios.post(API_URL + "signup", {
        username,
        email,
        password,
    });
};

const login = (username, password) => {
    return axios
        .post(API_URL + "signin", {
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