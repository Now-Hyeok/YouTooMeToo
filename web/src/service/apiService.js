import axios from "axios";

const apiClient = axios.create({
    baseURL:'http://' + import.meta.env.VITE_API_IP + ':' + import.meta.env.VITE_API_PORT,
    headers:{
        'Content-Type' : 'application/json'
    },
    timeout:5000
})

export default apiClient;