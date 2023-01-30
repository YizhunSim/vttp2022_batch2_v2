import { HttpClient } from '@angular/common/http';
import { Gif, SearchCriteria } from './../models';
import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { GiphService } from '../giph.service';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-giphyform',
  templateUrl: './giphyform.component.html',
  styleUrls: ['./giphyform.component.css'],
})
export class GiphyformComponent implements OnInit {
  @Output()
  onSearch = new Subject<SearchCriteria>;

  searchForm!: FormGroup;
  giphies !: [];


  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.searchForm = this.createForm();
  }

  processForm(){
    const searchCriteria: SearchCriteria = this.searchForm.value;
    // searchCriteria.limit = searchCriteria.limit;
    this.onSearch.next(searchCriteria);
    this.searchForm = this.createForm();
  }

  private createForm() : FormGroup{
    return this.searchForm = this.fb.group({
      name: this.fb.control(''),
      limit: this.fb.control(''),
    });
  }
}
