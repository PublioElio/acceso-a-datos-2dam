package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "telefonos", schema = "public", catalog = "ACDATJPA")
public class TelefonoJPAEntity {
    @ManyToOne
    @JoinColumn(name = "persona_id", foreignKey = @ForeignKey(name="PERSON_ID_FK"))
    private PersonaJPAEntity persona;

    @Id
    @Column(name = "numTelefono", nullable = false, length = 9)
    private String numTelefono;

    public TelefonoJPAEntity(){
    }

    public TelefonoJPAEntity(String numTelefono){
        this.numTelefono = numTelefono;
    }

    public PersonaJPAEntity getPersona() {
        return persona;
    }

    public void setPersona(PersonaJPAEntity persona) {
        this.persona = persona;
    }
}
