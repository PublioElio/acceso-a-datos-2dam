package org.example.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "personas", schema = "public", catalog = "ACDATJPA")
public class PersonaJPAEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "persona_id", nullable = false)
    private int persona_id;

    @Basic
    @Column(name = "nombrePersona", nullable = true, length = 48)
    private String nombrePersona;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<TelefonoJPAEntity> telefonos;

    public PersonaJPAEntity(){
    }

    public PersonaJPAEntity(String nombrePersona){
        this.nombrePersona = nombrePersona;
    }
}
