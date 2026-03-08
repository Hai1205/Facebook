import axiosInstance from "../service/axiosInstance";

const endpoint = "/api/users";

export const followUser = async (currentUserId: string, opponentId: string): Promise<any> => {
  return await axiosInstance.post(`${endpoint}/${currentUserId}/follows/${opponentId}`);
};

export const getAllUser = async (): Promise<any> => {
  return await axiosInstance.get(`${endpoint}/`);
};

export const getSuggestedUsers = async (userId: string): Promise<any> => {
  return await axiosInstance.get(`${endpoint}/${userId}/suggestions`);
};

export const getUser = async (userId: string): Promise<any> => {
  return await axiosInstance.get(`${endpoint}/${userId}`);
};

export const getUserProfile = async (currentUserId: string, targetUserId: string): Promise<any> => {
  return await axiosInstance.get(`${endpoint}/${currentUserId}/profile/${targetUserId}`);
};

export const getUserFriendsRequests = async (userId: string): Promise<any> => {
  return await axiosInstance.get(`${endpoint}/${userId}/friend-requests`);
};

export const createUser = async (
  formData: FormData
): Promise<any> => {
  return await axiosInstance.post(`${endpoint}`, formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
};

export const updateUser = async (
  userId: string,
  formData: FormData
): Promise<any> => {
  return await axiosInstance.put(`${endpoint}/${userId}`, formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
};

export const updateUserBio = async (
  userId: string,
  formData: FormData
): Promise<any> => {
  return await axiosInstance.put(`${endpoint}/${userId}/bio`, formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
};

export const updateCoverPhoto = async (
  userId: string,
  formData: FormData
): Promise<any> => {
  return await axiosInstance.put(`${endpoint}/${userId}/cover-photo`, formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
};

export const updateAvatarPhoto = async (
  userId: string,
  formData: FormData
): Promise<any> => {
  return await axiosInstance.put(`${endpoint}/${userId}/avatar-photo`, formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
};

export const deleteUser = async (userId: string): Promise<any> => {
  return await axiosInstance.delete(`${endpoint}/${userId}`);
};

export const searchUsers = async (queryString: string): Promise<any> => {
  return await axiosInstance.get(`${endpoint}/search${queryString}`);
};

export const sendFriendRequest = async (
  currentUserId: string,
  opponentId: string
): Promise<any> => {
  return await axiosInstance.post(
    `${endpoint}/${currentUserId}/friend-requests/${opponentId}`
  );
};

export const unFriend = async (
  currentUserId: string,
  opponentId: string
): Promise<any> => {
  return await axiosInstance.delete(
    `${endpoint}/${currentUserId}/friends/${opponentId}`
  );
};

export const respondFriendRequest = async (
  currentUserId: string,
  opponentId: string,
  formData: FormData
): Promise<any> => {
  return await axiosInstance.put(
    `${endpoint}/${currentUserId}/friend-requests/${opponentId}`,
    formData
  );
};

export const getAllFriendsRequest = async (): Promise<any> => {
  return await axiosInstance.get(`${endpoint}/friend-requests`);
};

export const getFriendRequestStatus = async (currentUserId: string, targetUserId: string): Promise<any> => {
  return await axiosInstance.get(`${endpoint}/${currentUserId}/friend-requests/${targetUserId}/status`);
};