import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {AppComponent }   from './app.component';
import {DatePipe} from "@angular/common";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS,HttpClientModule} from "@angular/common/http";
import {InterceptorService} from "./service/auth/interceptor.service";
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from "./app-routing.module";
import {MainPageComponent} from "./modules/main-page/main-page/main-page.component";
import {LoginPageComponent} from "./modules/login-page/login-page/login-page.component";
import {NotFoundComponent} from "./modules/not-found/not-found/not-found.component";
import {RegPageComponent} from "./modules/reg-page/reg-page/reg-page.component";
import {SearchPageComponent} from "./modules/search-page/search-page/search-page.component";
import {FooterBlockComponent} from "./modules/bars/footer-block/footer-block/footer-block.component";
import {HeaderBlockComponent} from "./modules/bars/header-block/header-block/header-block.component";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatInputModule} from "@angular/material/input";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatSliderModule} from "@angular/material/slider";
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {MatButtonModule} from "@angular/material/button";
import { MatRadioModule } from '@angular/material/radio';
import { MatCardModule } from '@angular/material/card';
import {ProfileComponent} from "./modules/profile/profile/profile.component";
import {LayoutModule} from "@angular/cdk/layout";
import {NewCollectionComponent} from "./modules/profile/profile/new-collection/new-collection.component";
import { MyItemsComponent } from './modules/profile/profile/items/my-items/my-items.component';
import { NewItemComponent } from './modules/profile/profile/items/new-item/new-item.component';
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatDividerModule} from "@angular/material/divider";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {MatPaginatorModule} from "@angular/material/paginator";
import { ViewItemComponent } from './modules/profile/profile/items/view-item/view-item.component';
import { CreateItemDialogComponent } from './modules/profile/profile/items/view-item/create-item-dialog/create-item-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatIconModule} from "@angular/material/icon";
import {MatRippleModule} from "@angular/material/core";
import {ScrollingModule} from "@angular/cdk/scrolling";
import {A11yModule} from "@angular/cdk/a11y";
import {MatBadgeModule} from "@angular/material/badge";
import {MatChipsModule} from "@angular/material/chips";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {AdminPageComponent} from "./modules/admin/admin-page/admin-page.component";
import { DragDropDirective } from './modules/profile/profile/new-collection/drag-drop.directive';
import {NgxSpinnerModule} from "ngx-spinner";
import {CdkTableModule} from "@angular/cdk/table";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatSidenavModule} from "@angular/material/sidenav";





@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        AppRoutingModule,
        MatInputModule,
        MatSelectModule,
        MatFormFieldModule,
        MatToolbarModule,
        MatSliderModule,
        MatSlideToggleModule,
        MatButtonModule,
        MatRadioModule,
        MatCardModule,
        LayoutModule,
        MatTableModule,
        MatSortModule,
        MatCheckboxModule,
        MatDividerModule,
        MatProgressBarModule,
        MatPaginatorModule,
        MatDialogModule,
        MatIconModule,
        MatRippleModule,
        ScrollingModule,
        A11yModule,
        MatBadgeModule,
        MatChipsModule,
        MatAutocompleteModule,
        NgxSpinnerModule,
        CdkTableModule,
        MatDatepickerModule,
        MatSidenavModule
    ],
    declarations: [
        AppComponent,
        MainPageComponent,
        LoginPageComponent,
        NotFoundComponent,
        RegPageComponent,
        SearchPageComponent,
        ProfileComponent,
        NewCollectionComponent,
        FooterBlockComponent,
        HeaderBlockComponent,
        MyItemsComponent,
        NewItemComponent,
        AdminPageComponent,
        ViewItemComponent,
        CreateItemDialogComponent,
        DragDropDirective
    ],
    providers:    [InterceptorService,{provide: HTTP_INTERCEPTORS,useClass: InterceptorService,multi: true}],
    bootstrap:    [ AppComponent ]
})
export class AppModule { }