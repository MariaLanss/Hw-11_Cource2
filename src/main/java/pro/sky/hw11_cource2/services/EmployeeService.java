package pro.sky.hw11_cource2.services;

import pro.sky.hw11_cource2.models.Employee;

import java.util.Map;

public interface EmployeeService {
    Map<String, Employee> index();

    Employee add(String firstName, String lastName, int department, int salary);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);
}