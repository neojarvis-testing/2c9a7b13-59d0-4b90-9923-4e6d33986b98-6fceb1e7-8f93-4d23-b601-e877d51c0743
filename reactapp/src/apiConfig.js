import axios from 'axios';

const api = axios.create({
    baseURL: "https://8080-adfbdfbaadbebefafdcfbaafbfcebefdbedc.project.examly.io/api",
    headers: {
        "Content-Type": "application/json",
    },
});

const getToken = () => localStorage.getItem("authToken");

api.interceptors.request.use(
    (config) => {
        const token = getToken();
        if(token){
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => Promise.reject(error)
);

export default api;