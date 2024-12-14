package com.data.service;

import java.util.Map;

import com.data.binding.ChildBinding;
import com.data.binding.ChildRequestBinding;
import com.data.binding.EducationBinding;
import com.data.binding.IncomeBinding;
import com.data.binding.PlanSelctionBinding;
import com.data.dto.Summary;

public interface DataCollectionService {

	public Long loadCaseNum(Integer appId); //it may generate case number or 0

	public Map<Integer, String> getPlans();

	public Long savePlanSelection(PlanSelctionBinding planSelctionBinding);

	public Long saveIncomeData(IncomeBinding incomeBinding);

	public Long saveEducation(EducationBinding educationBinding);

	public Long saveChildernData(ChildRequestBinding childRequestBinding);

	public Summary getSummery(Long caseNum);
	

}
