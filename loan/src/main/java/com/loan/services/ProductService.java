package com.loan.services;

import java.util.List;
import java.util.Optional;

import com.loan.Exception.ServicesException;
import com.loan.entities.LoanProducts;

public interface ProductService 
{
	public List<LoanProducts> getProducts() throws ServicesException;

	public LoanProducts addProduct(LoanProducts product) throws ServicesException;

	public LoanProducts getProduct(int parseInt) throws ServicesException;

	public LoanProducts updateProduct(LoanProducts product) throws ServicesException;
	
	public double getEMI(int i) throws ServicesException;

	public LoanProducts deleteProduct(int parseInt) throws ServicesException;
	
//	public void save(LoanProducts products);
//	
//	public List<LoanProducts> findAll();
//	
//	public LoanProducts findById(int id);
//	
//	public void update(LoanProducts product);
//	
//	public void delete(int id);

}
