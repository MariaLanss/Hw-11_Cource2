package pro.sky.hw11_cource2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
public class EmployeeStorageIsFullException extends RuntimeException{

    public EmployeeStorageIsFullException() {
        super("Массив сотрудников заполнен");
    }
}
