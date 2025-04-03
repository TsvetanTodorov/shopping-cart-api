package shopapi.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopapi.exception.DomainException;
import shopapi.web.dto.ShoppingCartResponse;
import shopapi.web.mapper.DtoMapper;


@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }


    public void deleteShoppingCart(ShoppingCart shoppingCart) {
        if (shoppingCartRepository.existsById(shoppingCart.getId())) {
            shoppingCartRepository.delete(shoppingCart);
        }
    }

    public ShoppingCartResponse getShoppingCartByUserEmail(String email) {

        ShoppingCart shoppingCart = shoppingCartRepository.findByOwnerEmail(email)
                .orElseThrow(() -> new DomainException("Shopping cart for user with email " + email + " not found"));

        return DtoMapper.fromShoppingCart(shoppingCart);
    }
}
