package com.loan.Controller;

import com.loan.Exception.ControllerException;
import com.loan.Exception.ServicesException;
import com.loan.entities.LoanProducts;
import com.loan.services.ProductService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//http://localhost:8082/swagger-ui.html

@RestController
@RequestMapping("/api")
public class Controller 
{
	@Autowired
	private ProductService productservice;
	
	@GetMapping("/home")
   public String home()
   {
	   return "Welcome to Loan Api";
   }
	
	//To get all the products
	
	@GetMapping("/loanproducts")
	public ResponseEntity<?> getProducts() throws ServicesException
	{
		List<LoanProducts> l;
		try {
			l = this.productservice.getProducts();
			return new ResponseEntity<>(l, HttpStatus.OK);
		}
		catch(ServicesException e) {
			ControllerException exp = new ControllerException(e.getCode(), e.getMessage());
			return new ResponseEntity<>(exp, HttpStatus.NOT_FOUND);
		}
		catch(Exception e) {
			ControllerException exp = new ControllerException("601", "Controller Exception");
			return new ResponseEntity<>(exp, HttpStatus.NOT_FOUND);
		}
		}
		
	
	@GetMapping("/loanproducts/{productId}")
	public ResponseEntity<?> getProduct(@PathVariable String productId) throws ServicesException
	{
		LoanProducts l;
		try {
			l = this.productservice.getProduct(Integer.parseInt(productId));
			return new ResponseEntity<>(l, HttpStatus.OK);
		}
		catch(ServicesException e) {
			ControllerException exp = new ControllerException(e.getCode(), e.getMessage());
			return new ResponseEntity<>(exp, HttpStatus.NOT_FOUND);
		}
		catch(Exception e) {
			ControllerException exp = new ControllerException("602", "Controller Exception");
			return new ResponseEntity<>(exp, HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@PostMapping(path="/loanproducts")
	public ResponseEntity<?> addProduct(@RequestBody LoanProducts product) throws ServicesException
	{
		LoanProducts l;
		try {
			l = this.productservice.addProduct(product);
			return new ResponseEntity<>(l, HttpStatus.OK);
		}
		catch(ServicesException e) {
			ControllerException exp = new ControllerException(e.getCode(), e.getMessage());
			return new ResponseEntity<>(exp, HttpStatus.NOT_FOUND);
		}
		catch(Exception e) {
			ControllerException exp = new ControllerException("603", "Controller Exception");
			return new ResponseEntity<>(exp, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/loanproducts")
	public ResponseEntity<?> updateProduct(@RequestBody LoanProducts product) throws ServicesException
	{
		LoanProducts l;
		try {
			l = this.productservice.updateProduct(product);
			return new ResponseEntity<>(l, HttpStatus.OK);
		}
		catch(ServicesException e) {
			ControllerException exp = new ControllerException(e.getCode(), e.getMessage());
			return new ResponseEntity<>(exp, HttpStatus.NOT_FOUND);
		}
		catch(Exception e) {
			ControllerException exp = new ControllerException("604", "Controller Exception");
			return new ResponseEntity<>(exp, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/emi/{productId}")
	public ResponseEntity<?> getEMI(@PathVariable("productId") String pid) throws NumberFormatException, ServicesException
	{
		double emi;
		try {
			emi = this.productservice.getEMI(Integer.parseInt(pid));
			return new ResponseEntity<>(emi, HttpStatus.OK);
		}
		catch(ServicesException e) {
			ControllerException exp = new ControllerException(e.getCode(), e.getMessage());
			return new ResponseEntity<>(exp, HttpStatus.NOT_FOUND);
		}
		catch(Exception e) {
			ControllerException exp = new ControllerException("605", "Controller Exception");
			return new ResponseEntity<>(exp, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/loanproducts/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable String productId) throws ServicesException
	{
		LoanProducts l;
		try {
			l = this.productservice.deleteProduct(Integer.parseInt(productId));
			return new ResponseEntity<>(l, HttpStatus.OK);
		} 
		catch (ServicesException e) {
			ControllerException exp = new ControllerException(e.getCode(), e.getMessage());
			return new ResponseEntity<>(exp, HttpStatus.NOT_FOUND);
		}	
		catch(Exception e) {
			ControllerException exp = new ControllerException("606", "Controller Exception");
			return new ResponseEntity<>(exp, HttpStatus.NOT_FOUND);
		}
	}
}