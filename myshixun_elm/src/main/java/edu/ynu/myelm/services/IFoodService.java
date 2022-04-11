package edu.ynu.myelm.services;

import edu.ynu.myelm.entities.Business;

public interface IFoodService {
    public boolean listFoodAll(Business business);//查询商家自己的所有菜品
    public boolean createFood(Business business);//创建商家自己的菜品
    public boolean updateFood(Business business);//修改商家自己菜品
    public boolean deleteFood(Business business);//删除商家自己菜品


}
