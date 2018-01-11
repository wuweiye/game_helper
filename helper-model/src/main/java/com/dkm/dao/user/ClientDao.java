package com.dkm.dao.user;


import com.dkm.model.user.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDao extends JpaRepository<Client, Long > {


    Client findByClientSecret(String clientSecret);
}
