package com.Hotel.io.Project.domain;


;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Hotel implements Serializable {
    private static final long serialVersionUID=425345L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



}
