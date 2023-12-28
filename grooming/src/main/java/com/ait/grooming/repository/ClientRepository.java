package com.ait.grooming.repository;

import com.ait.grooming.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>  {

}