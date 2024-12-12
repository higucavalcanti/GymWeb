import { AxiosError } from "axios";
import { api } from "../api";
import { AuthInterface } from "../Types/authType";

export const CreateUser = async (data: AuthInterface) => {
  try {
    const response = await api.post("/register", data);
    return response;
  } catch (error) {
    if (error instanceof AxiosError) {
      return error.response;
    }
    throw new Error("Erro desconhecido durante a criação do usuário.");
  }
};

export const LoginUser = async (data: AuthInterface) => {
  try {
    const response = await api.post("/login", data);
    return response;
  } catch (error) {
    if (error instanceof AxiosError) {
      return error.response;
    }
    throw new Error("Erro desconhecido durante o login.");
  }
};
