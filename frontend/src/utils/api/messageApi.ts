import { geminiApiKey, geminiApiUrl } from "@/lib/utils";
import axiosInstance from "../service/axiosInstance";

const endpoint = "/api/messages";

export const getConversation = async (
    user1Id: string,
    user2Id: string
): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/conversations/${user1Id}/${user2Id}`);
};

export const getContacts = async (userId: string): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/users/${userId}/contacts`)
}

export const getLatestMessages = async (userId: string): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/users/${userId}/latest`)
}

export const countUnreadMessages = async (userId: string): Promise<any> => {
    return await axiosInstance.get(`${endpoint}/users/${userId}/unread/count`)
}

export const generateBotResponse = async (text: string): Promise<any> => {
    try {
        const requestOptions = {
            method: "POST",
            header: { "Content-Type": "application/json" },
            body: JSON.stringify({
                contents: [{
                    parts: [{ text: text }]
                }]
            })
        };

        const url = `${geminiApiUrl}${geminiApiKey}`;

        const response = await fetch(url, requestOptions);
        const data = await response.json();

        if (!response.ok) {
            throw new Error(data?.error?.message || "Failed to generate response");
        }

        const apiResponseText = data.candidates[0].content.parts[0].text.trim();
        return apiResponseText;
    } catch (error) {
        console.log(error);
    }
}