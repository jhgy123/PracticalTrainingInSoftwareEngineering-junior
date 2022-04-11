package edu.ynu.service;

import edu.ynu.dao.BusinessDao;
import edu.ynu.dao.UserDao;
import edu.ynu.entities.Admin;
import edu.ynu.dao.AdminDao;
import edu.ynu.entities.Business;
import edu.ynu.entities.User;

import java.sql.SQLException;
import java.util.List;

public class AdminService {

    public Admin login (Integer id,String password) throws Exception{
        var adminDao=new AdminDao();
        var admin=adminDao.exactQuery(id,password);
        if(admin.size()==1)  return admin.get(0);
        else return null;
    }

    public boolean Insert (String name,String password)throws Exception{
        var ad=new Admin();
        var add=new AdminDao();
        ad.setAdminName(name);
        ad.setPassword(password);
        return add.save(ad);
    }

    public boolean userSave(User us) throws SQLException {
        var adminDao=new AdminDao();
        return adminDao.userSave(us);
    }

    public boolean businessSave(Business bs)throws SQLException{
        var adminDao =new AdminDao();
        return adminDao.BusinessSave(bs);
    }

    public boolean userRemoveById(int id) throws SQLException {
        var adminDao=new AdminDao();
        return adminDao.userDelete(id);
    }

    public  boolean businessRemoveById(int id)throws  SQLException{
        var adminDao =new AdminDao();
        return adminDao.businessDelete(id);
    }

    public boolean adminSave(Admin ad) throws SQLException {
        var adminDao=new AdminDao();
        return adminDao.adminSave(ad);
    }

    public List<Business> listAllBusiness() throws SQLException {
        var businessDao=new BusinessDao();
        List<Business> bs=businessDao.dimQuery(null);
        return bs;
    }

    public List<User> listAllUser()throws SQLException{
        var userDao=new UserDao();
        List<User> us=userDao.dimQuery(null);
        return us;
    }

    public List<User> queryUserByName(String userName)throws Exception{
        var usDao=new UserDao();
        var usList=usDao.dimQuery(userName);
        return usList;
    }

    public User queryUserById(Integer id)throws SQLException{
        var userDao=new UserDao();
        var users=userDao.exactQueryId(id);
        if(users.size()==1) return users.get(0);
        else return null;
    }

    public List<Business> queryBusinessByName(String businessName)throws Exception{
        var businessDao=new BusinessDao();
        var businessList=businessDao.dimQuery(businessName);
        return businessList;
    }

    public Business queryBusinessById(Integer id)throws SQLException{
        var businessDao=new BusinessDao();
        var bs=businessDao.exactQueryId(id);
        if(bs.size()==1)  return bs.get(0);
        else return null;
    }

    public void showOwnInfo(Admin admin){
        var adminDao=new AdminDao();
        adminDao.showOwnInfo(admin);
    }

    public boolean update(Admin admin)throws SQLException{
        var adminDao=new AdminDao();
        return adminDao.update(admin);
    }

    public boolean updatePassword(Integer id,String password) throws SQLException {
        var adminDao=new AdminDao();
        return adminDao.updatePassword(id,password);
    }
}
