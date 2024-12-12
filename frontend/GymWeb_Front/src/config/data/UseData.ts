import { useContext } from "react";
import { DataContext } from "./DataProvider";
import { UserInterface } from "../../services/Types/userType";

interface DataContextType {
  reloadPage: () => void;
  userInfo: UserInterface | undefined;
  userId: string | undefined;
  token: string;
}

export function useData(): DataContextType {
  const context = useContext(DataContext);

  if (!context) {
    throw new Error("useData must be used within a DataProvider");
  }

  return context;
}