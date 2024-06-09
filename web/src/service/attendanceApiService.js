import apiClient from "./apiService";


let prefixUrl = "/attendance";


const updateAttendance = async (data)=>{
    try {
        const response = await apiClient.post(prefixUrl+"/updateAttendance",data)
        return response;
    } catch (error) {
        return error;
    }

}

const getAttendanceList = async () =>{
    try {
        const response = await apiClient.get(prefixUrl)
        return response;
    } catch (error) {
        return error;
    }
}

export {updateAttendance,getAttendanceList}