package com.invoice.rsbcurd;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InvoiceServicesApplicationTests {

	@SuppressWarnings("static-access")
	@Test
	void contextLoads() {
		InvoiceServicesApplication invoiceServicesApplication = new InvoiceServicesApplication();
		String[] args = {"TEST"};
		invoiceServicesApplication.main(args);
		assertNotNull(invoiceServicesApplication);
	}

}
