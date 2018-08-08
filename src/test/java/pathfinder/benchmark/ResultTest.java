package pathfinder.benchmark;

import org.junit.Test;
import static org.junit.Assert.*;

public class ResultTest {

    @Test
    public void gettersReturnCorrectValues() {
        Experiment e = new Experiment(203, "lak100d.map", 538, 792, 212, 586, 296, 130, 814.82546844);
        Result r = new Result(e, 15, 814.82546844);
        assertEquals(e, r.getExperiment());
        assertEquals(15, r.getTime());
        assertEquals(814.82546844, r.getDist(), 0.000000005);
    }

}
