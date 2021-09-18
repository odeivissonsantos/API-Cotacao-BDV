package com.odeivissonsantos.apicotacao.entitys;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity(name = "COTACAO")
public class Cotacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String simbolo;
	
	private Double valorAbertura;
	
	private Double  valorFechamento;
	
	private Date data;

}
