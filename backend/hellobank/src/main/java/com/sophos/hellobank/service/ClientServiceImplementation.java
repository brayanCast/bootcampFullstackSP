package com.sophos.hellobank.service;


import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.hellobank.entity.Account;
import com.sophos.hellobank.entity.Client;
import com.sophos.hellobank.repository.ClientRepository;

@Service
public class ClientServiceImplementation implements ClientService{

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AccountService accountService;

    @Override
    public boolean ageClient(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears() >= 18;
        
    }

    //Metodo para crear todos los clientes
    @Override
    public Client createClient(Client client){
        Account account = new Account();
        List<Account> accountClient = new ArrayList<>();
        account.setClient(client);
        accountClient.add(account);
        return clientRepository.save(client);
    }

    //Metodo para obtener todos los clientes
    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
  
    //Metodo para consultar y modificar los clientes por Id
    @Override
    public Optional<Client> getClientById(int idClient) {
        return clientRepository.findById(idClient);
    }

    @Override
    public Client updateClient(Client client){
        
        boolean clientFound = true;
        for(Client existClient : getAllClients()){
            if(existClient.getIdClient() == client.getIdClient()){
                clientFound = true;
                existClient.setDocumentType(client.getDocumentType());
                existClient.setNumberDocument(client.getNumberDocument());//Puede que necesite condicional
                existClient.setNameClient(client.getNameClient());
                existClient.setLastNameClient(client.getLastNameClient());
                existClient.setEmailClient(client.getEmailClient());
                existClient.setBirthDate(client.getBirthDate());   
            }
        }
        if(!clientFound) getAllClients().add(client);
        return clientRepository.save(client);
    }

    @Override
    public boolean deleteClientById(int idClient){
            return getClientById(idClient).map(clients ->{
                clientRepository.deleteById(idClient);
                return true;
            }).orElse(false);

    }
    
    @Override
    public boolean isPresent(Account account){
        boolean resourceFound = false;
    for (Account currentAccount : accountService.getAllAccounts())
    if (Objects.equals(currentAccount.getStateAccount(), account.getStateAccount())) {
        resourceFound = true;
    }
    return resourceFound;
    }

}