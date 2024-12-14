package com.data.service;

import java.util.List;

import com.data.binding.EducationBinding;
import com.data.binding.IncomeBinding;
import com.data.entity.ChildrenEntity;
import com.data.entity.DcCaseEntity;

public interface DataGetService {
	
	
	public DcCaseEntity getCase(Long caseId);
	public EducationBinding getEductionDetails(Long caseId);
	public IncomeBinding getIncomeDetails(Long caseId);
	public List<ChildrenEntity> getChildrenInfo(Long caseId);

}
