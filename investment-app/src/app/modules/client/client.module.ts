import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ClientRoutingModule } from './client-routing.module';
import { HomeComponent } from './home/home.component';
import { DetailsComponent } from './details/details.component';
import { CreateComponent } from './create/create.component';
import { InvestorTableComponent } from './investor-table/investor-table.component';



@NgModule({
  declarations: [HomeComponent, DetailsComponent, CreateComponent, InvestorTableComponent],
  imports: [
    CommonModule,
    ClientRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class ClientModule { }
