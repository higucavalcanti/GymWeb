import { Eye, EyeClosed } from "@phosphor-icons/react";
import { Input } from "../../components/Input";
import * as S from "./styles";
import { Button } from "../../components/Button";
import { Card } from "../../components/Card/Index";
import { useState } from "react";
import { Title } from "../../components/Title";
import { useNavigate } from "react-router-dom";
import Cookies from "js-cookie";
import { useAuth } from "../../config/auth/UseAuth";
import {
  errorNotification,
  successNotification,
  warningNotification,
} from "../../components/Notification";
import { AuthInterface } from "../../services/Types/authType";
import { LoginUser } from "../../services/AuthServices";

export function Login() {
  const navigate = useNavigate();
  const { auth, reloadPage } = useAuth();

  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [isPassVisible, setIsPassVisible] = useState<boolean>(false);

  const handleChangeEmail = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(e.target.value);
  };

  const handleChangePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  const portalCards = [
    {
      title: "...",
      content:
        "Explore os recursos e serviços oferecidos pela universidade. Faça login para acessar seu painel personalizado.",
    },
    {
      title: "...",
      content:
        "Fique por dentro das datas importantes, como provas, entregas de trabalhos e eventos.",
    },
    {
      title: "Material de Apoio",
      content:
        "Compartilhe material de apoio com colegas e amigos, como apostilas, slides e artigos relevantes.",
    },
    {
      title: "Horário do Laboratório",
      content:
        "Agende horários de laboratórios de maneira prática e eficiente.",
    },
    {
      title: "Fórum",
      content:
        "Interaja com seus colegas e professores, tire dúvidas, compartilhe opiniões e experiências.",
    },
    {
      title: "Comércio Local",
      content:
        "Conheça o comércio local e suas informações, como restaurantes, academias e outros serviços.",
    },
  ];

  const settings = {
    autoplay: true,
    infinite: true,
    speed: 500,
    slidesToShow: 3,
    slidesToScroll: 1,
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 4,
        },
      },
      {
        breakpoint: 645,
        settings: {
          slidesToShow: 2,
        },
      },
    ],
  };

  const AuthData: AuthInterface = {
    email: email,
    password: password,
    username: "",
  };

  const validateFields = (): boolean => {
    if (!email || !password) {
      warningNotification("Preencha todos os campos!");
      return false;
    }
    return true;
  };

  const login = async () => {
    if (!validateFields()) return;

    const response = await LoginUser(AuthData);
    if (response?.status === 200) {
      Cookies.set("token", response.data.token, { expires: 1, secure: true });
      auth();
      reloadPage();
      successNotification("Usuário logado com sucesso!");
      navigate("/");
    } else {
      errorNotification("Erro ao tentar logar. Tente novamente.");
    }
  };

  return (
    <S.Container>
      <S.Form>
        <S.TextArea>
          <div>
            <h2>Acesse o GymWeb</h2>
            <p>
              Bem-vindo ao GymWeb! Nosso sistema de gerenciamento está em fase
              inicial de protótipo e foi desenvolvido para ajudar você a
              organizar e acompanhar sua rotina de treinos de forma prática e
              eficiente. Aqui, você pode explorar recomendações de suplementos,
              visualizar listas de treinos personalizados, e conferir exercícios
              com séries e repetições adaptadas às suas necessidades.
            </p>
          </div>
        </S.TextArea>
        <S.LoginArea>
          <S.Login>
            <h3>Login</h3>
            <Input label="Email:" inputFunction={handleChangeEmail} />
            <S.LoginFormPassword>
              <Input
                label="Senha:"
                rightAdd={
                  <span
                    onClick={() => setIsPassVisible(!isPassVisible)}
                    style={{ cursor: "pointer" }}
                  >
                    {isPassVisible ? (
                      <Eye size={24} color="#7fc7d9" weight="duotone" />
                    ) : (
                      <EyeClosed size={24} color="#7fc7d9" weight="duotone" />
                    )}
                  </span>
                }
                inputFunction={handleChangePassword}
                type={isPassVisible ? "text" : "password"}
              />
            </S.LoginFormPassword>
            <S.ButtonsRow>
              <Button
                label="Cadastrar"
                size="large"
                color="#6a1b9a"
                buttonFunction={() => navigate("/register")}
              />
              <Button label="Entrar" size="large" buttonFunction={login} />
            </S.ButtonsRow>
          </S.Login>
        </S.LoginArea>
      </S.Form>
      <S.About>
        <Title text={"Recomendação de Suplementos"} />
        <S.CarouselArea {...settings}>
        {portalCards.map((item, index) => (
          <Card
            key={index}
            title={item.title}
            content={item.content}
            rateCard={false}
            like={false}
          />
        ))}
      </S.CarouselArea>
      </S.About>
    </S.Container>
  );
}