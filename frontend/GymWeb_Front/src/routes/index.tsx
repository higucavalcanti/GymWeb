import { BrowserRouter, Route, Routes } from "react-router-dom";
import styled from "styled-components";
import { Login } from "../pages/login";
import { Home } from "../pages/home";
import Registration from "../pages/registration";

import { Profile } from "../pages/profile";

import { AuthProvider } from "../config/auth/AuthProvider";
import { DataProvider } from "../config/data/DataProvider";

const Container = styled.main`
  height: 100dvh;
`;

export const AppRoutes = () => {
  return (
    <AuthProvider>
      <DataProvider>
        <BrowserRouter>
          <Container>
            <Routes>
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Registration />} />
                <Route path="/" element={<Home />} />
                <Route path="/profile" element={<Profile />} />
            </Routes>
          </Container>
        </BrowserRouter>
      </DataProvider>
    </AuthProvider>
  );
};
