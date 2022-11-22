import axios from 'axios';
import authHeader from '../services/authHeader';

const port = 5000
// const rdsUrl = 'dietplannerrds-env.eba-s2nrkgap.eu-north-1.elasticbeanstalk.com'

const basePath = `http://localhost:5000/`;

export const remove = (val) => {
    axios.delete(basePath + 'calc/all/' + val, { headers: authHeader()}).then(r => {});
}

export const saveFood = (stoObj) => {
    return axios.post(basePath + `calc/add`, stoObj, { headers: authHeader() });
}

export const getAll = () => {
    return axios.get(basePath + 'calc/all', { headers: authHeader() });
}

export const getFavourites = () => {
    return axios.get(basePath + 'favourites/user', { headers: authHeader() });
}