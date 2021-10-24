package testProjectiles;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author Joel Båtsman Hilmersson
 * <p>
 * Class for testing the MatrixProjectile
 */
public class TestMatrixProjectile {

    @Test
    public void testVirusIsHit() {
        List<IProjectile> pList = new ArrayList<>();
        IProjectile p = ProjectileFactory.createMatrixProjectile(0, 0, 0, 1, pList);

        p.update(true, 0, -1);
        assertTrue(pList.size() > 0);
    }
}
