package org.example.crud;

import org.example.EntradaTeclado;
import org.example.entidades.DepartamentosEntidad;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CrudDepartamento {

    private CrudDepartamento() {
    }

    public static DepartamentosEntidad buscarDepartamento(Session session, int idDepartamento){
        return session.get(DepartamentosEntidad.class, idDepartamento);
    }

    public static List<DepartamentosEntidad> listarDepartamentos(Session session) {
        Query<DepartamentosEntidad> miQuery = session.createQuery("from DepartamentosEntidad ",
                DepartamentosEntidad.class);
        return miQuery.list();
    }

    public static boolean actualizarDepartamento(Session session,
                                                int idDepartamento,
                                                String nombre,
                                                double presupuesto,
                                                double gastos) {
        boolean departamentoActualizado = false;
        Query<DepartamentosEntidad> miQuery = session.createQuery("from DepartamentosEntidad where id = :id",
                DepartamentosEntidad.class);
        miQuery.setParameter("id", idDepartamento);

        List<DepartamentosEntidad> listaDepartamentos = miQuery.list();

        if (!listaDepartamentos.isEmpty()) {
            DepartamentosEntidad departamento = listaDepartamentos.get(0);
            cumplimentarDatosDepartamento(idDepartamento, nombre, presupuesto, gastos, departamento);
            Transaction transaction = session.beginTransaction();
            session.merge(departamento);
            transaction.commit();
            departamentoActualizado = true;
        }
        return departamentoActualizado;
    }

    private static void cumplimentarDatosDepartamento(int idDepartamento, String nombre, double presupuesto,
                                                      double gastos, DepartamentosEntidad departamento) {
        departamento.setId(idDepartamento);
        departamento.setNombre(nombre);
        departamento.setPresupuesto(presupuesto);
        departamento.setGastos(gastos);
    }

    public static boolean insertarDepartamento(Session session,
                                               String nombre,
                                               double presupuesto,
                                               double gastos) {
        Query<DepartamentosEntidad> miQuery = session.createQuery("from DepartamentosEntidad where nombre = :nombre",
                DepartamentosEntidad.class);
        miQuery.setParameter("nombre", nombre);
        List<DepartamentosEntidad> listaDepartamentos = miQuery.list();
        boolean departamentoInsertado = listaDepartamentos.isEmpty();
        if (departamentoInsertado) {
            DepartamentosEntidad departamento = new DepartamentosEntidad(nombre, presupuesto, gastos);
            cumplimentarDatosNuevoDepartamento(nombre, presupuesto, gastos, departamento);
            int id = generarNuevaId(session);
            departamento.setId(id);
            Transaction transaction = session.beginTransaction();
            session.persist(departamento);
            transaction.commit();
        }
        return departamentoInsertado;
    }

    private static void cumplimentarDatosNuevoDepartamento(String nombre, double presupuesto, double gastos,
                                                           DepartamentosEntidad departamento) {
        departamento.setNombre(nombre);
        departamento.setPresupuesto(presupuesto);
        departamento.setGastos(gastos);
    }

    public static int obtenerMaximaId(Session session) {
        Query<Integer> query = session.createQuery("SELECT MAX(e.id) FROM DepartamentosEntidad e", Integer.class);
        Integer maxId = query.uniqueResult();
        return maxId != null ? maxId : 0;
    }

    public static int generarNuevaId(Session session) {
        int maxId = obtenerMaximaId(session);
        return maxId + 1;
    }

    public static boolean borrarDepartamento(Session session, int idDepartamento) {

        boolean departamentoEliminado = session.get(DepartamentosEntidad.class, idDepartamento) != null;
        if (departamentoEliminado) {
            Query<DepartamentosEntidad> miQuery = session.createQuery("from DepartamentosEntidad where id = :id",
                    DepartamentosEntidad.class);
            miQuery.setParameter("id", idDepartamento);
            List<DepartamentosEntidad> listaDepartamentos = miQuery.list();
            DepartamentosEntidad departamento = listaDepartamentos.get(0);
            if ("s".equalsIgnoreCase(confirmarBorrado(departamento))) {
                Transaction transaction = session.beginTransaction();
                session.remove(departamento);
                transaction.commit();
            }
        }
        return departamentoEliminado;
    }

    private static String confirmarBorrado(DepartamentosEntidad departamento) {
        String respuesta;
        do{
            respuesta = EntradaTeclado.pedirCadena("Los datos que se eliminaran son: " + departamento.toString() +
                    "\nEsta seguro (S)i/(N)o");
        }while(!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n"));
        return respuesta;
    }
}
