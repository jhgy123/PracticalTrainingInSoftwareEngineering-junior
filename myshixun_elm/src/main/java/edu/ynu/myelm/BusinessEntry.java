package edu.ynu.myelm;
import edu.ynu.myelm.entities.Admin;
import edu.ynu.myelm.entities.Business;
import edu.ynu.myelm.services.impl.BusinessService;

import java.util.Scanner;
//商家系统入口
public class BusinessEntry {
    private BusinessService businessservice = new BusinessService();
    public  void work(){
        Business user=null;//当前用户
        //用户登录板块
        do {
            System.out.println("===================================================================================================================================");
            System.out.println("|                                                     欢迎使用”饿了么“商家系统！                                                       |");
            System.out.println("===================================================================================================================================");
            user = businessservice.login();//用户登录
        }while (user==null);
        businessWork(user);
    }

    public void businessWork(Business user){
        int operation=0;
        while (operation!=5) {
            //功能选择输出
//            boolean flag=false;//用于标记每次操作是否成功
            Scanner inputOperation = new Scanner(System.in);
            System.out.println("============================================================*功能选择表*=============================================================");
            System.out.format("|-------- %-16s -------- %-16s --------- %-16s --------- %-18s --------|\n", "1.查看我的信息", "2.修改我的信息", "3.修改我的密码", "4.菜品管理");
            System.out.format("|-------- %-14s    ----------------------------------------------------------------------------------------------------|\n", "5.退出系统");
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
                    if(businessservice.listMyInf(user)){
                        System.out.println("******”1.查看我的信息“操作成功！******");
                    }
                    else {
                        System.out.println("******”1.查看我的信息“操作失败！******");
                    }
                    break;
                case 2:
                    if(businessservice.updateBusinessInf(user)){
                        System.out.println("******”2.修改我的信息“操作成功！******");
                    }
                    else {
                        System.out.println("******”2.修改我的信息“操作失败！******");
                    }
                    break;
                case 3:
                    if(businessservice.updateBusinessPassword(user)){
                        System.out.println("******”3.修改我的密码“操作成功！******");
                    }
                    else {
                        System.out.println("******”3.修改我的密码“操作失败！******");
                    }
                    break;
                case 4:
                    if(!businessservice.FoodManager(user)){
                        System.out.println("******已退出”4.菜品管理“操作！******");
                    }
                    break;
                case 5:
                    System.out.println("===================================================================================================================================");
                    System.out.println("|                                                     感谢使用”饿了么“商家系统！                                                       |");
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

    public static void main(String[] args) {
        new BusinessEntry().work();
    }
}
