package cz.fdweb.shop.user.specification;

import cz.fdweb.shop.user.entity.UserEntity;
import cz.fdweb.shop.user.filter.UserFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Vytváří dynamický filtr (Specification) pro uživatele.
 * <p>
 * Na základě hodnot v {@link UserFilter} skládá WHERE podmínky,
 * které se následně používají jak pro výpis, tak pro statistiky.
 */
public class UserSpecification {

    /**
     * Sestaví Specification pro filtrování uživatelů.
     * <p>
     * Podmínky se přidávají pouze pokud jsou ve filtru vyplněny.
     *
     * @param filter vstupní filtrační parametry
     * @return Specification pro JPA dotaz
     */
    public static Specification<UserEntity> build(UserFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getName() != null && !filter.getName().isEmpty()) {
                predicates.add(cb.like(
                        cb.lower(root.get("name")),
                        "%" + filter.getName().toLowerCase() + "%"
                    )
                );
            }

            if (!filter.isHidden()) {
                predicates.add(cb.equal(root.get("hidden"), filter.isHidden()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
