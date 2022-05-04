package ru.geekbrains.march.market.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.march.market.api.UserProfileDto;
import ru.geekbrains.march.market.api.RegisterUserDto;
import ru.geekbrains.march.market.auth.converters.UserConverter;
import ru.geekbrains.march.market.auth.services.UserService;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping("/registration")
    public UserProfileDto registrationNewUser(@RequestBody RegisterUserDto registerUserDto) {
        return userConverter.entityToDto(userService.createNewUser(registerUserDto));
    }
}
