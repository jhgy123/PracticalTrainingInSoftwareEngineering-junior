package edu.ynu.myelm.dao.impl;

import edu.ynu.myelm.entities.Business;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class BusinessDao extends BaseDao<Business>{ //继承BaseDao泛型类

    //根据商家的名称关键字、地址关键字、介绍关键字模糊查询
    public List<Business> query(String nameKey,String addressKey,String explainKey){
        try{
            EntityManager manager = JPAUtil.getEntityManager();
            //模糊查询的jpql语句
            String sql = "select b from Business b where b.name like '%"+nameKey+"%' and b.address like '%"+addressKey+"%' and b.explain like '%"+explainKey+"%'";
            Query query = manager.createQuery(sql);
            List<Business> list = query.getResultList();
            manager.close();
            return list;
        }catch (Exception exception){
            return null;
        }

    }


}
