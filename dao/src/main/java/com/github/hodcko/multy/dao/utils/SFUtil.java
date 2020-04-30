package com.github.hodcko.multy.dao.utils;

import com.github.hodcko.multy.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class SFUtil {
    private static final SessionFactory sessionFactory;
    private static ResourceBundle resource = ResourceBundle.getBundle("db");
    private static String url = resource.getString("url");

    static {
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        // Hibernate settings equivalent to hibernate.cfg.xml's properties
        Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, url);
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "Sasha123");
        settings.put(Environment.HBM2DDL_AUTO, "validate");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.USE_SQL_COMMENTS, "true");
        settings.put(Environment.FORMAT_SQL, "false");
        settings.put(Environment.ISOLATION, "2");
        settings.put(Environment.CACHE_REGION_FACTORY, "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        settings.put(Environment.USE_SECOND_LEVEL_CACHE, "true");


        // settings.put(Environment.STORAGE_ENGINE, "innodb");
        // Apply settings
        serviceRegistryBuilder.applySettings(settings);
        // Create registry
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        // Create MetadataSources
        MetadataSources sources = new MetadataSources(serviceRegistry);
        sources.addAnnotatedClass(Student.class);
        sources.addAnnotatedClass(Teacher.class);
        sources.addAnnotatedClass(AuthUser.class);
        sources.addAnnotatedClass(Curs.class);
        sources.addAnnotatedClass(DTOGroup.class);
        sources.addAnnotatedClass(Gradebook.class);
        // Create Metadata
        Metadata metadata = sources.getMetadataBuilder().build();
        // Create SessionFactory
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
