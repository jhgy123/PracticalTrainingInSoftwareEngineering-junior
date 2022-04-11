package edu.ynu.myelm;

import edu.ynu.myelm.entities.Admin;
import edu.ynu.myelm.services.impl.AdminService;

import java.util.Scanner;
//管理员系统入口
public class AdminEntry {
    private AdminService adminservice = new AdminService();
    public  void work(){
        Admin user=null;//当前用户
        //用户登录板块
        do {
            System.out.println("===================================================================================================================================");
            System.out.println("|                                                    欢迎使用”饿了么“后台管理系统！                                                     |");
            System.out.println("===================================================================================================================================");
            user = adminservice.login();//用户登录
        }while (user==null);
        if(user.getId()==1){
            superadminWork(user);
        }
        else{
            adminWork(user);
        }
    }

    public void superadminWork(Admin user){
        int operation=0;
        while (operation!=10) {
            //功能选择输出
//            boolean flag=false;//用于标记每次操作是否成功
            Scanner inputOperation = new Scanner(System.in);
            System.out.println("============================================================*功能选择表*=============================================================");
            System.out.format("|-------- %-15s -------- %-15s --------- %-15s --------- %-15s --------|\n","1.查询所有管理员用户","2.新增管理员用户","3.删除管理员用户","4.修改我的登录密码");
            System.out.format("|-------- %-16s -------- %-16s --------- %-17s --------- %-18s --------|\n","5.查询所有商家信息","6.搜索商家","7.新增商家","8.删除商家");
            System.out.format("|-------- %-14s -------- %-16s ----------------------------------------------------------------------|\n","9.查询指定商家的所有菜品","10.退出系统");
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
                    if(adminservice.listAdminAll()){
                        System.out.println("******”1.查询所有管理员用户“操作成功！******");
                    }
                    else {
                        System.out.println("******”1.查询所有管理员用户“操作失败！******");
                    }
                    break;
                case 2:
                    if(adminservice.addAdmin()){
                        System.out.println("******”2.新增管理员用户“操作成功！******");
                    }
                    else {
                        System.out.println("******”2.新增管理员用户“操作失败！******");
                    }
                    break;
                case 3:
                    if(adminservice.deleteAdmin()){
                        System.out.println("******”3.删除管理员用户“操作成功！******");
                    }
                    else {
                        System.out.println("******”3.删除管理员用户“操作失败！******");
                    }
                    break;
                case 4:
                    if(adminservice.updatePassword(user)){
                        System.out.println("******”4.修改我的登录密码“操作成功！******");
                    }
                    else {
                        System.out.println("******”4.修改我的登录密码“操作失败！******");
                    }
                    break;
                case 5:
                    if(adminservice.listBusinessAll()){
                        System.out.println("******”5.查询所有商家信息“操作成功！******");
                    }
                    else {
                        System.out.println("******”5.查询所有商家信息“操作失败！******");
                    }
                    break;
                case 6:
                    if(adminservice.searchBusiness()){
                        System.out.println("******”6.搜索商家“操作成功！******");
                    }
                    else {
                        System.out.println("******”6.搜索商家“操作失败！******");
                    }
                    break;
                case 7:
                    if(adminservice.addBusiness()){
                        System.out.println("******”7.新增商家“操作成功！******");
                    }
                    else {
                        System.out.println("******”7.新增商家“操作失败！******");
                    }
                    break;
                case 8:
                    if(adminservice.deleteBusiness()){
                        System.out.println("******”8.删除商家“操作成功！******");
                    }
                    else {
                        System.out.println("******”8.删除商家“操作失败！******");
                    }
                    break;
                case 9:
                    if(adminservice.listFoodAllByBusinessId()){
                        System.out.println("******”9.查询指定商家的所有菜品“操作成功！******");
                    }
                    else {
                        System.out.println("******”9.查询指定商家的所有菜品“操作失败！******");
                    }
                    break;
                case 10:
                    System.out.println("===================================================================================================================================");
                    System.out.println("|                                                    感谢使用”饿了么“后台管理系统！                                                     |");
                    System.out.println("===================================================================================================================================");
                    System.out.println("******”10.退出系统“操作成功！******");
                    break;
                default:
                    System.out.println("******输入的操作错误，请重新输入！******");
                    operation=0;
                    break;
            }
        }
    }
    public void adminWork(Admin user){
        int operation=0;
        while (operation!=7) {
            //功能选择输出
//            boolean flag=false;//用于标记每次操作是否成功
            Scanner inputOperation = new Scanner(System.in);
            System.out.println("============================================================*功能选择表*=============================================================");
            System.out.format("|-------- %-18s -------- %-18s --------- %-16s --------- %-15s --------|\n","1.查询所有商家信息","2.搜索商家","3.新增商家","4.删除商家");
            System.out.format("|-------- %-16s -------- %-16s --------- %-16s ------------------------------------|\n","5.查询指定商家的所有菜品","6.修改我的登录密码","7.退出系统");
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
                    if(adminservice.listBusinessAll()){
                        System.out.println("******”1.查询所有商家信息“操作成功！******");
                    }
                    else {
                        System.out.println("******”1.查询所有商家信息“操作失败！******");
                    }
                    break;
                case 2:
                    if(adminservice.searchBusiness()){
                        System.out.println("******”2.搜索商家“操作成功！******");
                    }
                    else {
                        System.out.println("******”2.搜索商家“操作失败！******");
                    }
                    break;
                case 3:
                    if(adminservice.addBusiness()){
                        System.out.println("******”3.新增商家“操作成功！******");
                    }
                    else {
                        System.out.println("******”3.新增商家“操作失败！******");
                    }
                    break;
                case 4:
                    if(adminservice.deleteBusiness()){
                        System.out.println("******”4.删除商家“操作成功！******");
                    }
                    else {
                        System.out.println("******”4.删除商家“操作失败！******");
                    }
                    break;
                case 5:
                    if(adminservice.listFoodAllByBusinessId()){
                        System.out.println("******”5.查询指定商家的所有菜品“操作成功！******");
                    }
                    else {
                        System.out.println("******”5.查询指定商家的所有菜品“操作失败！******");
                    }
                    break;
                case 6:
                    if(adminservice.updatePassword(user)){
                        System.out.println("******”6.修改我的登录密码“操作成功！******");
                    }
                    else {
                        System.out.println("******”6.修改我的登录密码“操作失败！******");
                    }
                    break;
                case 7:
                    System.out.println("===================================================================================================================================");
                    System.out.println("|                                                    感谢使用”饿了么“后台管理系统！                                                     |");
                    System.out.println("===================================================================================================================================");
                    System.out.println("******”7.退出系统“操作成功！******");
                    break;
                default:
                    System.out.println("******输入的操作错误，请重新输入！******");
                    operation=0;
                    break;
            }
        }
    }

    public static void main(String[] args) {
       new AdminEntry().work();
    }

}
