import { ResponseSaleDetail } from "../saleDetailsDTO/responseSaleDetail";

export interface ResponseSale{
    name:string,
    price:string,
    createTime:string,
    pharmacyName:string,
    saleDetails:ResponseSaleDetail[];
}