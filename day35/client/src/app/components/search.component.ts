import { BGGService } from './../bgg-service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit{

  searchForm !: FormGroup

  constructor(private fb: FormBuilder, private bggSvc: BGGService){

  }
  ngOnInit(): void {
      this.searchForm = this.createForm();
  }

  doSearch(){
    const name = this.searchForm.get('name')?.value;
    console.info('>>>> name: ', name);

    this.bggSvc.searchGameByName(name)
    .then(games => {
      console.info('>>>> games: ', games);
      this.searchForm = this.createForm();
    })
    .catch(error => {
      console.error(">>>> error: ", error)
    })
  }

  private createForm() : FormGroup{
    return this.fb.group({
      name: this.fb.control('', [Validators.required])
    })
  }
}
