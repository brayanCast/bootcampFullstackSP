package com.sophos.hellobank.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

import com.sophos.hellobank.entity.Account;
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
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        Optional<Client> existclient = Optional.empty();
        if(clientService.ageClient(client.getBirthDate()) && !existclient.isPresent()){
            client.setCreationDate(LocalDate.now());
            client.setModificationDate(null);
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

    @GetMapping("/list/{idClient}")
    public ResponseEntity<Client> getClientById(@PathVariable("idClient")int idClient){
        return clientService.getClientById(idClient).map(client -> new ResponseEntity<>(client, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/idClient")
    //@ResponseBody
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        try {
            clientService.updateClient(client);
            client.setModificationDate(LocalDateTime.now());
            return new ResponseEntity<Client>(client, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/list/{idClient}")
    public ResponseEntity<Client> deleteClientById(List<Account> account, @PathVariable("idClient") int idClient)throws Exception{
        
        if(clientService.deleteClientById(idClient)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }   
    }
}