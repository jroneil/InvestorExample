import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateComponent } from './create/create.component';
import { DetailsComponent } from './details/details.component';
import { HomeComponent } from './home/home.component';


const routes: Routes = [
  { path: 'client', redirectTo: 'client/home', pathMatch: 'full'},
  { path: 'client/home', component: HomeComponent },
  { path: 'client/details/:id', component: DetailsComponent },
  { path: 'client/create', component: CreateComponent },
  { path: 'client/update/:id', component: CreateComponent }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
