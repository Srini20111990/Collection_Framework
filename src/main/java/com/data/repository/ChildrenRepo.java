package com.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.entity.ChildrenEntity;



public interface ChildrenRepo extends JpaRepository<ChildrenEntity,Integer>{
	public List<ChildrenEntity> findByCaseNum(Long l);
}
