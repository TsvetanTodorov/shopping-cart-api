package shopapi.product;

import jakarta.persistence.*;
import lombok.*;
import shopapi.shop.Shop;
import shopapi.shoppingcart.ShoppingCart;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @ManyToOne
    private ShoppingCart shoppingCart;

    @ManyToOne
    private Shop shop;


}
