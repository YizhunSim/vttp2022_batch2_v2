import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ContactRepository } from './contact.repository';
import { Contact } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  contactForm!: FormGroup;
  contacts!: Contact[];

  constructor(
    private fb: FormBuilder,
    private contactRepoSvc: ContactRepository
  ) {}

  async ngOnInit() {
    this.contactForm = this.createForm();

    this.contacts = await this.contactRepoSvc.getAllContacts();
  }

  async processContact() {
    try {
      const contact = this.contactForm.value as Contact;
      console.info('>>>> contact: ', contact);

      const k = await this.contactRepoSvc.addContact(contact);
      console.info('>>>> k ', k);
      this.contacts = await this.contactRepoSvc.getAllContacts();
    } catch (error) {
      console.error('error: ', error);
    }
  }

  // processContact(){
  //   const contact = this.contactForm.value as Contact
  //   console.info('>>>> contact: ', contact)
  //   this.contactRepoSvc.addContact(contact)
  // .then(v => {
  //   console.info('>>>> v: ', v)
  //   this.ngOnInit()
  // })
  // .catch(error => {
  //   console.error('error: ', error)
  // })

  // }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control(''),
      email: this.fb.control(''),
    });
  }
}
