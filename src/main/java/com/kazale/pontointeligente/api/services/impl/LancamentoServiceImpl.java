package com.kazale.pontointeligente.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.kazale.pontointeligente.api.entities.Lancamento;
import com.kazale.pontointeligente.api.repositories.LancamentoRepository;
import com.kazale.pontointeligente.api.services.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);

	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest) {
		log.info("Buscando lançamentos para o funcionário ID {}", funcionarioId);
		return this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
	}

	public List<Lancamento> buscarTodosPorFuncionarioId(Long funcionarioId) {
		log.info("Buscando todos os lançamentos para o funcionário ID {}", funcionarioId);
		return this.lancamentoRepository.findByFuncionarioIdOrderByDataDesc(funcionarioId);
	}
	
   /**
	*   Using @Cacheable
	*  If we annotate our bean by Spring @Cacheable annotation, it declares that it will be cached. 
	*  We need to provide cache name defined in ehcache.xml. In our example we have a cache named
	*  as empcache in ehcache.xml and we have provided this name in @Cacheable. When Spring will 
	*  hit the method for the first time, the result of that method will be cached and for same 
	*  argument value, Spring will not hit the method next time. Once the cache is expired, 
	*  then the Spring will hit the method again for the same argument value.
    */
	@Cacheable("lancamentoPorId")
	public Optional<Lancamento> buscarPorId(Long id) {
		log.info("Buscando um lançamento pelo ID {}", id);
		return this.lancamentoRepository.findById(id);
	}
	
	//Irá susbstiuir o valor do cache atual pelo cache informado.
	/**
     * This page will provide spring @CachePut annotation example using JavaConfig. This annotation is 
	 * used to put value in cache for the given cache name and key. In contrary to @Cacheable 
	 * annotation, the method annotated with @CachePut runs for every call and put results 
	 * in cache. @CachePut has elements such as cacheNames, value, condition, key, unless, 
	 * keyGenerator etc. To compute the key, method parameters are used by default, but a
	 * SpEL expression can be provided via the key attribute.
     * Now @CachePut is used as follows.
	 */
	@CachePut("lancamentoPorId")
	public Lancamento persistir(Lancamento lancamento) {
		log.info("Persistindo o lançamento: {}", lancamento);
		return this.lancamentoRepository.save(lancamento);
	}
	
	public void remover(Long id) {
		log.info("Removendo o lançamento ID {}", id);
		this.lancamentoRepository.deleteById(id);
	}

	@Override
	public Optional<Lancamento> buscarUltimoPorFuncionarioId(Long funcionarioId) {
		log.info("Buscando o último lançamento por ID de funcionário {}", funcionarioId);
		return Optional.ofNullable(
				this.lancamentoRepository.findFirstByFuncionarioIdOrderByDataCriacaoDesc(funcionarioId));
	}

}
