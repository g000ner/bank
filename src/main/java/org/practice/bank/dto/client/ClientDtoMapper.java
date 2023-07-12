package org.practice.bank.dto.client;

import org.practice.bank.model.Client;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ClientDtoMapper implements Function<Client, ClientDto> {
    @Override
    public ClientDto apply(Client client) {
        return new ClientDto(
                client.getId(),
                client.getFullName(),
                client.getPassportNumber(),
                client.getPassportSeria()
        );
    }
}
