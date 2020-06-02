package com.oneil.investment.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity(name="funds")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"investor"})
public class Fund extends BaseIdEntity {
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	
	
	@ManyToOne
	@JsonBackReference
	private Investor investor;
}
