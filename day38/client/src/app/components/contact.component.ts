import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ContactRepository } from '../contact.repository';
import { ContactService } from '../contact.service';
import { CanLeave, Contact } from '../models';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit, CanLeave {
  contactForm!: FormGroup;
  contacts!: Contact[];

  constructor(
    private fb: FormBuilder,
    private contactRepo: ContactRepository,
    private router: Router,
    private contactSvc: ContactService
  ) {}

  async ngOnInit() {
    this.contactForm = this.createForm();

    this.contacts = await this.contactRepo.getAllContacts();
  }

  async processContact() {
    try {
      const contact = this.contactForm.value as Contact;
      console.info('>>>> contact: ', contact);

      const k = await this.contactRepo.addContact(contact);
      console.info('>>>> k ', k);
      this.contacts = await this.contactRepo.getAllContacts();
      this.ngOnInit()
      this.router.navigate([ '/' ])
    } catch (error) {
      console.error('error: ', error);
    }
  }

  // CanLeave Interface
  canLeave(): boolean {
      return this.contactForm.dirty;
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

  saveContacts() : void{
    this.contactSvc.saveContacts(this.contacts);
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control(''),
      email: this.fb.control(''),
    });
  }
}
