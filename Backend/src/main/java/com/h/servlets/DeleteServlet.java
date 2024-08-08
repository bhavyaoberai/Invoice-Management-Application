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

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteServlet() {
        super();
    }
    
    private InvoiceDao invoiceDao;

	public void init(ServletConfig config) throws ServletException {
		invoiceDao = new InvoiceDaoImpl();
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
	    response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
	    response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
	    response.addHeader("Access-Control-Max-Age", "1728000");
	    
	    int customerOrderId = Integer.parseInt(request.getParameter("customerOrderId"));
		
		try {
			invoiceDao.deleteInvoice(customerOrderId);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
