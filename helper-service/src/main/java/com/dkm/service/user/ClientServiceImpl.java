package com.dkm.service.user;

import com.dkm.dao.user.ClientDao;
import com.dkm.model.user.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService  {

    @Autowired
    private ClientDao clientDao;

    @Override
    public Client createClient(Client client) {

        client.setClientId(UUID.randomUUID().toString());
        client.setClientSecret(UUID.randomUUID().toString());
        return clientDao.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        return clientDao.save(client);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientDao.delete(clientId);
    }

    @Override
    public Client findOne(Long clientId) {
        return clientDao.findOne(clientId);
    }

    @Override
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    @Override
    public Client findByClientId(String clientId) {
        return clientDao.findOne(Long.decode(clientId));
    }

    @Override
    public Client findByClientSecret(String clientSecret) {
        return clientDao.findByClientSecret(clientSecret);
    }
}
