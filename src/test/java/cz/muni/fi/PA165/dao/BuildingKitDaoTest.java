
package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.TestUtils;
import cz.muni.fi.PA165.domain.BuildingKit;
import cz.muni.fi.PA165.domain.Color;
import java.math.BigDecimal;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;

/**
 *
 * @author Pavol Bako
 */
public class BuildingKitDaoTest extends TestCase{
    
    private BuildingKitDao dao;

    @Override
    public void setUp()throws Exception{
        super.setUp(); 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        dao = new BuildingKitDaoImpl();
        dao.setEntityManager(emf.createEntityManager());
    }
    
    public void tearDow()throws Exception{
        super.tearDown();
    }
    
    public void testCreateBuildingKit(){
        System.out.println("TEST CreateBuildingKit");
        
        try{
        dao.CreateBuildingKit(null);
        fail("creating NULL building kit");
        } catch (IllegalArgumentException ex){
        }
        
        BuildingKit kit = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 2005);
        dao.CreateBuildingKit(kit);
        assertNotNull(kit.getId());
        assertEquals(kit.getName(), "name");
        assertEquals(kit.getDescription(), "description");
        assertEquals(kit.getPrice(), BigDecimal.ZERO);
        assertEquals(kit.getYearFrom(), 2005);
    }
    public void removeBuildingKitTest(){
        
    }
    public void updateBuildingKitTest(){
        
    }
    public void findAllTest(){
        
    }
    public void findByPrice(){
        
    }
    public void findByYearFrom(){
        
    }
}
