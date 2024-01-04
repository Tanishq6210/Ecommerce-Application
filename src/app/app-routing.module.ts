import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ItemsComponent } from './items/items.component';
import { CartComponent } from './cart/cart.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'items', component: ItemsComponent},
  {path: 'cart', component: CartComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
