import axiosInstance from "../service/axiosInstance";

const endpoint = "/api/notifications";

export const getAllNoti = async (): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/`);
}

export const deleteNoti = async (notiId: string): Promise<any> => {
    return await axiosInstance.delete(`${endpoint}/${notiId}`);
}

export const deleteUserNotifications = async (userId: string): Promise<any> => {
    return await axiosInstance.delete(`${endpoint}/users/${userId}`);
}

export const getUserNotifications = async (userId: string): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/users/${userId}`);
}

export const markRead = async (notiId: string): Promise<any> => {
    return await axiosInstance.put(`${endpoint}/${notiId}/read`);
}

export const markAllRead = async (userId: string): Promise<any> => {
    return await axiosInstance.put(`${endpoint}/users/${userId}/read`);
}
