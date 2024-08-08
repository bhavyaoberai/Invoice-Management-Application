package com.highradius.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.highradius.implementation.InvoiceDao;
import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

@WebServlet("/DataLoadingServlet")
public class DataLoadingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public DataLoadingServlet() {
        super();
    }
    
    private InvoiceDao invoiceDao;

	public void init(ServletConfig config) throws ServletException {
		invoiceDao = new InvoiceDaoImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
	    response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
	    response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
	    response.addHeader("Access-Control-Max-Age", "1728000");

		int start = Integer.parseInt(request.getParameter("start"));
		int end = Integer.parseInt(request.getParameter("end"));

		PrintWriter out = response.getWriter();

		Gson gson = new Gson();

		String jsonResponse = new String();
		
		try {
			List<Invoice> invoiceList = invoiceDao.getInvoice(start, end);	
			jsonResponse = gson.toJson(invoiceList);

			out.print(jsonResponse);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
