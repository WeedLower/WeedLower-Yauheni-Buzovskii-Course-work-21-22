import {CollectionModel} from "./collections";
import {AuthUserModel} from "./authuser";
import {TagModel} from "./tag";

export class ItemModel {
    public id: number;
    public name: string;
    public collection:CollectionModel;
    public author:AuthUserModel;
    public tags:string[];
    public tagSet: TagModel[];
    public likes?: number;
    public meLiked?:boolean;
    public optionalNumberField1 ?:number;
    public optionalNumberField2 ?:number;
    public optionalNumberField3 ?:number;
    public optionalStringField1 ?:string;
    public optionalStringField2 ?:string;
    public optionalStringField3 ?:string;
    public optionalTextField1 ?:string;
    public optionalTextField2 ?:string;
    public optionalTextField3 ?:string;
    public optionalDataField1 ?:Date;
    public optionalDataField2 ?:Date;
    public optionalDataField3 ?:Date;
    public optionalCheckboxField1 ?:boolean;
    public optionalCheckboxField2 ?:boolean;
    public optionalCheckboxField3 ?:boolean;
}