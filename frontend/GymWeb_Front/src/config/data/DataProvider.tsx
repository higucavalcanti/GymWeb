import { createContext, ReactNode, useEffect, useState } from "react";
import { jwtDecode } from "jwt-decode";
import { GetUser } from "../../services/UserServices";
import { UserInterface } from "../../services/Types/userType";
import { useAuth } from "../auth/UseAuth";

interface DataContextType {
  reloadPage: () => void;
  userInfo: UserInterface | undefined;
  userId: string | undefined;
  token: string;
}

interface DataProviderProps {
  children: ReactNode;
}

export const DataContext = createContext<DataContextType | undefined>(
  undefined
);

export function DataProvider({ children }: DataProviderProps) {
  const { token } = useAuth(); // Obtém o token do AuthProvider
  const [userInfo, setUserInfo] = useState<UserInterface | undefined>();
  const [userId, setUserId] = useState<string>();

  useEffect(() => {
    if (token) {
      const decoded: any = jwtDecode(token); // Decodifica o JWT para obter informações do usuário
      const userId = decoded?.sub || "";
      const fetchUser = async () => {
        const response = await GetUser(parseInt(userId), token);
        if (response?.status === 200) {
          setUserInfo(response.data);
          setUserId(userId);
        }
      };
      fetchUser();
    }
  }, [token]);

  const reloadPage = () => {
    window.location.reload(); // Alternativa mais simples para recarregar a página
  };

  return (
    <DataContext.Provider value={{ reloadPage, userInfo, userId, token }}>
      {children}
    </DataContext.Provider>
  );
}
