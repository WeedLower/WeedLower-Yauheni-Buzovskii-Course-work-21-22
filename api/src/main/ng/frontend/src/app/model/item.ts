import {CollectionModel} from "./collections";
import {AuthuserModel} from "./authuser";
import {TagModel} from "./tag";

export class ItemModel {
    public id: number;
    public name: string;
    public collection:CollectionModel;
    public author:AuthuserModel;
    public tags:string[];
    public tagSet: TagModel[];
    public likes?: number;
    public meLiked?:boolean;
}