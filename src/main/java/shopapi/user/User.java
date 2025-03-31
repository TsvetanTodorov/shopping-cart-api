package shopapi.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import shopapi.shoppingcart.ShoppingCart;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @Column(nullable = false)
    private LocalDateTime updatedOn;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "owner")
    private ShoppingCart shoppingCart;


}
