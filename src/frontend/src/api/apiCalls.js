import axios from 'axios';

// const port = 5000

const rdsUrl = 'dietplannerrds-env.eba-s2nrkgap.eu-north-1.elasticbeanstalk.com'

const basePath = `http://${rdsUrl}/foods`;

export const remove = (val) => {
    axios.delete(basePath + '/all/' + val)
}

export const saveFood = (stoObj) => {
    return axios.post(basePath + `/add`, stoObj);
}

export const getAll = () => {
    return axios.get(basePath + '/all');
}

