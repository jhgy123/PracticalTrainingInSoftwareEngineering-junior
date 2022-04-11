package edu.ynu.service;

import edu.ynu.dao.BusinessDao;
import edu.ynu.dao.FoodDao;
import edu.ynu.dao.UserDao;
import edu.ynu.entities.Business;
import edu.ynu.entities.Food;
import edu.ynu.entities.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    public User login(Integer userId, String password) throws SQLException {
        var usDao = new UserDao();
        var usLst = usDao.exactQuery(userId, password);

        if (usLst.size()==1)
            return usLst.get(0);
        else {
            return null;
        }
    }
    public List<Business> listBusinessByName(String BusinessName) throws SQLException{
        var businessDao=new BusinessDao();
        List<Business> bs=businessDao.dimQuery(BusinessName);
        return bs;
    }
    public Business listBusinessById(Integer bsId) throws SQLException{
        var businessDao=new BusinessDao();
        List<Business> bs=businessDao.exactQueryId(bsId);
        if (bs.size()==1)
            return bs.get(0);
        else {
            return null;
        }
    }
    public List<Business> listAllBusiness() throws SQLException {
        var businessDao=new BusinessDao();
        List<Business> bs=businessDao.dimQuery(null);
        return bs;
    }
    public void showOwnInfo(User user){
        var userDao=new UserDao();
        userDao.showOwnInfo(user);
    }
    public boolean updatePassword(Integer id,String password) throws SQLException {
        var userDao=new UserDao();
        return userDao.updatePassword(id,password);
    }
    public boolean update(User us) throws SQLException {
        var userDao=new UserDao();
        return userDao.update(us);
    }
    public List<Food> queryFoodByBusinessId(Integer id) throws SQLException {
        var foodDao=new FoodDao();
        return foodDao.queryByBusinessId(id);
    }
}
