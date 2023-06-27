package pro.sky.hw11_cource2.services;

import org.springframework.stereotype.Service;
import pro.sky.hw11_cource2.DepartmentNotFoundException;
import pro.sky.hw11_cource2.models.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentsServiceImpl implements DepartmentService {
    private final EmployeeService employees;

    public DepartmentsServiceImpl(EmployeeService employees) {
        this.employees = employees;
    }

    @Override
    public Employee getMaxSalaryEmployee(int department) {
        return employees.index().values().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(DepartmentNotFoundException::new);
    }
    @Override
    public Employee getMinSalaryEmployee(int department) {
        return employees.index().values().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(DepartmentNotFoundException::new);
    }

    @Override
    public List<Employee> getAllEmployeesForDepartment(int department) {
        return employees.index().values().stream()
                .filter(e-> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesByDepartments(){
        return employees.index().values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public void raiseSalary(int increaseRate, int department) {
        employees.index().values().stream()
                .filter(e -> e.getDepartment() == department)
                .forEach(e -> e.setSalary((int) (e.getSalary() * ((float) increaseRate / 100 + 1.0f))));
    }

    public int calcTotalSalaryAMonth(int department) {
        return employees.index().values().stream()
                .filter(e -> e.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public double calcAverageSalary(int department) {
        return employees.index().values().stream()
                .filter(e -> e.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .average().orElseThrow(DepartmentNotFoundException::new);

    }
}
