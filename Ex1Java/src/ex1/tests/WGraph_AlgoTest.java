package ex1.tests;
import ex1.src.*;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class WGraph_AlgoTest {


   
    private weighted_graph WeightedGraph()
    {
    	 
        /*			
         * 				5
        graph:         / \
                      /   \
                  (9)/     \(6)	                 
                    /       \  				
                   / (2) (11)\
                  6------3----4    
                  |     /\    |
                  |    /  \   |
              (14)|   /    \  |(15)
                  |  /(9)(10\ |
                  | /        \|
                  1-----------2       
                        (7)
                        
         */
    	
    	
    	weighted_graph graph=new WGraph_DS();
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addNode(6);
		graph.connect(1, 2, 7);
		graph.connect(1, 3, 9);
		graph.connect(1, 6, 14);
		graph.connect(2, 4, 15);
		graph.connect(2, 3, 10);
		graph.connect(3, 4, 11);
		graph.connect(3, 6, 2);
		graph.connect(4, 5, 6);
		graph.connect(6, 5, 9);
		
		return graph;
    }

    
    
    private weighted_graph WeightedGraph2()
    {
    	 
        /*			
         * 				
        graph:         
                     
                        5           
                             				
                     (2) (11)
                  6------3----4    
                  |     /\    |
                  |    /  \   |
              (14)|   /    \  |(15)
                  |  /(9)(10\ |
                  | /        \|
                  1-----------2       
                        (7)
                        
         */
    	
    	
    	weighted_graph graph2=new WGraph_DS();
    	graph2.addNode(1);
    	graph2.addNode(2);
    	graph2.addNode(3);
    	graph2.addNode(4);
    	graph2.addNode(5);
    	graph2.addNode(6);
    	graph2.connect(1, 2, 7);
    	graph2.connect(1, 3, 9);
    	graph2.connect(1, 6, 14);
    	graph2.connect(2, 4, 15);
    	graph2.connect(2, 3, 10);
    	graph2.connect(3, 4, 11);
    	graph2.connect(3, 6, 2);
		
		return graph2;
    }
    
 
    
    @Test
    void init() {
        weighted_graph g = WeightedGraph();
        weighted_graph g2 = WeightedGraph2();
        
    	weighted_graph_algorithms algoGraphAlgorithms=new WGraph_Algo();
    	assertNull(algoGraphAlgorithms.getGraph());
    	algoGraphAlgorithms.init(g);   	
        assertTrue(algoGraphAlgorithms.getGraph() == g);
        
        WGraph_Algo algoGraph=new WGraph_Algo();
    	assertNull(algoGraph.getGraph());
        algoGraph.init(g2);   	
        assertTrue(algoGraph.getGraph() == g2);
     
    }
    
    
    
    

    @Test
    void getGraph() {
           
       	weighted_graph_algorithms algoGraphAlgorithms=new WGraph_Algo();
       	assertNull(algoGraphAlgorithms.getGraph());
       	
 	   weighted_graph g = WeightedGraph2();
       algoGraphAlgorithms.init(g);   	
           assertTrue(algoGraphAlgorithms.getGraph() == g);
           
           assertTrue(algoGraphAlgorithms.getGraph().nodeSize() == 6);
           algoGraphAlgorithms.getGraph().removeNode(5);
           assertTrue(algoGraphAlgorithms.getGraph().nodeSize() == 5);
           assertTrue(algoGraphAlgorithms.getGraph().edgeSize() == 7);




    }

    
    @Test
    void copy() {
    	
        weighted_graph graph = WeightedGraph2();
        weighted_graph_algorithms algoGraphAlgorithms = new WGraph_Algo(graph);
        weighted_graph copy=algoGraphAlgorithms.copy();
        assertEquals(graph,copy);
        

    }

    @Test
    void isConnected() {
        weighted_graph graph1 = WeightedGraph(); //graph connected
        weighted_graph_algorithms algoGraphAlgorithms1 = new WGraph_Algo(graph1);
        algoGraphAlgorithms1.isConnected();
        
        
        weighted_graph graph2 = WeightedGraph2(); //graph not connected
        weighted_graph_algorithms algoGraphAlgorithms2 = new WGraph_Algo(graph2);
        algoGraphAlgorithms2.isConnected();
        
        WGraph_DS graph3 = new WGraph_DS();          //graph 1 nodes
        graph3.addNode(1);
        weighted_graph wgraph3=graph3;
        weighted_graph_algorithms algoGraphAlgorithms3 = new WGraph_Algo(wgraph3);
        algoGraphAlgorithms3.isConnected();

        
        WGraph_DS graph4 = new WGraph_DS();            //graph 0 nodes
        weighted_graph wgraph4=graph4;                                     
        weighted_graph_algorithms algoGraphAlgorithms4 = new WGraph_Algo(wgraph4);
        algoGraphAlgorithms4.isConnected();


        

    }

    @Test
    void shortestPathDist() {
    	 weighted_graph graph1 = WeightedGraph(); //graph connected
         weighted_graph_algorithms algoGraphAlgorithms1 = new WGraph_Algo(graph1);
         assertTrue(algoGraphAlgorithms1.shortestPathDist(1,5) == 20);
         assertTrue(algoGraphAlgorithms1.shortestPathDist(2,5) == 21);
         assertFalse(algoGraphAlgorithms1.shortestPathDist(1,6) == 14);

         
         weighted_graph graph2 = WeightedGraph2(); //graph not connected
         weighted_graph_algorithms algoGraphAlgorithms2 = new WGraph_Algo(graph2);
         assertTrue(algoGraphAlgorithms2.shortestPathDist(1,5) == -1);
         assertTrue(algoGraphAlgorithms2.shortestPathDist(1,6) == 11);
         assertFalse(algoGraphAlgorithms2.shortestPathDist(1,1) == 14);
         
         WGraph_DS graph3 = new WGraph_DS();          //graph 1 nodes
         graph3.addNode(1);
         weighted_graph wgraph3=graph3;
         weighted_graph_algorithms algoGraphAlgorithms3 = new WGraph_Algo(wgraph3);
         assertTrue(algoGraphAlgorithms3.shortestPathDist(1,2) == -1);
         assertFalse(algoGraphAlgorithms3.shortestPathDist(1,1) == 14);
         assertFalse(algoGraphAlgorithms3.shortestPathDist(1,3) == 5);

         
         WGraph_DS graph4 = new WGraph_DS();            //graph 0 nodes
         weighted_graph wgraph4=graph4;                                     
         weighted_graph_algorithms algoGraphAlgorithms4 = new WGraph_Algo(wgraph4);
         assertTrue(algoGraphAlgorithms4.shortestPathDist(1,2) == -1);
         assertFalse(algoGraphAlgorithms4.shortestPathDist(1,1) == 14);
         assertFalse(algoGraphAlgorithms4.shortestPathDist(1,3) == 5);
         
         
    }


    @Test
    void shortestPath() {
    	 weighted_graph graph1 = WeightedGraph(); //graph connected
         weighted_graph_algorithms algoGraphAlgorithms1 = new WGraph_Algo(graph1);
        List<node_info> shortestPath1 = algoGraphAlgorithms1.shortestPath(1,4);
        int[] array = new int[3];
        int[] array1 = {1,3,4};      
        int i = 0;
        for(node_info n : shortestPath1)
        {
        	array[i] = n.getKey();
            i++;
        }
        assertArrayEquals(array,array1);
        
        
        weighted_graph graph2 = WeightedGraph(); //graph connected
        weighted_graph_algorithms algoGraphAlgorithms2 = new WGraph_Algo(graph2);
       List<node_info> shortestPath2 = algoGraphAlgorithms2.shortestPath(1,5);
       int[] array2 = new int[4];
       int[] arr2 = {1,3,6,5};      
       int y = 0;
       for(node_info n : shortestPath2)
       {
       	array2[y] = n.getKey();
           y++;
       }
       assertArrayEquals(array2,arr2);
       
       
       

       WGraph_DS graph3 = new WGraph_DS();          //graph 1 nodes
       graph3.addNode(1);
       weighted_graph wgraph3=graph3;
       weighted_graph_algorithms algoGraphAlgorithms3 = new WGraph_Algo(wgraph3);
       List<node_info> shortestPath3 = algoGraphAlgorithms3.shortestPath(1,5);

       assertNull(shortestPath3);


       
       WGraph_DS graph4 = new WGraph_DS();            //graph 0 nodes
       weighted_graph wgraph4=graph4;                                     
       weighted_graph_algorithms algoGraphAlgorithms4 = new WGraph_Algo(wgraph4);
       List<node_info> shortestPath4 = algoGraphAlgorithms3.shortestPath(1,5);

       assertNull(shortestPath4);

        


    }


    @Test
    void savePlusLoad(){
    
    	weighted_graph graph1 = WeightedGraph(); //graph connected
        weighted_graph_algorithms algoGraphAlgorithms1 = new WGraph_Algo(graph1);
        algoGraphAlgorithms1.save("test1");
        weighted_graph graph2 = new WGraph_DS();
        weighted_graph_algorithms algoGraphAlgorithms1Again = new WGraph_Algo(graph2);
        algoGraphAlgorithms1Again.load("test1");

        assertEquals(algoGraphAlgorithms1Again,algoGraphAlgorithms1);

    }

}
