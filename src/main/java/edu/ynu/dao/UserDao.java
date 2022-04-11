package edu.ynu.dao;

import edu.ynu.entities.Business;
import edu.ynu.entities.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> {

    @Override
    public boolean save(User us) throws SQLException {

        var sql = "INSERT INTO Users (id,organization,username,password) " +
                "values (9991,0 ,'" + us.getUserName() + "','" + us.getPassword() + "')";

        var result = DBHelper.executeUpdate(sql);

        return result > 0;
    }

    @Override
    public ArrayList<User> exactQuery(Integer userId, String password) throws SQLException {
        var users = new ArrayList<User>();

        if (password == null || password.isEmpty())
            return null;

        var sql = "select * from Users where userId ='" + userId + "' and password ='" + password + "'";

        var result = DBHelper.executeQuery(sql);

        while (result.next()) {
            var organization = result.getString("organization");
            var name=result.getString("userName");
            var sex=result.getString("sex");

            var us = User.builder()
                    .userName(name)
                    .userId(userId)
                    .password(password)
                    .organization(organization)
                    .sex(sex)
                    .build();

            users.add(us);
        }
        return users;
    }

    @Override
    public List<User> exactQueryId(Integer id) throws SQLException{

        var users = new ArrayList<User>();

        var sql = "select * from Users where userId = '" + id + "'";

        var result = DBHelper.executeQuery(sql);

        while (result.next()) {
            var name = result.getString("userName");
            var ps = result.getString("password");
            var organization=result.getString("organization");
            var sex=result.getString("sex");

            var user = User.builder()
                    .userName(name)
                    .userId(id)
                    .password(ps)
                    .organization(organization)
                    .sex(sex)
                    .build();

            users.add(user);
        }
        return users;
    }

    @Override
    public void showOwnInfo(User user){
        System.out.println("账户ID："+user.getUserId());
        System.out.println("账户名称："+user.getUserName());
        System.out.println("账户身份："+user.getOrganization());
        System.out.println("账户性别："+user.getSex());
    }

    @Override
    public boolean update(User user)throws SQLException{
        var sql="update Users set userName=?,password=?,organization=?,sex=? where userId=?";

        var con=DBHelper.getConnection();
        var pst=con.prepareStatement(sql);

        pst.setString(1,user.getUserName());
        pst.setString(2,user.getPassword());
        pst.setString(3,user.getOrganization());
        pst.setString(4,user.getSex());
        pst.setInt(5,user.getUserId());

        return pst.executeUpdate()>0;
    }

    @Override
    public boolean updatePassword(Integer id, String password)throws SQLException{
        if (password == null || password.isEmpty())
            password = "%";

        var sql="update Users set password="+password+ " where userid="+id;

        return DBHelper.executeUpdate(sql)>0;
    }

    public ArrayList<User> dimQuery(String userName) throws SQLException {

        var users = new ArrayList<User>();

        if (userName == null || userName.isEmpty())
            userName = "%";

        var sql = "select * from Users where username like '%" + userName + "%'";

        var result = DBHelper.executeQuery(sql);

        while (result.next()) {
            var name = result.getString("userName");
            var ps = result.getString("password");
            var organization = result.getString("organization");
            var sex=result.getString("sex");
            var id=result.getInt("userId");

            var us = User.builder()
                    .userName(name)
                    .password(ps)
                    .organization(organization)
                    .userId(id)
                    .sex(sex)
                    .build();

            users.add(us);
        }
        return users;
    }

}
