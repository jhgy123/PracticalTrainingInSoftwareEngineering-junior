import edu.ynu.myelm.dao.impl.BusinessDao;
import edu.ynu.myelm.entities.Business;
import edu.ynu.myelm.entities.Food;
import org.junit.Test;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.Table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class test {

    @Test
    public void testCreateBusiness(){//创建Course数据
        EntityManager manager = JPAUtil.getEntityManager();
        //2、获取事物管理器
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        //3、创建实体对象
        Business business = new Business();//创建Course对象
        business.setPassword("sjfiofj455");
        business.setName("lihua");
        business.setDeliveryPrice(562.3651236);
        business.setStarPrice(2.34569);
        //4、保存到数据库
        manager.persist(business);
        //5、提交事物
        transaction.commit();
        //6、关闭资源
        manager.close();
        System.out.println(business.getId());

    }
    @Test
    public void testCreateFood(){//创建Course数据
        EntityManager manager = JPAUtil.getEntityManager();
        //2、获取事物管理器
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        //3、创建实体对象
        Food food = new Food();//创建Course对象
        food.setFoodName("ifaojf");
        food.setFoodPrice(1.3698);

        String jpql = "select s from " + "Business" + " as s where s.businessId=1";//编写JPQL语句
        Query query = manager.createQuery(jpql);//根据jpql语句创建Query查询对象
         List<Business> A= query.getResultList();//获取查询对象结果集
        for(Business b:A){
            food.setBusiness(b);
        }
//        food.setBusiness();
        //4、保存到数据库
        manager.persist(food);
        //5、提交事物
        transaction.commit();
        //6、关闭资源
        manager.close();
        System.out.println();

    }


    @Test
        public void jdbcall() throws ClassNotFoundException, SQLException {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//加载驱动类11069
            String url="jdbc:sqlserver://127.0.0.1:11069;DatabaseName=Software_Practical_Training;TrustServerCertificate=true";
            String username="sa";
            String password="180714";
            Connection conn= DriverManager.getConnection(url,username,password);//用参数得到连接对象
            System.out.println("连接成功！");
            System.out.println(conn);
    }
    @Test
    public void testprint() {

//        System.out.println("============================================================*功能选择表*=============================================================");
//        System.out.format("|-------- %-15s -------- %-15s --------- %-15s --------- %-15s --------|\n","1.查询所有管理员用户","2.新增管理员用户","3.删除管理员用户","4.修改我的登录密码");
//        System.out.format("|-------- %-16s -------- %-16s --------- %-17s --------- %-18s --------|\n","5.查询所有商家信息","6.搜索商家","7.新增商家","8.删除商家");
//        System.out.format("|-------- %-14s -------- %-16s ----------------------------------------------------------------------|\n","9.查询指定商家的所有菜品","10.退出系统");
//        System.out.println("===================================================================================================================================");
//        System.out.println("============================================================*功能选择表*=============================================================");
//        System.out.format("|-------- %-18s -------- %-18s --------- %-16s --------- %-15s --------|\n","1.查询所有商家信息","2.搜索商家","3.新增商家","4.删除商家");
//        System.out.format("|-------- %-16s -------- %-16s --------- %-16s ------------------------------------|\n","5.查询指定商家的所有菜品","6.修改我的登录密码","7.退出系统");
//        System.out.println("===================================================================================================================================");
//        System.out.println("=============================================================菜单信息表=============================================================");
//        System.out.format("%-10s %-30s %-30s %-30s %-30s %n","菜品编号","菜品名称","菜品介绍","菜品价格","所属商家编号");
//        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("=========================================================*菜单管理功能选择表*==========================================================");
        System.out.format("|-------- %-16s -------- %-16s --------- %-16s --------- %-18s --------|\n", "1.查看我的信息", "2.修改我的信息", "3.修改我的密码", "4.菜品管理");
        System.out.format("|-------- %-14s    ----------------------------------------------------------------------------------------------------|\n", "5.退出系统");
        System.out.println("===================================================================================================================================");
    }
}
