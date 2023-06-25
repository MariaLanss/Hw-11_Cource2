package pro.sky.hw11_cource2.services;

import pro.sky.hw11_cource2.models.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getMaxSalaryEmployee(int department);

    Employee getMinSalaryEmployee(int department);

    List<Employee> getAllEmployeesForDepartment(int department);

    Map<Integer, List<Employee>> getEmployeesByDepartments();
}