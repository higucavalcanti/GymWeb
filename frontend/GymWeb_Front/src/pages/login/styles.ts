import styled from "styled-components";
import { Carousel } from "antd";

export const Container = styled.div`
  width: 100%;
  height: 100%;
`;

export const Form = styled.section`
  background: linear-gradient(
    to right,
    var(--purple-900) 16%,
    var(--purple-700) 47%,
    var(--purple-500) 69%,
    var(--purple-300) 100%
  );
  display: flex;
  flex-direction: column;

  @media (min-width: 426px) and (max-width: 1023px) {
    flex-direction: row;
  }
  @media (min-width: 1024px) {
    flex-direction: row;
  }
`;

export const TextArea = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  gap: 1rem;
  padding: 2rem 3rem;

  div {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100%;
    width: 100%;
    gap: 1rem;

    @media (min-width: 426px) and (max-width: 1023px) {
      width: 100%;
      align-items: flex-start;
    }
    @media (min-width: 1024px) {
      width: 70%;
      align-items: flex-start;
    }
  }

  h2 {
    color: var(--purple-300);
  }

  p {
    color: var(--gray-100);
    width: 70%;
    text-align: justify;
  }

  @media (min-width: 426px) and (max-width: 1023px) {
    width: 40%;
  }
  @media (min-width: 1024px) {
    width: 50%;
  }
`;

export const LoginArea = styled.div`
  display: flex;
  justify-content: center;
  width: 100%;
  padding: 2rem 3rem;

  @media (min-width: 426px) and (max-width: 1023px) {
    width: 60%;
  }
  @media (min-width: 1024px) {
    width: 50%;
  }
`;

export const Login = styled.div`
  background: var(--purple-100);
  height: 100%;
  width: 100%;
  border-radius: 2rem;
  display: flex;
  flex-direction: column;
  padding: 1rem;


  @media (min-width: 426px) and (max-width: 1023px) {
    width: 100%;
  }
  @media (min-width: 1024px) {
    width: 70%;
  }

  h3 {
    align-self: center;
    color: var(--purple-700);
  }
`;

export const LoginFormPassword = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;

  @media (min-width: 426px) and (max-width: 1023px) {
    flex-direction: row;
  }
  @media (min-width: 1024px) {
    flex-direction: row;
  }
`;

export const ButtonsRow = styled.div`
  display: flex;
  justify-content: center;
  gap: 13.5rem;
  button {
    background-color: var(--purple-700);
    color: var(--gray-100);

    &:hover {
      background-color: var(--purple-500);
    }
  }
`;

export const ForgotPassText = styled.label`
  text-decoration: underline;
  font-size: small;
  align-self: flex-end;
  cursor: pointer;
  color: var(--purple-500);
`;

export const About = styled.section`
  height: 40%;
  padding: 1rem;
  display: flex;
  flex-direction: column;
  gap: 1rem;

  h2 {
    padding: 0rem 8rem;
    color: var(--purple-700);
  }
`;

export const CarouselArea = styled(Carousel)`
  .slick-slide {
    display: flex !important;
    justify-content: center;
    padding: 1rem;
  }

  .slick-track {
    display: flex;
    gap: 1rem;
  }

  section {
    background: var(--purple-100);
    border-radius: 1rem;
    padding: 1rem;
  }
`;