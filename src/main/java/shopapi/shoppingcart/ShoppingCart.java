package shopapi.shoppingcart;

import jakarta.persistence.*;
import lombok.*;
import shopapi.product.Product;
import shopapi.user.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private BigDecimal totalPrice;

    @OneToOne
    private User owner;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shoppingCart")
    private List<Product> products;


}
