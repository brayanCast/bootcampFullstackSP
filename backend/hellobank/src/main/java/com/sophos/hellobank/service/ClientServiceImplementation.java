package com.sophos.hellobank.service;

import static java.time.temporal.ChronoUnit.YEARS;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.hellobank.entity.Client;
//import com.sophos.hellobank.entity.User;
import com.sophos.hellobank.repository.ClientRepository;


@Service
public class ClientServiceImplementation implements ClientService{

    @Autowired
    ClientRepository clientRepository;

    @Override
    public long ageClient(LocalDate birthDate_client, LocalDate creationDate_client) {
        return YEARS.between(birthDate_client, creationDate_client);
    }

    //Metodo para crear todos los clientes
    @Override
    public Client createClient(Client client){
        /*User user =new User();
        user.getDocumentNumber_user();
        user.getName_user();
        user.getLastName_user();
        */
        return clientRepository.save(client);
    }

    //Metodo para obtener todos los clientes
    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
  
    //Metodo para consultar y modificar los clientes por Id
    @Override
    public Optional<Client> getClientById(int id_client) {
        return clientRepository.findById(id_client);
    }

    @Override
    public Client updateClient(Client client){
        
        boolean clientFound = true;
        for(Client existClient : getAllClients()){
            if(existClient.getId_client() == client.getId_client()){
                clientFound = true;
                existClient.setDocumentType_client(client.getDocumentType_client());
                existClient.setNumberDocument_client(client.getNumberDocument_client());//Puede que necesite condicional
                existClient.setName_client(client.getName_client());
                existClient.setLastName_client(client.getLastName_client());
                existClient.setEmail_client(client.getEmail_client());
                existClient.setBirthDate_client(client.getBirthDate_client());

                existClient.getModificationDateClient();
            }
        }
        if(!clientFound) getAllClients().add(client);
        return clientRepository.save(client);
    }

    //Metodo para eliminar los clientes por Id
    @Override
    public boolean deleteClientById(int id_client) {
        return getClientById(id_client).map(client ->{
            clientRepository.deleteById(id_client);
            return true;
        }).orElse(false);
    }
}