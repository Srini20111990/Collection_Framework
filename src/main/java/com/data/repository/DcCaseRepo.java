package com.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.entity.DcCaseEntity;

public interface DcCaseRepo extends JpaRepository<DcCaseEntity,Long> {

}
