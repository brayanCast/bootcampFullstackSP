import { ClientService } from './../client.service';
import { Client } from './../client';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit{
  clients : Client[];

  constructor(private clientService : ClientService, private router : Router){}

  ngOnInit(): void {
    this.getClient();
  }

  private getClient(){
    this.clientService.getClientList().subscribe(fact => {
      this.clients = fact;
    });
  }
}
