import { HttpClient, HttpParams, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom } from "rxjs";
import { Contact } from "./models";

Injectable()
export class ContactService {
    constructor(private http: HttpClient){

    }

    saveContacts(contacts: Contact[]): Promise<Contact[]> {
        const payload = new HttpParams()
        
        contacts.forEach(c => {
            payload.set("name", c.name)
            payload.set("email", c.email)
        })

        const headers = new HttpHeaders()
        .set("Content-Type", "application/x-www-form-urlencoded")
        .set("Accept", "application/json")


        return firstValueFrom(
            this.http.post<Contact[]>('/api/contact', payload.toString(), {headers})
        )
    }
    
    
}