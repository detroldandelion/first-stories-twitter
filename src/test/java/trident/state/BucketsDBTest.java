package trident.state;

import java.lang.reflect.Method;
import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import cern.colt.matrix.impl.DenseDoubleMatrix1D;
import entities.SparseVector;
import entities.Tweet;

public class BucketsDBTest extends TestCase{
	

	BucketsDB b;
	public BucketsDBTest(String bck){
		super(bck);
		b = new BucketsDB(2, 13, 100);
	}
	
	/**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( BucketsDBTest.class );
    }
    
    
    public void testUpdateRandomVectors(){
    	b.updateRandomVectors(5);
    	assertEquals(5, b.getRandomVectors().get(0)[0].cardinality());
    }
    
    public void testUpdateRandomVectorsNegative(){
    	b.updateRandomVectors(-1);
    	assertEquals(0, b.getRandomVectors().get(0)[0].cardinality());
    }
    
    public void testCreateRandomVector() throws Exception{
    	Method method = b.getClass().getDeclaredMethod("createRandomVector",int.class);
    	method.setAccessible(true);
    	
    	DenseDoubleMatrix1D d = (DenseDoubleMatrix1D) method.invoke(b, 2);
    	assertEquals(d.cardinality(), 2);
    }
    
    //not so sufficient
    public void testGetPossibleNeighbours() throws Exception{
    	Method method = b.getClass().getDeclaredMethod("getPossibleNeighbors",Tweet.class);
    	method.setAccessible(true);
    	
    	Tweet inputTweet = new Tweet(1L, "Hi I'm a tweet");
    	double[] values = {1.0, 0.5};
    	inputTweet.setSparseVector(new SparseVector(values));

    	ArrayList<Tweet> tweets = (ArrayList<Tweet>) method.invoke(b, inputTweet);
    	assertNotNull(tweets);
    }
    
    
    
}
