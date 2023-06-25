package pro.sky.hw11_cource2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.hw11_cource2.models.Employee;
import pro.sky.hw11_cource2.services.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    private final DepartmentService departmentService;

    public DepartmentsController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getMaxSalaryEmpForDepartment(@RequestParam("departmentId") Integer department) {
        return departmentService.getMaxSalaryEmployee(department);
    }

    @GetMapping("/min-salary")
    public Employee getMinSalaryEmpForDepartment(@RequestParam("departmentId") Integer department) {
        return departmentService.getMinSalaryEmployee(department);
    }

    @GetMapping(value = "/all", params = "departmentId")
    public List<Employee> getEmployeesForDepartments(@RequestParam Integer departmentId) {
        return departmentService.getAllEmployeesForDepartment(departmentId);

    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> getEmployeesByDepartments() {
        return departmentService.getEmployeesByDepartments();
    }
}