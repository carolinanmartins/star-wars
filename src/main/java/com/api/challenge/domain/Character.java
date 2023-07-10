package com.api.challenge.domain;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Data
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
}