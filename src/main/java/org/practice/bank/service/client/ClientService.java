package org.practice.bank.service.client;

import org.practice.bank.dto.client.ClientDto;
import org.practice.bank.model.Client;

import java.util.List;

public interface ClientService {

    ClientDto getById(Long id);
    void save(Client client);
    void delete(Long id);
    List<ClientDto> getAll();

}
