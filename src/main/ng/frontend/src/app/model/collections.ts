import {AuthuserModel} from "./authuser";
import {ImageModel} from "./Image";

export class CollectionModel {
    public id: string;
    public name: string;
    public owner: AuthuserModel;
    public description: string;
    public topic: string;
    public img?:ImageModel;
    public numberName1 ?:string;
    public numberName2 ?:string;
    public numberName3 ?:string;
    public stringName1 ?:string;
    public stringName2 ?:string;
    public stringName3 ?:string;
    public textName1 ?:string;
    public textName2 ?:string;
    public textName3 ?:string;
    public dataName1 ?:string;
    public dataName2 ?:string;
    public dataName3 ?:string;
    public checkboxName1 ?:string;
    public checkboxName2 ?:string;
    public checkboxName3 ?:string;

}