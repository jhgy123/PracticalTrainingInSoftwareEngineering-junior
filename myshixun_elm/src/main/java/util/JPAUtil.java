package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
//    实体管理工厂
    public static EntityManagerFactory factory;
    static {
        factory = Persistence.createEntityManagerFactory("default");
    }
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}