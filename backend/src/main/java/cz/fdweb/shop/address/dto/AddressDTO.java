package cz.fdweb.shop.address.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.fdweb.shop.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @JsonProperty("_id")
    private Long id;

    private String street;

    private String city;

    private String zip;

    private String country;

    private UserEntity user;

    private boolean hidden;

    private LocalDateTime hiddenAt;
}
