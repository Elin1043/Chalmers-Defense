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

/**
 * @author Joel Båtsman Hilmersson
 * Test class for all targeting modes
 */
public class TestTargetModes {

    private final ITargetMode firstTarget = TargetModeFactory.getTargetModes().get(0); // Index 0 represents First target mode
    private final ITargetMode lastTarget = TargetModeFactory.getTargetModes().get(1); // Index 1 represents Last target mode
    private final ITargetMode closestTarget = TargetModeFactory.getTargetModes().get(2); // Index 2 represents Closest target mode

    private final List<IVirus> vList = new ArrayList<>();

    @Before
    public void setUpViruses() {
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
    public void testGetFirstTarget() {
        IVirus lastVirus = firstTarget.getCorrectVirus(vList, 0, 0); // Tower position is ignored when calculating first virus
        assertEquals(lastVirus, vList.get(4));
    }

    @Test
    public void testGetLastTarget() {
        vList.get(0).update();
        vList.add(VirusFactory.createVirusOne());
        IVirus lastVirus = lastTarget.getCorrectVirus(vList, 0, 0); // Tower position is ignored when calculating last virus
        assertEquals(lastVirus, vList.get(5));
    }

    @Test
    public void testGetClosestTarget() {
        IVirus closestVirus = closestTarget.getCorrectVirus(vList, vList.get(2).getX(), vList.get(2).getY()); // This will put the "tower" directly on virus three, and therefore it should be the closest
        assertEquals(closestVirus, vList.get(2));
    }
}
