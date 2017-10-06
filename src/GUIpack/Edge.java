package GUIpack;

public class Edge {
	double edgeWeight;
	int pos1;
	int pos2;
	
	
	public Edge(int pos1, int pos2, double edgeWeight){
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.edgeWeight = edgeWeight;
	}


	public double getEdgeWeight() {
		return edgeWeight;
	}


	public void setEdgeWeight(double edgeWeight) {
		this.edgeWeight = edgeWeight;
	}


	public int getPos1() {
		return pos1;
	}


	public void setPos1(int pos1) {
		this.pos1 = pos1;
	}


	public int getPos2() {
		return pos2;
	}


	public void setPos2(int pos2) {
		this.pos2 = pos2;
	}
	
}
