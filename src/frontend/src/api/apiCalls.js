import axios from 'axios';
import authHeader from '../services/authHeader';
import {API_URL} from '../Constants';


export const remove = (val) => {
    axios.delete(API_URL + 'calc/all/' + val, { headers: authHeader()}).then(r => {});
}

export const saveFood = (stoObj) => {
    return axios.post(API_URL + `calc/add`, stoObj, { headers: authHeader() });
}

export const getAll = () => {
    return axios.get(API_URL + 'calc/all', { headers: authHeader() });
}

export const getFavourites = () => {
    return axios.get(API_URL + 'favourites/user', { headers: authHeader() });
}