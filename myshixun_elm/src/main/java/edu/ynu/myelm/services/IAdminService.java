package edu.ynu.myelm.services;

import edu.ynu.myelm.entities.Admin;

public interface IAdminService {
    public Admin login();//超级管理员或管理员登录
    //Admin对象的操作
    public boolean addAdmin();//新增管理员用户
    public boolean deleteAdmin();//删除管理员用户
    public boolean updatePassword(Admin admin);//修改管理员用户的登录密码
    public boolean listAdminAll();//显示所有的管理员用户

    //对商家对象的操作
    public boolean listBusinessAll();//显示所有商家信息
    public boolean searchBusiness();//搜索商家
    public boolean addBusiness();//新建商家
    public boolean deleteBusiness();//删除商家

    //对菜单对象的操作
    public boolean listFoodAllByBusinessId();//查询商家的所有菜品
}
