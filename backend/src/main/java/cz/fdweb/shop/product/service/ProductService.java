package cz.fdweb.shop.product.service;

import cz.fdweb.shop.product.dto.ProductDTO;
import cz.fdweb.shop.product.dto.ProductSaveDTO;
import cz.fdweb.shop.product.filter.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductDTO addProduct(ProductSaveDTO productSaveDTO);

    Page<ProductDTO> findProducts(ProductFilter filter, Pageable pageable);

    ProductDTO editProduct(Long productId, ProductSaveDTO productSaveDTO);

    ProductDTO getProductById(Long productId);

    ProductDTO removeProduct(Long id);
}
