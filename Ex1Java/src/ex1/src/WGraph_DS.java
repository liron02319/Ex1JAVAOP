package ex1.src;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;


public class WGraph_DS implements weighted_graph, Serializable {

		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numberOfEdges;       //count the edges in graph
	private int MC;       //Mode Count - for testing changes in the graph.
	private HashMap<Integer,node_info> gHashNode;
	private HashMap<Integer,HashMap<Integer,Double>> gHashNighbors;

	
	
	//constructors
	
	public WGraph_DS() {           //default constructor
		
		this.numberOfEdges=0;
		this.MC=0;
		this.gHashNode=new HashMap<Integer,node_info>();
        this.gHashNighbors=new HashMap<Integer,HashMap<Integer,Double>>();
	}
	
	
    public WGraph_DS(weighted_graph other) {   //copy constructor
    	
    	this(); //call to default constructor  
    	
    	for(node_info node : other.getV()) {
    		 this.addNode(node.getKey());      // copy to all the nodes on the graph
    		 this.gHashNode.get(node.getKey()).setTag(node.getTag());    //copy the tag
    		 this.gHashNode.get(node.getKey()).setInfo(node.getInfo());   //copy the info

    	}
    	
		for(node_info node : other.getV()) {
			
			for(node_info insideNode : other.getV(node.getKey()))
			{
				this.connect(node.getKey(),insideNode.getKey(),other.getEdge(node.getKey(),insideNode.getKey()));        //connect the vortex in the new copy graph like the original
		
			}			
		}		
	}
	
    
	
    /**
     * return the node_data by the node_id,
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
	@Override
	public node_info getNode(int key) {           
		if(this.gHashNode.containsKey(key)) {   //if the graph have that vortex-key
	         return this.gHashNode.get(key);

		}
       return null;
	}
	
	
	
	
	
	
	 /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     * @return
     */
	@Override
	public boolean hasEdge(int node1, int node2) {          
		
		 boolean condition1=this.gHashNode.containsKey(node1);
		 boolean condition2=this.gHashNode.containsKey(node2);
		 
		if( condition1 && condition2 ) {      // if to the graph have that node vertexes
            return this.gHashNighbors.get(node1).containsKey(node2); }          //check if node1 and node2 connected("neighbors")
        else {
            return false;}
	
		
	    }
	

	
	
	
	
	/**
     * return the weight if the edge (node1, node1). In case
     * there is no such edge - should return -1
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     * @return
     */
	@Override
	public double getEdge(int node1, int node2) {      
		if(this.hasEdge(node1,node2)){          // if to the vertex have edge between them
			return this.gHashNighbors.get(node1).get(node2);							
		}
		return -1;
	}

	
	
	
	
	
	
	
	/**
     * add a new node to the graph with the given key.
     * Note: this method should run in O(1) time.
     * Note2: if there is already a node with such a key -> no action should be performed.
     * @param key
     */
	@Override
	public void addNode(int key) {             
		if(this.gHashNode.containsKey(key)) {     // if to the graph have that node vertex
            return;}
		
		gHashNode.put(key,new NodeInfo(key));
		gHashNighbors.put(key,new HashMap<Integer,Double>());
		this.MC++;
	}
	

	
	
	
	
	
	/**
     * Connect an edge between node1 and node2, with an edge with weight >=0.
     * Note: this method should run in O(1) time.
     * Note2: if the edge node1-node2 already exists - the method simply updates the weight of the edge.
     */
	
	@Override                 
	public void connect(int node1, int node2, double w) {
		   boolean condition1=  this.gHashNode.containsKey(node1);   //check if in the graph have the node with that key
	       boolean condition2=  this.gHashNode.containsKey(node2);     //check if in the graph have the node with that key
	       boolean condition3= !( hasEdge(node1,node2) );      //check if node1 and node2 not connected("neighbors")
	       boolean condition5= node1!=node2;           //check if not same vertex
	         
	       if(w<0) { //if the weight is not positive
	            throw new IllegalArgumentException("the weight is not positive-your weight have to be positive");}
	       
	        if(hasEdge(node1,node2)) {        //if the edge node1-node2 already exists - updates the weight of the edge.
	        this.gHashNighbors.get(node1).put(node2,w);
	        this.gHashNighbors.get(node2).put(node1,w);
	        return;}
	           
	        
	       if(!condition5) { //check if same vertex
	       //	System.out.println("fail to connect,this is the same 'key' number node : "+node1);
	       	return;
	       }
	       
	       if(!condition1) { //check if in the graph the node not exist
	    //	   System.out.println("the graph not contains that 'key' number node : "+node1);
	    	  	return;
	       }
	       
	       if(!condition2) {  ////check if in the graph the node not exist
	    	//   System.out.println("the graph not contains that node : "+node2);
	    	   return;
	       }
	         
	        	     
	       
			if( condition1 && condition2 && condition3  && condition5) 
			{							
				 this.gHashNighbors.get(node1).put(node2,w);
			     this.gHashNighbors.get(node2).put(node1,w);
	
				 this.numberOfEdges++;
				 this.MC++;
		     //  System.out.println("connected succuess : "+node1 +","+node2);
				}
	}

	
	
	/**
     * This method return a pointer (shallow copy) for a
     * Collection representing all the nodes in the graph.
     * Note: this method should run in O(1) tim
     * @return Collection<node_data>
     */
	@Override
	public Collection<node_info> getV() {
        return this.gHashNode.values();

	}

	
	
	
	
	  /**
    *
    * This method returns a Collection containing all the
    * nodes connected to node_id
    * Note: this method can run in O(k) time, k - being the degree of node_id.
    * @return Collection<node_data>
    */
	@Override
	public Collection<node_info> getV(int node_id) {
				
	   LinkedList<node_info> collectionOfNeighbors = new LinkedList<node_info>();

		if( this.gHashNode.containsKey(node_id))
		{
				 
			            for(Integer index : this.gHashNighbors.get(node_id).keySet())    //collect all the list of keys from the hashmap
			            {
			            	collectionOfNeighbors.add(this.gHashNode.get(index));      //add the nodes to list
			            }			      
			            
		}
		return collectionOfNeighbors; 

		/*
		 * else { throw new
		 * IllegalArgumentException(" IllegalArgumentException: node_id not exist"); }
		 */			
            
	}
	
	
	
	
	 /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(n), |V|=n, as all the edges should be removed.
     * @return the data of the removed node (null if none).
     * @param key
     */
	@Override
	public node_info removeNode(int key) 
	{
		if(key<0) {
			throw new IllegalArgumentException("IllegalArgumentException: node_id not positive");  }

		
		if(this.gHashNode.containsKey(key)){
			
			for(Integer keyPosition : this.gHashNighbors.get(key).keySet())    
            {
				this.gHashNighbors.get(keyPosition).remove(key);         //remove all the neighbors that connected to the node we want to delete
				this.MC++; 
            }
			
			
			this.numberOfEdges=this.numberOfEdges - this.gHashNighbors.get(key).size();			
			this.gHashNighbors.get(key).clear();
            this.MC++;     //the node we choose deleted
			  return this.gHashNode.remove(key); }

       return null; //the key node not on the graph
         
	}
	

	
	 /**
     * Delete the edge from the graph,
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     */
	@Override
	public void removeEdge(int node1, int node2) {
		//remove the edge between 2 vortex in the graph

		 boolean condition1=  this.gHashNode.containsKey(node1);        //check if the hashmap contain that key(node)
	     boolean condition2=  this.gHashNode.containsKey(node2);
	     boolean condition3= hasEdge(node1,node2);
	     boolean condition5= node1!=node2; 
	     
	     
	       if(!condition5) {
	       //	System.out.println("fail to removeEdge,this is the same 'key' number node : "+node1);
	       	return;
	       }
	       
	       if(!condition1) {
	    	//   System.out.println("the graph not contains that 'key' number node : "+node1);
	    	  	return;
	       }
	       
	       if(!condition2) {
	    	//   System.out.println("the graph not contains that node : "+node2);
	    	   return;
	       }
	       if(!condition3) {
		    	//   System.out.println("their is no edge between"+node1 ","+node2);
		    	   return;
		      }
	         
	        
	       if(condition3)
	        {
	    	    this.gHashNighbors.get(node1).remove(node2);
	            this.gHashNighbors.get(node2).remove(node1);
	            this.numberOfEdges-- ;
	            this.MC++;
                 // System.out.println("removeEdge success ")
	        }     
	
		
	}
	
	
	 /** return the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     * @return
     */

	@Override
	public int nodeSize() {
		return this.gHashNode.size(); //return the number of vertices (nodes) in the graph.
	}

	
	 /**
     * return the number of edges (undirectional graph).
     * Note: this method should run in O(1) time.
     * @return
     */
	@Override
	public int edgeSize() {
		return this.numberOfEdges; //return the number of Edges in the graph.

	}

	/**
     * return the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     * @return
     */
	@Override
	public int getMC() {
		return this.MC;
	}
	
	
	
	
	/**
	 * print the graph 
	 * @return print the graph 
	 */
/*	public String toString()
    {
		
        String graph ="";
        
        for(node_info node : gHashNode.values())
        {
        	graph= graph + "["+node.getKey()+"]->";
            int counter = gHashNighbors.get(node.getKey()).size();
            
            for(Integer insideNode : gHashNighbors.get(node.getKey()).keySet())
            {    
            	graph= graph +"["+ insideNode+ "]";
            	if(counter>1) {
            	graph= graph +  ",";            	
            	 counter--; }
            	
            	
            } 
            graph= graph +"\n";
         }
        return graph;
	
      }
	
	*/
	
	
	
	



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WGraph_DS other = (WGraph_DS) obj;
		
		
		if (numberOfEdges != other.edgeSize()) return false;
		if (MC != other.getMC()) return false;
		return (Objects.equals(gHashNighbors, other.gHashNighbors)) && (Objects.equals(gHashNode, other.gHashNode)) ;
		
		
		
	}












	private static class NodeInfo implements node_info,Comparable<node_info>,Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int key; //the key(num) of this node
		private double tag;// the weight 
		private String info; //the color
	
		
		public NodeInfo(int key) {      //constructor with parameter
	     	this.key=key;
			this.tag=0;
			this.info=null;
		
		}
		
		

		  /**
	     * Return the key (id) associated with this node.
	     * Note: each node_data should have a unique key.
	     * @return
	     */
		@Override
		public int getKey() {
			return this.key;  //return they key
		}

		
		 /**
	     * return the remark (meta data) associated with this node.
	     * @return
	     */
		@Override
		public String getInfo() {
			return this.info;
		}

		
		
		 /**
	     * Allows changing the remark (meta data) associated with this node.
	     * @param s
	     */
		@Override
		public void setInfo(String s) {
			 this.info=s;
			
		}
		
		
		
		/**
	     * Temporal data (aka distance, color, or state)
	     * which can be used be algorithms
	     * @return
	     */
		@Override
		public double getTag() {
			   return this.tag;	

		}
		
		  /**
	     * Allow setting the "tag" value for temporal marking an node - common
	     * practice for marking by algorithms.
	     * if t negetive ,t=0;
	     * @param t - the new value of the tag
	     */
		@Override
		public void setTag(double t) 
		{
			if(t<0) 
			{this.tag=0;}             //if t<0 t=0
			else
			{this.tag=t;	}
		}

		
		
		/**
		 * print the data of the String node
		 * @return the string data of the node
		 */
				@Override
		public String toString()
	    {
			String s= "[ key = "+this.key+ " , info = " + this.info + " , tag = "+ this.tag+ " ] ";
	        return s;
	    }



		@Override
		public int compareTo(node_info other) {
			 if(this.getTag() > other.getTag())
	                return 1;
	            else if(this.getTag() < other.getTag())
	                return -1;
	            else
	                return 0;
		}
		
		
		  @Override
	        public boolean equals(Object o) {
	            if (this == o) return true;
	            if (o == null || getClass() != o.getClass()) return false;
	            NodeInfo node = (NodeInfo) o;
	            return key == node.getKey() && Double.compare(node.tag, tag) == 0 &&Objects.equals(info, node.info);
	        }
		
		
				

	}
	
}
