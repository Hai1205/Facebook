import axiosInstance from "../service/axiosInstance";

const endpoint = "/api/stats";

export const getGeneralStat = async (): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/`)
}

export const getPopularPostStat = async (): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/popular-posts`)
}

export const getTopUsersStat = async (): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/top-users`)
}