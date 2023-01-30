import { HttpBinService } from './http-bin.service';
import { UserData } from './models';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { firstValueFrom, map, take } from 'rxjs';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  data!: UserData;
  form!: FormGroup;

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private httpBinService: HttpBinService
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      name: this.fb.control(''),
      email: this.fb.control(''),
    });
  }

  doPost() {
    const formData: UserData = this.form.value;
    this.httpBinService
      .doPost(formData)
      .then((result) => {
        console.info('result:', result);
        this.data = result;
      })
      .catch((error) => {
        console.info('error', error);
        this.data = error;
      });
  }

  doPostAsForm() {
    const formData: UserData = this.form.value;
    this.httpBinService
      .doPostAsForm(formData)
      .then((result) => {
        console.info('result:', result);
        this.data = result;
      })
      .catch((error) => {
        console.info('error', error);
        this.data = error;
      });
  }

  processForm() {
    const formData: UserData = this.form.value;
    this.httpBinService
      .doGet(formData)
      .then((result) => {
        console.info('>>> in then');
        this.data = result;
      })
      .catch((error) => {
        console.info('>>> in error');
        console.error('>>> error: ', error);
        this.data = error;
      })
      .finally(() => {
        console.info('>>> in finally');
        this.ngOnInit();
      });
  }
}
