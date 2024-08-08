package com.highradius.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.implementation.InvoiceDao;
import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateServlet() {
        super();
    }
    
    private InvoiceDao invoiceDao;

	public void init(ServletConfig config) throws ServletException {
		invoiceDao = new InvoiceDaoImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
	    response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
	    response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
	    response.addHeader("Access-Control-Max-Age", "1728000");
		
		int customerOrderId = Integer.parseInt(request.getParameter("customerOrderId"));
		String orderCurrency = request.getParameter("orderCurrency");
		int companyCode = Integer.parseInt(request.getParameter("companyCode"));
		String distributionChannel = request.getParameter("distributionChannel");
		
		try {
			Invoice invoice = new Invoice();

			invoice.setOrderCurrency(orderCurrency);
			invoice.setCompanyCode(companyCode);
			invoice.setDistributionChannel(distributionChannel);

			invoiceDao.updateInvoice(customerOrderId, invoice);
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
