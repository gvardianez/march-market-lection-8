package ru.geekbrains.march.market.auth.converters;

import org.springframework.stereotype.Component;

import ru.geekbrains.march.market.api.UserProfileDto;
import ru.geekbrains.march.market.auth.entities.Role;
import ru.geekbrains.march.market.auth.entities.User;

import java.util.stream.Collectors;

@Component
public class UserConverter {

    public UserProfileDto entityToDto(User user){
        return new UserProfileDto(user.getId(), user.getUsername(), user.getEmail(), user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
    }

}
