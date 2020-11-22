# EX0

Ex1
The task deals with the development of a data structure of a weighted and unintentional graph
Examples of a weighted and undirected graph:

<a href="http://www.siz.co.il/"><img src="http://up419.siz.co.il/up3/xjzcymjk5y2n.jpg" border="0" alt="דוגמא" /></a>

in this task we have three classes: NodeInfo(inner class), WGraphDS, WGraphAlgo.
and three interfaces  : node_info, weighted_graph , weighted_graph_algorithms

Each class implements an interface
 *NodeInfo implement node_info.
 *WGraphDS implement weighted_graph.
 *WGraphAlgo  implement weighted_graph_algorithms.


The class NodeInfo is a private inner class in WGraphDS class that Represents vertex of the graph.
Every vertex is a unique key, and every vertex contains:
 *Key-number of the vertex(and a unique key to use hashmap).
 *Tag-distance from the parent vertex.
 *Info-color of the vertex 
 (Have 2 colors: White color, Black color).
(we used it at algoritem Dijkstra-An explanation of this algorithm is below).


 
The class Graph_DS Represents a graph that build with 2 hashmap,1 contain the vertexs on the graph ( between 2 vertex have edge with weight>=0)
the other contain the ''neighbors of the vertex and the weight''
and MC that is  the Mode Count - for testing changes in the 
graph. Any change in the inner state of the graph should cause an increment in the ModeCount


The class Graph_Algo have some algorithms on the graph.
Example of algorithms:  Dijkstra, Connceted.
we used Dijkstra algoritem at this task

**Above each method there is an explanation in the task itself. 


/**
	* Algorithm dijksta:
    * input a start node and from him It goes over each vertex in the graph ,and changes the tag's neighbors to the tag to be the shortest distance from that start node
	(on our task we built that the hashmap will contains the shortest path from the start node to the finish node we choose)
    * White color - not visited nodes
    * Black color - visited node

 	How the function is built on this task:
 	
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



	 



 
