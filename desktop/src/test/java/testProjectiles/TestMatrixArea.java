package testProjectiles;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author Joel Båtsman Hilmersson
 *<p>
 * Test class for testing MatrixArea
 */
public class TestMatrixArea {

    IProjectile matrixArea;

    @Before
    public void createMatrixArea(){
        List<IProjectile> pList = new ArrayList<>();
        IProjectile p = ProjectileFactory.createMatrixProjectile(0,0,0,1, pList);
        p.update(true, 0, -1);
        matrixArea = pList.get(0);
    }

    @Test
    public void testAreaUpdate(){
        while (!matrixArea.canRemove()){
            matrixArea.update(false, 0, 0);
        }
        assertTrue(matrixArea.canRemove());
    }

    @Test
    public void testVirusIsHit(){
        matrixArea.update(true, 12345, 0);
        assertTrue(matrixArea.haveHitBefore(12345));
    }

}
