package pathfinder.benchmark;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExperimentTest {

    @Test
    public void gettersReturnCorrectValues() {
        Experiment e = new Experiment(203, "lak100d.map", 538, 792, 212, 586, 296, 130, 814.82546844);
        assertEquals(203, e.getBucket());
        assertEquals("lak100d.map", e.getMap());
        assertEquals(538, e.getSizeX());
        assertEquals(792, e.getSizeY());
        assertEquals(212, e.getSourceX());
        assertEquals(586, e.getSourceY());
        assertEquals(296, e.getDestX());
        assertEquals(130, e.getDestY());
        assertEquals(814.82546844, e.getDist(), 0.000000005);
    }

}
