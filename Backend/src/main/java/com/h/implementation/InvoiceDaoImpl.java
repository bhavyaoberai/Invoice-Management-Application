package com.highradius.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.highradius.connection.DatabaseConnection;
import com.highradius.model.Invoice;

public class InvoiceDaoImpl implements InvoiceDao {
	
	private static final String SELECT_ALL_INVOICES = "SELECT * FROM h2h_oap LIMIT ?,?";
	private static final String MAX_SERIAL_NUMBER = "SELECT MAX(Sl_no) AS MaxSerialNumber FROM h2h_oap";
	private static final String INSERT_INVOICE = "INSERT INTO h2h_oap"
			+ "(Sl_NO, CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, COMPANY_CODE,ORDER_CREATION_DATE, ORDER_CURRENCY, CUSTOMER_NUMBER, AMOUNT_IN_USD, ORDER_AMOUNT)"
			+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_INVOICE = "UPDATE h2h_oap SET ORDER_CURRENCY = ?, COMPANY_CODE = ?, DISTRIBUTION_CHANNEL = ? WHERE CUSTOMER_ORDER_ID = ?";
	private static final String DELETE_INVOICE = "DELETE FROM h2h_oap WHERE CUSTOMER_ORDER_ID = ?";
	private static final String SEARCH_INVOICE = "SELECT * FROM h2h_oap WHERE CUSTOMER_ORDER_ID = ?";
	
	private DatabaseConnection dbConn;
	
	public InvoiceDaoImpl() {
		dbConn = new DatabaseConnection();
	}
	
	@Override
	public List<Invoice> getInvoice(int start, int end) throws ClassNotFoundException {
		List<Invoice> invoices = new ArrayList<>();
		
		try (Connection con = dbConn.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SELECT_ALL_INVOICES);
			
			stmt.setInt(1, start);
			stmt.setInt(2, end);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Invoice invoice = new Invoice();
				
				invoice.setSlNo(rs.getInt("Sl_no"));
				invoice.setCustomerOrderId(rs.getInt("CUSTOMER_ORDER_ID"));
				invoice.setSalesOrg(rs.getInt("SALES_ORG"));
				invoice.setDistributionChannel(rs.getString("DISTRIBUTION_CHANNEL"));
				invoice.setCompanyCode(rs.getInt("COMPANY_CODE"));
				invoice.setOrderCreationDate(rs.getString("ORDER_CREATION_DATE"));
				invoice.setOrderCurrency(rs.getString("COMPANY_CODE"));
				invoice.setCustomerNumber(rs.getInt("CUSTOMER_NUMBER"));
				invoice.setAmountInUSD(rs.getDouble("AMOUNT_IN_USD"));
				invoice.setOrderAmount(rs.getDouble("ORDER_AMOUNT"));
				
				invoices.add(invoice);
			}
			
			stmt.close();
			con.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return invoices;
	}

	@Override
	public List<Invoice> searchInvoice(int customerOrderId) throws ClassNotFoundException {
		List<Invoice> invoices = new ArrayList<>();
		
		try (Connection con = dbConn.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SEARCH_INVOICE);
			
			stmt.setInt(1, customerOrderId);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Invoice invoice = new Invoice();
				
				invoice.setSlNo(rs.getInt("Sl_no"));
				invoice.setCustomerOrderId(rs.getInt("CUSTOMER_ORDER_ID"));
				invoice.setSalesOrg(rs.getInt("SALES_ORG"));
				invoice.setDistributionChannel(rs.getString("DISTRIBUTION_CHANNEL"));
				invoice.setCompanyCode(rs.getInt("COMPANY_CODE"));
				invoice.setOrderCreationDate(rs.getString("ORDER_CREATION_DATE"));
				invoice.setOrderCurrency(rs.getString("COMPANY_CODE"));
				invoice.setCustomerNumber(rs.getInt("CUSTOMER_NUMBER"));
				invoice.setAmountInUSD(rs.getDouble("AMOUNT_IN_USD"));
				invoice.setOrderAmount(rs.getDouble("ORDER_AMOUNT"));
				
				invoices.add(invoice);
			}
			
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return invoices;
	}

	@Override
	public void insertInvoice(Invoice invoice) throws ClassNotFoundException {
		try (Connection con = dbConn.getConnection()) {
			PreparedStatement getNextSerialNoStmt = con.prepareStatement(MAX_SERIAL_NUMBER);
			
			ResultSet rs = getNextSerialNoStmt.executeQuery();
			
			int nextSerialNumber = 1;
			
			while(rs.next()) {
				int maxSerialNumber = rs.getInt("MaxSerialNumber");
				nextSerialNumber = maxSerialNumber + 1;
			}
			
			invoice.setSlNo(nextSerialNumber);
			
			PreparedStatement stmt = con.prepareStatement(INSERT_INVOICE);
			
			stmt.setInt(1, invoice.getSlNo());
			stmt.setInt(2, invoice.getCustomerOrderId());
			stmt.setInt(3, invoice.getSalesOrg());
			stmt.setString(4, invoice.getDistributionChannel());
			stmt.setInt(5, invoice.getCompanyCode());
			stmt.setString(6, invoice.getOrderCreationDate());
			stmt.setString(7, invoice.getOrderCurrency());
			stmt.setInt(8, invoice.getCustomerNumber());
			stmt.setDouble(9, invoice.getAmountInUSD());
			stmt.setDouble(10, 0);
			
			stmt.executeUpdate();
			
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateInvoice(int customerOrderId, Invoice invoice) throws ClassNotFoundException {
		try (Connection con = dbConn.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(UPDATE_INVOICE);

			stmt.setString(1, invoice.getOrderCurrency());
			stmt.setInt(2, invoice.getCompanyCode());
			stmt.setString(3, invoice.getDistributionChannel());
			stmt.setInt(4, customerOrderId);

			stmt.executeUpdate();
			
			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteInvoice(int customerOrderId) throws ClassNotFoundException {
		try (Connection con = dbConn.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(DELETE_INVOICE);

			stmt.setInt(1, customerOrderId);

			stmt.executeUpdate();

			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
}