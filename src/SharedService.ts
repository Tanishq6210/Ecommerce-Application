import { Injectable } from '@angular/core';
import { Person } from './app/Person';
@Injectable({
  providedIn: 'root',
})
export class SharedService {
  person: Person | undefined;

  setPerson(person: Person) {
    this.person = person;
  }

  getPerson(): Person | undefined {
    return this.person;
  }
  constructor() { }
}