package shopapi.shoppingcart;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import shopapi.exception.DomainException;
import shopapi.user.UserRepository;
import shopapi.user.UserService;
import shopapi.web.dto.ShoppingCartResponse;
import shopapi.web.mapper.DtoMapper;



@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository,@Lazy UserService userService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userService = userService;
    }

    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
       return shoppingCartRepository.save(shoppingCart);
    }

    @Transactional
    public void deleteShoppingCart(ShoppingCart shoppingCart) {
        if(shoppingCartRepository.existsById(shoppingCart.getId())) {
            shoppingCartRepository.delete(shoppingCart);
        }
    }

    public ShoppingCartResponse getShoppingCartByUserEmail(String email) {

        ShoppingCart shoppingCart = shoppingCartRepository.findByOwnerEmail(email)
                .orElseThrow(() -> new DomainException("Shopping cart for user with email " + email + " not found"));

        return DtoMapper.fromShoppingCart(shoppingCart);
    }
}
