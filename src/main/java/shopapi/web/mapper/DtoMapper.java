package shopapi.web.mapper;

import lombok.experimental.UtilityClass;
import shopapi.shoppingcart.ShoppingCart;
import shopapi.user.User;
import shopapi.web.dto.ShoppingCartResponse;
import shopapi.web.dto.UserResponse;

@UtilityClass
public class DtoMapper {

    public static UserResponse fromUser(User user) {

        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .age(user.getAge())
                .createdOn(user.getCreatedOn())
                .build();
    }

    public static ShoppingCartResponse fromShoppingCart(ShoppingCart shoppingCart) {

        return ShoppingCartResponse.builder()
                .id(shoppingCart.getId())
                .products(shoppingCart.getProducts())
                .totalPrice(shoppingCart.getTotalPrice())
                .build();
    }
}
