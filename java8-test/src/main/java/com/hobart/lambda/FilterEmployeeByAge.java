package com.hobart.lambda;

import com.hobart.model.Employee;

public class FilterEmployeeByAge implements MyPredicate<Employee> {

	@Override
	public boolean test(Employee t) {
		return t.getAge()>35;
	}

}
