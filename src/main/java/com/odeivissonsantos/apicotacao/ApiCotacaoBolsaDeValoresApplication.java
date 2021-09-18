package com.odeivissonsantos.apicotacao;

import java.util.Date;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.odeivissonsantos.apicotacao.entitys.Cotacao;
import com.odeivissonsantos.apicotacao.repositorys.CotacaoRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@EnableScheduling
@SpringBootApplication
public class ApiCotacaoBolsaDeValoresApplication {

	@Autowired
	private CotacaoRepository cotacaoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiCotacaoBolsaDeValoresApplication.class, args);
		
	}
	
	@Scheduled(fixedDelay = 1000)
	public void gerarData() {
		log.info(cotacaoRepository.findFirstBySimboloOrderByTimestampDesc("TESTE")
				.map(this::gerarNovaData)
				.orElseGet(this::inicializeData));
	}
	
	private Cotacao gerarNovaData() {
		return cotacaoRepository.save(Cotacao.builder()
				.simbolo("TESTE")
				.valorAbertura(0.222222)
				.valorFechamento(0.222222)
				.data(new Date())
				.build());
	}
	
	private Cotacao inicializeData(Cotacao cotacao) {
		return cotacaoRepository.save(Cotacao.builder()
				.simbolo(cotacao.getSimbolo())
				.valorAbertura(cotacao.getValorAbertura() * new RandomDataGenerator().nextUniform(-0.10, 0.10))
				.valorFechamento(cotacao.getValorFechamento()  * new RandomDataGenerator().nextUniform(-0.10, 0.10))
				.data(new Date())
				.build());
	}

	private <U> U gerarNovaData(Cotacao cotacao1) {
		return null;
	}

}
