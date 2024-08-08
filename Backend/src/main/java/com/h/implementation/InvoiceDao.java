package com.highradius.implementation;

import java.util.List;

import com.highradius.model.Invoice;

public interface InvoiceDao {
	List<Invoice> getInvoice(int start, int end) throws ClassNotFoundException;
	
	List<Invoice> searchInvoice(int customerOrderId) throws ClassNotFoundException;
	
	void insertInvoice(Invoice invoice) throws ClassNotFoundException;
	
	void updateInvoice(int customerOrderId, Invoice invoice) throws ClassNotFoundException;
	
	void deleteInvoice(int customerOrderId) throws ClassNotFoundException;
}
