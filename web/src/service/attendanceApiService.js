import apiClient from "./apiService";


let prefixUrl = "/attendance";

export default{
    async attendance(){
        let result = undefined;
        await apiClient.post(prefixUrl,data)
        .then(response => {
            result = response;
        }).catch(error => {
            result = error;
        });
        return result;
    }
}