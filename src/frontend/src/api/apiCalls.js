import axios from 'axios';

const basePath = 'http://localhost:8080/foods';

export const saveFood = (stoObj) => {
    return axios.post(basePath + `/add`, stoObj);
}

export const getAll = () => {
    return axios.get(basePath + '/all');
}

