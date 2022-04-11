package edu.ynu.myelm.dao.impl;

import edu.ynu.myelm.dao.IBaseDao;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDao<T> implements IBaseDao<T> {//对接口的实现
    private Class<T> clz;
    public BaseDao() {//获取到T的类型
        ParameterizedType parametclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parametclass.getActualTypeArguments();
        clz = (Class<T>) actualTypeArguments[0];
    }
    @Override
    public boolean save(Object s) {//重写save()方法，保存对象并持久化到数据库
        try {
            EntityManager manager = JPAUtil.getEntityManager();//开启一个实体管理器
            manager.getTransaction().begin();//事务开始
            manager.persist(s);//将要保存的对象持久化到数据库中
            manager.getTransaction().commit();//事务提交
            manager.close();
            return true;
        }catch (Exception exception){
            return false;
        }
    }

    @Override
    public boolean update(Object s) {//重写updateOrAdd()方法,如有主键相同的字段，则修改；主键不同的字段，则新增
        try {
            EntityManager manager = JPAUtil.getEntityManager();//开启一个实体管理器
            manager.getTransaction().begin();//事务开始
            manager.merge(s);//将修改后的对象持久化到数据库中
            manager.getTransaction().commit();//事务提交
            manager.close();
            return true;
        }catch (Exception exception) {
            return false;
        }

    }

    @Override
    public boolean delete(Serializable i) {//重写delete()方法，删除记录的方法,通过主键删除
        try {
            EntityManager manager = JPAUtil.getEntityManager();//开启一个实体管理器
            manager.getTransaction().begin();//事务开始
            T t = manager.find(clz, i);//按要求查找要删除的数据库记录的对象
            manager.remove(t);//将要删除的对象从数据库中删除
            manager.getTransaction().commit();//事务提交
            manager.close();
            return true;
        }catch (Exception exception) {
            return false;
        }
    }

    @Override
    public T getOne(Serializable i) {//重写getOne()方法,根据主键进行查找获取对象
        try {
            EntityManager manager = JPAUtil.getEntityManager();//开启一个实体管理器
            T result = manager.find(clz, i);//按要求查找要符合要求的数据库记录的对象，并获取对象
            manager.close();
            return result;
        }catch (Exception exception){
            return null;
        }
    }

    @Override
    public List<T> getAll() {//重写getAll()方法
        try {
            EntityManager manager = JPAUtil.getEntityManager();//开启一个实体管理器
            String sql = "select p from "+ clz.getName() +" as p";//编写JPQL语句
            Query query = manager.createQuery(sql);//执行JPQL语句
            List<T> list= query.getResultList();//获取结果集
            manager.close();
            return list;
        }catch (Exception exception){
            return null;
        }
    }

}
