import { useContext } from "react";
import { AuthContext } from "./AuthProvider";

interface AuthContextType {
  isAuthenticated: boolean;
  auth: () => void;
  logout: () => void;
  reloadPage: () => void;
  token: string;
}

export function useAuth(): AuthContextType {
  const context = useContext(AuthContext);

  if (!context) {
    throw new Error("useAuth must be used within an AuthProvider");
  }

  return context;
}
