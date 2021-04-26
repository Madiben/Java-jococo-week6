
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.*;

public class RectangleTest{

    Rectangle Recclass;

    @Before
    public void Setup() throws RectangleException
    {
    Recclass = new Rectangle(5,4); 
    try{
        Rectangle failrecea = new Rectangle(-6,5);
        Assert.fail("exceptioned above negative");
    }catch(Exception e){
            System.out.println(e.getMessage());
    }
    try{
        Rectangle failreceb = new Rectangle(6,-5);
        Assert.fail("exceptioned above negative");
    }catch(Exception e){
            System.out.println(e.getMessage());
    }
    }

    @Test
    public void getAreaTest() {
        assertEquals( "Rect Area is 20", 20, Recclass.getArea(),0.00001 );
    }
    @Test
    public void getPerimeterTest() {
        assertEquals( "Rect Perimeter is 18", 18, Recclass.getPerimeter(),0.00001 );
    }
    @Test
    public void setATest()throws RectangleException {
        double newA= 6; 
        Rectangle rec=new Rectangle(2,2);
        rec.setA(newA);
        assertEquals( "set A to is", rec.getA(), newA ,0.00001);
    }

    @Test
    public void getATest() throws RectangleException{
        Rectangle instance = new Rectangle(2,2);
        double expResult = 5;
        instance.setA(5);
        double result = instance.getA();
        assertEquals("get A is",expResult, result,0.00001);
    }
    @Test
    public void setBTest()throws RectangleException {
        double newB= 4; 
        Rectangle rec=new Rectangle(2,2);
        rec.setB(newB);
        assertEquals( "set B to is", rec.getB(), newB,0.00001 );
    }

    @Test
    public void getBTest()throws RectangleException {
        Rectangle instance = new Rectangle(2,2);
        double expResult = 5;
        instance.setB(5);
        double result = instance.getB();
        assertEquals("get B is",expResult, result,0.00001);
    }
    @Test
    public void isSquareTest() throws RectangleException{
        Rectangle instance = new Rectangle(2,2);
        assertFalse( "Rect is not Square", Recclass.isSquare() );
        assertTrue( "Rect is not Square", instance.isSquare());
        
    }
    

}
