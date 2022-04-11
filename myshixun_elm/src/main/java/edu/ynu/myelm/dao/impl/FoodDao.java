package edu.ynu.myelm.dao.impl;

import edu.ynu.myelm.entities.Business;
import edu.ynu.myelm.entities.Food;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class FoodDao extends BaseDao<Food>{ //继承BaseDao泛型类

    //通过商家的编号查询菜品列表
    public List<Food> queryFoodByBussinessId(int businessid){
        try{
            EntityManager manager = JPAUtil.getEntityManager();
            //根据businessid查询Food的jpql语句
            String sql = "select f from Food f where f.business="+businessid;
            Query query = manager.createQuery(sql);
            List<Food> list = query.getResultList();
            manager.close();
            return list;
        }catch (Exception exception){
            return null;
        }
    }


}
