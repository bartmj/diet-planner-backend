import axios from 'axios';
import authHeader from '../services/authHeader';

const port = 5000
// const rdsUrl = 'dietplannerrds-env.eba-s2nrkgap.eu-north-1.elasticbeanstalk.com'

const basePath = `http://localhost:5000/calc`;

export const remove = (val) => {
    axios.delete(basePath + '/all/' + val).then(r => {});
}

export const saveFood = (stoObj) => {
    return axios.post(basePath + `/add`, stoObj, { headers: authHeader()});
}

export const getAll = () => {
    return axios.get(basePath + '/all', { headers: authHeader()});
}