import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom } from "rxjs";

@Injectable()
export class BGGService {
    BACKEND :string = "http://localhost:8080";

    constructor(private http: HttpClient){

    }

    searchGameByName(name: string): Promise<Game[]>{
        const params = new HttpParams().set("name", name)
        return firstValueFrom(
            this.http.get<Game[]>(`${BACKEND}/api/games`, { params })
        )
    }

}