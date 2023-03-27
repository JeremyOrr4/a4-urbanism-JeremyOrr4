package ca.mcmaster.cas.se2aa4.a3.island.Shapes;

import org.testng.Assert;

public class BoundTest {

    private boolean SetUpCircleBound(){
        Circle circle = new Circle(400,400,200);
        return circle.contains(199,400);
    }

    private void TestCircleBound(){
        boolean context = SetUpCircleBound();
        System.out.println("Testing validity of circle bounds");
        Assert.assertTrue(!context);
        System.out.println("Test Passed -> Found: "+context+" Expected: false");
    }

    private boolean SetUpIrregularBound(){
        Irregular irr = new Irregular(400,400,50,50, 1111);
        return irr.contains(300,300);
    }

    private void TestIrrBound(){
        boolean context = SetUpIrregularBound();
        System.out.println("Testing validity irregular bounds");
        Assert.assertTrue(!context);
        System.out.println("Test Passed -> Found: "+context+" Expected: false");
    }

    public void TestSuite(){
        TestCircleBound();
        TestIrrBound();
    }
}
