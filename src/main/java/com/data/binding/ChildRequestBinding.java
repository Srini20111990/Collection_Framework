package com.data.binding;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChildRequestBinding {
	private Long caseNum;
	private List<ChildBinding> childs;

}
