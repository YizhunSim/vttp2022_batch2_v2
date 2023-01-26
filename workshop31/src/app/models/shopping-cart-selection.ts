export class ShoppingCartSelection {
    public name: string;
    public imageUrl: string;
    public quantity: number;
    

    constructor(name : string, imageUrl: string, qty : number){
        this.name = name;
        this.imageUrl = imageUrl;
        this.quantity = qty;
    }
}