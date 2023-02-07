import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, Subject } from "rxjs";

const BACKEND :string = "http://localhost:8080";

@Injectable()
export class BGGService {
    onSearchResults = new Subject<Game[]>();
    onSearchQueries = new Subject<string>();

    constructor(private http: HttpClient){

    }

    searchGameByName(name: string): Promise<Game[]>{
        this.onSearchQueries.next(name);
        
        const params = new HttpParams().set("name", name)
        return firstValueFrom(
            this.http.get<Game[]>(`${BACKEND}/api/games`, { params })
        ).then(results => {
            this.onSearchResults.next(results);
            return results;
        })
    }

}