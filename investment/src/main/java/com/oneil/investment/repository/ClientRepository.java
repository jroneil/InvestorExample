package com.oneil.investment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oneil.investment.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
}