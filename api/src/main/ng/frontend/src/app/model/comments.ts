import {AuthuserModel} from "./authuser";
import {ItemModel} from "./item";

export class CommentsModel {
    public id: number;
    public owner: AuthuserModel;
    public comment:string;
    public item:ItemModel;
}