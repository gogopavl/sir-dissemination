package main_package;

/**
 * Class that implements a link between two vertices
 * 
 */
public class Link {
	private Vertex firstVertex;
	private Vertex secondVertex;
	private int weight;
	
	public Link(Vertex firstVertex,Vertex secondVertex,int weight){
		this.firstVertex = firstVertex;
		this.secondVertex = secondVertex;
		this.weight = weight;
	}
	
	public Vertex getFirstVertex() {
		return firstVertex;
	}
	public void setFirstVertex(Vertex firstVertex) {
		this.firstVertex = firstVertex;
	}
	public Vertex getSecondVertex() {
		return secondVertex;
	}
	public void setSecondVertex(Vertex secondVertex) {
		this.secondVertex = secondVertex;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void show(){
		System.out.println("("+firstVertex.getIndex()+","+secondVertex.getIndex()+")");
	}
	
}
