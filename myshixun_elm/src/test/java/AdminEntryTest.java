import edu.ynu.myelm.dao.impl.BusinessDao;
import edu.ynu.myelm.entities.Admin;
import edu.ynu.myelm.entities.Business;
import edu.ynu.myelm.services.impl.AdminService;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

public class AdminEntryTest {
    BusinessDao udao = new BusinessDao();//创建StudentDao对象

    @Test
    public void testDeleteBusiness(){
        System.out.println(udao.getOne(8));

    }
    @Test
    public void testAdminServiceLogin(){
        edu.ynu.myelm.services.impl.AdminService a=new edu.ynu.myelm.services.impl.AdminService();
        Admin A=a.login();
        System.out.println(A);

    }
    @Test
    public void testAdminServiceAddAdmin(){
        edu.ynu.myelm.services.impl.AdminService a=new edu.ynu.myelm.services.impl.AdminService();
        boolean result=a.addAdmin();
        System.out.println(result);

    }
    @Test
    public void testAdminServiceDeleteAdmin(){
        edu.ynu.myelm.services.impl.AdminService a=new edu.ynu.myelm.services.impl.AdminService();
        boolean result=a.deleteAdmin();
        System.out.println(result);

    }
    @Test
    public void testAdminServiceListAllAdmin(){
        AdminService a=new AdminService();
        boolean result=a.listAdminAll();
        System.out.println(result);

    }
    @Test
    public void testAdminServiceListAllBusiness(){
        AdminService a=new AdminService();
        boolean result=a.listBusinessAll();
        System.out.println(result);


    }
    @Test
    public void testqueryBusiness(){
        AdminService a=new AdminService();
        boolean result=a.searchBusiness();
        System.out.println(result);

    }
    @Test
    public void testaddBusiness(){
        AdminService a=new AdminService();
        boolean result=a.addBusiness();
        System.out.println(result);

    }
    @Test
    public void testdeleteBusiness(){
        AdminService a=new AdminService();
        boolean result=a.deleteBusiness();
        System.out.println(result);

    }
    @Test
    public void testlistfoodallByBusinessId(){
        AdminService a=new AdminService();
        boolean result=a.listFoodAllByBusinessId();
        System.out.println(result);

    }
    @Test
    public void testlogin(){
        AdminService a=new AdminService();
        Admin result=a.login();
        System.out.println(result);

    }

}
