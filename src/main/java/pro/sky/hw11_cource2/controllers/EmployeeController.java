package pro.sky.hw11_cource2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.hw11_cource2.models.Employee;
import pro.sky.hw11_cource2.services.EmployeeService;
import pro.sky.hw11_cource2.services.EmployeeServiceImpl;

import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeService = employeeServiceImpl;
    }

    @GetMapping
    public Map<String, Employee> index(){
        return employeeService.index();
    }

    @GetMapping("/add")
    public Employee add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                        @RequestParam("department") Integer department, @RequestParam("salary") Integer salary){
        return employeeService.add(firstName, lastName, department, salary);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        return employeeService.remove(firstName, lastName);
    }
    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        return employeeService.find(firstName, lastName);
    }

}