package com.ait.grooming.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
//@ToString
public class User {
    public enum Role {
        ADMIN, MASTER, CLIENT, GUEST
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            insertable = false, updatable = false)
    private Long id;
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    @Column(name = "lastname", length = 20)
    private String lastName;
    @Column(name = "username", length = 20)
    private String userName;
    @Column(name = "password", length = 20)
    private String password;
    @Column(name = "email", length = 20, nullable = false)
    private String email;
    @Column(name = "phone", length = 20, nullable = false)
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "registration_date")
    private LocalDate registrationDate;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Column(name = "is_blocked")
    private boolean isBlocked;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Discount> discounts;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Pet> pets;

//    @Override
//    public final boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null) return false;
//        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
//        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
//        if (thisEffectiveClass != oEffectiveClass) return false;
//        User user = (User) o;
//        return getId() != null && Objects.equals(getId(), user.getId());
//    }
//
//    @Override
//    public final int hashCode() {
//        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
//    }
}
