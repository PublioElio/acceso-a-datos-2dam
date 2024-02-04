package org.acdat.jpa;

import jakarta.persistence.*;
import org.acdat.negocio.Agencia;
import org.acdat.negocio.Cliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AgenciasJPA")
public class AgenciaJPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agencia_id")
    protected int id;
    @Column
    protected String nombre;
    @Column
    protected String direccion;
    @Column
    protected String telefono;

    public AgenciaJPAEntity() {
    }

    public AgenciaJPAEntity(int id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Agencia> mostrarAgencia() throws SQLException, SQLException {
        List<Agencia> agenciaList = new ArrayList<Agencia>();
        List<AgenciaJPAEntity> agenciaJPAEntities = new ArrayList<>();

        Session session = null;
        try {
            session = abrirSession();
            if(session != null){
                agenciaJPAEntities = session.createNativeQuery("SELECT * FROM AgenciasJPA", AgenciaJPAEntity.class ).list();
            }

            session.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < agenciaJPAEntities.size(); i++){
            AgenciaJPAEntity agenciaJPA = agenciaJPAEntities.get(i);
            Agencia agencia = new Agencia();

            agencia.setId(agenciaJPA.getId());
            agencia.setNombre(agenciaJPA.getNombre());
            agencia.setDireccion(agenciaJPA.getDireccion());
            agencia.setTelefono(agenciaJPA.getTelefono());

            agenciaList.add(agencia);
        }


        return agenciaList;
    }
    public boolean agregarAgencia(Agencia agencia) throws SQLException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = abrirSession();
            if (session != null) {
                transaction = session.beginTransaction();
                AgenciaJPAEntity agenciaJPA = new AgenciaJPAEntity();
                agenciaJPA.id = agencia.getId();
                agenciaJPA.nombre = agencia.getNombre();
                agenciaJPA.direccion = agencia.getDireccion();
                agenciaJPA.telefono = agencia.getTelefono();

                session.persist(agenciaJPA);

                session.flush();
                transaction.commit();
                session.close();

                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean actualizarAgencia(Agencia agencia) throws SQLException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = abrirSession();
            if (session != null) {
                transaction = session.beginTransaction();
                AgenciaJPAEntity agenciaJPA = new AgenciaJPAEntity();
                agenciaJPA.id = agencia.getId();
                agenciaJPA.nombre = agencia.getNombre();
                agenciaJPA.direccion = agencia.getDireccion();
                agenciaJPA.telefono = agencia.getTelefono();

                session.saveOrUpdate(agenciaJPA);

                session.flush();
                transaction.commit();
                session.close();

                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public Agencia cargarAgencia (int id) throws SQLException {
        Agencia agencia = new Agencia();
        AgenciaJPAEntity agenciaJPAEntity = null;
        List<AgenciaJPAEntity> agenciaJPAEntities = new ArrayList<>();
        try {
            Session session = abrirSession();

            if (session != null){
                agenciaJPAEntities = session.createNativeQuery("SELECT * FROM AgenciasJPA WHERE agencia_id = ?",AgenciaJPAEntity.class).setParameter(1,id).list();
                session.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        agenciaJPAEntity = agenciaJPAEntities.get(0);

        agencia.setId(agenciaJPAEntity.getId());
        agencia.setNombre(agenciaJPAEntity.getNombre());
        agencia.setDireccion(agenciaJPAEntity.getDireccion());
        agencia.setTelefono(agenciaJPAEntity.getTelefono());

        return agencia;
    }

    public boolean existeAgencia (int id) throws SQLException {
        List<AgenciaJPAEntity> agenciaJPAEntities = new ArrayList<>();
        try {
            Session session = abrirSession();

            if (session != null){
                agenciaJPAEntities = session.createNativeQuery("SELECT * FROM AgenciasJPA WHERE agencia_id = ?",AgenciaJPAEntity.class).setParameter(1,id).list();

                session.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (agenciaJPAEntities.size() == 1)
            return true;

        return false;
    }

    public boolean eliminarAgencia(int agenciaId) throws SQLException {
        List<AgenciaJPAEntity> agenciaJPAEntities = new ArrayList<>();
        Transaction transaction = null;
        try {
            Session session = abrirSession();

            if (session != null){
                transaction = session.beginTransaction();
                agenciaJPAEntities = session.createNativeQuery("SELECT * FROM AgenciasJPA WHERE agencia_id = ?",AgenciaJPAEntity.class).setParameter(1,agenciaId).list();
                session.delete(agenciaJPAEntities.get(0));
                session.flush();
                transaction.commit();
                session.close();
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    static Session abrirSession() throws Exception {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        if (session == null) {
            return null;
        }else {
            System.out.println("Conexión Establecida");
        }
        return session;
    }
}
