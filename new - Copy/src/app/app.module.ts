import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule,Routes} from '@angular/router';
import { confirmValidator } from './home/validate-directive';

import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { AdminComponent } from './admin/admin.component';
import { CouncellerComponent } from './counceller/counceller.component';
import { HomeComponent } from './home/home.component';

import { AdminServiceService} from './admin-service.service';
import { HomeServiceService} from './home-service.service';
import { UserServiceService} from './user-service.service';
import { CouncellerServiceService} from './counceller-service.service';
import { UserMainbannerComponent } from './user-mainbanner/user-mainbanner.component';
import { UserNavbarComponent } from './user-navbar/user-navbar.component';
import { UserCheckstressComponent } from './user-checkstress/user-checkstress.component';

const appRoutes:Routes = [
  {path:'',component:HomeComponent},
  {path:'user/:id',component:UserComponent},
  {path:'counceller/:id',component:CouncellerComponent},
  {path:'admin/:id',component:AdminComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    AdminComponent,
    CouncellerComponent,
    HomeComponent,
    confirmValidator,
    UserMainbannerComponent,
    UserNavbarComponent,
    UserCheckstressComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [HomeServiceService,AdminServiceService,UserServiceService,CouncellerServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
