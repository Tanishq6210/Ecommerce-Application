import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';
import { DataServiceService } from '../data-service.service';
import { Router } from '@angular/router';
import { SharedService } from '../../SharedService';
import { Person } from '../Person';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  login = true;
  person: any;

  constructor(private formBuilder: FormBuilder, private service: DataServiceService, private router: Router, private sharedService: SharedService) {}

  loginForm = this.formBuilder.group ({
    login_name: ['',],
    login_password: ['', ]
  })

  get login_name() {
    return this.loginForm.get('login_name') as FormControl;
  }

  get login_password() {
    return this.loginForm.get('login_password') as FormControl

  }


  registerForm = this.formBuilder.group ({
    register_name: ['',],
    register_password: ['', ]
  })

  get register_name() {
    return this.registerForm.get('register_name') as FormControl;
  }

  get register_password() {
    return this.registerForm.get('register_password') as FormControl

  }
  
  toggleLogin() {
    this.login = !this.login;
    console.log("hi")
  }
  onLogin() {
    let name: string = this.loginForm?.controls['login_name'].value!;
    let password: string = this.loginForm?.controls['login_password'].value!;

    this.service.login(name, password).subscribe(
      (data) => {
        console.log(data)
        const person = new Person(data.user_name, data.user_password);
        person.user_id = data.user_id;
        this.sharedService.setPerson(person);

        this.router.navigate(['/items']);
      },
      (error) => {
        alert("No user found")
      }
    )
  }

  onRegister() {
    let name: string = this.registerForm?.controls['register_name'].value!;
    let password: string = this.registerForm?.controls['register_password'].value!;

    const person = new Person(name, password);
    this.service.register(person).subscribe(
      (data) => {
        console.log(data)
        const person = new Person(data.user_name, data.user_password);
        person.user_id = data.user_id;
        this.sharedService.setPerson(person);

        this.router.navigate(['/items']);
      },
      (error) => {
        alert("No user found")
      }
    )
  }
}
