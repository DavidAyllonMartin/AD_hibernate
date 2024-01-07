package ejercicios;

import Entidades.DepartamentosEntity;
import Entidades.EmpleadosEntity;
import Entidades.LocalizacionesEntity;
import Utils.SessionFactoryUtil;
import excepciones.NoExisteElEmpleadoException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class Ejercicios5_1 {
    public static void main(String[] args) {
        try {
            SessionFactory sf = SessionFactoryUtil.getSessionFactory();
            Session session = sf.openSession();

            //ejercicio5_1_1(session);
            //ejercicio5_1_2(session);
            //ejercicio5_1_3(session);
            //ejercicio5_1_4(session);
            ejercicio5_1_5(session, 203);

            session.close();


        }catch (Throwable t){
            System.err.println("ERROR: "+t.getCause()+" : " +t.getMessage());

        }
    }

    private static Object[] ejercicio5_1_6(Session session, int id, HashMap<Integer, DepartamentosEntity> departamentos) throws NoExisteElEmpleadoException{
        //Voy a devolver un array de objetos para poder sacar también el objeto departamento. Lo que me hubiera gustado
        // es que el objeto Empleado tuviera un atributo que fuera el objeto Departamento pero parece que no opera así por defecto Hibernate
        Object[] retorno = new Object[2];
        EmpleadosEntity empleado = session.get(EmpleadosEntity.class, id);
        DepartamentosEntity departamento;
        if (empleado != null){
            if (departamentos.containsKey(empleado.getIdDepartamento())){
                departamento = departamentos.get(empleado.getIdEmpleado());
            }else {
                departamento = session.load(DepartamentosEntity.class, empleado.getIdDepartamento());
                departamentos.put(departamento.getIdDepartamento(), departamento);
            }
        }else{
            throw new NoExisteElEmpleadoException();
        }
        retorno[0] = empleado;
        retorno[1] = departamento;
        return retorno;
    }

    private static Object[] ejercicio5_1_5(Session session, int id) throws NoExisteElEmpleadoException{
        //Voy a devolver un array de objetos para poder sacar también el objeto departamento. Lo que me hubiera gustado
        // es que el objeto Empleado tuviera un atributo que fuera el objeto Departamento pero parece que no opera así por defecto Hibernate
        Object[] retorno = new Object[2];
        EmpleadosEntity empleado = session.get(EmpleadosEntity.class, id);
        DepartamentosEntity departamento;
        if (empleado != null){
            departamento = session.load(DepartamentosEntity.class, empleado.getIdDepartamento());
        }else{
            throw new NoExisteElEmpleadoException();
        }
        retorno[0] = empleado;
        retorno[1] = departamento;
        return retorno;
    }

    private static void ejercicio5_1_4(Session session) {
        try {
            Transaction transaction = session.beginTransaction();
            //No puedo cargar el departamento Ventas si no conozco su id con lo que sabemos hasta ahora. Necesitamos HQL
            DepartamentosEntity departamento = session.get(DepartamentosEntity.class, 80);
            if (departamento != null){
                session.delete(departamento);
            }else{
                System.out.println("Aquí lo suyo sería lanzar una excepción de que no existe o informar de alguna manera, pero es mucho mejor el System.out.println, que Sergio nos lo dice todos los días. O mejor todavía, le pones err en vez de out, que así te sale en rojito. Soy una máquina, yo nunca voy a estar al otro lado del dedo");
            }
            transaction.commit();
        }catch (Exception e){
        System.out.println("El System.out.println es una práctica recomendadísima en las excepciones. Comprueba las claves ajenas antes de borrar, anda.");
        }
    }

    private static void ejercicio5_1_3(Session session) {
        Transaction transaction = session.beginTransaction();
        EmpleadosEntity empleado = session.get(EmpleadosEntity.class, 301);
        if (empleado != null){
            empleado.setSalario(empleado.getSalario().multiply(new BigDecimal("1.05")));
            session.update(empleado);
        }
        transaction.commit();

        transaction = session.beginTransaction();
        DepartamentosEntity departamento = session.get(DepartamentosEntity.class, 20);
        if (departamento != null){
            departamento.setIdLocalizacion(4000);
            session.update(departamento);
        }
        transaction.commit();

    }

    private static void ejercicio5_1_2(Session session) {
        Transaction transaction = session.beginTransaction();
        EmpleadosEntity[] empleados = new EmpleadosEntity[5];
        empleados[0] = new EmpleadosEntity(1001, "Juan", "Perez", "juan@example.com", "123456789", Date.valueOf("2023-01-01"), "IT_PROG", new BigDecimal(5000), new BigDecimal("0.2"), 101, 100);
        empleados[1] = new EmpleadosEntity(1002, "Maria", "Lopez", "maria@example.com", "987654321", Date.valueOf("2023-02-01"), "IT_PROG", new BigDecimal(6000), new BigDecimal("0.15"), 101, 100);
        empleados[2] = new EmpleadosEntity(1003, "Carlos", "Gomez", "carlos@example.com", "555555555", Date.valueOf("2023-03-01"), "IT_PROG", new BigDecimal(5500), new BigDecimal("0.1"), 101, 100);
        empleados[3] = new EmpleadosEntity(1004, "Ana", "Rodriguez", "ana@example.com", "444444444", Date.valueOf("2023-04-01"), "IT_PROG", new BigDecimal(4800), new BigDecimal("0.12"), 101, 100);
        empleados[4] = new EmpleadosEntity(1005, "Pedro", "Martinez", "pedro@example.com", "777777777", Date.valueOf("2023-05-01"), "IT_PROG", new BigDecimal(8000), new BigDecimal("0.3"), 101, 100);

        for (EmpleadosEntity empleado : empleados){
            session.persist(empleado);
        }

        transaction.commit();
    }

    private static void ejercicio5_1_1(Session session) {
        Transaction transaction = session.beginTransaction();

        LocalizacionesEntity localizacion = new LocalizacionesEntity();

        localizacion.setIdLocalizacion(5000);
        localizacion.setDireccion("Plaza de la constitución S/N");
        localizacion.setCodigoPostal("28411");
        localizacion.setCiudad("Moralzarzal");
        localizacion.setProvincia("Madrid");
        localizacion.setIdPais("ES");

        DepartamentosEntity departamento1 = new DepartamentosEntity();
        DepartamentosEntity departamento2 = new DepartamentosEntity();
        DepartamentosEntity departamento3 = new DepartamentosEntity();

        departamento1.setIdDepartamento(100);
        departamento2.setIdDepartamento(200);
        departamento3.setIdDepartamento(300);

        departamento1.setNombreDepartamento("Departamento 1");
        departamento2.setNombreDepartamento("Departamento 2");
        departamento3.setNombreDepartamento("Departamento 3");

        departamento1.setIdDirector(100);
        departamento2.setIdDirector(100);
        departamento3.setIdDirector(100);

        departamento1.setIdLocalizacion(5000);
        departamento2.setIdLocalizacion(5000);
        departamento3.setIdLocalizacion(5000);

        session.persist(localizacion);

        session.persist(departamento1);
        session.persist(departamento2);
        session.persist(departamento3);

        transaction.commit();
    }
}