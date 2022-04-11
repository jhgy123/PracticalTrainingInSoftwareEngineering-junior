package edu.ynu.dao;

import edu.ynu.entities.Business;
import edu.ynu.entities.Food;
import edu.ynu.entities.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessDao extends AbstractDao<Business> {
    @Override
    public boolean save(Business bs) throws SQLException {

        var sql = "INSERT INTO Business (businessId,businessName,businessAddress,password,businessExplain,startPrice,deliverPrice) " +
                "values ('" + bs.getBusinessId() + "','" + bs.getBusinessName() + "','" + bs.getBusinessAddress() + "','"
                + bs.getPassword() + "','" + bs.getBusinessExplain() + "','" + bs.getStartPrice() + "','" + bs.getDeliverPrice() + "')";

        var result = DBHelper.executeUpdate(sql);

        return result > 0;
    }

    @Override
    public List<Business> dimQuery(String BusinessName) throws SQLException {

        var bs = new ArrayList<Business>();
        if (BusinessName == null || BusinessName.isEmpty())
            BusinessName = "%";

        var sql = "select * from Business where businessName like '%" + BusinessName + "%'";

        var result = DBHelper.executeQuery(sql);

        while (result.next()) {
            var name = result.getString("businessName");
            var address = result.getString("businessAddress");
            var ps = result.getString("password");
            var id = result.getInt("businessId");
            var explain = result.getString("businessExplain");
            var sp = result.getDouble("startPrice");
            var dp = result.getDouble("deliverPrice");

            var business = Business.builder()
                    .businessId(id)
                    .businessName(name)
                    .businessAddress(address)
                    .password(ps)
                    .businessExplain(explain)
                    .startPrice(sp)
                    .deliverPrice(dp)
                    .build();

            bs.add(business);
        }
        return bs;
    }

    @Override
    public List<Business> exactQuery(Integer businessId, String password) throws SQLException {
        if (password == null || password.isEmpty())
            password = "%";

        var bs = new ArrayList<Business>();

        var sql = "select * from Business where businessId =" + businessId + " and password ='" + password + "'";

        var result = DBHelper.executeQuery(sql);

        while (result.next()) {
            var name = result.getString("businessName");
            var bid = result.getInt("businessId");
            var address = result.getString("businessAddress");
            var explain = result.getString("businessExplain");
            var sp = result.getDouble("startPrice");
            var dp = result.getDouble("deliverPrice");
            var business = Business.builder()
                    .businessName(name)
                    .password(password)
                    .businessId(bid)
                    .deliverPrice(dp)
                    .businessAddress(address)
                    .businessExplain(explain)
                    .startPrice(sp)
                    .build();

            bs.add(business);
        }
        return bs;
    }

    @Override
    public void showOwnInfo(Business business){
        System.out.println("商户ID："+business.getBusinessId());
        System.out.println("商户名称："+business.getBusinessName());
        System.out.println("商户住址："+business.getBusinessAddress());
        System.out.println("商户注释："+business.getBusinessExplain());
        System.out.println("起送费："+business.getStartPrice());
        System.out.println("配送费："+business.getDeliverPrice());
    }

    @Override
    public boolean update(Business business) throws SQLException {
        String sql = "update business set businessName=?,businessAddress=?,businessExplain=?," +
                "startPrice=?,deliverPrice=? where businessId=?";

        var con = DBHelper.getConnection();
        var pst = con.prepareStatement(sql);

        pst.setString(1, business.getBusinessName());
        pst.setString(2, business.getBusinessAddress());
        pst.setString(3, business.getBusinessExplain());
        pst.setDouble(4, business.getStartPrice());
        pst.setDouble(5, business.getDeliverPrice());
        pst.setInt(6, business.getBusinessId());

        return pst.executeUpdate()>0;
    }

    @Override
    public boolean updatePassword(Integer id, String password) throws SQLException {
        if (password == null || password.isEmpty())
            password = "%";

        var sql = "update Business set password='" + password + "'where businessId =" + id;
        return DBHelper.executeUpdate(sql) > 0;
    }

    @Override
    public List<Business> exactQueryId(Integer id) throws SQLException {

        var bs = new ArrayList<Business>();

        var sql = "select * from business where businessId = '" + id + "'";

        var result = DBHelper.executeQuery(sql);

        while (result.next()) {
            var name = result.getString("businessName");
            var ps = result.getString("password");
            var address = result.getString("businessAddress");
            var explain = result.getString("businessExplain");
            var sp = result.getDouble("startPrice");
            var dp = result.getDouble("deliverPrice");

            var business = Business.builder()
                    .businessName(name)
                    .password(ps)
                    .businessId(id)
                    .deliverPrice(dp)
                    .businessAddress(address)
                    .businessExplain(explain)
                    .startPrice(sp)
                    .build();

            bs.add(business);
        }
        return bs;
    }

    public List<Food> queryOwnFoodById(Business bs,Integer id) throws SQLException {
        List<Food> foods=new ArrayList<Food>();
        var sql="select * from Food where foodId="+id+" and businessId = "+bs.getBusinessId();
        System.out.println(sql);

        var resultSet=DBHelper.executeQuery(sql);

        while(resultSet.next()){
            var name=resultSet.getString("foodName");
            var explain=resultSet.getString("foodExplain");
            var price=resultSet.getDouble("foodPrice");

            var food=Food.builder()
                    .foodId(id)
                    .foodName(name)
                    .foodExplain(explain)
                    .foodPrice(price)
                    .businessId(bs.getBusinessId())
                    .build();

            foods.add(food);
        }
        return foods;
    }

}

