package edu.ynu.myelm.services;

import edu.ynu.myelm.entities.Admin;
import edu.ynu.myelm.entities.Business;

public interface IBusinessService {
    public Business login();//商家登录

    //对商家对象进行操作
    public boolean listMyInf(Business business);//查看当前商家信息
    public boolean updateBusinessInf(Business business);//修改当前商家信息
    public boolean updateBusinessPassword(Business business);//修改商家密码

    //对菜品对象进行操作
    public boolean FoodManager(Business user);//所属商品管理
}
