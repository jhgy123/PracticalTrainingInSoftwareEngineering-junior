package edu.ynu.myelm.services.impl;

import edu.ynu.myelm.dao.impl.BusinessDao;
import edu.ynu.myelm.entities.Admin;
import edu.ynu.myelm.entities.Business;
import edu.ynu.myelm.services.IBusinessService;

import java.util.Scanner;

public class BusinessService implements IBusinessService {

    private BusinessDao businessdao = new BusinessDao();//创建BusinessDao对象

    @Override
    //商家登录，当商家编号或密码错误或输入格式错误时，返回null
    public Business login() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入商家编号：");
        int businessId;
        try {
            businessId = input.nextInt();
        }catch (Exception exception){ //输入异常处理
            System.out.println("******输入的商家编号格式错误！******");
            return null;
        }
        System.out.println("请输入商家密码：");
        String password = input.next();
//        AdminDao admindao = new AdminDao();//创建AdminDao对象
        Business business=businessdao.getOne(businessId);
        if(business==null||!business.getPassword().equals(password)){
            System.out.println("******输入的商家编号或密码错误！******");
            return null;
        }
        else {
            System.out.println("******"+business.getName()+"用户，您好！欢迎使用“饿了么”商家系统！******");
            return business;
        }
    }

    @Override
    //商家查询自己的用户信息
    public boolean listMyInf(Business business) {
        System.out.println("=============================================================商家信息表=============================================================");
        System.out.format("%-5s %-20s %-30s %-20s %-20s %-20s %n","商家编号","商家名称","商家地址","商家介绍","起送费","配送费");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        try{
            System.out.format("%-8s %-20s %-30s %-20s %-20s %-20s %n",business.getId(),business.getName(),business.getAddress(),business.getExplain(),business.getStarPrice(),business.getDeliveryPrice());
        }catch (Exception e){
            return false;
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        return true;
    }

    @Override
    //修改商家自己的用户信息,输入的起送费、配送费格式错误时返回false
    public boolean updateBusinessInf(Business business) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入要修改的”商家名称“(*****注：如若不修改则直输入“*”继续操作！*****)：");
        String updateName = input.next();
        updateName=updateName.trim();//去除首位空格符
        System.out.println("请输入要修改的“商家地址”(*****注：如若不修改则直输入“*”继续操作！*****)：");
        String updateAddress = input.next();
        updateAddress=updateAddress.trim();//去除首位空格符
        System.out.println("请输入要修改的“商家介绍”(*****注：如若不修改则直输入“*”继续操作！*****)：");
        String updateExplain = input.next();
        updateExplain=updateExplain.trim();//去除首位空格符
        float updateStarPrice,updateDeliveryPrice;
        System.out.println("请输入要修改的“起送费”(*****注：如若不修改则直输入“*”继续操作！*****)：");
        String strUpdateStarPrice = input.next();
        strUpdateStarPrice=strUpdateStarPrice.trim();//去除首位空格符
        if(!strUpdateStarPrice.equals("*")){
            try {  //输入格式错误处理
                updateStarPrice=Float.parseFloat(strUpdateStarPrice);
            }catch (Exception e){
                System.out.println("******输入要修改的“起送费”格式错误！******");
                return false;
            }
            business.setStarPrice(updateStarPrice);
        }
        System.out.println("请输入要修改的“配送费”(*****注：如若不修改则直输入“*”继续操作！*****)：");
        String strUpdateDeliveryPrice = input.next();
        strUpdateDeliveryPrice=strUpdateDeliveryPrice.trim();//去除首位空格符
        if(!strUpdateDeliveryPrice.equals("*")){
            try {  //输入格式错误处理
                updateDeliveryPrice=Float.parseFloat(strUpdateDeliveryPrice);
            }catch (Exception e){
                System.out.println("******输入要修改的“配送费”格式错误！******");
                return false;
            }
            business.setDeliveryPrice(updateDeliveryPrice);
        }
        if(!updateName.equals("*")){
            business.setName(updateName);
        }
        if(!updateAddress.equals("*")){
            business.setAddress(updateAddress);
        }
        if(!updateExplain.equals("*")){
            business.setExplain(updateExplain);
        }
        return businessdao.update(business);//将修改后的内容保存至数据库中
    }

    @Override
    //修改商家自己的登录密码
    public boolean updateBusinessPassword(Business business){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入旧密码：");
        String oldPassword = input.next();
        System.out.println("请输入新密码：");
        String newPassword = input.next();
        System.out.println("请输再次入新密码：");
        String newPassword2 = input.next();
        oldPassword=oldPassword.trim();//去除首位空格符
        newPassword=newPassword.trim();//去除首位空格符
        newPassword2=newPassword2.trim();//去除首位空格符
        if(!newPassword.equals(newPassword2)){
            System.out.println("******输入的两次新密码不一致！******");
            return false;
        }
        else if(!business.getPassword().equals(oldPassword)||oldPassword.equals("")){
            System.out.println("******输入的商家密码错误！******");
            return false;
        }
        else if(business.getPassword().equals(oldPassword)){
            business.setPassword(newPassword);
            businessdao.update(business);//将修改后的商家持久化到数据库中
            return true;
        }
        return false;
    }

    @Override
    //食品菜单管理
    public boolean FoodManager(Business user) {
        FoodService foodService=new FoodService();
        int operation=0;
        while (operation!=5) {
            Scanner inputOperation = new Scanner(System.in);
            //功能选择输出
            System.out.println("=========================================================*菜品管理功能选择表*==========================================================");
            System.out.format("|-------- %-17s -------- %-17s --------- %-17s --------- %-18s --------|\n", "1.查询所有菜品", "2.新增菜品", "3.修改菜品", "4.删除菜品");
            System.out.format("|-------- %-14s    ---------------------------------------------------------------------------------------------------|\n", "5.退出“菜单管理”");
            System.out.println("===================================================================================================================================");
            System.out.println("请输入要执行的操作序号：");
            try {
                operation=inputOperation.nextInt();
            }catch (Exception e){
                System.out.println("******输入的操作错误，请重新输入！******");
                operation=0;
                continue;
            }
            //选择执行的操作
            switch (operation){
                case 1:
                    if(foodService.listFoodAll(user)){
                        System.out.println("******”1.查询所有菜品“操作成功！******");
                    }
                    else {
                        System.out.println("******”1.查询所有菜品“操作失败！******");
                    }
                    break;
                case 2:
                    if(foodService.createFood(user)){
                        System.out.println("******”2.新增菜品“操作成功！******");
                    }
                    else {
                        System.out.println("******”2.新增菜品“操作失败！******");
                    }
                    break;
                case 3:
                    if(foodService.updateFood(user)){
                        System.out.println("******”3.修改菜品“操作成功！******");
                    }
                    else {
                        System.out.println("******”3.修改菜品“操作失败！******");
                    }
                    break;
                case 4:
                    if(foodService.deleteFood(user)){
                        System.out.println("******”4.删除菜品“操作成功！******");
                    }
                    else {
                        System.out.println("******”4.删除菜品“操作失败！******");
                    }
                    break;
                case 5:
                    System.out.println("===================================================================================================================================");
                    System.out.println("|                                                        您已退出“菜单管理”！                                                         |");
                    System.out.println("===================================================================================================================================");
                    System.out.println("******”10.退出菜单管理“操作成功！******");
                    break;
                default:
                    System.out.println("******输入的操作错误，请重新输入！******");
                    operation=0;
                    break;
            }
        }
        return false;
    }
}
