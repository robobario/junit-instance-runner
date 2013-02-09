import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;

@RunWith(SuiteRunner.class)
public class TestA {
    private int guy = 10;

    private TestA(){

    }

    public static List<Object> testInstances(){
        ArrayList<Object> objects = new ArrayList<Object>();
        objects.add(new TestA());
        objects.add(new TestB());
        return objects;
    }


    @Test
    public void testFun(){
        assertTrue(guy == 10);
        guy = 5;
    }


    @Test
    public void testBad(){
        assertTrue(guy == 5);
    }
}
