import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from './client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private baseURL = "http://localhost:8080/{nameUser}/client";

  constructor(private httpClient : HttpClient) {  }

  getClientList():Observable<Client[]>{
    return this.httpClient.get<Client[]>(`${this.baseURL}`)};

}
