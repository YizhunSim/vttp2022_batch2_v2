import { Gif, SearchCriteria } from './models';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, map, take } from 'rxjs';

@Injectable()
export class GiphService {
  constructor(private http: HttpClient) {}

  search(searchCriteria: SearchCriteria): Promise<Gif[]> {
    const params = new HttpParams()
      .set('q', searchCriteria.name)
      .set('limit', searchCriteria.limit)
      .set('api_key', 'HzoON4zU9YnfBnpS53qwq0Byk3Q5CsqR');

    
    return firstValueFrom<Gif[]>(
      this.http
        .get<any>('https://api.giphy.com/v1/gifs/search', { params: params })
        .pipe(
            take(1),
            map(v => {
                const data: any[] = v.data;
                return data.map(g => {
                    return {
                        title: g.title,
                        url: g.url,
                        imageUrl: g.images.fixed_height.url
                    } as Gif
                })
            })

    )
    )
  }
}
