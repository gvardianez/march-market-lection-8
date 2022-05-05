package ru.geekbrains.march.market.auth.validators;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.RegisterUserDto;
import ru.geekbrains.march.market.auth.exceptions.FieldValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegistrationValidator {

    public void validate(RegisterUserDto registerUserDto) {
        List<String> errors = new ArrayList<>();
        if (registerUserDto.getUsername() == null || registerUserDto.getUsername().isBlank()) { // здесь разобрался, почему периодически ловится NPE,
            errors.add("Поле имя пользователя не должно быть пустым");                          // а иногда хватает проверки isBlank.
        }
        if (registerUserDto.getEmail() == null || registerUserDto.getEmail().isBlank()) {
            errors.add("Поле email должно быть заполнено");
        }
        if (registerUserDto.getPassword() == null || registerUserDto.getConfirmPassword() == null ||
                registerUserDto.getPassword().isBlank() || registerUserDto.getConfirmPassword().isBlank()) {
            errors.add("Поля с паролями должны быть заполнены");
            throw new FieldValidationException(errors);
        }
        if (!registerUserDto.getPassword().equals(registerUserDto.getConfirmPassword())) {
            errors.add("Введеные пароли не совпадают");
        }
        if (!errors.isEmpty()) {
            throw new FieldValidationException(errors);
        }
    }
}
