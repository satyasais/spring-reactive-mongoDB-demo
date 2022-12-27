package com.invoice.rsbcurd.reactive.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.invoice.rsbcurd.reactive.model.Invoice;

@Repository
public interface InvoiceRepository extends ReactiveMongoRepository<Invoice, Integer> {

}
