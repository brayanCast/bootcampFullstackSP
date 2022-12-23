package com.sophos.hellobank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.sophos.hellobank.entity.Client;

public interface ClientService {
    public long ageClient(LocalDate birthDateClient, LocalDate creationDateCient);
    public Client createClient(Client client);
    public List<Client> getAllClients();
    public Optional<Client> getClientById(int idClient);
    public Client updateClient(Client client);
    public boolean deleteClientById(int idClient);
}