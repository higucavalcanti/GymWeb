import { z } from "zod";

export interface UserInterface {
  username: string;
  email: string;
}

export const UserSchema = z.object({
  name: z
    .string({
      required_error: "O username é obrigatório",
    })
    .min(2, { message: "Necessário mais de 2 caracteres no username" })
    .max(50, { message: "Apenas 50 caracteres permitidos no username" }),
  email: z
    .string({
      required_error: "O email é obrigatório",
    })
    .email(),
});
