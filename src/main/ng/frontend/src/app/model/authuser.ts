import {Role} from "./role";

export class AuthUserModel {
    id: string;
    name: string;
    surname: string;
    email: string;
    status: boolean;
    role: Role;

    static cloneBase(user: AuthUserModel) : AuthUserModel{
        const cloneUserModel: AuthUserModel = new AuthUserModel();
        cloneUserModel.id = user.id;
        cloneUserModel.email=user.email;
        cloneUserModel.name=user.name;
        cloneUserModel.surname=user.surname;
        cloneUserModel.status=user.status;
        cloneUserModel.role=user.role;
        return cloneUserModel;
    }
}