package cz.fdweb.shop.product.controller;

import cz.fdweb.shop.product.dto.ProductDTO;
import cz.fdweb.shop.product.dto.ProductSaveDTO;
import cz.fdweb.shop.product.filter.ProductFilter;
import cz.fdweb.shop.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductSaveDTO productSaveDTO) {
        return productService.addProduct(productSaveDTO);
    }

    @GetMapping
    public Page<ProductDTO> findProducts(@ModelAttribute ProductFilter filter) {

        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize());

        return productService.findProducts(filter, pageable);
    }

    @GetMapping("{productId}")
    public ProductDTO getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @PutMapping("/{productId}")
    public ProductDTO editProduct(@PathVariable Long productId, @RequestBody ProductSaveDTO productSaveDTO) {
        return productService.editProduct(productId, productSaveDTO);
    }

    @DeleteMapping("/{productId}")
    public ProductDTO removePerson(@PathVariable Long productId) {
        return productService.removeProduct(productId);
    }
}
