import { Component, OnInit } from '@angular/core';
import { ClientService } from '../client.service';
import { Client } from '../client';
import { Investor } from '../investor';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  clients: Client[] = [];
  selectedClient: Client;

  constructor(public clientService: ClientService) { }

  ngOnInit() {

    this.onReload();
  }
 onRemove(myid:string){
   console.log('remove button cliecked'+myid);
   this.clientService.delete(myid).subscribe(() => console.log("client deleted"));
   this.onReload();
 }
 onReload(){
  this.clientService.getAll().subscribe((data: Client[])=>{
    console.log(data);
    this.clients = data;
 });
 }
 OnSelectClient(item: any){

  this.selectedClient = item

}
}
