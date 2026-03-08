import axiosInstance from "../service/axiosInstance";

const endpoint = "/api/auth";

export const register = async (formData: FormData): Promise<any> => {
  return await axiosInstance.post(`${endpoint}/register`, formData);
};

export const login = async (formData: FormData): Promise<any> => {
  return await axiosInstance.post(`${endpoint}/login`, formData);
};

export const loginGoogle = async (formData: FormData): Promise<any> => {
  return await axiosInstance.post(`${endpoint}/login-google`, formData);
};

export const logout = async (): Promise<any> => {
  return await axiosInstance.post(`${endpoint}/logout`);
};

export const checkAdmin = async (): Promise<any> => {
  return await axiosInstance.get(`${endpoint}/admin-check`)
}

export const sendOTP = async (email: string): Promise<any> => {
  return await axiosInstance.post(`${endpoint}/${email}/otp/send`)
}

export const checkOTP = async (email: string, formData: FormData): Promise<any> => {
  return await axiosInstance.post(`${endpoint}/${email}/otp/verify`, formData)
}

export const changePassword = async (userId: string, formData: FormData): Promise<any> => {
  return await axiosInstance.put(`${endpoint}/users/${userId}/password/change`, formData);
};

export const forgotPassword = async (email: string, formData: FormData): Promise<any> => {
  return await axiosInstance.put(`${endpoint}/${email}/password/forgot`, formData);
};

export const resetPassword = async (userId: string): Promise<any> => {
  return await axiosInstance.put(`${endpoint}/users/${userId}/password/reset`);
};