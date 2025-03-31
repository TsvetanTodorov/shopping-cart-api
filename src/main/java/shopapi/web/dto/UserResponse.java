package shopapi.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {

    private String firstName;

    private String lastName;

    private String email;

    private int age;

    private LocalDateTime createdOn;

}
