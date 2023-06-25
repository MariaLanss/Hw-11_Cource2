package pro.sky.hw11_cource2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.hw11_cource2.DepartmentNotFoundException;
import pro.sky.hw11_cource2.models.Employee;
import pro.sky.hw11_cource2.services.DepartmentsServiceImpl;
import pro.sky.hw11_cource2.services.EmployeeService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentsServiceTest {
    @Mock
    private EmployeeService employeeServiceMock;
    @InjectMocks
    private DepartmentsServiceImpl out;

    @BeforeEach
    private void setUp(){
        Employee e1 = new Employee("Татьяна", "Адаменко", 1, 10000);
        Employee e2 = new Employee("Рената", "Андреева", 1, 20000);
        Employee e3 = new Employee("Наталья", "Баринова", 2, 25000);
        Employee e4 = new Employee("Надежда", "Васильева", 2, 30000);
        Employee e5 = new Employee("Мария", "Грачева", 3, 35000);
        Employee e6 = new Employee("Елена", "Денисова", 3, 40000);

        HashMap<String, Employee> hm = new HashMap<>(Map.of(
                e1.generateKey(), e1,
                e2.generateKey(), e2,
                e3.generateKey(), e3,
                e4.generateKey(), e4,
                e5.generateKey(), e5,
                e6.generateKey(), e6
        ));

        when(employeeServiceMock.index()).thenReturn(hm);
    }

    @Test
    void shouldReturnMaxSalaryEmployeeForDepartment(){
        Employee expected = new Employee("Надежда", "Васильева", 2, 3000);
        Employee actual = out.getMaxSalaryEmployee(2);
        assertEquals(expected, actual);
    }
    @Test
    void shouldThrowDepartmentNotFoundExceptionForMaxSalary() {
        assertThrows(DepartmentNotFoundException.class, ()->out.getMaxSalaryEmployee(22));
    }

    @Test
    void shouldReturnMinSalaryEmployeeForDepartment() {
        Employee expected = new Employee("Елена", "Денисова", 3, 40000);
        Employee actual = out.getMinSalaryEmployee(2);
        assertEquals(expected, actual);
    }
    @Test
    void shouldThrowDepartmentNotFoundExceptionForMinSalary() {
        assertThrows(DepartmentNotFoundException.class, ()->out.getMinSalaryEmployee(11));
    }


    @Test
    void shouldReturnListOfEmployeesForDepartment() {
        List<Employee> expected = new ArrayList<>(List.of(
                new Employee("Татьяна", "Адаменко", 1, 10000),
                new Employee("Наталья", "Баринова", 2, 25000)
        ));
        List<Employee> actual = out.getAllEmployeesForDepartment(1);
        assertEquals(expected,actual);
    }
    @Test
    void shouldReturnEmptyListWhenWrongDepartment() {
        List<Employee> expected = new ArrayList<>();
        List<Employee> actual = out.getAllEmployeesForDepartment(11);
        assertEquals(expected,actual);
    }

    @Test
    void shouldReturnEmployeesByDepartments() {
        Employee e1 = new Employee("Татьяна", "Адаменко", 1, 10000);
        Employee e2 = new Employee("Рената", "Андреева", 1, 20000);
        Employee e3 = new Employee("Наталья", "Баринова", 2, 25000);
        Employee e4 = new Employee("Надежда", "Васильева", 2, 30000);
        Employee e5 = new Employee("Мария", "Грачева", 3, 35000);
        Employee e6 = new Employee("Елена", "Денисова", 3, 40000);
        Map<Integer, List<Employee>> expected = new HashMap<>(Map.of(
                1, Arrays.asList(e1, e3),
                2, Arrays.asList(e2, e4, e5, e6)
        ));
        Map<Integer, List<Employee>> actual = out.getEmployeesByDepartments();

        assertEquals(expected.keySet(), actual.keySet());
        for (Integer i:expected.keySet()) {
            List<Employee> expectedList = expected.get(i);
            List<Employee> actualList = actual.get(i);
            assertTrue(expectedList.containsAll(actualList) && actualList.containsAll(expectedList));
        }

    }
}