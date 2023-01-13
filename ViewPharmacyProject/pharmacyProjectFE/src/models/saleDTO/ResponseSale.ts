import { ResponseSaleDetail } from "../saleDetailsDTO/responseSaleDetail";

export interface ResponseSale{
    id:string,
    name:string,
    price:string,
    createTime:string,
    isConfirmed:string,
    pharmacyName:string,
    saleDetails:ResponseSaleDetail[];
}