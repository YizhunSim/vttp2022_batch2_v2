import { Subject } from 'rxjs';
import { Injectable } from "@angular/core";
import { User } from '../models';

@Injectable()
export class FriendsService {
    friends : Subject<User> = new Subject<User>();
}