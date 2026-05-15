package cz.fdweb.shop.address.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.fdweb.shop.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressSaveDTO {

    @JsonProperty("_id")
    private Long id;

    private String street;

    private String city;

    private String zip;

    private String country;

    private UserEntity user;
}


