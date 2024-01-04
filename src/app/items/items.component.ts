import { Component, OnInit } from '@angular/core';
import { SharedService } from '../../SharedService';
import { Item } from '../Item';
import { DataServiceService } from '../data-service.service';
import { share } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrl: './items.component.css'
})
export class ItemsComponent implements OnInit{

  constructor(private sharedService: SharedService, private service: DataServiceService, private router: Router) {}

  items: Item[] = [];

  ngOnInit(): void {
    console.log(this.sharedService.getPerson())  
    this.getAllItems()
  }

  getAllItems() {
    this.service.getAllItems().subscribe(
      (data) => this.items = data
    )
  }

  addToCart(item_id: number) {
    let person = this.sharedService.getPerson();

    this.service.addItemToCart(person!.user_id, item_id).subscribe(
      (data) => {
        console.log(data)
        alert("Item Added")
      }
    )
  }
}
