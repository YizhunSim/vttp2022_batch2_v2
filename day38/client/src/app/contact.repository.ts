import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, CanDeactivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import Dexie from "dexie";
import { Observable } from "rxjs";
import { CanLeave, Contact } from "./models";

@Injectable()
export class ContactRepository extends Dexie implements CanActivate, CanDeactivate<CanLeave>{
    contact!: Dexie.Table<Contact, string>
    canLogin: boolean = false;

    constructor(private router: Router){
        super('contactDB')
        this.version(1).stores({
            contact: "email" // index
        })
        this.contact = this.table('contact')
    }

    addContact(contact: Contact) : Promise<string> {
        return this.contact.add(contact)
    }

    getAllContacts(): Promise<Contact[]> {
        return this.contact.orderBy('email').toArray(); // ordering must be by index
    }

    canActivate(route: ActivatedRouteSnapshot, 
        state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        if (this.canLogin){
            return true
        }
        return this.router.parseUrl('/');
    }

    canDeactivate(component: CanLeave, currentRoute: ActivatedRouteSnapshot, currentState: RouterStateSnapshot, nextState: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        if (!component.canLeave()){
            return true;
        }
        // true -> OK, false -> Cancel
        return confirm(('You have not saved your data. Leave?'))
    }
}