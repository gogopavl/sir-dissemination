package graph_structure;
/**
 * Class that represents a vertex
 *
 */
public class Vertex {
	private int index;
	private Vertex ancestor1;
	private Vertex ancestor2;
	private String state;
	private double inf_time;
	
	public Vertex(int index){
		this.index = index;
		ancestor1 = null;
		ancestor2 = null;
	}
	
	public Vertex(Vertex ancestor1,Vertex ancestor2){
		String temp = Integer.toString(ancestor1.getIndex()) + Integer.toString(ancestor2.getIndex());
		this.ancestor1 = ancestor1;
		this.ancestor2 = ancestor2;
		index = Integer.parseInt(temp);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Vertex getAncestor1() {
		return ancestor1;
	}

	public void setAncestor1(Vertex ancestor1) {
		this.ancestor1 = ancestor1;
	}

	public Vertex getAncestor2() {
		return ancestor2;
	}

	public void setAncestor2(Vertex ancestor2) {
		this.ancestor2 = ancestor2;
	}
	public String getState() {
		return state;
	}

	public void setState(String st) {
		this.state = st;
	}
	public double getInfTime() {
		return inf_time;
	}

	public void setInfTime(double it) {
		this.inf_time = it;
	}
}
