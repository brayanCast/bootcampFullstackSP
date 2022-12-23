package com.sophos.hellobank.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.hellobank.entity.Client;
import com.sophos.hellobank.repository.ClientRepository;
import com.sophos.hellobank.service.ClientService;


@RestController
@RequestMapping("/client")
public class ClientController{

    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;

    @PostMapping
    //@ResponseBody
    public ResponseEntity<Client> createClient(@RequestBody Client client, LocalDate birthDate_client, LocalDate creationDate_client){
       
        if(clientService.ageClient(birthDate_client, creationDate_client) >= 18){
        return new ResponseEntity<>(clientService.createClient(client), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Client>> getClients(){
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    } 

    @GetMapping("/list/{id_client}")
    public ResponseEntity<Client> getClientById(@PathVariable("id_client")int id_client){
        return clientService.getClientById(id_client).map(client -> new ResponseEntity<>(client, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    //@ResponseBody
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        try {
            clientService.updateClient(client);
            return new ResponseEntity<Client>(client, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/list/{id_client}")
    public ResponseEntity<Client> deleteClientById(@PathVariable("id_client") int id_client){
        
        if(clientService.deleteClientById(id_client)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }   
    }
}