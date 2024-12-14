package com.data.dto;

import java.util.List;

import com.data.binding.ChildBinding;

import com.data.binding.EducationBinding;
import com.data.binding.IncomeBinding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Summary {
	
	private Integer appId;
	private IncomeBinding income;

	private EducationBinding eduaction;

	private List<ChildBinding> childrens;

	private String planName;

}
