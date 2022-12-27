package com.invoice.rsbcurd.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.invoice.rsbcurd.reactive.model.Invoice;
import com.invoice.rsbcurd.reactive.repo.InvoiceRepository;
import com.invoice.rsbcurd.reactive.service.impl.InvoiceServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
class InvoiceServiceImplTest {
	
	
	@InjectMocks
	private InvoiceServiceImpl invoiceServiceImpl;
	
	@Mock
	private InvoiceRepository invoiceRepository;
	
	@Test
	void saveOneInvoiceTest() {
		
		Invoice invoice = new Invoice(1,"InvioceTest","Invioce001",Double.MAX_VALUE);
		
		Mono<Invoice> invoiceMono = Mono.just(new Invoice(1,"InvioceTest","Invioce001",Double.MAX_VALUE));
		
		Mockito.when(invoiceRepository.save(Mockito.any(Invoice.class))).thenReturn(invoiceMono);
		
		Mono<Invoice> result=invoiceServiceImpl.saveInvoice(invoice);
		
		assertEquals(invoiceMono, result);
		
	}
	
	@Test
	void getOneInvoiceTest() {
		
		Invoice invoice = new Invoice(1,"InvioceTest","Invioce001",Double.MAX_VALUE);
		
		Mono<Invoice> invoiceMono = Mono.just(invoice);
		
		Mockito.when(invoiceRepository.findById(Mockito.anyInt())).thenReturn(invoiceMono);
		
		Mono<Invoice> result=invoiceServiceImpl.getOneInvoice(1);
		
		assertEquals(invoiceMono.block(), result.block());
		
	}
	
	@Test
	void findAllInvoicesTest() {
		
		Invoice invoice = new Invoice(1,"InvioceTest","Invioce001",Double.MAX_VALUE);
		Invoice invoice1= new Invoice(2,"InvioceTest1","Invioce002",Double.MAX_VALUE);
		
		Flux<Invoice> invoiceFlux = Flux.just(invoice,invoice1);
		
		Mockito.when(invoiceRepository.findAll()).thenReturn(invoiceFlux);
		
		Flux<Invoice> result=invoiceServiceImpl.findAllInvoices();
		
		StepVerifier.create(result).expectSubscription()
		.expectNext(new Invoice(1,"InvioceTest","Invioce001",Double.MAX_VALUE))
		.expectNext(new Invoice(2,"InvioceTest1","Invioce002",Double.MAX_VALUE))
		.verifyComplete();
		
	}
	
	
	@Test
	void deleteInvoiceTest() {
		
		Mockito.when(invoiceRepository.deleteById(Mockito.anyInt())).thenReturn(Mono.empty());
		
		Mono<Void> result=invoiceServiceImpl.deleteInvoice(1);
		
		assertEquals(Mono.empty(), result);
		
	}
	
	@Test
	void deleteInvoiceTestFallback() {
		
		Exception ex= new Exception();
		
		Mono<String> result=invoiceServiceImpl.monoFallback(new Exception());
		
		assertEquals(Mono.just("Recovered: " + ex.toString()).block(), result.block());
		
	}


}
