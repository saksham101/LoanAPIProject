package com.loan.dao;

import org.springframework.stereotype.Repository;

import com.loan.entities.LoanProducts;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductDao extends JpaRepository<LoanProducts, Integer>
{
	LoanProducts findById(int pid);
}
