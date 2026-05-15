package cz.fdweb.shop.user.entity;

import cz.fdweb.shop.address.entity.AddressEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "user")
    private List<AddressEntity> addresses = new ArrayList<>();

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private boolean hidden;

    @Column
    private LocalDateTime hiddenAt;
}
