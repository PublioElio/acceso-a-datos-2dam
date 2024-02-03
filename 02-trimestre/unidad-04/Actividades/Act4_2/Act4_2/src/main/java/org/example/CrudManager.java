package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.example.entidades.DepartamentosEntityJPA;
import org.example.entidades.EmpleadosEntityJPA;

import java.util.List;

public class CrudManager {

    private final SessionFactory sessionFactory;

    public CrudManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void agregarEmpleado(EmpleadosEntityJPA empleado) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(empleado);
            transaction.commit();
        }
    }

    public void agregarDepartamento(DepartamentosEntityJPA departamento) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(departamento);
            transaction.commit();
        }
    }

    public EmpleadosEntityJPA obtenerEmpleado(Integer empleadoId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(EmpleadosEntityJPA.class, empleadoId);
        }
    }

    public DepartamentosEntityJPA obtenerDepartamento(Integer departamentoId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(DepartamentosEntityJPA.class, departamentoId);
        }
    }

    public List<EmpleadosEntityJPA> obtenerEmpleadosPorDepartamento(Integer departamentoId) {
        try (Session session = sessionFactory.openSession()) {
            Query<EmpleadosEntityJPA> query = session.createQuery(
                    "SELECT e FROM EmpleadosEntityJPA e WHERE e.idDepartamento = :departamentoId",
                    EmpleadosEntityJPA.class
            );
            query.setParameter("departamentoId", departamentoId);
            return query.list();
        }
    }
    public List<EmpleadosEntityJPA> listarEmpleados(){
        try (Session session = sessionFactory.openSession()) {
            Query<EmpleadosEntityJPA> query = session.createQuery(
                    "FROM EmpleadosEntityJPA", EmpleadosEntityJPA.class);
            return query.list();
        }
    }

    public List<Integer> listarDepartamentosPorID() {
        try (Session session = sessionFactory.openSession()) {
            Query<Integer> query = session.createQuery(
                    "SELECT d.id FROM DepartamentosEntityJPA d", Integer.class);
            return query.list();
        }
    }

    public List<DepartamentosEntityJPA> listarDepartamentosPorNombre() {
        try (Session session = sessionFactory.openSession()) {
            Query<DepartamentosEntityJPA> query = session.createQuery(
                    "FROM DepartamentosEntityJPA", DepartamentosEntityJPA.class);
            return query.list();
        }
    }

    public void actualizarEmpleado(EmpleadosEntityJPA empleado) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(empleado);
            transaction.commit();
        }
    }

    public void eliminarEmpleado(int empleadoId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            EmpleadosEntityJPA empleado = session.find(EmpleadosEntityJPA.class, empleadoId);
            if (empleado != null) {
                session.remove(empleado);
            }
            transaction.commit();
        }
    }
}


