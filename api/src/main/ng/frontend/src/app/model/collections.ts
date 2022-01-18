import {AuthuserModel} from "./authuser";
import {ImageModel} from "./Image";

export class CollectionModel {
    public id: string;
    public name: string;
    public owner: AuthuserModel;
    public description: string;
    public topic: string;
    public img?:ImageModel;
}