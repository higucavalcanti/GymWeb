import { createContext, ReactNode, useState } from "react";
import Cookies from "js-cookie";

interface AuthContextType {
  isAuthenticated: boolean;
  auth: () => void;
  logout: () => void;
  reloadPage: () => void;
  token: string;
}

interface AuthProviderProps {
  children: ReactNode;
}

export const AuthContext = createContext<AuthContextType | undefined>(
  undefined
);

export function AuthProvider({ children }: AuthProviderProps) {
  const token = Cookies.get("token") || ""; // Obtém o token dos cookies
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(!!token);

  const auth = () => {
    if (token) {
      setIsAuthenticated(true);
    }
  };

  const logout = () => {
    setIsAuthenticated(false);
    Cookies.remove("token"); // Remove o token dos cookies
  };

  const reloadPage = () => {
    window.location.reload(); // Recarrega a página
  };

  return (
    <AuthContext.Provider value={{ isAuthenticated, auth, logout, reloadPage, token }}>
      {children}
    </AuthContext.Provider>
  );
}