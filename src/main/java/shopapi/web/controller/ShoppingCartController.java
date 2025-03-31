package shopapi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopapi.exception.DomainException;
import shopapi.shoppingcart.ShoppingCartService;
import shopapi.user.UserService;
import shopapi.web.dto.ShoppingCartResponse;



@RestController
@RequestMapping("/api/v1/shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @GetMapping("/shopping-cart")
    public ResponseEntity<ShoppingCartResponse> getShoppingCartByUserEmail(@RequestParam String email) {

        try{
            ShoppingCartResponse responseDto = shoppingCartService.getShoppingCartByUserEmail(email);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(responseDto);
        }catch (DomainException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }




    }


}
