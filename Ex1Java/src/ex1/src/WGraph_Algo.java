package ex1.src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class WGraph_Algo implements weighted_graph_algorithms{


	
	 private weighted_graph graph;
	 private int counterTheVortex;
	 
	 
	
	 
	 //constructors
	
	 public WGraph_Algo() {            //default constructor
		  	this.graph=null;
		  			counterTheVortex=0;}
		    
		    
	public WGraph_Algo(weighted_graph g) {        // constructor with parameter
		        init(g);}
	 
	
	
	
	/**
     * Init the graph on which this set of algorithms operates on.
     * @param g
     */
	@Override
	public void init(weighted_graph g) {
         this.graph=g;
         counterTheVortex=0;
         
		
	}

	
	
	/**
     * Return the underlying graph of which this class works.
     * @return
     */
	@Override
	public weighted_graph getGraph() {
		return this.graph;
	}

	
	
	/**
     * Compute a deep copy of this weighted graph.
     * @return
     */
	@Override
	public weighted_graph copy() {
		WGraph_DS copy = new WGraph_DS(this.graph);

        return copy;
    	
	
	}

	
	  /**
     * Returns true if and only if (iff) there is a valid path from EVREY node to each
     * other node. NOTE: assume ubdirectional graph.
     * @return
     */
	@Override
	public boolean isConnected() {

		
		 if(this.graph.nodeSize() == 0) { return true;}
		// if(this.graph.nodeSize() == 1) { return true;}
		  algoritemDijkstra(graph.getV().iterator().next());
		  int numberOfVortexOnGraph= this.graph.nodeSize();
		 
		  if(numberOfVortexOnGraph==counterTheVortex) {   //if all the vortex in algoritemDijkstra black = number of vortex on graph 
			  counterTheVortex=0;
			  return true;}
		  
		  counterTheVortex=0;
		  return false; }

	

	
	  /**
     * returns the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     * @param src - start node
     * @param dest - end (target) node
     * @return returns the length of the shortest path between src to dest
     */
	@Override
	public double shortestPathDist(int src, int dest) {

		 if( ( this.graph.getNode(src) ) == null || (this.graph.getNode(dest) ) == null || src==dest)        //if started node/destination is null or start node=destination(same vertex)
	            return -1;
		
	    algoritemDijkstra(this.graph.getNode(src));
		double theLengthOfShortestPath= this.graph.getNode(dest).getTag();
		
		if(theLengthOfShortestPath==Double.MAX_VALUE) {
			return -1;
		}
		if(counterTheVortex==1) {
			return -1;
		}
		else {
			return theLengthOfShortestPath;
		}

	}

	
	 /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
	@Override
	public List<node_info> shortestPath(int src, int dest) {

		LinkedList<node_info> listShortestPath = new LinkedList<node_info>();
	       
			 if( ( this.graph.getNode(src) ) == null || (this.graph.getNode(dest) ) == null )       //if src/dest vertex not on the graph
		            return null;
			 		
			 
			  if(src==dest) //the same number
		        {
		            return null;
		        }
			 
			 
			  
		        HashMap<Integer,node_info> parents = algoritemDijkstra(this.graph.getNode(src)); //hashmap that the key is the number of son and the value is the parent 
	           	      
		        if((this.graph.getNode(dest).getTag() == Double.MAX_VALUE) ) // if the start vortex not connected to vortexes or the destination in the graph
		        {
		            return null;
		        }
		        
		        if(counterTheVortex==0) { //no vertex that connected in the graph
		            return null;

				}
		        
		        
		        
		        
		        listShortestPath.addFirst( this.graph.getNode(dest));
		        
		        node_info path = parents.get(dest); // the path is the value of the node and that is his parent

		        while(path!=null) {
		        	
		        	listShortestPath.addFirst(this.graph.getNode(path.getKey()));
		        	path = parents.get(path.getKey());
		        }
		        
			return listShortestPath;


		
	}

	
	
	
	
	
	
	
	
	
	
	  /**
     * Saves this weighted (undirected) graph to the given
     * file name
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
	
	@Override
	public boolean save(String file) {
				  
	        try {
	        		        	
	        	FileOutputStream myFile = new FileOutputStream(file);
	        	ObjectOutputStream ObjectOutputStream=new ObjectOutputStream(myFile);
	        	ObjectOutputStream.writeObject(graph);
	        	ObjectOutputStream.close();
				myFile.close();

	        	
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	            return false;
	        }
	        catch(IOException e)
	        {
	            e.printStackTrace();
	            return false;
	        }
	        return true;

	}

	
	  /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
	@Override
	public boolean load(String file) {
		
        try {
           	
        	FileInputStream myFile = new FileInputStream(file);
			ObjectInputStream ObjectInputStream = new ObjectInputStream(myFile);
			this.graph = (weighted_graph)ObjectInputStream.readObject();
			//System.out.println(graph.toString());
			ObjectInputStream.close();
			myFile.close();
			
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
		
	
	
	
	
	
	
	
	
	
	/**
	* Algorithm dijksta:
    * input a start node and from him It goes over each vertex in the graph ,and changes the tag's neighbors to the tag to be the shortest distance from that start node
    * White color - not visited nodes
    * Black color - visited node

 	How the function is built:
 	
	 * Create a new hashmap that returnable at end of algoritem 
	    with that hashmap , we used to know the shortest distance from that start node to other node we chose
	 *	Create an Priority Queue
	 *	Go through all the vertices in the graph, paint each vertex white(not visited node), and set info to white and tag to max value
	 *	Then, set the tag to 0 to start vertex we chose to start with
     *(remove and add)-because the PriorityQueue not Sorting the weight at the vortex after we the set tag of somevortex, we remove the node and add him agaim to fix that.
	 *	 make a loop with conditions as long as the queue is not empty
	 *	That the condition is true(the queue is not empty):
	 *	1-Remove from the queue the vertex with the minimun weight and saves his adress
	 *	2-Goes over all its neighboring vertices
	 *	3- And for each vertex that is checked:
	 *	  -If the vertex is not white, 
	 * 	  -if the vortex is white ,we use a variable that inside him we store the the weight from the vortex we saves his adress+the weight of the edge between them((mathematics) addition)
	 * 
	 *if the neighbor tag(weight) is bigger than that variable , we update the tag in that neighbor vortex.
	 *add to hashmap  the key of neighbor that contains the value of the vertex we saves his addres before
	 *(remove and add)-because the PriorityQueue not Sorting the weight at the vortex after we the set tag of somevortex, we remove the node and add him agaim to fix that.
	 *continue to the next neighbor

     *else,we continue to the next neighbor 
     
     *if we done with the neighbors of the vertex we change the color to black,
	 *add 1 to counter vertex
     *return the hashmap;
	
	 * @param node - the vortex(node) we start from him
	 * @return HashMap algoritemDijkstra
	 */
	private HashMap<Integer, node_info> algoritemDijkstra(node_info node)
    {
	   
        HashMap<Integer,node_info> dijkstra = new HashMap<>(); 
		 Collection<node_info> vortexInGraph=graph.getV(); 
        PriorityQueue<node_info> Queue = new PriorityQueue<node_info>(); // init PriorityQueue of node_info
        
        for(node_info n : vortexInGraph) //Go through all the node(vertices) in the graph, paint each vertex white and tag to max value
        {
            n.setInfo("WHITE"); 
            n.setTag(Double.MAX_VALUE); 
            dijkstra.put(n.getKey(),null);
            Queue.add(n); 
        }
        
        node.setTag(0); //set the vertex we chose to start with tag 0
        Queue.remove(node); //because the PriorityQueue not Sorting after the set we remove the node and add him agaim
        Queue.add(node);   

        while(!Queue.isEmpty()) 
        {
	        node_info vortex=Queue.remove(); // Removes the vertex with the smallest weight
            for(node_info neighbor : this.graph.getV(vortex.getKey())) 
            {
                if(neighbor.getInfo().equals("WHITE")) 
                {
                	
                	double minWeight=vortex.getTag()+graph.getEdge(vortex.getKey(), neighbor.getKey());               	
                    if(minWeight < neighbor.getTag()) { 
                    	neighbor.setTag(minWeight);	                       
                    	dijkstra.put(neighbor.getKey(), vortex); ////in the position of the key(vortex) have the parent of that vortex(node)    				
                    	Queue.remove(neighbor); //because the PriorityQueue not Sorting after the set we remove the node and add him agaim
                    	Queue.add(neighbor);
                        }
                 }
             }
            
            if(!(this.graph.getV(vortex.getKey()).size()==0)) {   //if the vortex connected to graph
            	 vortex.setInfo("BLACK");
     			counterTheVortex++;  
		       }
            
            if((this.graph.nodeSize()==1)) {   //if have 1 vortex in graph
           	 vortex.setInfo("BLACK");
    			counterTheVortex++;  
		       }
            
        }
        return dijkstra; //return the father path

    }
	
	
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) 
        {return true;}
        if (o == null || getClass() != o.getClass()) {
        	return false;}
        WGraph_Algo other = (WGraph_Algo) o;
        return Objects.equals(graph, other.graph);
    }
	
	
	
	
	
	
	
	

}
