import { NgModule } from '@angular/core';

import {RouterModule, Routes} from "@angular/router";

import {MainPageComponent} from "./modules/main-page/main-page/main-page.component";
import {LoginPageComponent} from "./modules/login-page/login-page/login-page.component";
import {NotFoundComponent} from "./modules/not-found/not-found/not-found.component";
import {RegPageComponent} from "./modules/reg-page/reg-page/reg-page.component";
import {SearchPageComponent} from "./modules/search-page/search-page/search-page.component";
import {ProfileComponent} from "./modules/profile/profile/profile.component";
import {NewCollectionComponent} from "./modules/profile/profile/new-collection/new-collection.component";
import {MyItemsComponent} from "./modules/profile/profile/items/my-items/my-items.component";
import {NewItemComponent} from "./modules/profile/profile/items/new-item/new-item.component";
import {ViewItemComponent} from "./modules/profile/profile/items/view-item/view-item.component";
import {AdminPageComponent} from "./modules/admin/admin-page/admin-page.component";

const routes: Routes = [
  {path: '', component: MainPageComponent},
  {path: 'login', component: LoginPageComponent},
  {path: 'reg', component:RegPageComponent },
  {path: 'admin',component:AdminPageComponent},
  {path: 'search', component:SearchPageComponent },
  {path: 'search/:id', component:SearchPageComponent },
  {path: 'search/tag/:id',component:SearchPageComponent},
  {path: 'profile',component:ProfileComponent },
  {path: 'profile/col/:id', component:NewCollectionComponent},
  {path: 'profile/collect', component:NewCollectionComponent},
  {path: 'profile/collect/edit/:editId', component:NewCollectionComponent},
  {path: 'profile/:id',component:ProfileComponent },
  {path: 'item/:id',component:ViewItemComponent},
  {path: 'profile/collect/:id',component:MyItemsComponent},
  {path: 'profile/collect/:id/item/:itemId',component:NewItemComponent},
  {path: 'profile/collect/:id/new-item',component:NewItemComponent},
  {path: '**', component:NotFoundComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
