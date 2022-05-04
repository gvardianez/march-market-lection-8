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
        if (registerUserDto.getUsername() == null || registerUserDto.getUsername().isBlank()) { //без проверки на null при первой загрузке странице на фронте,
            errors.add("Поле имя пользователя не должно быть пустым");                            // если оставить пустыми поля и передать форму, ловится NPE
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
