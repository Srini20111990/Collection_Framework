package com.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.data.binding.ChildBinding;
import com.data.binding.ChildRequestBinding;
import com.data.binding.EducationBinding;
import com.data.binding.IncomeBinding;
import com.data.binding.PlanSelctionBinding;
import com.data.config.CitizenFeignClient;
import com.data.config.PlansFeignClient;
import com.data.dto.Summary;
import com.data.entity.ChildrenEntity;
import com.data.entity.DcCaseEntity;
import com.data.entity.EducationEntity;
import com.data.entity.IncomeEntity;
import com.data.repository.ChildrenRepo;
import com.data.repository.DcCaseRepo;
import com.data.repository.EducationRepo;
import com.data.repository.IncomeRepo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@Service
public class DataCollectionServiceImpl implements DataCollectionService,DataGetService {
	
	private ChildrenRepo childrenRepo;
	private DcCaseRepo dcCaseRepo;
	private EducationRepo educationRepo;
	private IncomeRepo incomeRepo;
	
	private CitizenFeignClient citizenFeignClient;
	private PlansFeignClient plansFeignClient;

	public DataCollectionServiceImpl(ChildrenRepo childrenRepo, DcCaseRepo dcCaseRepo, EducationRepo educationRepo,
			IncomeRepo incomeRepo, CitizenFeignClient citizenFeignClient, PlansFeignClient plansFeignClient) {
		
		this.childrenRepo = childrenRepo;
		this.dcCaseRepo = dcCaseRepo;
		this.educationRepo = educationRepo;
		this.incomeRepo = incomeRepo;
		this.citizenFeignClient = citizenFeignClient;
		this.plansFeignClient = plansFeignClient;
	}

	@Override
	public Long loadCaseNum(Integer appId) {
		ResponseEntity<Boolean> response = citizenFeignClient.verifyCitizen(appId);
		
		 // Check if the response is successful and has a valid body
	    if (response.getStatusCode().is2xxSuccessful() && Boolean.TRUE.equals(response.getBody())) {
	        // Create and save the case entity if the citizen is verified
	        DcCaseEntity dcEntity = new DcCaseEntity();
	        dcEntity.setAppId(appId);
	        DcCaseEntity savedEntity = dcCaseRepo.save(dcEntity);
	        return savedEntity.getCaseNum();
	    } else {
	        // Citizen not verified, return 0L or handle the case as per requirements
	        return 0L;
	    }
		
	}

	@Override
	public Map<Integer, String> getPlans() {
		Map<Integer,String> planCategory = plansFeignClient.planNames().getBody();
		return planCategory;
	}

	@Override
	public Long savePlanSelection(PlanSelctionBinding planSelctionBinding) {
		Optional<DcCaseEntity> byId = dcCaseRepo.findById(planSelctionBinding.getCaseNum());
		
		if(byId.isPresent()) {
			DcCaseEntity dcCaseEntity = byId.get();
			dcCaseEntity.setPlanId(planSelctionBinding.getPlanId());
			DcCaseEntity save = dcCaseRepo.save(dcCaseEntity);
			 return save.getCaseNum();
		}
//		return dcCaseRepo.findById(planSelctionBinding.getCaseNum())
//		.map(entity->{
//			entity.setPlanId(planSelctionBinding.getPlanId());
//			DcCaseEntity save = dcCaseRepo.save(entity);
//			return save.getCaseNum();
//		}).orElse(0l);
		
		return 0L;
	}

	@Override
	public Long saveIncomeData(IncomeBinding incomeBinding) {
		Optional<DcCaseEntity> byId = dcCaseRepo.findById(incomeBinding.getCaseNum());
		if(byId.isPresent()) {
			IncomeEntity incomeEntity=new IncomeEntity();
			BeanUtils.copyProperties(incomeBinding, incomeEntity);
			IncomeEntity save = incomeRepo.save(incomeEntity);
			return save.getCaseNum();
		}else
		{
			return 0l;
		}
		
	}

	@Override
	public Long saveEducation(EducationBinding educationBinding) {
		Optional<DcCaseEntity> byId = dcCaseRepo.findById(educationBinding.getCaseNum());
		if(byId.isPresent()) {
			EducationEntity educationEntity=new EducationEntity();
			BeanUtils.copyProperties(educationBinding, educationEntity);
			EducationEntity save = educationRepo.save(educationEntity);
			return save.getCaseNum();
			
		}else {
			return 0l;
		}
		
	}

	@Override
	public Long saveChildernData(ChildRequestBinding childRequestBinding) {
		
		Optional<DcCaseEntity> byId = dcCaseRepo.findById(childRequestBinding.getCaseNum());
		if(byId.isPresent()) {
			List<ChildBinding> childs=childRequestBinding.getChilds();
			Long caseNum = childRequestBinding.getCaseNum();
			for(ChildBinding childBinding:childs)
			{
				ChildrenEntity entity=new ChildrenEntity();
				BeanUtils.copyProperties(childBinding, entity);
				entity.setCaseNum(caseNum);
				childrenRepo.save(entity);
			}
			return childRequestBinding.getCaseNum();
			
		}else {
			return 0l;
		}
	}

	@Override
	public Summary getSummery(Long caseNum) {
		String planName="";
		Optional<DcCaseEntity> byId = dcCaseRepo.findById(caseNum);
	    
	
		if(byId.isPresent()) {
			List<ChildrenEntity> childrenEntity = childrenRepo.findByCaseNum(caseNum);
			EducationEntity educationEntity = educationRepo.findByCaseNum(caseNum);
			IncomeEntity incomeEntity = incomeRepo.findByCaseNum(caseNum);
			Map<Integer,String> plans = getPlans();
			String name = plans.get(byId.get().getPlanId());
			planName=name;
		}
		Summary summary=new Summary();
		summary.setPlanName(planName);
		summary.setAppId(byId.get().getAppId());
		
		EducationBinding educationBinding =new EducationBinding();
		BeanUtils.copyProperties(educationEntity, educationBinding);
		summary.setEduaction(educationBinding);
		
		IncomeBinding incomeBinding=new IncomeBinding();
		BeanUtils.copyProperties(incomeEntity, incomeBinding);
		ModalMapper   
		summary.setIncome(incomeBinding);
		
		List<ChildBinding> childs=new ArrayList<>();
		for(ChildrenEntity entity:childrenEntity)
		{
			ChildBinding childBinding=new ChildBinding();
			BeanUtils.copyProperties(entity, childBinding);
			childs.add(childBinding);
		}
		summary.setChildrens(childs);
		
		return summary;
		
	}

	@Override
	public DcCaseEntity getCase(Long caseId) {
		Optional<DcCaseEntity> byId = dcCaseRepo.findById(caseId);
		if(byId.isPresent()) {
			DcCaseEntity dcCaseEntity = byId.get();
			return dcCaseEntity;
		}else
		return null;
	}

	@Override
	public EducationBinding getEductionDetails(Long caseId) {
		  EducationEntity byCaseNum = educationRepo.findByCaseNum(caseId);
		  EducationBinding eb=new EducationBinding();
		  BeanUtils.copyProperties(byCaseNum, eb);
		  return eb;
		
	}

	@Override
	public IncomeBinding getIncomeDetails(Long caseId) {
		IncomeEntity byCaseNum = incomeRepo.findByCaseNum(caseId);
		IncomeBinding ib=new IncomeBinding();
		BeanUtils.copyProperties(byCaseNum, ib);
		
		return ib;
	}

	@Override
	public List<ChildrenEntity> getChildrenInfo(Long caseId) {
		List<ChildrenEntity> byCaseNum = childrenRepo.findByCaseNum(caseId);
	
		return byCaseNum;
	}
	
	
	
	

}
