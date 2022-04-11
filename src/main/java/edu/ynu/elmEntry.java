package edu.ynu;

import edu.ynu.dao.DBHelper;
import edu.ynu.entities.*;
import edu.ynu.service.*;

import java.sql.SQLException;
import java.util.Scanner;

public class elmEntry {

    static Scanner sc = new Scanner(System.in);
    static UserService userSvc = new UserService();
    static BusinessService businessSvc = new BusinessService();
    static AdminService adminSvc = new AdminService();
    static FoodService foodSvc = new FoodService();

    public static void main(String[] args) throws Exception {

        System.out.println("欢迎来到饿了么，请登录！");
        System.out.println("请输入账号");
        var id = sc.nextInt();
        System.out.println("请输入密码");
        var password = sc.next();

        int role = id % 10;
        switch (role) {
            case 1:
                var user = userSvc.login(id, password);
                if (user == null) {
                    System.out.println("登陆失败");
                } else {
                    System.out.println("登录成功");
                    setUserSvc(user);
                }
                break;
            case 2:
                var business = businessSvc.login(id, password);
                if (business == null) {
                    System.out.println("登陆失败");
                } else {
                    System.out.println("登录成功");
                    setBusinessSvc(business);
                }
                break;
            case 3:
                var admin = adminSvc.login(id, password);
                if (admin == null) {
                    System.out.println("登陆失败");
                } else {
                    System.out.println("登录成功");
                    setAdminSvc(admin);
                }
                break;
            default:
                System.out.println("账号输入错误!");
        }
        DBHelper.close();
    }

    public static void setUserSvc(User user) throws SQLException {
        int key;
        do {
            System.out.println("1.展示所有商家信息  2.展示自己信息  3.修改密码  4.更改账户信息  5.搜索商家  6.退出饿了么");
            System.out.println("请输入您的选择");
            var choice = sc.nextInt();
            switch (choice) {
                case 1:
                    var business = userSvc.listAllBusiness();
                    System.out.println(business);
                    break;
                case 2:
                    userSvc.showOwnInfo(user);
                    break;
                case 3:
                    System.out.println("请输入你想修改的密码：");
                    var password1 = sc.next();
                    System.out.println("请确认你的密码：");
                    var password2 = sc.next();
                    if (password1.equals(password2)) {
                        userSvc.updatePassword(user.getUserId(), password1);
                        System.out.println("密码修改成功");
                    } else {
                        System.out.println("两次密码不一样，密码修改失败！");
                    }
                    break;
                case 4:
                    System.out.println("请输入用户名称:");
                    var name = sc.next();
                    System.out.println("请输入身份:");
                    var organization = sc.next();
                    System.out.println("请输入性别:");
                    var sex = sc.next();

                    var reUser = User.builder()
                            .userId(user.getUserId())
                            .userName(name)
                            .organization(organization)
                            .sex(sex)
                            .build();
                    System.out.println(reUser);
                    if (userSvc.update(reUser)) System.out.println("用户信息修改成功");
                    else System.out.println("用户信息修改失败！");
                    break;
                case 5:
                    System.out.println("1：根据商家名称进行搜索  2：根据商家Id进行搜索");
                    var x = sc.nextInt();
                    String businessName;
                    int businessId;
                    if (x == 1) {
                        System.out.println("请输入商家名称（部分或者全部）:");
                        businessName = sc.next();
                        var bs = userSvc.listBusinessByName(businessName);
                        System.out.println(bs);
                    } else if (x == 2) {
                        System.out.println("请输入商家ID");
                        businessId = sc.nextInt();
                        var bis = userSvc.listBusinessById(businessId);
                        if (bis == null) System.out.println("找不到该商户，请检查商户ID是否输入正确！");
                        else {
                            System.out.println(bis);
                            var bisFood = userSvc.queryFoodByBusinessId(businessId);
                            System.out.println(bisFood);
                        }
                    } else System.out.println("输入错误");
                    break;
                case 6:
                    System.out.println("期待您的下次使用");
                    return;
                default:
                    System.out.println("输入错误，请尝试再次输入！");
                    break;
            }
            System.out.println("1:继续使用饿了么  其他数：退出饿了么");
            key = sc.nextInt();
        } while (key == 1);
    }

    public static void setBusinessSvc(Business business) throws SQLException {
        System.out.println("欢迎来到饿了么商家系统");
        int key;
        do {
            System.out.println("1.展示自己信息  2.修改密码  3.更改账户信息  4.展示自己的食品信息  5.增加食品  6.删除食品  7.修改食品信息  8.退出饿了么");
            System.out.println("请输入您的选择");
            var choice = sc.nextInt();
            switch (choice) {
                case 1:
                    businessSvc.showOwnInfo(business);
                    break;
                case 2:
                    System.out.println("请输入你想修改的密码：");
                    var password1 = sc.next();
                    System.out.println("请确认你的密码：");
                    var password2 = sc.next();
                    if (password1.equals(password2)) {
                        businessSvc.updatePassword(business.getBusinessId(), password1);
                        System.out.println("密码修改成功");
                    } else {
                        System.out.println("两次密码不一样，密码修改失败！");
                    }
                    break;
                case 3:
                    System.out.println("请输入商户名称：");
                    var name = sc.next();
                    System.out.println("请输入商户住址：");
                    var address = sc.next();
                    System.out.println("请输入商户注释：");
                    var explain = sc.next();
                    System.out.println("请输入商户起送费:");
                    var sp = sc.nextDouble();
                    System.out.println("请输入商户配送费:");
                    var dp = sc.nextDouble();

                    business = Business.builder()
                            .businessName(name)
                            .businessAddress(address)
                            .businessExplain(explain)
                            .startPrice(sp)
                            .deliverPrice(dp)
                            .build();

                    businessSvc.update(business);
                    break;
                case 4:
                    System.out.println(userSvc.queryFoodByBusinessId(business.getBusinessId()));
                    break;
                case 5:
                    System.out.println("请输入食品ID：");
                    var foodId = sc.nextInt();
                    System.out.println("请输入食品名称：");
                    var foodName = sc.next();
                    System.out.println("请输入食品注解：");
                    var foodExplain = sc.next();
                    System.out.println("请输入食品价格：");
                    var foodPrice = sc.nextDouble();

                    var food = Food.builder()
                            .foodId(foodId)
                            .foodName(foodName)
                            .foodExplain(foodExplain)
                            .foodPrice(foodPrice)
                            .businessId(business.getBusinessId())
                            .build();

                    foodSvc.saveFood(food);
                    break;
                case 6:
                    System.out.println("请输入您想删除的食品ID：");
                    var fid = sc.nextInt();
                    if (foodSvc.removeFoodById(fid)) {
                        System.out.println("删除成功");
                    } else System.out.println("找不到该食品，删除失败！");
                    break;
                case 7:
                    System.out.println("请输入食品ID");
                    var ownFid = sc.nextInt();
                    var ownFood = businessSvc.queryOwnFoodById(business, ownFid);

                    if (ownFood == null) System.out.println("找不到该食品！");
                    else {
                        System.out.println("请输入食品名称：");
                        var ownFoodName = sc.next();
                        System.out.println("请输入食品注解：");
                        var ownFoodExplain = sc.next();
                        System.out.println("请输入食品价格：");
                        var ownFoodPrice = sc.nextDouble();

                        var ownFoods = Food.builder()
                                .foodId(ownFood.getFoodId())
                                .foodName(ownFoodName)
                                .foodExplain(ownFoodExplain)
                                .foodPrice(ownFoodPrice)
                                .businessId(business.getBusinessId())
                                .build();

                        if (foodSvc.updateById(ownFoods)) System.out.println("食品添加成功");
                        else System.out.println("食品添加失败");
                    }
                    break;
                case 8:
                    System.out.println("期待您的下次使用");
                    return;
                default:
                    System.out.println("输入错误，请尝试再次输入！");
                    break;
            }
            System.out.println("1:继续使用饿了么  其他数：退出饿了么");
            key = sc.nextInt();
        } while (key == 1);
    }

    public static void setAdminSvc(Admin admin) throws Exception {
        System.out.println("欢迎来到饿了么后台管理系统");
        int key;
        do {
            System.out.println("1.展示个人信息  2.查看所有用户  3.删除用户  4.增加用户  5.查询用户  ");
            System.out.println("6.修改个人信息  7.查看所有商家  8.删除商家 9.增加商家  10.查询商家  ");
            System.out.println("11.修改密码   12.退出饿了么");
            var choice = sc.nextInt();

            switch (choice) {
                case 1:
                    adminSvc.showOwnInfo(admin);
                    break;
                case 2:
                    var users = adminSvc.listAllUser();
                    System.out.println(users);
                    break;
                case 3:
                    System.out.println("请输入你想删除的用户ID");
                    var userId = sc.nextInt();
                    if (adminSvc.userRemoveById(userId)) System.out.println("删除成功");
                    else System.out.println("用户不存在，删除失败！");
                    break;
                case 4:
                    System.out.println("请输入用户ID：");
                    var userID = sc.nextInt();
                    System.out.println("请输入用户名字");
                    var userName = sc.next();
                    System.out.println("请输入用户密码");
                    var userPassword = sc.next();
                    System.out.println("请输入用户身份");
                    var userOrganization = sc.next();
                    System.out.println("请输入用户性别");
                    var userSex = sc.next();

                    var user = User.builder()
                            .userName(userName)
                            .userId(userID)
                            .password(userPassword)
                            .organization(userOrganization)
                            .sex(userSex)
                            .build();

                    adminSvc.userSave(user);
                    break;
                case 5:
                    System.out.println("1：根据用户名称进行搜索  2：根据用户Id进行搜索");
                    int x = sc.nextInt();
                    if (x == 1) {
                        System.out.println("请输入用户名称（全部或者部分）");
                        var uName = sc.next();
                        var usLst = adminSvc.queryUserByName(uName);
                        if (usLst == null) System.out.println("找不到用户，请检查用户名是否输入正确！");
                        else System.out.println(usLst);
                    } else if (x == 2) {
                        System.out.println("请输入用户ID");
                        var uId = sc.nextInt();
                        var qUser = adminSvc.queryUserById(uId);
                        if (qUser == null) System.out.println("找不到该用户，请检查用户ID是否输入正确！");
                        else System.out.println(qUser);
                    } else System.out.println("输入错误!");
                    break;
                case 6:
                    System.out.println("请输入账户名称：");
                    var adminName = sc.next();

                    var reAdmin = Admin.builder()
                            .adminName(adminName)
                            .adminId(admin.getAdminId())
                            .password(admin.getPassword())
                            .build();
                    System.out.println(reAdmin);
                    if (adminSvc.update(reAdmin)) System.out.println("信息修改成功");
                    else System.out.println("信息修改失败");
                    break;
                case 7:
                    var bs = adminSvc.listAllBusiness();
                    System.out.println(bs);
                    break;
                case 8:
                    System.out.println("请输入你想删除的商家ID");
                    var businessId = sc.nextInt();
                    if (adminSvc.businessRemoveById(businessId)) System.out.println("删除成功");
                    else System.out.println("删除失败，没有该商家ID，请检查输入!");
                    break;
                case 9:
                    System.out.println("请输入商家ID：");
                    var businessID = sc.nextInt();
                    System.out.println("请输入商家名称：");
                    var businessName = sc.next();
                    System.out.println("请输入商家地址");
                    var businessAddress = sc.next();
                    System.out.println("请输入商家密码：");
                    var businessPassword = sc.next();
                    System.out.println("请输入商家注解：");
                    var businessExplain = sc.next();
                    System.out.println("请输入商家起送费：");
                    var sp = sc.nextDouble();
                    System.out.println("请输入商家配送费：");
                    var dp = sc.nextDouble();

                    var bis = Business.builder()
                            .businessId(businessID)
                            .businessName(businessName)
                            .businessAddress(businessAddress)
                            .password(businessPassword)
                            .businessExplain(businessExplain)
                            .startPrice(sp)
                            .deliverPrice(dp)
                            .build();

                    if (adminSvc.businessSave(bis)) System.out.println("添加商家成功");
                    else System.out.println("添加商家失败");
                    break;
                case 10:
                    System.out.println("1.根据商家名进行搜索  2.根据商家ID进行搜索");
                    int y = sc.nextInt();
                    if (y == 1) {
                        System.out.println("请输入商家名（部分或者全部）");
                        var qName = sc.next();
                        var bsLst = adminSvc.queryBusinessByName(qName);
                        if (bsLst == null) System.out.println("找不到该商家，请检查输入是否正确！");
                        else System.out.println(bsLst);
                    } else if (y == 2) {
                        System.out.println("请输入商家ID");
                        var qId = sc.nextInt();
                        var qBusiness = adminSvc.queryBusinessById(qId);
                        if (qBusiness == null) System.out.println("找不到该用户，请检查商家ID是否输入正确！");
                        else System.out.println(qBusiness);
                    } else System.out.println("输入错误，请检查输入!");
                    break;
                case 11:
                    System.out.println("请输入你想修改的密码：");
                    var password1 = sc.next();
                    System.out.println("请确认你的密码：");
                    var password2 = sc.next();
                    if (password1.equals(password2)) {
                        adminSvc.updatePassword(admin.getAdminId(), password1);
                        System.out.println("密码修改成功");
                    } else {
                        System.out.println("两次密码不一样，密码修改失败！");
                    }
                    break;
                case 12:
                    System.out.println("期待您的下次使用");
                    return;
                default:
                    System.out.println("输入错误，请尝试再次输入！");
                    break;
            }
            System.out.println("1:继续使用饿了么  其他数：退出饿了么");
            key = sc.nextInt();
        } while (key == 1);

    }

}



