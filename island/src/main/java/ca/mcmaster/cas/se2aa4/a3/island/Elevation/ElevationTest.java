package ca.mcmaster.cas.se2aa4.a3.island.Elevation;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor;
import org.testng.Assert;
import org.testng.junit.*;
/**Test invariants of elevation profiles**/
public class ElevationTest {
    private int SetUpVolcano(){
        VolcanoProfile volcTest = new VolcanoProfile();
        int centerX = 500;
        int centerY = 500;
        double Elev1 = volcTest.ProduceElevation(400,400,centerX,centerY);
        double Elev2 = volcTest.ProduceElevation(300,300,centerX,centerY);
        return (int) (Elev1-Elev2);
    }

    private void TestVolcano(){
        int context = SetUpVolcano();
        System.out.println("Testing volcano profile invariant");
        Assert.assertTrue(context>0);
        System.out.println("Test Passed! -> Invariant Satisfied");
    }

    private int SetUpExample(){
        ExampleElevationProfile examTest = new ExampleElevationProfile();
        double Elev1 = examTest.ProduceElevation(300,400,0,0);
        double Elev2 = examTest.ProduceElevation(600,600,0,0);
        return (int)(Elev1-Elev2);
    }

    private void TestExample(){
        int context = SetUpExample();
        System.out.println("Testing example (sweeping) profile invariant");
        Assert.assertTrue(context<0);
        System.out.println("Test Passed! -> Invariant Satisfied");
    }

    public void TestSuite(){
        TestVolcano();
        TestExample();
    }
}
