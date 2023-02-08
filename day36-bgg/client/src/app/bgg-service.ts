import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, lastValueFrom } from "rxjs";
import { Comment, Game } from "./models";

@Injectable()
export class BggService {
    constructor(private http: HttpClient){

    }

    getGames(): Promise<Game[]>{
        return lastValueFrom(
            this.http.get<Game[]>('/api/games')
        )
    }

    getGameCommentsById(gameId: string): Promise<Comment[]>{
        return firstValueFrom(
            this.http.get<Comment[]>(`/api/game/${gameId}/comments`)
        )
    }
}