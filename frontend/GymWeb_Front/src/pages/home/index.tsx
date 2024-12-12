import {
  UserCircle,
} from "@phosphor-icons/react";
import { Title } from "../../components/Title";
import * as S from "./styles";
import { Button } from "../../components/Button";
import { useNavigate } from "react-router-dom";

export function Home() {
  const navigate = useNavigate();


  const sendToProfile = () => {
    navigate("/profile");
  };
  return (
    <S.Container>
      <S.Content>
        <Title
          text={"GymWeb"}
          item={
            <Button
              label={"Ver Perfil"}
              icon={<UserCircle size={32} weight="fill" />}
              shape="round"
              color="#365486"
              secondColor=" #7fc7d9"
              buttonFunction={sendToProfile}
            />
          }
        />
      </S.Content>
    </S.Container>
  );
}
