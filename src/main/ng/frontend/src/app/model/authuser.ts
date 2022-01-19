import {Role} from "./role";

export class AuthuserModel{
    id: string;
    name: string;
    surname: string;
    email: string;
    status: boolean;
    role: Role;

    static cloneBase(user: AuthuserModel) : AuthuserModel{
        const cloneUserModel: AuthuserModel = new AuthuserModel();
        cloneUserModel.id = user.id;
        cloneUserModel.email=user.email;
        cloneUserModel.name=user.name;
        cloneUserModel.surname=user.surname;
        cloneUserModel.status=user.status;
        cloneUserModel.role=user.role;
        return cloneUserModel;
    }
}