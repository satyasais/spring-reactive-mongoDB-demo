package com.invoice.rsbcurd.constants;

import lombok.Generated;

@Generated
public class ApiConstants {
	
	 private ApiConstants() {
		    throw new IllegalStateException("ApiConstants class");
		  }

	public static final String INVOICE = "/invoice";
	public static final String LOGIN = "/login";
	public static final String SAVE = "/save";
	public static final String ALL_INVOICES = "/allInvoices";
	public static final String ALL_INVOICES_KAFKA = "/allInvoiceskafka";
	public static final String DELETE_ID = "/delete/{id}";
	public static final String GET_ID = "/get/{id}";
	public static final String DELETED = " deleted !";
	public static final String INVOICE_WITH_ID = "Invoice with id: ";
	public static final String API_FOR_DELETING_ONE_INVOICE_DETAILS_FROM_DATABASE = "API for Deleting One Invoice details from database";
	public static final String DELETING_ONE_INVOICE_DETAILS = "Deleting One Invoice details";
	public static final String API_FOR_RETREIVING_ONE_INVOICE_DETAILS_FROM_DATABASE = "API for Retreiving One Invoice details from database";
	public static final String RETREIVING_ONE_INVOICE_DETAILS = "Retreiving One Invoice details";
	public static final String API_FOR_RETREIVING_ALL_INVOICE_DETAILS_FROM_DATABASE = "API for Retreiving All Invoice details from database";
	public static final String RETREIVING_ALL_INVOICE_DETAILS = "Retreiving All Invoice details";
	public static final String API_FOR_SAVING_INVOICE_DETAILS_INTO_DATABASE = "API for Saving Invoice details into database";
	public static final String SAVING_INVOICE_DETAILS = "Saving Invoice details";
	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = (long)5*60*60;
    public static final String SIGNING_KEY = "devsai123r";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String TOPIC_NAME ="NewTopic";
    public static final String GROUP_ID ="group_id";
    
	public static final String CONTENT_FOR_USER_OR_ADMIN = "Content for user or admin";
	public static final String CONTENT_FOR_ADMIN = "Content for admin";
	public static final String CONTENT_FOR_USER = "Content for user";
	public static final String HAS_ROLE_USER_OR_HAS_ROLE_ADMIN = "hasRole('USER') or hasRole('ADMIN')";
	public static final String RESOURCE_USER_OR_ADMIN = "/resource/user-or-admin";
	public static final String HAS_ROLE_ADMIN = "hasRole('ADMIN')";
	public static final String RESOURCE_ADMIN = "/resource/admin";
	public static final String HAS_ROLE_USER = "hasRole('USER')";
	public static final String RESOURCE_USER = "/resource/user";
	
	public static final String REDIRECTED_URL = "redirected URL";
	public static final String API_FOR_ERROR_PAGE = "API For Error Page";
	public static final String LOGIN_PAGE = "Login Page";
	public static final String USER_PAGE = "User Page";
	public static final String ADMIN_PAGE = "Admin Page";
	public static final String ADMIN_OR_USER_PAGE = "Admin OR User Page";
	public static final String API_FOR_LOGIN_PAGE = "API For Login Page";
	public static final String API_FOR_USER_PAGE = "API For User Page";
	public static final String API_FOR_ADMIN_PAGE = "API For Admin Page";
	public static final String API_FOR_ADMIN_OR_USER_PAGE = "API For Admin OR User Page";
	public static final String API_FOR_REDIRECTED_URL = "API For redirected URL";
	public static final String ERROR_PAGE = "Error Page";
	public static final  String PATH = "/api"+ "/error";
	public static final String BACKWARD = "/";
	public static final String NO_MAPPING_FOUND = "No Mapping Found";
	public static final String[] PUBLIC_URLS = { "/login", "/user/register", "/user/resetpassword/**", "/user/image/**" };
	
}
