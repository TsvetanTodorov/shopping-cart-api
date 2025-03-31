package shopapi.web.dto;

import lombok.Builder;
import lombok.Data;
import shopapi.product.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ShoppingCartResponse {

    private UUID id;

    private List<Product> products;

    private BigDecimal totalPrice;
}
