import { ContactService} from '../contact.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Contact } from '../models';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  contactForm !: FormGroup;

  values$!: Subscription
  state$!: Subscription

  constructor(private fb: FormBuilder, private contactSvc: ContactService){
    
  }

  processContact(){
    console.info(">>> processContact Called");
    const contact: Contact = this.contactForm.value as Contact;
    this.contactSvc.saveContact(contact)
    .then(result => {
      console.info('>>>> result: ', result);
      this.initForm();
    })
    .catch(error => {
      console.error(">>>> error: ", error)
    })
  }
  
  ngOnInit(): void {
      this.initForm();
  }

  private initForm() {
    this.contactForm = this.createForm()
    if (this.values$) {
      this.values$.unsubscribe()
      this.state$.unsubscribe()
    }
    this.values$ = this.contactForm.valueChanges.subscribe(
      (values) => {
        console.info('>>> values: ', values)
      }
    )
    this.state$ = this.contactForm.statusChanges.subscribe(
      (state) => {
        console.info('>>> state: ', state)
      }
    )
  }


  private createForm() : FormGroup{
    return this.fb.group({
      name: this.fb.control('', [Validators.required]),
      phone: this.fb.control('', [Validators.required]),
      email: this.fb.control('', [Validators.required, Validators.email])
    })
  }
}
