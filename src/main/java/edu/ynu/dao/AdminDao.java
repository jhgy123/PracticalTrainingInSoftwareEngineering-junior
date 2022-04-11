package edu.ynu.dao;

import edu.ynu.entities.Admin;
import edu.ynu.entities.Business;
import edu.ynu.entities.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao extends AbstractDao<Admin>{

    @Override
    public List<Admin> exactQuery(Integer adminId,String Password)throws SQLException{
        var admins=new ArrayList<Admin>();

        if (Password == null || Password.isEmpty())
            Password = "%";

        var sql = "select * from Admin where adminId = '" + adminId + "' and password ='" + Password + "'";
        var result=DBHelper.executeQuery(sql);

        while(result.next()){
            var name=result.getString("AdminName");

            var admin = Admin.builder()
                    .adminId(adminId)
                    .adminName(name)
                    .password(Password)
                    .build();

           admins.add(admin) ;
        }
        return admins;
    }

    @Override
    public List<Admin> dimQuery(String adminName) throws SQLException{
        var admins=new ArrayList<Admin>();

        if(adminName==null || adminName.isEmpty())
            adminName="%";

        var sql="select * from Admin where name = '%" + adminName + "%'";
        var result=DBHelper.executeQuery(sql);

        while(result.next()){
            var name=result.getString("AdminName");
            var password=result.getString("password");
            var adminId=result.getInt("adminId");
            var admin = Admin.builder()
                    .adminId(adminId)
                    .adminName(name)
                    .password(password)
                    .build();

            admins.add(admin) ;
        }
        return admins;
    }

    @Override
    public void showOwnInfo(Admin admin){
        System.out.println("管理员ID："+admin.getAdminId());
        System.out.println("管理员名称："+admin.getAdminName());
    }

    @Override
    public boolean update(Admin admin)throws SQLException{
        var sql="update Admin set adminName=? , password=? where adminId=?";

        var con=DBHelper.getConnection();
        var pst=con.prepareStatement(sql);

        pst.setString(1, admin.getAdminName());
        pst.setString(2, admin.getPassword());
        pst.setInt(3,admin.getAdminId());

        return pst.executeUpdate()>0;
    }

    @Override
    public boolean updatePassword(Integer id,String password) throws  SQLException{
        if (password == null || password.isEmpty())
            password = "%";

        var sql = "update Admin set password = '"+password +"'where AdminId = "+id;

        return DBHelper.executeUpdate(sql)>0;
    }


    public boolean userSave(User us) throws SQLException {
        var sql = "INSERT INTO Users (userId,organization,userName,password,sex) " +
                "values ('"+us.getUserId()+"','" +us.getOrganization()+"','"+ us.getUserName() + "','"
                + us.getPassword() +"','" + us.getSex() +"')";
        System.out.println(sql);
        var result = DBHelper.executeUpdate(sql);

        return result > 0;
    }

    public boolean BusinessSave(Business bs) throws SQLException {
        var sql = "INSERT INTO Business (businessId,businessName,businessAddress," +
                "password,businessExplain,startPrice,deliverPrice) " +
                "values ('"+bs.getBusinessId()+"','" + bs.getBusinessName() + "','"+bs.getBusinessAddress()+"','"
                +bs.getPassword()+"','"+bs.getBusinessExplain()+"','"+bs.getStartPrice()+"','"+bs.getDeliverPrice()+"')";

        var result = DBHelper.executeUpdate(sql);

        return result > 0;
    }

    public boolean userDelete(int id) throws SQLException {
        var sql="delete from Users where userId="+id;
        var result=DBHelper.executeUpdate(sql);
        return result > 0;
    }

    public boolean businessDelete(int id)throws SQLException{
        var sql="delete from Business where businessId="+id;

        var result=DBHelper.executeUpdate(sql);

        return result > 0;
    }

    public boolean adminSave(Admin ad) throws SQLException {
        var sql = "INSERT INTO Admin (AdminId,adminName,password) " +
                "values ('"+ad.getAdminId()+"','" + ad.getAdminName() + "','"
                + ad.getPassword()+"')";

        var result = DBHelper.executeUpdate(sql);

        return result > 0;
    }


}
