package ca.mcmaster.cas.se2aa4.a3.island.Biomes;

import org.testng.Assert;
/** Test correctness of parsing svg whittaker file**/
public class BiomeTest {
    public String[] setUpBiomeTest(){
        Whittaker w = new Whittaker();
        String[] biomes = new String[2];
        biomes[0] = w.evaluateBiome(629,209);
        biomes[1] = w.evaluateBiome(550,450);
        return biomes;
    }

    public void BiomeTest(){
        System.out.println("Testing whittaker parsing correctness");
        String[] context = setUpBiomeTest();
        Assert.assertEquals(context[0],"tropical");
        System.out.println("Test Passed! -> Found: "+context[0]+" Expected: tropical");
        Assert.assertEquals(context[1],"deciduous");
        System.out.println("Test Passed! -> Found: "+context[1]+" Expected: deciduous");
    }
}
