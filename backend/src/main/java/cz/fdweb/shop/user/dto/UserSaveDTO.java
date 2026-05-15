package cz.fdweb.shop.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.fdweb.shop.address.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveDTO {

    @JsonProperty("_id")
    private Long id;

    private String name;

    private List<AddressDTO> addresses;

    private String phone;

    private String mail;
}


