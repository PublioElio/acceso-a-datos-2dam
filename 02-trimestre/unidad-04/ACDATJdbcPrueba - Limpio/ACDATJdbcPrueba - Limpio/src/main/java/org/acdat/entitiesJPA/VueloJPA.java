package org.acdat.entitiesJPA;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vuelos", schema = "public", catalog = "HibernatePrueba")
public class VueloJPA {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        @Column(name = "vuelo_id", nullable = false)
        private int vuelo_id;

        @Basic
        @Column(name = "origen", length = 50)
        private String origen;

        @Basic
        @Column(name = "destino", length = 50)
        private String destino;

        @Basic
        @Column(name = "fecha_salida")
        private Date fecha_salida;

        @Basic
        @Column(name = "fecha_llegada")
        private Date fecha_llegada;

        @Basic
        @Column(name = "costo")
        private double costo;

        public VueloJPA(){

        }

        public int getVuelo_id() {
                return vuelo_id;
        }

        public void setVuelo_id(int vuelo_id) {
                this.vuelo_id = vuelo_id;
        }

        public String getOrigen() {
                return origen;
        }

        public void setOrigen(String origen) {
                this.origen = origen;
        }

        public String getDestino() {
                return destino;
        }

        public void setDestino(String destino) {
                this.destino = destino;
        }

        public Date getFecha_salida() {
                return fecha_salida;
        }

        public void setFecha_salida(Date fecha_salida) {
                this.fecha_salida = fecha_salida;
        }

        public Date getFecha_llegada() {
                return fecha_llegada;
        }

        public void setFecha_llegada(Date fecha_llegada) {
                this.fecha_llegada = fecha_llegada;
        }

        public double getCosto() {
                return costo;
        }

        public void setCosto(double costo) {
                this.costo = costo;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                VueloJPA vueloJPA = (VueloJPA) o;
                return vuelo_id == vueloJPA.vuelo_id && Double.compare(costo, vueloJPA.costo) == 0 && Objects.equals(origen, vueloJPA.origen) && Objects.equals(destino, vueloJPA.destino) && Objects.equals(fecha_salida, vueloJPA.fecha_salida) && Objects.equals(fecha_llegada, vueloJPA.fecha_llegada);
        }

        @Override
        public int hashCode() {
                return Objects.hash(vuelo_id, origen, destino, fecha_salida, fecha_llegada, costo);
        }

        @Override
        public String toString() {
                return "VueloJPA{" +
                        "vuelo_id=" + vuelo_id +
                        ", origen='" + origen + '\'' +
                        ", destino='" + destino + '\'' +
                        ", fecha_salida=" + fecha_salida +
                        ", fecha_llegada=" + fecha_llegada +
                        ", costo=" + costo +
                        '}';
        }
        public List<VueloJPA> mostrarVuelos(){
                SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession();
                Query<VueloJPA> query = session.createQuery(
                                "FROM VueloJPA ", VueloJPA.class);
                        return query.list();
        }

        public void agregarVuelo(VueloJPA vuelo) {
                SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                session.persist(vuelo);
                transaction.commit();
        }

        public VueloJPA obtenerVuelo(Integer vuelo_id) {
                SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession();
                return session.find(VueloJPA.class, vuelo_id);
        }



}
