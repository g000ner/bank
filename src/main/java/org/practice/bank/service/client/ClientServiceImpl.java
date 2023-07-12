package org.practice.bank.service.client;

import lombok.extern.slf4j.Slf4j;
import org.practice.bank.dto.client.ClientDto;
import org.practice.bank.dto.client.ClientDtoMapper;
import org.practice.bank.model.Client;
import org.practice.bank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientDtoMapper clientDtoMapper;

    @Override
    public ClientDto getById(Long id) {
        return clientRepository.findById(id).map(clientDtoMapper).orElse(null);
    }

    @Override
    public Client selectById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientDto> getAll() {
        return clientRepository.findAll().stream().map(clientDtoMapper).collect(Collectors.toList());
    }
}
