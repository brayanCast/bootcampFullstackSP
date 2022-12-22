package com.sophos.hellobank.service;

import static java.time.temporal.ChronoUnit.YEARS;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.hellobank.entity.Client;
import com.sophos.hellobank.repository.ClientRepository;


@Service
public class ClientServiceImplementation implements ClientService{

    @Autowired
    ClientRepository clientRepository;

    @Override
    public long ageClient(LocalDate birthDateClient, LocalDate creationDateCient) {
        return YEARS.between(birthDateClient, creationDateCient);
    }

    //Metodo para crear todos los clientes
    @Override
    public Client createClient(Client client){
           return clientRepository.save(client);
    }

    //Metodo para obtener todos los clientes
    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
  
    //Metodo ara consultar y modificar los clientes por Id
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
                existClient.setDocumentTypeClient(client.getDocumentTypeClient());
                existClient.setNumberDocumentClient(client.getNumberDocumentClient());
                existClient.setNameClient(client.getNameClient());
                existClient.setLastNameClient(client.getLastNameClient());
                existClient.setEmailClient(client.getEmailClient());
                existClient.setBirthDateClient(client.getBirthDateClient());

                existClient.getModificationDateClient();
            }
        }
        if(!clientFound) getAllClients().add(client);
        return clientRepository.save(client);

    }

    //Metodo para eliminar los clientes por Id
    @Override
    public boolean deleteClientById(int idClient) {
        return getClientById(idClient).map(client ->{
            clientRepository.deleteById(idClient);
            return true;
        }).orElse(false);
    }
}