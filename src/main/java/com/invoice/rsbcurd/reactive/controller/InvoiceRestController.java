package com.invoice.rsbcurd.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.rsbcurd.constants.ApiConstants;
import com.invoice.rsbcurd.kafka.producer.service.KafkaProducer;
import com.invoice.rsbcurd.reactive.model.Invoice;
import com.invoice.rsbcurd.reactive.service.IInvoiceService;
import com.invoice.rsbcurd.swagger.SwaggerApiResponses;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiConstants.INVOICE)
@Slf4j
public class InvoiceRestController {


	@Autowired
	private IInvoiceService service;
	
//	@Autowired
//	private KafkaProducer kafkaProducer;
	
	@Operation(summary = ApiConstants.SAVING_INVOICE_DETAILS, description = ApiConstants.API_FOR_SAVING_INVOICE_DETAILS_INTO_DATABASE)
	@SwaggerApiResponses
	@PostMapping(ApiConstants.SAVE)
	public Mono<Invoice> saveOneInvoice(@RequestBody Invoice invoice){
		log.info("InvoiceRestController: saveOneInvoice invoice:{}",invoice);
//		kafkaProducer.sendMessage(invoice.toString());
		return service.saveInvoice(invoice);
	}
	
	@Operation(summary = ApiConstants.RETREIVING_ALL_INVOICE_DETAILS, description = ApiConstants.API_FOR_RETREIVING_ALL_INVOICE_DETAILS_FROM_DATABASE)
	@SwaggerApiResponses
	@GetMapping(ApiConstants.ALL_INVOICES)
	public Flux<Invoice> getAllInvoices(){
		log.info("InvoiceRestController: getAllInvoices" );
		return service.findAllInvoices();
	}
	
	@Operation(summary = ApiConstants.RETREIVING_ONE_INVOICE_DETAILS, description = ApiConstants.API_FOR_RETREIVING_ONE_INVOICE_DETAILS_FROM_DATABASE)
	@SwaggerApiResponses
	@GetMapping(ApiConstants.GET_ID)
	public Mono<Invoice> getOneInvoice(@PathVariable Integer id){
		log.info("InvoiceRestController: getOneInvoice id:{}",id );
		return service.getOneInvoice(id);
	}
	
	@Operation(summary = ApiConstants.DELETING_ONE_INVOICE_DETAILS, description = ApiConstants.API_FOR_DELETING_ONE_INVOICE_DETAILS_FROM_DATABASE)
	@SwaggerApiResponses
	@DeleteMapping(ApiConstants.DELETE_ID)
	public Mono<String> deleteInvoice(@PathVariable Integer id){
		service.deleteInvoice(id);
		log.info("InvoiceRestController: deleteInvoice id:{}",id );
		return Mono.just(ApiConstants.INVOICE_WITH_ID +id+ ApiConstants.DELETED);
	}
	
}
