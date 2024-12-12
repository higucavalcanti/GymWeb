import { useState } from "react";
import { Badge, Form, Input, message } from "antd";
import * as S from "./styles";
import { passowordValidation } from "./fuctions";
import { useNavigate } from "react-router-dom";
import { CreateUser } from "../../services/AuthServices";
import {
  errorNotification,
  successNotification,
} from "../../components/Notification";

interface SignUpProps {
  username: string;
  email: string;
  password: string;
  repeatPassword: string;
}

function Registration() {
  const navigate = useNavigate();
  const [isSubmit, setSubmit] = useState(false);
  const [form] = Form.useForm<SignUpProps>();

  const [lenght8, setLenght8] = useState(false);
  const [containsNumber, setContainsNumber] = useState(false);
  const [containsCharSpecial, setContainsCharSpecial] = useState(false);
  const [containsUppercase, setContainsUppercase] = useState(false);
  const [containsLowercase, setContainsLowercase] = useState(false);

  const passwordValidator = passowordValidation({
    setLenght8,
    setContainsNumber,
    setContainsCharSpecial,
    setContainsUppercase,
    setContainsLowercase,
  });

  const onFinish = async (values: SignUpProps) => {
    setSubmit(true);
    const { username, email, repeatPassword } = values;

    try {
      const response = await CreateUser({
        username,
        email,
        password: repeatPassword,
      });

      setSubmit(false);
      if (response?.status === 201) {
        successNotification("Usuário registrado com sucesso!");
        navigate("/login");
      } else if (response?.status === 400) {
        errorNotification("Erro ao registrar, tente novamente.");
      }
    } catch (error) {
      setSubmit(false);
      errorNotification("Erro inesperado. Tente novamente.");
    }
  };

  const onFinishFailed = () => {
    message.error("Preencha os campos corretamente!", 2);
  };

  return (
    <S.Container>
      <S.CustomForm
        form={form}
        name="signup"
        layout="vertical"
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
      >
        <S.RegistrationTitle>Cadastro</S.RegistrationTitle>
        <S.Grid>
          <Form.Item
            name="username"
            label="Seu username"
            rules={[{ required: true, message: "Por favor, digite um username!" }]}
          >
            <S.CustomInput placeholder="Digite seu username" />
          </Form.Item>

          <Form.Item
            name="email"
            label="Email"
            rules={[{ required: true, message: "Por favor digite seu email!" }]}
          >
            <S.CustomInput placeholder="Email" />
          </Form.Item>

          <Form.Item
            name="password"
            label="Senha"
            rules={[
              { required: true, message: "Por favor digite um senha!" },
              { validator: passwordValidator },
            ]}
            hasFeedback
          >
            <Input.Password placeholder="Senha" />
          </Form.Item>

          <Form.Item
            name="repeatPassword"
            label="Repita sua senha"
            rules={[
              { required: true, message: "Por favor, repita sua senha!" },
              ({ getFieldValue }) => ({
                validator(_, value) {
                  if (!value || getFieldValue("password") === value) {
                    return Promise.resolve();
                  }
                  return Promise.reject(new Error("Senha não correspondente!"));
                },
              }),
            ]}
            hasFeedback
          >
            <Input.Password placeholder="Repita sua senha" />
          </Form.Item>
        </S.Grid>
        <S.PassowrdCheck direction="vertical">
          <Badge
            status={lenght8 ? "success" : "error"}
            text="Senha com no mínimo 8 caracteres."
          />
          <Badge
            status={containsUppercase ? "success" : "error"}
            text="Senha com no mínimo 1 letra maiúscula."
          />
          <Badge
            status={containsLowercase ? "success" : "error"}
            text="Senha com no mínimo 1 letra minúscula."
          />
          <Badge
            status={containsNumber ? "success" : "error"}
            text="Senha com no mínimo 1 número."
          />
          <Badge
            status={containsCharSpecial ? "success" : "error"}
            text="Senha com no mínimo 1 caractere especial."
          />
        </S.PassowrdCheck>
        <S.ButtonRow>
          <S.CustomButton onClick={() => navigate("/login")}>Voltar</S.CustomButton>
          <S.CustomButton htmlType="submit" loading={isSubmit}>
            Cadastrar
          </S.CustomButton>
        </S.ButtonRow>
      </S.CustomForm>
    </S.Container>
  );
}

export default Registration;
