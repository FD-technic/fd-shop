package cz.fdweb.shop.product.service;

import cz.fdweb.shop.advice.BadRequestException;
import cz.fdweb.shop.advice.NotFoundException;
import cz.fdweb.shop.product.dto.ProductDTO;
import cz.fdweb.shop.product.dto.ProductSaveDTO;
import cz.fdweb.shop.product.entity.ProductEntity;
import cz.fdweb.shop.product.filter.ProductFilter;
import cz.fdweb.shop.product.mapper.ProductMapper;
import cz.fdweb.shop.product.repository.ProductRepository;
import cz.fdweb.shop.product.specification.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;


    /**
     * Konstruktor služby pro práci s produkty.
     *
     * @param productMapper mapper pro převod mezi DTO a entitou
     * @param productRepository repozitář pro práci s databází
     */
    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    /**
     * Vytvoří nový produkt
     *
     * @param productSaveDTO vstupní data produktu
     * @return vytvořený produkt jako DTO
     * @throws BadRequestException pokud nejsou splněny validační podmínky
     */
    @Override
    public ProductDTO addProduct(ProductSaveDTO productSaveDTO) {
        if (productSaveDTO.getName() == null || productSaveDTO.getName().isBlank()) {
            throw new BadRequestException("Product name is required");
        }

        if (productSaveDTO.getPrice() == null || productSaveDTO.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Price must be greather than 0");
        }

        ProductEntity entity = productMapper.toEntity(productSaveDTO);
        entity.setId(null);

        entity = productRepository.save(entity);

        return productMapper.toDTO(entity);
    }

    /**
     * Vrátí stránkovaný seznam produktů podle zadaného filtru.
     *
     * @param filter podmínky filtrování
     * @param pageable nastavení stránkování
     * @return stránka produktů
     */
    @Override
    public Page<ProductDTO> findProducts(ProductFilter filter, Pageable pageable) {

        var spec = ProductSpecification.build(filter);

        return productRepository
                .findAll(spec, pageable)
                .map(productMapper::toDTO);
    }

    /**
     * Upraví existující produkt.
     * Původní produkt je označen jako skrytý a vytvoří se nová verze.
     *
     * @param productId ID produktu
     * @param productSaveDTO nová data produktu
     * @return upravený produkt
     */
    @Override
    public ProductDTO editProduct(Long productId, ProductSaveDTO productSaveDTO) {
        ProductEntity oldEntity = fetchProductById(productId);
        ProductEntity newEntity = new ProductEntity();
        productMapper.cloneEntity(oldEntity, newEntity);

        oldEntity.setHidden(true);
        oldEntity.setHiddenAt(LocalDateTime.now());

        productMapper.updateEntity(productSaveDTO, newEntity);

        productRepository.saveAndFlush(oldEntity);

        ProductEntity saved = productRepository.save(newEntity);

        return productMapper.toDTO(saved);
    }

    /**
     * Vrátí produkt podle ID.
     *
     * @param productId ID produktu
     * @return nalezený produkt
     * @throws NotFoundException pokud produkt neexistuje
     */
    @Override
    public ProductDTO getProductById(Long productId) {
        ProductEntity entity = fetchProductById(productId);

        return productMapper.toDTO(entity);
    }

    /**
     * Soft delete produktu (nastaví hidden = true).
     *
     * @param id ID produktu
     * @return upravený produkt
     */
    @Override
    public ProductDTO removeProduct(Long id) {
        ProductEntity product = fetchProductById(id);

        product.setHidden(true);
        ProductEntity saved = productRepository.save(product);

        return productMapper.toDTO(saved);
    }

    // region: Private methods

    /**
     * Načte produkt podle ID nebo vyhodí výjimku.
     *
     * @param id ID produktu
     * @return entita produktu
     * @throws NotFoundException pokud produkt neexistuje
     */
    private ProductEntity fetchProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id " + id + " wasn't found."));
    }

}
