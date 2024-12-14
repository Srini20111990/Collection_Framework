package com.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.binding.EducationBinding;
import com.data.binding.IncomeBinding;
import com.data.entity.ChildrenEntity;
import com.data.entity.DcCaseEntity;
import com.data.service.DataGetService;

@RestController
@RequestMapping("/getdata")
public class DataGetController {
	
	
	@Autowired
	private DataGetService dataGetService;
	
	@GetMapping("/case/{caseId}")
	public ResponseEntity<DcCaseEntity> getDcData(@PathVariable Long caseID){
		DcCaseEntity case1 = dataGetService.getCase(caseID);
		
		return new ResponseEntity<>(case1,HttpStatus.OK);
	}
	
	@GetMapping("/education/{caseId}")
	public ResponseEntity<EducationBinding> getEducationData(@PathVariable Long caseID){
               EducationBinding eductionDetails = dataGetService.getEductionDetails(caseID);
		
		return new ResponseEntity<>(eductionDetails,HttpStatus.OK);
	
	}

	
	@GetMapping("/income/{caseId}")
	public ResponseEntity<IncomeBinding> getIncomeData(@PathVariable Long caseID){
		 IncomeBinding incomeDetails = dataGetService.getIncomeDetails(caseID);
			
			return new ResponseEntity<>(incomeDetails,HttpStatus.OK);
		
	}

	
	
	@GetMapping("/child/{caseId}")
	public ResponseEntity <List<ChildrenEntity>> getChildrenData(@PathVariable Long caseID){
		List<ChildrenEntity> childrenInfo = dataGetService.getChildrenInfo(caseID);
		return new ResponseEntity<>(childrenInfo,HttpStatus.OK);
	}
}
