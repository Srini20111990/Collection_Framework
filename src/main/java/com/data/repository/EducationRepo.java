package com.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.data.entity.EducationEntity;


public interface EducationRepo extends JpaRepository<EducationEntity,Integer> {
	public EducationEntity findByCaseNum(Long l);
}
