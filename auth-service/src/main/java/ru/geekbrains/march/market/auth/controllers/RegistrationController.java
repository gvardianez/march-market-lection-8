package ru.geekbrains.march.market.auth.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.march.market.api.UserProfileDto;
import ru.geekbrains.march.market.api.RegisterUserDto;
import ru.geekbrains.march.market.auth.converters.UserConverter;
import ru.geekbrains.march.market.auth.exceptions.AppError;
import ru.geekbrains.march.market.auth.services.UserService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Регистрация", description = "Методы для регистрации нового пользователя")
public class RegistrationController {
    private final UserService userService;
    private final UserConverter userConverter;

    @Operation(
            summary = "Запрос на создание нового пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserProfileDto.class))
                    ),
                    @ApiResponse(
                            description = "Имя пользователя или Email уже используются. Неправильно заполнены поля", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @PostMapping("/registration")
    public UserProfileDto registrationNewUser(@RequestBody RegisterUserDto registerUserDto) {
        return userConverter.entityToDto(userService.createNewUser(registerUserDto));
    }
}
