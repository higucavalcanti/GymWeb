import Cookies from "js-cookie";

export const isAuth = (): boolean => {
  const token = Cookies.get("token");
  return !!token; // Retorna verdadeiro se o token existir, falso caso contrário
};
