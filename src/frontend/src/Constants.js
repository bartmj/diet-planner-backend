const prod = 'http://dietplannerrds-env.eba-s2nrkgap.eu-north-1.elasticbeanstalk.com/';

const dev = 'http://localhost:5000/';

export const API_URL = process.env.NODE_ENV === 'development' ? dev : prod;