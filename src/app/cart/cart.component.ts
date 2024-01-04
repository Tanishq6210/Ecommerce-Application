import { Component, OnInit, NgZone, ChangeDetectorRef } from '@angular/core';
import { Item } from '../Item';
import { DataServiceService } from '../data-service.service';
import { SharedService } from '../../SharedService';
import { Person } from '../Person';
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnInit{
  items: Item[] = []
  person: Person | undefined;
  constructor(private service: DataServiceService, private sharedService: SharedService, private cdr: ChangeDetectorRef) {
    this.person = sharedService.getPerson();
  }
  ngOnInit(): void {
    this.getCartItems();
  }

  getCartItems() {
    this.service.getCartItems(this.person!.user_id).subscribe(
      (data) => {
        console.log(data)
        this.items = data
      }
    )
  }

  removeFromCart(item_id: number) {
    this.service.removeFromCart(this.person!.user_id, item_id).subscribe(
      (data) => {
          this.getCartItems();
          alert(data);
          // this.cdr.detectChanges();
      }
    )
  }
}
