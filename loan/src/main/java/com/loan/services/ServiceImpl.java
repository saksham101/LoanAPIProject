package com.loan.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.loan.Exception.ServicesException;
import com.loan.dao.ProductDao;
import com.loan.entities.LoanProducts;

@Service
public class ServiceImpl implements ProductService
{

	//List<LoanProducts> list;

	public ServiceImpl() {
//		list = new ArrayList<>();
//		list.add(new LoanProducts(1, "Saksham Garg", 5000, 5, 1.5, true, "Saksham Garg", "10-10-2010", "", ""));
//		list.add(new LoanProducts(2, "ramesh", 5000, 2, 5.5, true, "Saksham Garg", "10-10-2010", "", ""));
//		list.add(new LoanProducts(3, "suresh", 5000, 4, 1, false, "Saksham Garg", "10-10-2010", "", ""));
		
	}
	
	@Autowired
	private ProductDao productDao;


	@Override
	public List<LoanProducts> getProducts() throws ServicesException {
		List<LoanProducts> l = null;
		// TODO Auto-generated method stub
		//return  (List<LoanProducts>) hashOperation.entries("Product");
		//return list;
		try {
			l = productDao.findAll();
		}
		catch(Exception e) {
			throw new ServicesException("901", "Something went wrong!!");
		}
		if(l.isEmpty()) {
			throw new ServicesException("801", "Database is Empty!!");
		}
		return l;
		}
	
	@Override
	public LoanProducts getProduct(int productId) throws ServicesException {
		// TODO Auto-generated method stub
		LoanProducts l = null;
//		for (LoanProducts loanProducts : list) {
//			if(loanProducts.getProductId()==productId) {
//				lp = loanProducts;
//				break;
//			}
//		}
//		System.out.println(lp);
//		return lp;
		try {
			l = productDao.findById(productId);
		}
		catch(Exception e) {
			throw new ServicesException("902", "Something went wrong!!");
		}
		if(l == null) {
			throw new ServicesException("802", "Loan Product not Found!!");
		}
		return l;
		
	}


	@Override
	public LoanProducts addProduct(LoanProducts product) throws ServicesException {
		// TODO Auto-generated method stub
//		list.add(product);
//		return product;
		LoanProducts l = null;
		try {
			l = productDao.findById(product.getProductId());
		}
		catch(Exception e) {
			throw new ServicesException("903", "Something  went wrong!!");
		}
		if(l != null) {
			throw new ServicesException("803", "Product already Exist!!");
		}
		productDao.save(product);
		return l;
	}


	@Override
	public LoanProducts updateProduct(LoanProducts product) throws ServicesException 
	{
		// TODO Auto-generated method stub
//		list.forEach(e->{
//			if(e.getProductId() == product.getProductId()) {
//				e.setUpdatedBy(product.getUpdatedBy());
//				e.setUpdatedOn(product.getUpdatedOn());
//			}
//		});
//		return product;
		LoanProducts l = null;
		try {
			l = productDao.findById(product.getProductId());
			productDao.save(product);
		}
		catch(Exception e) {
			throw new ServicesException("904", "Something  went wrong!!");
		}
		if(l == null) {
			throw new ServicesException("804", "Product already Exist!!");
		}
		return l;
	}



    @Override
	public double getEMI(int productId) throws ServicesException {
		// TODO Auto-generated method stub
    	LoanProducts l;
    	try {
			l = productDao.findById(productId);
		}
		catch(Exception e) {
			throw new ServicesException("905", "Something went wrong!!");
		}
		if(l == null) {
			throw new ServicesException("805", "Loan Product not Found!!");
		}
		System.out.println("EMI will be: ");
		double emi=0.0;
		int principal=l.getPrincipalAmount();
		int tenure=l.getTenure();
		double interestRate=l.getInterestRate()/100;
		if(l!=null)
		{
			emi=principal*interestRate*(1+interestRate)*tenure/((1+interestRate)*tenure-1);
		}
		return emi;
	}

	@Override
	public LoanProducts deleteProduct(int parseInt) throws ServicesException {
			// TODO Auto-generated method stub
			//list=this.list.stream().filter(e->e.getProductId()!=parseInt).collect(Collectors.toList());
		LoanProducts l = null;
			try {
				productDao.deleteById(parseInt);
				l = this.getProduct(parseInt);
			}
			catch(Exception e) {
				throw new ServicesException("906", "Something went wrong!!");
			}
			if(l == null) {
				throw new ServicesException("806", "Product not found!!");
			}
			return l;
		}
}