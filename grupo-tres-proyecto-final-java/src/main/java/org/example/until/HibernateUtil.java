package org.example.until;

import models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    //SessionFactory es una clase de Hibernate para guardar la conexion
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Propiedades de conexio de Hibernate

                //MySQL
                /*Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/proyectofinal?serverTimezone=UTC");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");*/

                //Postgresql
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/proyecto_final");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.USER, "marineslemus");
                settings.put(Environment.PASS, "postgres");

                settings.put(Environment.SHOW_SQL, "false");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                //Manejar la construccion de tablas de la base de datos
                //settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);
                //Asociar las clases
                configuration.addAnnotatedClass(Boleta.class);
                configuration.addAnnotatedClass(Compra.class);
                configuration.addAnnotatedClass(Producto.class);
                configuration.addAnnotatedClass(Usuario.class);
                configuration.addAnnotatedClass(Tarjeta.class);
                //Servicio de parametros de conexion
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                //Crear conexion. Será utilizada por los DAO's
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
