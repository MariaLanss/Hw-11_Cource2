package pro.sky.hw11_cource2.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.hw11_cource2.DepartmentNotFoundException;
import pro.sky.hw11_cource2.EmployeeAlreadyAddedException;
import pro.sky.hw11_cource2.EmployeeNotFoundException;
import pro.sky.hw11_cource2.EmployeeWrongDataException;
import pro.sky.hw11_cource2.models.Employee;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        employees = new HashMap<>();
        Employee e1 = new Employee("Татьяна", "Адаменко", 1, 10000);
        employees.put(generateKey(e1), e1);
        Employee e2 = new Employee("Рената", "Андреева", 1, 20000);
        employees.put(generateKey(e2), e2);
        Employee e3 = new Employee("Наталья", "Баринова", 2, 25000);
        employees.put(generateKey(e3), e3);
        Employee e4 = new Employee("Надежда", "Васильева", 2, 30000);
        employees.put(generateKey(e4), e4);
        Employee e5 = new Employee("Мария", "Грачева", 3, 35000);
        employees.put(generateKey(e5), e5);
        Employee e6 = new Employee("Елена", "Денисова", 3, 40000);
        employees.put(generateKey(e6), e6);
    }

    @Override
    public Map<String, Employee> index(){
        return employees;
    }

    private static String generateKey(Employee employee){
        return employee.getFirstName() + " " + employee.getLastName();
    }

    @Override
    public Employee add(String firstName, String lastName, int department, int salary){
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)){
            throw new EmployeeWrongDataException();
        }
        if (department < 1 || department > 5){
            throw new DepartmentNotFoundException(department);
        }
        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName),
                department, salary);
        String key = generateKey(employee);
        if (employees.containsKey(key)){
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(key, employee);
        return employee;
    }



    @Override
    public Employee remove(String firstName, String lastName){
        Employee employee = this.find(firstName, lastName);
        if (!employees.remove(generateKey(employee), employee)){
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName){
        Employee employee = new Employee(firstName, lastName);
        Employee employeeFound = employees.get(generateKey(employee));
        if (employeeFound == null || (!employeeFound.getFirstName().equals(firstName) || !employeeFound.getLastName().equals(lastName))) {
            throw new EmployeeNotFoundException();
        }
        return employeeFound;
    }

    public void printAllEmployeesInfo(){
        employees.values().forEach(System.out::println);
    }

    public int calcTotalSalaryAMonth(){
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }
    public Employee getMinSalaryEmployee(){
        return employees.values().stream()
                .min(Comparator.comparingInt(Employee::getSalary)).orElse(null);
    }
    public Employee getMaxSalaryEmployee(){
        return employees.values().stream()
                .min(Comparator.comparingInt(Employee::getSalary)).orElse(null);
    }
    public double calcAverageSalary(){
        return  employees.values().stream()
                .mapToDouble(Employee::getSalary).average().orElse(0);
    }

    public void printEmpNames(){
        employees.keySet().forEach(System.out::println);
    }
}
