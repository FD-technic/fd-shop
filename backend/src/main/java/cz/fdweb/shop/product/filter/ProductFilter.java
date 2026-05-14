package cz.fdweb.shop.product.filter;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductFilter {

    private Long productId = null;
    private String name = "";
    private String description = "";
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private boolean onStock;
    private int page = 0;
    private int pageSize = 30;
    private boolean hidden = false;
}
