import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule} from '@angular/common/http';
import { RouterModule,Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';


import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { AdminComponent } from './admin/admin.component';
import { CouncellerComponent } from './counceller/counceller.component';
import { HomeComponent } from './home/home.component';


import { AdminServiceService} from './admin-service.service';
import { HomeServiceService} from './home-service.service';
import { UserServiceService} from './user-service.service';
import { CouncellerServiceService} from './counceller-service.service';

const appRoutes:Routes = [
  {path:'',component:HomeComponent},
  {path:'user',component:UserComponent},

];

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
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [UserServiceService,HomeServiceService,AdminServiceService,CouncellerServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
