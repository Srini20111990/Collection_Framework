package com.data.binding;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChildBinding {
	
   private String childName;
	private LocalDate dob;
	private Integer childAge;
	private Long ssn;
	private Long caseNum;
	

}