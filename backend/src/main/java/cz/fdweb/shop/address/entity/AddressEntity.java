package cz.fdweb.shop.address.entity;

import cz.fdweb.shop.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String zip;

    @Column(nullable = false)
    private String country;

    @ManyToOne
    private UserEntity user;

    @Column(nullable = false)
    private boolean hidden;

    @Column
    private LocalDateTime hiddenAt;
}
