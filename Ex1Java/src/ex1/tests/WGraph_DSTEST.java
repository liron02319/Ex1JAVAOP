package ex1.tests;
import ex1.src.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.LinkedList;





class WGraph_DSTEST {

	//Tests
	// to each function of WGraph_DSTEST Class have test
	
	
	  @BeforeEach
	    public void runBeforeEachTest() {
	        System.out.println("@Before - runBeforeEachTest");
	    }
	    
	    // e.g. Creating an similar object and share for all @Test
	    @AfterEach
	    public void runAfterEachTest() {
	        System.out.println("@AfterEach - runAfterEachTest\n");
	    }
	    
	    
	    
	 //test 1
	    
	    @Test
		public void testGetNode(){
	        System.out.println("testGetNode");

	    	 weighted_graph graph=new WGraph_DS();
	    	 assertSame(graph.getNode(1),null); //node not exist in the graph
	    	 assertSame(graph.getNode(2),null); //node not exist in the graph
	    	 assertSame(graph.getNode(3),null); //node not exist in the graph	    	 
	         graph.addNode(1);
	         assertNotNull(graph.getNode(1)); //we check that we not null because we add node to the graph
	         graph.addNode(2);
	         assertNotNull(graph.getNode(2));  //we check that we not null because we add node to the graph
	         
	         assertNull(graph.getNode(-1)); //node not exist in the graph	 

	         
	    }
	    
	    
	    
		 //test 2

	    @Test
	  		public void testHasEdge(){
	        System.out.println("testHasEdge");

	  	     weighted_graph graph=new WGraph_DS();
	  	        graph.addNode(1);
	  	        graph.addNode(2);
	  	        graph.addNode(3);
	  	        assertFalse(graph.hasEdge(0,1));
	  	        assertFalse(graph.hasEdge(0,2));
	  	        assertFalse(graph.hasEdge(0,3));
	  	        assertFalse(graph.hasEdge(1,2));
	  	        assertFalse(graph.hasEdge(2,3));
	  	        assertFalse(graph.hasEdge(0,0));
	  	        assertFalse(graph.hasEdge(1,1));
	  	        
	  	        graph.connect(1,1,5);
	  	        assertFalse(graph.hasEdge(0,1));
	  	        
	  	        graph.connect(1,2,6);
	  	        assertTrue(graph.hasEdge(1,2));
	  	        assertFalse(graph.hasEdge(1,3));
	  	        
	  	        graph.connect(1,3,7);
	  	        assertTrue(graph.hasEdge(1,3));
	  	        assertFalse(graph.hasEdge(2,3));

	  	        graph.connect(2,3,8);
	  	        assertTrue(graph.hasEdge(2,3));
	  	        assertFalse(graph.hasEdge(3,3));

	    
	    }
	  	    	 
		 //test 3
	 
	  	    	 
	    @Test
  		public void testGetEdge(){
	        System.out.println("testGetEdge");

	    	weighted_graph graph = new WGraph_DS();
  	        graph.addNode(1);
  	        graph.addNode(2);
  	        graph.addNode(3);
  	        
  	        graph.connect(1,2,5);
  	        assertFalse(graph.getEdge(1,2)==6);
  	        assertTrue(graph.getEdge(1,2)==5);
  	        
  	        graph.connect(2,3,2);
  	        assertFalse(graph.getEdge(2,3)==6);
	        assertTrue(graph.getEdge(2,3)==2);
  	        
	        assertFalse(graph.getEdge(0,1)==6);
	        assertFalse(graph.getEdge(1,3)==5);
  	         
  	    }
	    
	    
	    
		 //test 4

	    @Test
  		public void testAddNode(){
	        System.out.println("testAddNode");

	    	weighted_graph graph = new WGraph_DS();
  	    	assertTrue(graph.nodeSize() == 0);
  	    	
  	        graph.addNode(1);
  	    	assertFalse(graph.nodeSize() == 0);
  	    	assertTrue(graph.nodeSize() == 1);

  	        graph.addNode(-1);
  	      assertTrue(graph.nodeSize() == 2);


  	        graph.addNode(0);
  	    	assertFalse(graph.nodeSize() == 1);
  	        assertTrue(graph.nodeSize() == 3);
  	        
  	        graph.addNode(3);
  	    	assertFalse(graph.nodeSize() == 2);
	        assertTrue(graph.nodeSize() == 4);
  	    	
  	    }
	    
	    
		 //test 5

	    
	    @Test
  		public void testConnect(){
	        System.out.println(" testConnect");

	    	weighted_graph graph = new WGraph_DS();
	        graph.addNode(1);
	        graph.addNode(2);
	        graph.addNode(3);
	        assertFalse(graph.edgeSize() == 3);
	        assertTrue(graph.edgeSize() == 0);
	        
	        
	        graph.connect(1, 2 ,Double.MAX_VALUE);
	        assertTrue(graph.edgeSize() == 1);
	        assertTrue(graph.hasEdge(1,2));
	        assertFalse(graph.hasEdge(2,3));

	        graph.connect(1,3,6.0);
	        assertTrue(graph.edgeSize() == 2);
	        assertTrue(graph.getEdge(1,3)==6.0);
	        
	        graph.connect(1,3,10.0);
	        assertFalse(graph.edgeSize() == 3);
	        assertTrue(graph.getEdge(1,3)==10.0);

	        
	    
	        
	        try {
	            graph.connect(2,3,-6);
	            fail("weight not positive");
	        }
	        catch (Exception e) {
	            assertTrue(true);
	        }
	        
	        assertFalse(graph.edgeSize() == 3);
	        assertTrue(graph.edgeSize() == 2);
	        
	        
	        
	        weighted_graph g2 = new WGraph_DS();
	        g2.addNode(1);
	        g2.connect(1,1,6);
	        assertTrue(g2.edgeSize() == 0);

	        assertFalse(g2.edgeSize() == 1);

	        
	        
	        
  	    }
	    
	   
	    
	    
		 //test 6

	    @Test
  		public void testGetV(){
	        System.out.println(" testGetV");

	        weighted_graph graph = new WGraph_DS();
	        graph.addNode(1);
	        graph.addNode(2);
	        graph.addNode(3);
	        
	        LinkedList<node_info> node = new LinkedList<node_info>();
			 
	        
            for(node_info index : graph.getV())    //collect all nodes
            {
            	node.add(index);
            }
            
            int counter=1;
            for(node_info i  : node)    // compare between getV to the nodes we add;
            {
            	
             if(counter<=3)            	 
             {
            	 assertSame(i,graph.getNode(counter));
   	    	counter++;}
             
            }
	   
              	    	
  	         
  	    }
	    
	    
	    
		 //test 7

	    @Test
  		public void testGetVWithParameter(){
  	    	
	    	
	        System.out.println(" testGetVWithParameter");

	    	
	    	weighted_graph graph = new WGraph_DS();
	        graph.addNode(1);
	        graph.addNode(2);
	        graph.addNode(3);
	        graph.addNode(4);
	        graph.connect(1,2,2);
	        graph.connect(1,3,3);
	        graph.connect(1,4,4);

	        
	        int[] arrayKeyConnected = {2,3,4};
	        int[] array = new int[3];
	      
	        int i = 0;
	        for(node_info node : graph.getV(1))
	        {
	        	array[i] = node.getKey();
	            i++;
	        }
	        assertArrayEquals(arrayKeyConnected,array);
	    }
  	    	
  	         
  	    
	    
	    
	    
		 //test 8

	    @Test
  		public void testRemoveNode(){
	        System.out.println(" testRemoveNode");

	    	 weighted_graph graph = new WGraph_DS();
	         assertTrue(graph.nodeSize() == 0);
	         graph.addNode(1);
	         graph.addNode(2);
	         graph.addNode(3);
	         graph.addNode(4);
	         assertTrue(graph.nodeSize() == 4);	    

	         graph.removeNode(4);
	         assertTrue(graph.nodeSize() == 3);	 
	         
	         graph.addNode(4);
	         graph.connect(1,2,5);
	         graph.connect(1,3,6);
	         graph.connect(2,4,7);
	         assertTrue(graph.getEdge(1, 2) == 5);	    
	         assertTrue(graph.getEdge(1, 3) == 6);	    
	         assertTrue(graph.getEdge(2, 4) == 7);	    
	         assertTrue(graph.edgeSize() == 3 );	    

	         assertTrue(graph.hasEdge(1,2));
	         assertTrue(graph.hasEdge(1,3));
	         assertTrue(graph.hasEdge(2,4));
	           
	         graph.removeNode(1);
	         assertTrue(graph.edgeSize() == 1 );	    
	         assertTrue(graph.nodeSize() == 3);	 

  	    	
  	    }
	    
	    
	    
		 //test 9

	    @Test
  		public void testRemoveEdge(){
	        System.out.println(" testRemoveEdge");

	    	 weighted_graph graph = new WGraph_DS();
	         assertTrue(graph.nodeSize() == 0);
	         graph.addNode(1);
	         graph.addNode(2);
	         graph.addNode(3);
	         graph.addNode(4);
	         assertTrue(graph.nodeSize() == 4);	    

	         graph.removeNode(4);
	         assertTrue(graph.nodeSize() == 3);	 
	         
	         graph.addNode(4);
	         graph.connect(1,2,5);
	         graph.connect(1,3,6);
	         graph.connect(2,4,7);
	         assertTrue(graph.getEdge(1, 2) == 5);	    
	         assertTrue(graph.getEdge(1, 3) == 6);	    
	         assertTrue(graph.getEdge(2, 4) == 7);	    
	         assertTrue(graph.edgeSize() == 3 );	    

	         assertTrue(graph.hasEdge(1,2));
	         assertTrue(graph.hasEdge(1,3));
	         assertTrue(graph.hasEdge(2,4));
	           
	         graph.removeEdge(1, 2);
	         assertTrue(graph.edgeSize() == 2 );	    
	         graph.removeEdge(1, 3);
	         assertTrue(graph.edgeSize() == 1 );	    

	         assertTrue(graph.nodeSize() == 4);	 

  	    	
  	    }
	    
	    
	    
	    
		 //test 10

	    @Test
  		public void testNodeSize(){
	        System.out.println(" testNodeSize");

	    	 weighted_graph graph = new WGraph_DS();
	         assertTrue(graph.nodeSize() == 0);
	         graph.addNode(1);
	         assertTrue(graph.nodeSize() == 1);
	         graph.addNode(2);
	         assertTrue(graph.nodeSize() == 2);
	         graph.addNode(3);
	         assertTrue(graph.nodeSize() == 3);
	         graph.removeNode(3);
	         assertTrue(graph.nodeSize() == 2);
	         
	         
  	    }
	    
	    
	    
	    
		 //test 11

	    @Test
  		public void testEdgeSize(){
	    	
	        System.out.println(" testEdgeSize");

	    	  weighted_graph graph = new WGraph_DS();
	          graph.addNode(1);
	          graph.addNode(2);
	          graph.addNode(3);
	          graph.addNode(4);
	          assertTrue(graph.edgeSize() == 0);
	          graph.connect(1,2,5);
	          graph.connect(1,3,6);
	          graph.connect(2,4,7);
	       
	          assertTrue(graph.edgeSize() == 3);
	          graph.removeNode(1);
	          assertTrue(graph.edgeSize() == 1);
	          graph.removeNode(2);
	          assertTrue(graph.edgeSize() == 0);
	          assertTrue(graph.nodeSize() == 2);

  	    }
	    
	    
	    
		 //test 12

	    @Test
  		public void testGetMC(){
	        System.out.println("- testGetMC");

	    	  weighted_graph graph = new WGraph_DS();
	          assertTrue(graph.getMC() == 0);
	          graph.addNode(1);
	          assertTrue(graph.getMC() == 1);
	          graph.addNode(2);
	          assertTrue(graph.getMC() == 2);
	          graph.addNode(3);
	          assertTrue(graph.getMC() == 3);
	          graph.addNode(4);
	          assertTrue(graph.getMC() == 4);

	          graph.connect(1,1,22);
	          assertTrue(graph.getMC() == 4);
	          graph.connect(1,2,5);
	          assertTrue(graph.getMC() == 5);
	          graph.connect(1,3,3);
	          assertTrue(graph.getMC() == 6);
	          graph.connect(2,4,4);
	          assertTrue(graph.getMC() == 7);
	       //   graph.connect(2,4,8);
	        //  assertTrue(graph.getMC() == 8);

	          
	          graph.removeEdge(2, 4);
	          assertTrue(graph.getMC() == 8);
		         
		      graph.removeNode(1);
		      assertTrue(graph.getMC() == 11);       //delete node+2 edge =3 +8 =11

	          
  	    }
	    
	  
	   
	    
	    
	    /*
	    @Test
	    void runTime() {
	    	 long start = new Date().getTime();
	    	weighted_graph grr=graph_creator(1000000   , 10000000, 0);
	    	long end = new Date().getTime();
	        double dt = (end-start)/1000.0;
	        System.out.println(dt);
	       */
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	   /* 
	    
	    
	 @Test
	public void test1ConnectAndEdge(){
		
		  System.out.println("@Test - test1ConnectAndEdge");
		 
		WGraph_DS g=new WGraph_DS();
		
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		
		
		assertSame(3, g.nodeSize() );  //true
		assertSame(0, g.edgeSize() );    //true
		assertSame(3, g.getMC() );        //true
		
		
		double weight5=5.0;
		g.connect(1, 2, weight5);
		
		assertTrue(g.hasEdge(1,2));  //true
		assertTrue(weight5==g.getEdge(1,2));  //true
		assertSame(true, g.hasEdge(1,2));  //true
		assertFalse(g.hasEdge(1,3));   //false
		assertSame(4, g.getMC());        //true
		
		weight5=6.0;
		g.connect(1, 2, weight5);
		assertTrue(weight5==g.getEdge(1,2));  //true
		assertFalse(weight5==5.0);  //true

		//weight5=-1;
		//g.connect(1, 2, weight5);
		//assertFalse(weight5==-1);  //true
		
		//assertSame(-1, g.getEdge(1, 2));        //true

		
		
		
	}
	 
	 
	 
	 @Test
		public void test2removes(){
		  System.out.println("@Test - test2removes");

		 
		 WGraph_DS g=new WGraph_DS();
			g.addNode(1);
			g.addNode(2);
			g.addNode(3);
			
			g.connect(1, 2, 5.0);
			g.connect(1, 3, 6.0);
			
			assertTrue( 5==g.getMC());  //true
			assertTrue(2==g.edgeSize());  //true
			assertTrue(g.hasEdge(1,2));  //true
			assertTrue(g.hasEdge(1,3));  //true
			assertTrue(5.0==g.getEdge(1,2));  //true
			assertTrue(6.0==g.getEdge(1,3));  //true

			
			
			g.removeEdge(1, 2);
			
			assertTrue( 6==g.getMC());  //true
			assertFalse(g.hasEdge(1,2));  
			assertFalse(g.hasEdge(1,2));  
			assertTrue(1==g.edgeSize());  //true

			
			g.connect(1, 2, 10.0);
			assertTrue(g.hasEdge(1,2));  //true
			assertTrue(10.0==g.getEdge(1, 2));  //true
			assertTrue(2==g.edgeSize());  //true

			g.removeNode(1)	;
			assertFalse(g.hasEdge(1,2));  //true
			assertFalse(g.hasEdge(1,2));  //true
			assertFalse(g.hasEdge(1,3));  //true
			assertTrue(0==g.edgeSize());  //true
			assertSame(10,g.getMC());  //true


			

		 
	 }

	
	 
	 @Test
		public void testCopy(){
		  System.out.println("@Test - testCopy");

			 WGraph_DS g=new WGraph_DS();
				g.addNode(1);
				g.addNode(2);
				g.addNode(3);
				g.connect(1, 2, 5.0);
				g.connect(1, 3, 6.0);
				assertTrue( 5==g.getMC());  //true
				assertTrue(2==g.edgeSize());  //true
				assertTrue(g.hasEdge(1,2));  //true
				assertTrue(g.hasEdge(1,3));  //true
				assertTrue(5.0==g.getEdge(1,2));  //true
				assertTrue(6.0==g.getEdge(1,3));  //true
				
				WGraph_DS copy=new WGraph_DS(g);
				
				assertTrue(5==copy.getMC());  //true
				assertTrue(2==copy.edgeSize());  //true
				assertTrue(copy.hasEdge(1,2));  //true
				assertTrue(copy.hasEdge(1,3));  //true
				assertTrue(5.0==copy.getEdge(1,2));  //true
				assertTrue(6.0==copy.getEdge(1,3));  //true
				
				
		 
	 }
*/
	
   
    



     
	
	
	

}
