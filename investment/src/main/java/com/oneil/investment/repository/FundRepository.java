package com.oneil.investment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oneil.investment.entity.Fund;

public interface FundRepository extends JpaRepository<Fund, Long> {
	
}