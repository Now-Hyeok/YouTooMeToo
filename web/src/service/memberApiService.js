import apiClient from "./apiService";

const prefixUrl = "/members";

const getMemberList = async () => {
    try {
        const response = await apiClient.get(prefixUrl);
        return response;
    } catch (error) {
        return error;
    }
};

const registerMember = async (data) => {
    try {
        const response = await apiClient.post(prefixUrl, data);
        return response;
    } catch (error) {
        return error;
    }
};

const getMember = async (memberId) => {
    try {
        const response = await apiClient.get(`${prefixUrl}/${memberId}`);
        return response;
    } catch (error) {
        return error;
    }
};

const deleteMember = async (data) => {
    try {
        const response = await apiClient.post(prefixUrl+"/delete",data);
        return response;
    } catch (error) {
        return error;
    }
};

export { getMemberList, registerMember, getMember, deleteMember };
