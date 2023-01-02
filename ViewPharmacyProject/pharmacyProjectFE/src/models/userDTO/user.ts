import { Timestamp } from "rxjs";
export interface User{
    id:number;
    name:string
    username:string;
    password:string;
    email:string;
    createTime:string;
    role:string;
}