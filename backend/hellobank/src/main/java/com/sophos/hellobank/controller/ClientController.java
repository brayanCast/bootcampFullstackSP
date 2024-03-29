package com.sophos.hellobank.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.sophos.hellobank.enuminterface.StateAccount;
import com.sophos.hellobank.repository.ClientRepository;
import com.sophos.hellobank.repository.UserRepository;
import com.sophos.hellobank.service.AccountService;
import com.sophos.hellobank.service.ClientService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("{name_user}/client")
public class ClientController{

    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountService accountService;


    @PostMapping
    //@ResponseBody
    public ResponseEntity<Client> createClient(@RequestBody Client client, @PathVariable("name_user") String nameUser){
        Optional<Client> existclient = Optional.empty();
        if(clientService.ageClient(client.getBirthDate()) && !existclient.isPresent()){
            client.setCreationUser(String.format("%s", userRepository.findByNameUser(nameUser).getNameUser()));
            client.setCreationDate(LocalDate.now());
            client.setModificationDate(LocalDateTime.now());
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
    public ResponseEntity<Client> getClientById(@PathVariable("id_client")int idClient, List<Account> account){
        return clientService.getClientById(idClient).map(client -> new ResponseEntity<>(client, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping List<Account> getAccounts(@PathVariable int idClient){
        return clientRepository.findById(idClient).get().getAccount();
    }

    @PutMapping("/{id_client}")
    //@ResponseBody
    public ResponseEntity<Client> updateClient(@RequestBody Client client, @PathVariable("name_user") String nameUser){
        try {
            client.setModifyUser(String.format("%s", userRepository.findByNameUser(nameUser).getNameUser()));
            clientService.updateClient(client);
            client.setCreationDate(client.getCreationDate());
            client.setModificationDate(LocalDateTime.now());
            return new ResponseEntity<Client>(client, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/{id_client}")
    public ResponseEntity<Client> deleteClientById(Account account, @PathVariable("id_client") int idClient){
        ResponseEntity<Client> response = null;
        try {
            if(!clientService.isPresent(account)){
                StateAccount stateAccount = account.getStateAccount();
                switch(stateAccount){
                    case ACTIVE:
                    response = new ResponseEntity<>(HttpStatus.CONFLICT);

                    case INACTIVE:
                    response = new ResponseEntity<>(HttpStatus.CONTINUE);
    
                    case CANCELLED:
                    clientService.deleteClientById(idClient);
                    response = new ResponseEntity<>(HttpStatus.OK);
                }
                 response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            else{
                clientService.deleteClientById(idClient);
                response = new ResponseEntity<>(HttpStatus.OK);
            }
            return response;
        } catch (Exception e) {
            return response;
        }
    }
}