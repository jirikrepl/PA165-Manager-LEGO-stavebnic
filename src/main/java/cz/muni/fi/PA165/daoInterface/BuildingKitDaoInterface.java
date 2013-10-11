/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.daoInterface;

import cz.muni.fi.PA165.domain.BuildingKit;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Tomas Kopecky
 */
public interface BuildingKitDaoInterface {

    public void CreateBuildingKit(BuildingKit buildingKit);

    public void RemoveBuildingKit(BuildingKit buildingKit);

    public void UpdateBuildingKit(BuildingKit buildingKit);

    public List<BuildingKit> findAll();

    public List<BuildingKit> findByPrice(BigDecimal price);

    public List<BuildingKit> findByYearFrom(int yearFrom);
}
