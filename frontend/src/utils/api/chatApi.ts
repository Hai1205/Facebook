import axiosInstance from "../service/axiosInstance";

const endpoint = "/api/conversations";
const messageEndpoint = "/api/messages";

export const getOrCreateConversation = async (
    userId: string,
    otherUserId: string
): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/between/${userId}/${otherUserId}`);
};

export const createConversation = async (userId: string,
    otherUserId: string): Promise<any> => {
    return await axiosInstance.post(`${endpoint}/between/${userId}/${otherUserId}`)
}

export const getUserConversations = async (userId: string): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/users/${userId}`)
}

export const getMessages = async (conversationId: string, userId: string): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/${conversationId}/users/${userId}/messages`)
}

export const getConversation = async (conversationId: string, userId: string): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/${conversationId}/users/${userId}`)
}

export const getUsersWithConversation = async (userId: string): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/users/${userId}/participants`)
}

export const createGroupConversation = async (formData: FormData): Promise<any> => {
    return await axiosInstance.post(`${endpoint}/groups`, formData)
}

export const addUserToGroup = async (conversationId: string, userId: string): Promise<any> => {
    return await axiosInstance.post(`${endpoint}/${conversationId}/users/${userId}`)
}

export const deleteUserFromGroup = async (conversationId: string, userId: string): Promise<any> => {
    return await axiosInstance.post(`${endpoint}/delete-group/${conversationId}/${userId}`)
}

export const getOnlineUsers = async (): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/online-users`);
}

export const isUserOnline = async (userId: string): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/users/${userId}/online-status`);
}

export const sendMessage = async (messageData: any): Promise<any> => {
    return await axiosInstance.post(`${messageEndpoint}`, messageData);
}

export const sendMessageWithFiles = async (formData: FormData): Promise<any> => {
    return await axiosInstance.post(`${messageEndpoint}/files`, formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
}

export const sendMessageWithImages = async (formData: FormData): Promise<any> => {
    return await axiosInstance.post(`${messageEndpoint}/images`, formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
}

export const markMessageAsDeleted = async (messageId: string, userId: string): Promise<any> => {
    return await axiosInstance.delete(`${messageEndpoint}/${messageId}?userId=${userId}`);
}