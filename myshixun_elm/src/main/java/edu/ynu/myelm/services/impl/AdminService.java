package edu.ynu.myelm.services.impl;

import edu.ynu.myelm.dao.impl.AdminDao;
import edu.ynu.myelm.dao.impl.BusinessDao;
import edu.ynu.myelm.dao.impl.FoodDao;
import edu.ynu.myelm.entities.Admin;
import edu.ynu.myelm.entities.Business;
import edu.ynu.myelm.entities.Food;
import edu.ynu.myelm.services.IAdminService;

import java.util.List;
import java.util.Scanner;

public class AdminService implements IAdminService{

//    private Scanner input = new Scanner(System.in);
    private AdminDao admindao = new AdminDao();//创建AdminDao对象
    private BusinessDao businessdao = new BusinessDao();//创建BusinessDao对象
    private FoodDao fooddao=new FoodDao();//创建FoodDao对象

    @Override
    //管理员登录，当管理员编号或密码错误或输入格式错误时，返回null
    public Admin login() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入管理员编号：");
        int adminId;
        try {
            adminId = input.nextInt();
        }catch (Exception exception){ //输入异常处理
            System.out.println("******输入的管理员编号格式错误！******");
            return null;
        }
        System.out.println("请输入管理员密码：");
        String password = input.next();
//        AdminDao admindao = new AdminDao();//创建AdminDao对象
        Admin admin=admindao.getOne(adminId);
        if(admin==null||!admin.getPassword().equals(password)){
            System.out.println("******输入的管理员编号或密码错误！******");
            return null;
        }
        else {
            System.out.println("******"+admin.getName()+"用户，您好！欢迎使用“饿了么”后台管理系统！******");
            return admin;
        }
    }

    @Override
    //新增管理员用户，当新增管理员名称已存在，返回false
    public boolean addAdmin() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入新增管理员名称：");
        String newAdminName = input.next();
        newAdminName=newAdminName.trim();//去除首位空格符
        System.out.println("请设置新增管理员密码：");
        String newAdminPassword = input.next();
        newAdminPassword=newAdminPassword.trim();//去除首位空格符
//        AdminDao admindao = new AdminDao();//创建AdminDao对象
        Admin newAdmin;

        try{ //异常处理，如有相同名称的管理员将抛出异常
            newAdmin=Admin.builder()  //创建Admin对象
                    .name(newAdminName)
                    .password(newAdminPassword)
                    .build();
        }catch (Exception e){
            return false;
        }
        return admindao.save(newAdmin);
    }

    @Override
    //删除管理员用户，当删除的管理员编号不存在时，返回false
    public boolean deleteAdmin() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入要删除的管理员编号：");
        try {
            int deleteAdminId = input.nextInt();
            if(deleteAdminId==1){
                System.out.println("******您没有权限删除此管理员，删除失败！******");
            }
            return admindao.delete(deleteAdminId);
        }catch (Exception exception){ //输入异常处理
            System.out.println("******输入的管理员编号格式错误！******");
            return false;
        }
    }

    @Override
    public boolean updatePassword(Admin admin) {
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
        else if(!admin.getPassword().equals(oldPassword)||oldPassword.equals("")){
            System.out.println("******输入的管理员密码错误！******");
            return false;
        }
        else if(admin.getPassword().equals(oldPassword)){
            admin.setPassword(newPassword);
            admindao.update(admin);
            return true;
        }
        return false;
    }

    @Override
    //显示所有管理员的信息,返回true有管理员信息，返回false没有管理员信息
    public boolean listAdminAll() {
        Scanner input = new Scanner(System.in);
        List<Admin> list=admindao.getAll();
        if(list==null||list.size()==0){
            System.out.println("******没有管理员信息！******");
            return false;
        }
        System.out.println("========================管理员信息表========================");
        System.out.format("%-20s %-20s %-20s%n","管理员编号","管理员名称","管理员密码");
        System.out.println("---------------------------------------------------------");
        list.forEach(item->System.out.format("%-24s %-22s %-24s%n",item.getId(),item.getName(),item.getPassword()));
        System.out.println("---------------------------------------------------------");
        return true;
    }

    @Override
    //显示所有商家信息,返回true有商家信息，返回false没有商家信息
    public boolean listBusinessAll() {
        Scanner input = new Scanner(System.in);
        List<Business> list=businessdao.getAll();
        if(list==null||list.size()==0){
            System.out.println("******没有商家信息！******");
            return false;
        }
        System.out.println("=============================================================商家信息表=============================================================");
        System.out.format("%-5s %-20s %-30s %-20s %-20s %-20s %n","商家编号","商家名称","商家地址","商家介绍","起送费","配送费");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        list.forEach(item->System.out.format("%-8s %-20s %-30s %-20s %-20s %-20s %n",item.getId(),item.getName(),item.getAddress(),item.getExplain(),item.getStarPrice(),item.getDeliveryPrice()));
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        return true;
    }

    @Override
    //模糊查询商家信息
    public boolean searchBusiness() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入要查询的“商家编号”(*****注：如若未知则直接直输入“*”继续查询！*****)：");
        String strBussinessId = input.next();
        strBussinessId=strBussinessId.trim();//去除首位空格符
        if(strBussinessId.equals("*")){
            strBussinessId="";
        }
        int BussinessId;
        if(!strBussinessId.equals("")){//如果输入的商家编号不为空
             try{
                 BussinessId = Integer.parseInt(strBussinessId);
             }catch (Exception exception){
                 System.out.println("******输入的商家编号格式错误！******");
                 return false;
             }
             Business resultBusiness=businessdao.getOne(BussinessId);  //根据商家编号查询商家信息
            if(resultBusiness==null){
                System.out.println("******该商家不存在！******");
                return false;
            }
            //查询结果输出显示
            System.out.println("=============================================================商家信息表=============================================================");
            System.out.format("%-5s %-20s %-30s %-20s %-20s %-20s %n","商家编号","商家名称","商家地址","商家介绍","起送费","配送费");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
            System.out.format("%-8s %-20s %-30s %-20s %-20s %-20s %n",resultBusiness.getId(),resultBusiness.getName(),resultBusiness.getAddress(),resultBusiness.getExplain(),resultBusiness.getStarPrice(),resultBusiness.getDeliveryPrice());
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
            return true;
        }
        System.out.println("请输入”商家名称“的关键字(*****注：如若未知则直输入“*”继续查询！*****)：");
        String nameKey = input.next();
        System.out.println("请输入“商家地址”的关键字(*****注：如若未知则直接直输入“*”继续查询！*****)：");
        String addressKey = input.next();
        System.out.println("请输入“商家介绍”的关键字(*****注：如若未知则直接直输入“*”继续查询！*****)：");
        String explainKey = input.next();
        nameKey=nameKey.trim();//去除首位空格符
        if(strBussinessId.equals("*")){
            strBussinessId="";
        }
        addressKey=addressKey.trim();//去除首位空格符
        if(addressKey.equals("*")){
            addressKey="";
        }
        explainKey=explainKey.trim();//去除首位空格符
        if(explainKey.equals("*")){
            explainKey="";
        }
        List<Business> list=businessdao.query(nameKey,addressKey,explainKey);
        if(list==null||list.size()==0){
            System.out.println("******没有满足查询条件的商家信息！******");
            return false;
        }
        System.out.println("=============================================================商家信息表=============================================================");
        System.out.format("%-5s %-20s %-30s %-20s %-20s %-20s %n","商家编号","商家名称","商家地址","商家介绍","起送费","配送费");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        list.forEach(item->System.out.format("%-8s %-20s %-30s %-20s %-20s %-20s %n",item.getId(),item.getName(),item.getAddress(),item.getExplain(),item.getStarPrice(),item.getDeliveryPrice()));
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        return true;
    }

    @Override
//    新增商家
    public boolean addBusiness() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入新增商家名称：");
        String newBusinessName = input.next();
        newBusinessName=newBusinessName.trim();//去除首位空格符
        System.out.println("请设置新增商家密码：");
        String newBusinessPassword = input.next();
        newBusinessPassword=newBusinessPassword.trim();//去除首位空格符
        Business newBusiness;//创建Business对象
        try{//异常处理
            newBusiness=Business.builder()
                    .name(newBusinessName)
                    .password(newBusinessPassword)
                    .build();
        }catch (Exception e){
            return false;
        }
        return businessdao.save(newBusiness);
    }

    @Override
    public boolean deleteBusiness() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入要删除的商家编号：");
        try {
            int deleteBusinessId = input.nextInt();
            return businessdao.delete(deleteBusinessId);
        }catch (Exception exception){ //输入异常处理
            System.out.println("******输入的管理员编号格式错误！******");
            return false;
        }
    }

    @Override
    //通过商家的I查询该商家的菜单
    public boolean listFoodAllByBusinessId() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入要查询的商家编号：");
        int quaryBusinessId;
        try {
            quaryBusinessId = input.nextInt();
        }catch (Exception exception){ //输入异常处理
            System.out.println("******输入的商家编号格式错误！******");
            return false;
        }
        List<Food> list=fooddao.queryFoodByBussinessId(quaryBusinessId);
        if(list==null||list.size()==0){//查询结果为空
            System.out.println("******该商家没有菜单信息！******");
            return false;
        }
        System.out.println("=============================================================菜单信息表=============================================================");
        System.out.format("%-10s %-20s %-30s %-20s %-10s %n","食品编号","食品名称","食品介绍","食品价格","所属商家编号");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        list.forEach(item->System.out.format("%-12s %-20s %-30s %-25s %-8s %n",item.getFoodId(),item.getFoodName(),item.getFoodExplain(),item.getFoodPrice(),item.getBusiness().getId()));
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        return true;

    }


}
