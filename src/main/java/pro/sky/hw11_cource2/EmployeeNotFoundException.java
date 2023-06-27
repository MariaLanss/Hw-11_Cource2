package pro.sky.hw11_cource2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException() {
        super("Сотрудник не найден");
    }
}
