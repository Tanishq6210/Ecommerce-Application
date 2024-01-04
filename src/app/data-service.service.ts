import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Person } from './Person';
import { Item } from './Item';
@Injectable({
  providedIn: 'root'
})
export class DataServiceService {
  url = "http://localhost:8080/ecommerce"
  constructor(private http: HttpClient) { }

  login(user_name: string, user_password: string): Observable<Person> {
    let qparams: any = {};
    if(user_name.length > 0) {
      qparams['user_name'] = user_name;
    }

    return this.http.get<Person>(`${this.url}/login`, {params: qparams})
  }

  register(person: Person): Observable<Person> {
    return this.http.post<Person>(`${this.url}/register`, person);
  }

  getAllItems(): Observable<Item[]> {
    return this.http.get<Item[]>(`${this.url}/items`);
  }

  addItemToCart(user_id: number, item_id: number): Observable<Item> {
    console.log("user id -", user_id)
    console.log("item id - ", item_id)
    return this.http.post<Item>(`${this.url}/users/${user_id}/items/${item_id}/save`, {});
  }

  getCartItems(user_id: number): Observable<Item[]> {

    return this.http.get<Item[]>(`${this.url}/users/${user_id}/items`);
  }

  removeFromCart(user_id: number, item_id: number) {
    return this.http.delete(`${this.url}/users/${user_id}/items/${item_id}/remove`, {responseType : 'text'});
  }
}
