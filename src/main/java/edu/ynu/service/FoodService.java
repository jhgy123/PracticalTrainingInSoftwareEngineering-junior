package edu.ynu.service;

import edu.ynu.entities.Food;
import edu.ynu.dao.FoodDao;

import java.sql.SQLException;
import java.util.List;

public class FoodService {
    public List<Food> queryFoodById(Integer id) throws SQLException {
        var foodDao=new FoodDao();
        return foodDao.exactQueryId(id);
    }

    public List<Food> queryByBusinessId(Integer bsId) throws SQLException{
        var foodDao=new FoodDao();
        return foodDao.queryByBusinessId(bsId);
    }

    public boolean saveFood(Food food) throws SQLException {
        var foodDao=new FoodDao();
        return foodDao.save(food);
    }

    public boolean updateById(Food food) throws SQLException {
        var foodDao=new FoodDao();
        return foodDao.updateById(food);
    }

    public boolean removeFoodById(Integer id)throws  SQLException{
        var foodDao=new FoodDao();
        return foodDao.removeById(id);
    }
}
