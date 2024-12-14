package com.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.entity.IncomeEntity;

public interface IncomeRepo extends JpaRepository<IncomeEntity,Integer> {

	public IncomeEntity findByCaseNum(Long l);
	
}
