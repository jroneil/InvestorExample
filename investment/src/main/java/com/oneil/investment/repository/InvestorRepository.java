package com.oneil.investment.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oneil.investment.entity.Investor;

public interface InvestorRepository extends JpaRepository<Investor, Long> {
	@Query("SELECT u FROM Investors i WHERE i.client_id = ?1")
	List<Investor> findInvestorsByClientId(Long client_id);
}