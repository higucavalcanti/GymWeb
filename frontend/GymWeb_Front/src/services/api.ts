import axios, { AxiosInstance } from "axios";
import Cookies from "js-cookie";

const token = Cookies.get("token");

export const api: AxiosInstance = axios.create({
  baseURL: "http://localhost:8080",
  withCredentials: true,
  headers: {
    Authorization: token ? `Bearer ${token}` : "",
  },
});
