package com.data.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CHILDREN")
public class ChildrenEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer childId;
	
	private LocalDate dob;
	
	private String childName;
	
	private Integer childAge;
	
	private Long ssn;
	
	private Long caseNum;
}
