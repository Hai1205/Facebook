import axiosInstance from "../service/axiosInstance";

const endpoint = "/api/posts";

export const getAllPost = async (): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/`)
}

export const getAllStory = async (): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/stories`)
}

export const getAllReport = async (): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/reports`)
}

export const getUserPosts = async (userId: string): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/users/${userId}`)
}

export const createPost = async (userId: string, formData: FormData): Promise<any> => {
    formData.append("userId", userId);
    return await axiosInstance.post(`${endpoint}`, formData)
}

export const createStory = async (userId: string, formData: FormData): Promise<any> => {
    formData.append("userId", userId);
    return await axiosInstance.post(`${endpoint}/stories`, formData)
}

export const deletePost = async (postId: string): Promise<any> => {
    return await axiosInstance.delete(`${endpoint}/${postId}`)
}

export const deleteStory = async (storyId: string): Promise<any> => {
    return await axiosInstance.delete(`${endpoint}/stories/${storyId}`)
}

export const likePost = async (postId: string, userId: string): Promise<any> => {
    return await axiosInstance.post(`${endpoint}/${postId}/users/${userId}/likes`)
}

export const commentPost = async (postId: string, userId: string, formData: FormData): Promise<any> => {
    return await axiosInstance.post(`${endpoint}/${postId}/users/${userId}/comments`, formData)
}

export const deleteComment = async (commentId: string, postId: string): Promise<any> => {
    return await axiosInstance.delete(`${endpoint}/${postId}/comments/${commentId}`)
}

export const sharePost = async (postId: string, userId: string): Promise<any> => {
    return await axiosInstance.post(`${endpoint}/${postId}/users/${userId}/shares`);
}

export const updatePost = async (postId: string, formData: FormData): Promise<any> => {
    return await axiosInstance.put(`${endpoint}/${postId}`, formData);
}

export const getUserFeed = async (userId: string): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/users/${userId}/feed`);
}

export const getUserStoryFeed = async (userId: string): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/users/${userId}/stories/feed`);
}

export const searchPosts = async (query: string): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/search${query}`);
}

export const report = async (userId: string, contentId: string, formData: FormData): Promise<any> => {
    return await axiosInstance.post(`${endpoint}/reports/users/${userId}/contents/${contentId}`, formData);
}

export const resolveReport = async (reportId: string, formData: FormData): Promise<any> => {
    return await axiosInstance.put(`${endpoint}/reports/${reportId}`, formData);
}

export const deleteReport = async (reportId: string): Promise<any> => {
    return await axiosInstance.delete(`${endpoint}/reports/${reportId}`);
}

export const searchReports = async (query: string): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/reports/search${query}`);
}

export const getPost = async (postId: string): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/${postId}`);
}
