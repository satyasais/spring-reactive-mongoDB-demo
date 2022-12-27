package com.invoice.rsbcurd.reactive.service;

import com.invoice.rsbcurd.reactive.model.Invoice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IInvoiceService {

	public Mono<Invoice> saveInvoice(final Invoice invoice);
	
	public Flux<Invoice> findAllInvoices();
	
	public Mono<Invoice> getOneInvoice(final Integer id);
	
	public Mono<Void> deleteInvoice(final Integer id);
}
