import {AuthUserModel} from "./authuser";
import {ItemModel} from "./item";

export class CommentsModel {
    public id: number;
    public owner: AuthUserModel;
    public comment:string;
    public item:ItemModel;
}