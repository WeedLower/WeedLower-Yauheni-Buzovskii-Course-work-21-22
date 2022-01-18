import {Rolemodel} from "./rolemodel";
import {Role} from "./role";

export class AuthuserModel{
    id: string;
    name: string;
    surname: string;
    email: string;
    role: Role;

    static cloneBase(user: AuthuserModel) : AuthuserModel{
        const cloneUserModel: AuthuserModel = new AuthuserModel();
        cloneUserModel.id = user.id;
        cloneUserModel.email=user.email;
        cloneUserModel.name=user.name;
        cloneUserModel.surname=user.surname;
        cloneUserModel.role=user.role;
        return cloneUserModel;
    }
}