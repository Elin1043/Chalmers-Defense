package testTargetModes;

import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;
import com.mygdx.chalmersdefense.model.targetMode.TargetModeFactory;
import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.model.viruses.VirusFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Joel Båtsman Hilmersson
 */
public class TestTargetModes {

    private final ITargetMode firstTarget = TargetModeFactory.getTargetModes().get(0); // Index 0 represents First target mode
    private final ITargetMode lastTarget = TargetModeFactory.getTargetModes().get(1); // Index 1 represents Last target mode
    private final ITargetMode Target = TargetModeFactory.getTargetModes().get(2); // Index 2 represents Closest target mode

    private final List<IVirus> vList = new ArrayList<>();

    @Before
    public void setUpViruses(){
        // Clears list
        vList.clear();

        // Adds viruses
        vList.add(VirusFactory.createVirusOne());
        vList.add(VirusFactory.createVirusTwo());
        vList.add(VirusFactory.createVirusThree());
        vList.add(VirusFactory.createVirusFour());
        vList.add(VirusFactory.createVirusFive());

        // Spreads out viruses
        vList.get(1).update();
        vList.get(2).update();
        vList.get(3).update();
        vList.get(4).update();
    }

    @Test
    public void testGetLastTarget(){
        IVirus lastVirus = lastTarget.getRightVirus(vList, 0 , 0); // Tower position is ignored when calculating last virus
        assertEquals(lastVirus, vList.get(0));
    }
}
