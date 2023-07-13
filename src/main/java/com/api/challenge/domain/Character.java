package com.api.challenge.domain;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "characters")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Character implements Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE)
    @jakarta.persistence.Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String height;

    @Column(nullable = false)
    private String mass;

    @Column(nullable = false)
    private String hairColor;

    @Column(nullable = false)
    private String skinColor;

    @Column(nullable = false)
    private String eyeColor;

    @Column(nullable = false)
    private String birthYear;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String homeWorld;

    @Column
    private List<String> films;

    @Column
    private List<String> species;

    @Column
    private List<String> vehicles;

    @Column
    private List<String> starships;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private Date edited;

    @Column(nullable = false)
    private String url;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Character character = (Character) o;
        return getId() != null && Objects.equals(getId(), character.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}