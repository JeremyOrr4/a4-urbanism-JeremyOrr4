package ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.testng.Assert;
import org.testng.junit.*;
public class TileTest {
    Tiles.TileType TestTile;

    String setUpTileType(){
        this.TestTile = Tiles.TileType.LAND;
        Structs.Polygon p = Structs.Polygon.newBuilder().build();
        System.out.println("Testing tile type setter and getter");
        return Tiles.getTileType(Tiles.setType(p, Tiles.TileType.LAND));

    }

    public void TestTypes(){
        String context = setUpTileType();
        Assert.assertEquals(TestTile.name,context);
        System.out.println("Test Passed! -> Found: Land, Expected: Land");
    }

    String setUpTileColor(){
        this.TestTile = Tiles.TileType.WATER;
        Structs.Polygon p = Structs.Polygon.newBuilder().build();
        System.out.println("Testing tile color attribution");
        return (Tiles.setType(p, Tiles.TileType.WATER).getProperties(0).getValue());

    }

    public void TestColor(){
        String context = setUpTileColor();
        Assert.assertEquals(TestTile.color,context);
        System.out.println("Test Passed! -> Found: 0,0,151, Expected: 0,0,151");
    }

    public void testSuite(){
        TestColor();
        TestTypes();
    }


}
