package graph_structure;

/**
 * Class implementing a link between two vertices
 * @author gogopavl
 */
public class Link {
	private Vertex firstVertex;
	private Vertex secondVertex;
	private int weight; // If the graph is weighted
	
        /**
         * Link constructor
         * @param firstVertex Vertex object
         * @param secondVertex Vertex object 
         * @param weight Connection weight
         */
	public Link(Vertex firstVertex,Vertex secondVertex,int weight){
		this.firstVertex = firstVertex;
		this.secondVertex = secondVertex;
		this.weight = weight;
	}
        
        /**
         * Getter method for the link's first Vertex
         * @return The Vertex object
         */	
	public Vertex getFirstVertex() {
		return firstVertex;
	}
        
        /**
         * Setter method for the link's first Vertex
         * @param firstVertex The given Vertex object
         */
	public void setFirstVertex(Vertex firstVertex) {
		this.firstVertex = firstVertex;
	}
        
        /**
         * Getter method for the link's second Vertex
         * @return The Vertex object
         */
	public Vertex getSecondVertex() {
		return secondVertex;
	}
        
        /**
         * Setter method for the link's second Vertex
         * @param secondVertex The given Vertex object
         */
	public void setSecondVertex(Vertex secondVertex) {
		this.secondVertex = secondVertex;
	}
        
        /**
         * Getter method for the link's weight
         * @return The value (integer)
         */
	public int getWeight() {
		return weight;
	}

        /**
         * Setter method for the link's weight
         * @param weight The given weight value (integer)
         */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
        /**
         * Void method to display a link between two vertices
         */
	public void show(){
		System.out.println("("+firstVertex.getIndex()+","+secondVertex.getIndex()+")");
	}
	
}
