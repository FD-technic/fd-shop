package cz.fdweb.shop.product.specification;

import cz.fdweb.shop.product.entity.ProductEntity;
import cz.fdweb.shop.product.filter.ProductFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Vytváří dynamický filtr (Specification) pro produkty.
 * <p>
 * Na základě hodnot v {@link ProductFilter} skládá WHERE podmínky,
 * které se následně používají jak pro výpis, tak pro statistiky.
 */
public class ProductSpecification {

    /**
     * Sestaví Specification pro filtrování produktů.
     * <p>
     * Podmínky se přidávají pouze pokud jsou ve filtru vyplněny.
     *
     * @param filter vstupní filtrační parametry
     * @return Specification pro JPA dotaz
     */
    public static Specification<ProductEntity> build(ProductFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // stock_quantity > 0
            if (filter.isOnStock()) {
                predicates.add(cb.greaterThan(root.get("stockQuantity"), 0));
            }

            if (filter.getName() != null && !filter.getName().isEmpty()) {
                predicates.add(cb.like(
                        cb.lower(root.get("name")),
                        "%" + filter.getName().toLowerCase() + "%"
                    )
                );
            }

            // filtr podle názvu produktu (JOIN na položky faktury)
            if (filter.getDescription() != null && !filter.getDescription().isEmpty()) {
                predicates.add(cb.like(
                        cb.lower(root.get("description")),
                        "%" + filter.getDescription().toLowerCase() + "%"
                    )
                );
            }

            if (filter.getMinPrice() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.getMinPrice()));
            }

            if (filter.getMaxPrice() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.getMaxPrice()));
            }

            if (!filter.isHidden()) {
                predicates.add(cb.equal(root.get("hidden"), filter.isHidden()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
