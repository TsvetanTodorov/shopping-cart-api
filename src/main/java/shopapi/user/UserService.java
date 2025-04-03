package shopapi.user;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopapi.exception.DomainException;
import shopapi.shoppingcart.ShoppingCart;
import shopapi.shoppingcart.ShoppingCartService;
import shopapi.web.dto.UserRequest;
import shopapi.web.dto.UserResponse;
import shopapi.web.mapper.DtoMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public UserService(UserRepository userRepository, ShoppingCartService shoppingCartService) {
        this.userRepository = userRepository;
        this.shoppingCartService = shoppingCartService;
    }


    public User createUser(UserRequest dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DomainException("Email address already in use: [%s]".formatted(dto.getEmail()));
        }

        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .build();

        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart(new ShoppingCart());
        shoppingCart.setOwner(user);

        user.setShoppingCart(shoppingCart);

        userRepository.save(user);

        return user;
    }


    public List<User> getAllUsers() {

        return new ArrayList<>(userRepository.findAll());
    }

    public User getUserByEmail(String email) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new DomainException("No such user with the given email: [%s]".formatted(email));
        }

        return optionalUser.get();
    }

    @Transactional
    public boolean deleteUserByEmail(String email) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            shoppingCartService.deleteShoppingCart(user.getShoppingCart());
            userRepository.delete(user);

            return true;
        }

        return false;
    }

    @Transactional
    public UserResponse updateUser(UserRequest request) {

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setAge(request.getAge());
            user.setUpdatedOn(LocalDateTime.now());

            return DtoMapper.fromUser(user);
        } else {
            throw new DomainException("User with email [%s] not found".formatted(request.getEmail()));
        }
    }
}
