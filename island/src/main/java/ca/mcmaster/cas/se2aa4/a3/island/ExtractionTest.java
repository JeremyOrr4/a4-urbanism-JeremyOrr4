package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.testng.Assert;

public class ExtractionTest {

    Structs.Property Humidity = Structs.Property.newBuilder().setKey("Humidity").setValue("300").build();

    boolean SetUpCompareElev(){
        Structs.Property Elevation1 = Structs.Property.newBuilder().setKey("Elevation").setValue("100").build();
        Structs.Vertex v1 = Structs.Vertex.newBuilder().addProperties(Elevation1).build();

        Structs.Property Elevation2 = Structs.Property.newBuilder().setKey("Elevation").setValue("200").build();
        Structs.Vertex v2 = Structs.Vertex.newBuilder().addProperties(Elevation2).build();
        System.out.println("Testing vertex elevation comparison");
        return Extractor.CompareElev(v1,v2);
    }

    public void TestCompare(){
        boolean context = SetUpCompareElev();
        Assert.assertEquals(false,context);
        System.out.println("Test Passed! -> Found: "+"false"+" Expected: "+context);
    }

    boolean SetUpRiverTest(){
        Structs.Property isRiver = Structs.Property.newBuilder().setKey("IsRiver").setValue("True").build();
        Structs.Segment s = Structs.Segment.newBuilder().addProperties(isRiver).build();
        System.out.println("Testing river validation");
        return Extractor.isRiver(s);
    }

    public void TestRiver(){
        boolean context = SetUpRiverTest();
        Assert.assertEquals(true,context);
        System.out.println("Test Passed! -> Found: "+"true"+" Expected: "+context);
    }

    int SetUpHumidityTest(){
        Structs.Polygon Humid = Structs.Polygon.newBuilder().addProperties(Humidity).build();
        System.out.println("Testing humidity getter");
        return Extractor.getPolyHumidity(Humid);
    }

    public void TestHumidity(){
        int context = SetUpHumidityTest();
        int expected = Integer.parseInt(Humidity.getValue());
        Assert.assertEquals(context,expected);
        System.out.println("Test Passed! -> Found: "+context+" Expected: "+expected);
    }

    public void testSuite(){
        TestCompare();
        TestRiver();
        TestHumidity();
    }

}