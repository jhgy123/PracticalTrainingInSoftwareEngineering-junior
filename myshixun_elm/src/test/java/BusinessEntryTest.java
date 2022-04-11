import edu.ynu.myelm.dao.impl.AdminDao;
import edu.ynu.myelm.entities.Business;
import edu.ynu.myelm.services.impl.AdminService;
import edu.ynu.myelm.services.impl.BusinessService;
import edu.ynu.myelm.services.impl.FoodService;
import org.junit.Test;

public class BusinessEntryTest {
    @Test
    public void testlogin(){
        BusinessService a=new BusinessService();
        Business business =a.login();
        System.out.println(business);
    }

    @Test
    public void testListMyInf(){
        BusinessService a=new BusinessService();
        Business business = a.login();
        boolean result =a.listMyInf(business);
        System.out.println(result);
    }

    @Test
    public void testUpdateBusinessInf(){
        BusinessService a=new BusinessService();
        Business business = a.login();
        boolean result =a.updateBusinessInf(business);
        System.out.println(result);
    }

    @Test
    public void testUpdateBusinessPassword(){
        BusinessService a=new BusinessService();
        Business business = a.login();
        boolean result =a.updateBusinessPassword(business);
        System.out.println(result);
    }
    @Test
    public void testListFoodAll(){
        FoodService a=new FoodService();
        BusinessService b=new BusinessService();
        Business B=b.login();
        if(B!=null){
            boolean result =a.listFoodAll(B);
            System.out.println(result);
        }
        else{
            System.out.println(false);
        }

    }

    @Test
    public void testCreateFood(){
        BusinessService b=new BusinessService();
        FoodService a=new FoodService();
        Business B=b.login();
        if(B!=null){
            boolean result =a.createFood(B);
            System.out.println(result);
        }
        else{
            System.out.println(false);
        }
    }

    @Test
    public void testUpdateFood(){
        BusinessService b=new BusinessService();
        FoodService a=new FoodService();
        Business B=b.login();
        if(B!=null){
            boolean result =a.updateFood(B);
            System.out.println(result);
        }
        else{
            System.out.println(false);
        }
    }
    @Test
    public void testDeleteFood(){
        BusinessService b=new BusinessService();
        FoodService a=new FoodService();
        Business B=b.login();
        if(B!=null){
            boolean result =a.deleteFood(B);
            System.out.println(result);
        }
        else{
            System.out.println(false);
        }
    }
    @Test
    public void testFoodManager(){
        BusinessService b=new BusinessService();
        Business B=b.login();
        if(B!=null){
            boolean result =b.FoodManager(B);
            System.out.println(result);
        }
        else{
            System.out.println(false);
        }
    }
}
