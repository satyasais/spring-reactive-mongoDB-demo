package com.invoice.rsbcurd.reactive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.rsbcurd.reactive.model.Invoice;
import com.invoice.rsbcurd.reactive.repo.InvoiceRepository;
import com.invoice.rsbcurd.reactive.service.IInvoiceService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class InvoiceServiceImpl implements IInvoiceService {
	
	private static final String BACKEND_C = "backendC";
	
	@Autowired
	private InvoiceRepository repo;
	
	public Mono<Invoice> saveInvoice(Invoice invoice){
		log.info("InvoiceServiceImpl: saveOneInvoice invoice:{}",invoice);
		return repo.save(invoice);
	}
	
	public Flux<Invoice> findAllInvoices(){
		log.info("InvoiceServiceImpl: findAllInvoices");
		return repo.findAll().switchIfEmpty(Flux.empty());
	}
	
	public Mono<Invoice> getOneInvoice(Integer id){
		log.info("InvoiceServiceImpl: getOneInvoice id:{}",id);
		return repo.findById(id).switchIfEmpty(Mono.empty());
	}
	 @CircuitBreaker(name = BACKEND_C, fallbackMethod = "monoFallback")
	public Mono<Void> deleteInvoice(Integer id){
		log.info("InvoiceServiceImpl: deleteInvoice id:{}",id);
		return repo.deleteById(id);
	}
	
	 public Mono<String> monoFallback(Exception ex) {
	        return Mono.just("Recovered: " + ex.toString());
	    }

}
