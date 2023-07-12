package org.practice.bank.rest;

import org.practice.bank.dto.client.ClientDto;
import org.practice.bank.model.Client;
import org.practice.bank.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/clients")
public class ClientRestControllerV1 {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> getClient(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ClientDto client = clientService.getById(id);

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }


    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> saveClient(@RequestBody @Valid Client client) {
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        clientService.save(client);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> updateClient(@RequestBody @Valid Client client) {
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        clientService.save(client);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> deleteClient(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ClientDto client = clientService.getById(id);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientDto>> getAllClients() {
        List<ClientDto> clients = clientService.getAll();

        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
}
