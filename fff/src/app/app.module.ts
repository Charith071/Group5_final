import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import { AppComponent } from './app.component';
import { RouterModule} from '@angular/router';
import { UserComponent } from './user/user.component';
import { AdminComponent } from './admin/admin.component';
import { CouncellerComponent } from './counceller/counceller.component';
import { HomeComponent } from './home/home.component';
import { AdminServiceService} from './admin-service.service';
import { HomeServiceService} from './home-service.service';
import { UserServiceService} from './user-service.service';
import { CouncellerServiceService} from './counceller-service.service';


@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    AdminComponent,
    CouncellerComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path:"admin",component:AdminComponent},
      {path:"user",component:UserComponent},
      {path:"counceller",component:CouncellerComponent},
      {path:"",component:HomeComponent}
    ])
  ],
  providers: [UserServiceService,HomeServiceService,AdminServiceService,CouncellerServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }



