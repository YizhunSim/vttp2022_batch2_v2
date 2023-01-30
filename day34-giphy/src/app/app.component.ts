import { Gif, SearchCriteria } from './models';
import { GiphService } from './giph.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  gifs: Gif[] = [];

  constructor(private giphyService: GiphService){

  }

  onSearch(searchCriteria: SearchCriteria){
    console.info('>>> searchCriteria ', searchCriteria);
    this.giphyService.search(searchCriteria)
    .then(result => {
      console.info(">>> result: ", result)
      this.gifs = result;
    })
    .catch(error => {
      console.error(">>> error: ", error);
    })

    console.info('------- after calling giphyService.search()');
  }
}
