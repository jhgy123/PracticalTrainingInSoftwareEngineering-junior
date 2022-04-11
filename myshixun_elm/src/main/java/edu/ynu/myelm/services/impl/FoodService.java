package edu.ynu.myelm.services.impl;
import edu.ynu.myelm.dao.impl.BusinessDao;
import edu.ynu.myelm.dao.impl.FoodDao;
import edu.ynu.myelm.entities.Business;
import edu.ynu.myelm.entities.Food;
import edu.ynu.myelm.services.IFoodService;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FoodService implements IFoodService {
    private FoodDao fooddao = new FoodDao();//创建FoodDao对象

    @Override
    //查询商家自己的所有菜品
    public boolean listFoodAll(Business business) {
        Scanner input = new Scanner(System.in);
        List<Food> list=fooddao.queryFoodByBussinessId(business.getId());
        if(list==null||list.size()==0){
            System.out.println("******没有菜品信息！******");
            return false;
        }
        System.out.println("=============================================================菜品信息表=============================================================");
        System.out.format("%-10s %-30s %-30s %-30s %-30s \n","菜品编号","菜品名称","菜品介绍","菜品价格","所属商家编号");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        list.forEach(item->System.out.format("%-13s %-30s %-30s %-33s %-20s \n",item.getFoodId(),item.getFoodName(),item.getFoodExplain(),item.getFoodPrice(),item.getBusiness().getId()));
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        return true;
    }

    @Override
    //创建商家自己的菜品
    public boolean createFood(Business business) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入新增菜品名称：");
        String newFoodName = input.next();
        newFoodName=newFoodName.trim();//去除首位空格符
        System.out.println("请输入新增菜品介绍(*****注：如若无菜品介绍则直输入“*”继续操作！*****)：");
        String newFoodExplain = input.next();
        newFoodExplain=newFoodExplain.trim();//去除首位空格符
        System.out.println("请输入新增菜品价格：");
        String strNewFoodPrice = input.next();
        strNewFoodPrice=strNewFoodPrice.trim();//去除首位空格符
        if(strNewFoodPrice.equals("")||strNewFoodPrice==null){//检测输入的菜品价格是否为空
            System.out.println("******菜品价格不能为空！******");
            return false;
        }
        float newFoodPrice;
        try {  //输入格式错误处理
            newFoodPrice=Float.parseFloat(strNewFoodPrice);
        }catch (Exception e){
            System.out.println("******输入要新增的“菜品价格”格式错误！******");
            return false;
        }
        Food newFood;//创建Food对象
        try{//异常处理
            newFood=Food.builder()
                    .foodName(newFoodName)
                    .foodPrice(newFoodPrice)
                    .business(business) //将当前商家的商家编号写入菜单表中(外键赋值)
                    .build();
        }catch (Exception e){
            return false;
        }
        //如果有菜品介绍，则增加菜品介绍到对象实例里
        if(!newFoodExplain.equals("*")){
            newFood.setFoodExplain(newFoodExplain);

        }
        return fooddao.save(newFood);
    }

    @Override
    // 跟新商家自己的菜单
    public boolean updateFood(Business business) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入要修改的菜品的”菜品编号“：");
        String strFoodId = input.next();
        strFoodId=strFoodId.trim();//去除首位空格符
        if(strFoodId.equals("")){
            System.out.println("******要修改的菜品的”菜品编号“不能为空！******");
            return false;
        }
        int foodId;
        try {  //输入格式错误处理
            foodId=Integer.parseInt(strFoodId);
        }catch (Exception e){
            System.out.println("******输入要修改的菜品的”菜品编号“格式错误！******");
            return false;
        }
        List<Food> list=fooddao.queryFoodByBussinessId(business.getId());
        int count=0;
        Food upFood = null;//当前要更新的菜品对象
        //检测输入从菜品编号是否为该商家的菜品编号
        for (Food f : list) {
            count++;
            if (f.getFoodId()==foodId) {
                upFood=f;
                break;
            }
            else if(f.getFoodId()!=foodId&&count==list.size()){
                System.out.println("******您没有权限修改此菜品，请检查输入的“菜品编号”是否正确！******");
                return false;
            }
        }

        System.out.println("请输入要修改的“菜品名称”(*****注：如若不修改则直输入“*”继续操作！*****)：");
        String updateFoodName = input.next();
        updateFoodName=updateFoodName.trim();//去除首位空格符
        System.out.println("请输入要修改的“菜品介绍”(*****注：如若不修改则直输入“*”继续操作！*****)：");
        String updateFoodExplain = input.next();
        updateFoodExplain=updateFoodExplain.trim();//去除首位空格符
        float updateFoodPrice;
        System.out.println("请输入要修改的“菜品价格”(*****注：如若不修改则直输入“*”继续操作！*****)：");
        String strUpdateFoodPrice = input.next();
        strUpdateFoodPrice=strUpdateFoodPrice.trim();//去除首位空格符
        if(!strUpdateFoodPrice.equals("*")){
            try {  //输入格式错误处理
                updateFoodPrice=Float.parseFloat(strUpdateFoodPrice);
            }catch (Exception e){
                System.out.println("******输入要修改的“菜品价格”格式错误！******");
                return false;
            }
            upFood.setFoodPrice(updateFoodPrice);
        }
        if(!updateFoodName.equals("*")){
            upFood.setFoodName(updateFoodName);
        }
        if(!updateFoodExplain.equals("*")){
            upFood.setFoodExplain(updateFoodExplain);
        }
        return fooddao.update(upFood);//将修改后的内容保存至数据库中
    }

    @Override
    //删除商家自己的菜单
    public boolean deleteFood(Business business) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入要删除菜品的”菜品编号“：");
        String strFoodId = input.next();
        strFoodId=strFoodId.trim();//去除首位空格符
        if(strFoodId.equals("")){
            System.out.println("******要删除菜品的”菜品编号“不能为空！******");
            return false;
        }
        int foodId;
        try {  //输入格式错误处理
            foodId=Integer.parseInt(strFoodId);
        }catch (Exception e){
            System.out.println("******输入要删除菜品的”菜品编号“格式错误！******");
            return false;
        }
        List<Food> list=fooddao.queryFoodByBussinessId(business.getId());
        int count=0;
        //检测输入从菜品编号是否为该商家的菜品编号
        for (Food f : list) {
            count++;
            if (f.getFoodId()==foodId) {
                break;
            }
            else if(f.getFoodId()!=foodId&&count==list.size()){
                System.out.println("******您没有权限删除此菜品，请检查输入的“菜品编号”是否正确！******");
                return false;
            }
        }
        return fooddao.delete(foodId);
    }
}
