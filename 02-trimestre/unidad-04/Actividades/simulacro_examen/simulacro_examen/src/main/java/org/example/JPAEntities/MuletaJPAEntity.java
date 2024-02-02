package org.example.JPAEntities;

import jakarta.persistence.*;

@Entity
@Table(name = "muleta", schema = "public", catalog = "TuViajeExamen")
public class MuletaJPAEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MuletaJPAEntity that = (MuletaJPAEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
