export interface Item {
    itemName: string
    quantity: number
    price: number
}

export interface Order {
    name: string
    address: string
    email: string
    phone: number
    express: boolean
    delivery: string
    total: number
    cartItems: Item[]
}