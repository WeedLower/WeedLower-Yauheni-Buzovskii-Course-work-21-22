import {Role} from "./role";
export class UserModel{
    id: string;
    name: string;
    surname: string;
    email: string;
    password?: string;
    status:boolean;
    role: Role;
}

export class AuthToken{
    token: string;
}