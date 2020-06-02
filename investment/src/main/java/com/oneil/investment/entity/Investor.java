package com.oneil.investment.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"client"})
@Table(name="investors")
public class Investor extends BaseIdEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	
	@ManyToOne
	@JsonBackReference
	private Client client;
	
	
	public Investor(String name, String email) {
		super();
		this.name = name;
		this.email = email;
		
	}
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "investor")
	private Set<Fund> funds=new HashSet<>();
	
	
	public Investor addFund(Fund fund){
	       fund.setInvestor(this);
	        this.funds.add(fund);
	        return this;
	    }
}
