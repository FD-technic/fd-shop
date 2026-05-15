package cz.fdweb.shop.user.filter;

import cz.fdweb.shop.address.entity.AddressEntity;
import lombok.Data;

@Data
public class UserFilter {

    private String name;
    private AddressEntity address;
    private String phone;
    private String mail;
    private int page = 0;
    private int pageSize = 30;
    private boolean hidden = false;
}
