package com.hobart.lambda;

import com.hobart.model.Employee;

public class FilterEmployeeBySalary implements MyPredicate<Employee>{

	@Override
	public boolean test(Employee t) {
		return t.getSalary() > 5000;
	}

}
