package com.odeivissonsantos.apicotacao.repositorys;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.odeivissonsantos.apicotacao.entitys.Cotacao;

@Repository
public interface CotacaoRepository extends PagingAndSortingRepository<Cotacao, Long>  {
	
	@RestResource(rel = "cotacao", path = "cotacao")
	Page<Cotacao> findAllBySimbolo(@Param("simbolo") String simbolo, Pageable page);

	Optional<Cotacao>findFirstBySimboloOrderByTimestampDesc(String teste);
	
}
