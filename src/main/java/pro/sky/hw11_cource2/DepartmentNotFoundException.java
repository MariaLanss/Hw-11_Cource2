package pro.sky.hw11_cource2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException() {
        super("Отдел не найден");
    }
    public DepartmentNotFoundException(int department) {
        super("Отдел " + department + " не найден");
    }
}