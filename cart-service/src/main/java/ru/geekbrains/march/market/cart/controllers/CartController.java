package ru.geekbrains.march.market.cart.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.StringResponse;
import ru.geekbrains.march.market.cart.converters.CartConverter;
import ru.geekbrains.march.market.cart.exceptions.AppError;
import ru.geekbrains.march.market.cart.services.CartService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Tag(name = "Корзины", description = "Методы работы с корзинами")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @Operation(
            summary = "Запрос на создание случайного ID корзины для гостевого пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StringResponse.class))
                    )
            }
    )
    @GetMapping("/generate_id")
    public StringResponse generateGuestCartId() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @Operation(
            summary = "Запрос на получения текущей корзины для пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CartDto.class))
                    )
            }
    )
    @GetMapping("/{guestCartId}")
    public CartDto getCurrentCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        return cartConverter.entityToDto(cartService.getCurrentCart(currentCartId));
    }

    @Operation(
            summary = "Запрос на добавление продукта в корзину",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{guestCartId}/add/{productId}")
    public void addProductToCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId, @PathVariable Long productId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.addToCart(currentCartId, productId);
    }

    @Operation(
            summary = "Запрос на очистку корзины пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{guestCartId}/clear")
    public void clearCurrentCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.clearCart(currentCartId);
    }

    @Operation(
            summary = "Запрос на слияние гостевой корзины и пользовательской корзины при входе пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{guestCartId}/merge")
    public void mergeGuestAndUserCart(@RequestHeader String username, @PathVariable String guestCartId) {
        cartService.mergeCart(username, guestCartId);
    }

    private String selectCartId(String username, String guestCartId) {
        if (username != null) {
            return username;
        }
        return guestCartId;
    }

}
