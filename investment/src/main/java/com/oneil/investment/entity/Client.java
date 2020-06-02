package com.oneil.investment.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="clients")
public class Client extends BaseIdEntity  {

	private static final long serialVersionUID = 1L;

	
	private String clientName;
	private String description;
	
	public Client(String clientName, String description) {
		super();
		this.clientName = clientName;
		this.description = description;
	}

	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
	private Set<Investor> investors=new HashSet<>();

	public Client addInvestor(Investor investor){
       investor.setClient(this);
        this.investors.add(investor);
        return this;
    }


	

	
}
