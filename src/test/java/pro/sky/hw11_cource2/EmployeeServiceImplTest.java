package pro.sky.hw11_cource2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.hw11_cource2.DepartmentNotFoundException;
import pro.sky.hw11_cource2.EmployeeAlreadyAddedException;
import pro.sky.hw11_cource2.EmployeeNotFoundException;
import pro.sky.hw11_cource2.EmployeeWrongDataException;
import pro.sky.hw11_cource2.models.Employee;
import pro.sky.hw11_cource2.services.EmployeeService;
import pro.sky.hw11_cource2.services.EmployeeServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    private EmployeeService out;

    @BeforeEach
    private void setUp(){
        out = new EmployeeServiceImpl();
        out.add("Василий", "Шабашов", 3, 40000);
    }

    @Test
    void shouldReturnEmployeeWhenAddIsCalled() {
        Employee expected = new Employee("Григорий", "Иванов", 1, 30000);
        Employee actual = out.add("Григорий", "Иванов", 1, 30000);
        assertEquals(expected, actual);
    }
    @Test
    void shouldThrowExceptionWhenNameIsNotAlpha() {
        assertThrows(EmployeeWrongDataException.class, ()-> out.add("232dg", "LastName", 2, 10000));
        assertThrows(EmployeeWrongDataException.class, ()-> out.add("Name", "34.fg", 2, 10000));
        assertThrows(EmployeeWrongDataException.class, ()-> out.add("23!2dg", "34.fg", 2, 10000));
    }
    @Test
    void shouldThrowExceptionWhenDuplicatedEmployeeName() {
        assertThrows(EmployeeAlreadyAddedException.class, ()-> out.add("Ivan", "Turgenev", 2, 10000));
    }
    @Test
    void shouldThrowExceptionWhenDepartmentIsIncorrect(){
        assertThrows(DepartmentNotFoundException.class, ()-> out.add("Name", "LastName", 22, 10000));
    }

    @Test
    void shouldReturnEmployeeWhenFindIsCalled() {
        Employee expected = new Employee("Василий", "Шабашов", 4, 30000);
        Employee actual = out.find("Василий", "Шабашов");
        assertEquals(expected, actual);
    }
    @Test
    void shouldThrowExceptionWhenEmployeeNotFound() {
        assertThrows(EmployeeNotFoundException.class, ()->out.find("Алексей", "Григорьев"));
    }


    @Test
    void shouldReturnEmployeeWhenRemoveIsCalled() {
        Employee expected = new Employee("Василий", "Шабашов", 1, 30000);
        Employee actual = out.remove("Василий", "Шабашов");
        assertEquals(expected, actual);
    }
    @Test
    void shouldThrowExceptionWhenEmployeeNotFoundWhenRemoveCalled() {
        assertThrows(EmployeeNotFoundException.class, ()->out.remove("Алексей", "Григорьев"));
    }
}
