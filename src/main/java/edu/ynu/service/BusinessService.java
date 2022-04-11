package edu.ynu.service;

import edu.ynu.dao.BusinessDao;
import edu.ynu.entities.Business;
import edu.ynu.entities.Food;

import java.sql.SQLException;

public class BusinessService {

    public Business login(Integer businessId, String password) throws SQLException {
        var bsDao = new BusinessDao();
        var usLst = bsDao.exactQuery(businessId, password);

        if (usLst.size() == 1)
            return usLst.get(0);
        else {
            return null;
        }
    }

    public boolean updatePassword(Integer id,String password) throws SQLException {
        var businessDao=new BusinessDao();
        return businessDao.updatePassword(id,password);
    }

    public boolean update(Business bs) throws SQLException {
        var businessDao=new BusinessDao();
        return businessDao.update(bs);
    }

    public void showOwnInfo(Business business)throws  SQLException{
        var businessDao=new BusinessDao();
        businessDao.showOwnInfo(business);
    }

    public Food queryOwnFoodById(Business bs, Integer id)throws SQLException{
        var businessDao=new BusinessDao();
        var foods = businessDao.queryOwnFoodById(bs, id);
        System.out.println(foods.size());
        if(foods.size()==1)
            return foods.get(0);
        else return null;
    }
}
