package edu.ynu.dao;

import edu.ynu.entities.Food;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDao extends AbstractDao<Food>{

    @Override
    public List<Food> exactQueryId(Integer id) throws SQLException {

        var foods = new ArrayList<Food>();

        var sql="select * from Food where foodId="+id;
        var result=DBHelper.executeQuery(sql);

        while (result.next()) {
            var name = result.getString("foodName");
            var ownId = result.getInt("foodId");
            var explain = result.getString("foodExplain");
            var price=result.getDouble("foodPrice");
            var bsId=result.getInt("businessId");

            var fd = Food.builder()
                    .foodId(ownId)
                    .foodName(name)
                    .foodExplain(explain)
                    .foodPrice(price)
                    .businessId(bsId)
                    .build();

            foods.add(fd);
        }
        return foods;
    }

    @Override
    public boolean save(Food food) throws SQLException {

        var sql = "INSERT INTO Food (foodName,foodId,foodExplain,foodPrice,businessId) " +
                "values ('" + food.getFoodName() + "','" + food.getFoodId() +"','"
                +food.getFoodExplain()+ "','"+food.getFoodPrice()+"','"+food.getBusinessId()+"')";

        var result = DBHelper.executeUpdate(sql);

        return result > 0;
    }


    public List<Food> queryByBusinessId(Integer bsId)throws SQLException{
        var foods=new ArrayList<Food>();

        var sql="select * from Food where businessId="+bsId;
        var resultSet=DBHelper.executeQuery(sql);

        while(resultSet.next()){
            var id=resultSet.getInt("foodId");
            var name=resultSet.getString("foodName");
            var explain=resultSet.getString("foodExplain");
            var price=resultSet.getDouble("foodPrice");

            var food=Food.builder()
                    .foodId(id)
                    .foodName(name)
                    .foodExplain(explain)
                    .foodPrice(price)
                    .businessId(bsId)
                    .build();

            foods.add(food);
        }
        return foods;
    }

    public boolean updateById (Food food) throws SQLException {
        String sql = "update Food set foodName=?,foodExplain=?,foodPrice=? where foodId=?";
        var con = DBHelper.getConnection();
        var pst = con.prepareStatement(sql);

        pst.setString(1, food.getFoodName());
        pst.setString(2, food.getFoodExplain());
        pst.setDouble(3, food.getFoodPrice());
        pst.setInt(4, food.getFoodId());
        System.out.println(food.getFoodId());

        return pst.executeUpdate()>0;
    }

    public boolean removeById (Integer id) throws SQLException {
        var sql="delete from Food where foodId= "+id;
        return  DBHelper.executeUpdate(sql)>0;
    }
}
