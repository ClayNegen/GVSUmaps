package GUIpack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Louis Sullivan
 * @author Clay Negen
 * @author Douglas Wallim
 *
 */
public class GVSUMap {
	private List<MapNode> nodeList;
	private List<Edge> edgeList;


	
	/**
	 * 
	 * @param nodeList
	 * @param edgeList
	 */
	public GVSUMap(List<MapNode> nodeList, List<Edge> edgeList){
		this.nodeList = nodeList;
		this.edgeList = edgeList;
	}

	/**
	 * 
	 * @param nodeList
	 */
	public GVSUMap(List<MapNode> nodeList){
		this.nodeList = nodeList;
		edgeList = new ArrayList<Edge>();
	}



	/**
	 * 
	 */
	public GVSUMap(){
		nodeList = new ArrayList<MapNode>();
		edgeList = new ArrayList<Edge>();
	}


	/**
	 * 
	 * @param x
	 * @param y
	 * @param id
	 */
	public void addNode(int x, int y, String id){
		MapNode temp = new MapNode(x, y, id);
		nodeList.add(temp);
	}
	
	private void addNode(int x, int y, int id) {
		MapNode temp = new MapNode(x, y, Integer.toString(id));
		nodeList.add(temp);
	}

	/**
	 * 
	 * @return
	 */
	public List<MapNode> getNodeList(){
		return nodeList;
	}

	/**
	 * 
	 * @return
	 */
	public List<Edge> getEdgeList(){
		return edgeList;
	}

	/**
	 * 
	 * @param sourceLocId
	 * @param destLocId
	 */

	public void addLane(String sourceLocId, String destLocId) {
		MapNode sourceNode = null;
		MapNode destinationNode = null;
		for (MapNode node : nodeList) {
			if (node.getNodeId().equals(sourceLocId)) {
				sourceNode = node;
			}
		}

		for (MapNode node : nodeList) {
			if (node.getNodeId().equals(destLocId)) {
				destinationNode = node;
			}
		}

		if (sourceNode != null && destinationNode != null) {
			Edge lane = new Edge(sourceNode, destinationNode);
			edgeList.add(lane);
			lane = new Edge(destinationNode, sourceNode);
			edgeList.add(lane);
			return;
		}
		throw new NullPointerException();
	}

	public void addLane(int sourceLocNum, int destLocNum) {
		MapNode sourceNode = null;
		MapNode destinationNode = null;
		for (MapNode node : nodeList) {
			if (node.getNodeInfo().equals(Integer.toString(sourceLocNum))) {
				sourceNode = node;
			}
		}

		for (MapNode node : nodeList) {
			if (node.getNodeInfo().equals(Integer.toString(sourceLocNum))) {
				destinationNode = node;
			}
		}

		if (sourceNode != null && destinationNode != null) {
			Edge lane = new Edge(sourceNode, destinationNode);
			edgeList.add(lane);
			lane = new Edge(destinationNode, sourceNode);
			edgeList.add(lane);
			return;
		}
	}

	public void initialize(){
		this.addNode(388, 62, "");
		this.addNode(390, 101, "");
		this.addNode(402, 123, "");
		this.addNode(414, 136, "");
		this.addLane("388,62", "390,101");
		this.addLane("390,101", "402,123");
		this.addLane("402,123", "414,136");
		this.addNode(428, 124, "");
		this.addLane("414,136", "428,124");
		this.addNode(445, 133, "");
		this.addLane("428,124", "445,133");
		this.addNode(457, 118, "");
		this.addLane("445,133", "457,118");

		//End of Unamed nodes
		//todo: Go back and assign numbers to these nodes, will make debugging later on much easier

		this.addNode(472, 121, "3");
		this.addLane("457,118", "472,121");
		this.addNode(442, 153, "1");
		this.addLane("414,136", "442,153");
		this.addLane("445,133", "442,153");
		this.addNode(462, 162, "2");
		this.addLane("442,153", "462,162");
		this.addLane("462,162", "472,121");
		this.addNode(490, 124, "4");
		this.addLane("472,121", "490,124");
		this.addNode(491, 141, "5");
		this.addLane("490,124", "491,141");
		this.addNode(503, 142, "6");
		this.addLane("491,141", "503,142");
		this.addNode(496, 150, "7");
		this.addLane("496,150", "491,141");
		this.addLane("496,150", "503,142");
		this.addNode(497, 170, "8");
		
		//End of adding lanes by coordinates
		//todo: go back and rewrite with node numbers instead of coordinates to increase readability
		
		this.addLane(7, 8);
		this.addNode(492, 180, "9");
		this.addNode(480, 174, "10");
		this.addNode(483, 188, "11");
		
		//added overloaded addNode method to add by integer
		
		this.addLane(2, 10);
		this.addLane(10, 9);
		this.addLane(9, 8);
		this.addLane(10, 11);
		this.addLane(9, 11);
		this.addNode(515, 161, 12);
		this.addLane(12, 8);
		this.addNode(524, 174, 13);
		this.addLane(12, 13);
		this.addNode(470, 209, 14);
		this.addLane(11, 14);
		this.addNode(440, 119, "Alumni House");
		this.addLane("428,124",	"440,119");

		
		/**
		 * Louis Nodes 
		 */
		
		this.addNode(558, 167, "19");
		this.addNode(544, 180, "20");
		this.addNode(557, 177, "21");
		
		this.addNode(564, 175, "22");
		this.addNode(566, 190, "23");
		this.addNode(573, 176, "24");
		
		this.addNode(588, 174, "25");
		this.addNode(594, 165, "26");
			

		this.addNode(520, 201, 16);
		this.addNode(514, 129, 17);
		this.addLane(6, 17);
		this.addNode(534, 152, 18);
		this.addLane(13, 18);
		this.addNode(558, 167, 19);
		this.addLane(18, 19);
		this.addNode(544, 180, 20);
		this.addLane(13, 20);
		this.addNode(557, 177, 21);
		this.addLane(20, 21);
		this.addLane(20, 19);
		
		this.addLane(19, 21);
		this.addNode(564, 175, 22);
		this.addLane(19, 22);
		this.addLane(22, 21);
		this.addNode(566, 190, 23);
		this.addLane(22, 23);
		this.addNode(573, 176, 24);
		this.addLane(22, 24);

		/**
		 * @author Louis Sullivan
		 * Add nodes 25 through 38
		 */
		  this.addNode(588, 174, "25");
		  this.addNode(594, 165, "26");
		  //add node 27
		  this.addNode(582, 187, "27");
		  //add node 28
		  this.addNode(591, 198, "28");
		  //add node 29
		  this.addNode(577, 190, "29");
		  //add node 30
		  this.addNode(599, 199, "30");
		  //add node 31
		  this.addNode(607, 197, "31");
		  //add node 32
		  this.addNode(607, 154, "32");
		  //add node 33
		  this.addNode(567, 128, "33");
		  //add node 34
		  this.addNode(597, 122, "34");
		  //add node 35
		  this.addNode(603, 138, "35");
		  //add node 36
		  this.addNode(631, 196, "36");
		  //add node 37
		  this.addNode(632, 168, "37");
		  //add node 38
		  this.addNode(624, 161, "38");
		  

		  /**
		   * Add nodes 39 through 75
		   */
		  //add node 39
		  this.addNode(651, 166, "39");
		  //add node 40
		  this.addNode(656, 176, "Edward J. Frey LC");
		  //add node 41
		  this.addNode(663, 184, "41");
		  //add node 42
		  this.addNode(655, 191, "42");
		  //add node 43
		  this.addNode(669, 200, "43");
		  //add node 44
		  this.addNode(678, 192, "Grace O. Kisler LC");
		  //add node 45
		  this.addNode(670, 216, "45");
		  //add node 46
		  this.addNode(656, 211, "North C. LC");
		  //add node 47
		  this.addNode(680, 223, "Robert C. Pew LC");
		  //add node 48
		  this.addNode(666, 230, "48");
		  //add node 49
		  this.addNode(685, 242, "49");
		  //add node 50
		  this.addNode(688, 255, "50");
		  //add node 51
		  this.addNode(706, 258, "51");
		  //add node 52
		  this.addNode(742, 262, "52");
		  //add node 53
		  this.addNode(702, 246, "William Kill Patrick LC");
		  //add node 54
		  this.addNode(746, 252, "Dale Stafford LC");
		  //add node 55
		  this.addNode(742, 276, "Max Swansin LC");
		  //add node 56
		  this.addNode(702, 270, "Seedman LC");
		  //add node 57
		  this.addNode(662, 255, "57");
		  //add node 58
		  this.addNode(663, 244, "Auther Hills LC");
		  //add node 59
		  this.addNode(665, 266, "William Packard LC");
		  //add node 60
		  this.addNode(653, 253, "60");
		  //add node 61
		  this.addNode(634, 236, "61");
		  //add node 62
		  this.addNode(620, 230, "62");
		  //add node 63
		  this.addNode(617, 218, "Robert Kliener Commons");
		  //add node 64
		  this.addNode(602, 229, "64");
		  //add node 65
		  this.addNode(577, 231, "65");
		  //add node 66
		  this.addNode(592, 255, "66");
		  //add node 67
		  this.addNode(620, 254, "Greg Olsin Kisler LC");
		  //add node 68
		  this.addNode(583, 279, "68");
		  //add node 69
		  this.addNode(592, 279, "69");
		  //add node 70
		  this.addNode(587, 320, "70");
		  //add node 71
		  this.addNode(610, 320, "71");
		  //add node 72
		  this.addNode(598, 376, "72");
		  //add node 73
		  this.addNode(612, 378, "73");
		  //add node 74
		  this.addNode(614, 390, "74");
		  //add node 75
		  this.addNode(641, 376, "69");
		
		 /*Continued: go back and rewrite with node numbers instead of coordinates to increase*readability by louis
		  */
		  
		  this.addLane(19,20);
		  this.addLane(19,21);
		  this.addLane(19,22);
	      this.addLane(22,23);
	      this.addLane(22,24);
	      this.addLane(24,25);
	      this.addLane(25,26);
	      
	      this.addLane(25,27);
	      this.addLane(24, 27);
	      this.addLane(25,28);
	      this.addLane(27, 28);
	      this.addLane(24,29);
	      this.addLane(24,29);
	      this.addLane(29, 28);
	      this.addLane(30, 28);
	      this.addLane(31, 30);
	      
	      this.addLane(32, 31);
	      this.addLane(32, 19);
	      this.addLane(33, 34);
	      this.addLane(35, 34);
	      this.addLane(35, 32);
	      
	      this.addLane(36, 31);
	      this.addLane(37, 36);
	      this.addLane(38, 37);
	      this.addLane(39,37);
	      this.addLane(40, 39);
	      
	      this.addLane(41, 42);
	      this.addLane(42, 36);
	      this.addLane(43, 41);
	      this.addLane(43, 42);
	      this.addLane(44, 43);
	      
	      this.addLane(45, 43);
	      this.addLane(46, 43);
	      this.addLane(46, 45);
	      this.addLane(47, 45);
	      this.addLane(48, 45);
	      
	      this.addLane(49, 48);
	      this.addLane(50, 49);
	      this.addLane(52, 51);
	      this.addLane(53, 51);
	      this.addLane(54, 52);
	      
	      this.addLane(55, 52);
	      this.addLane(56, 51);
	      this.addLane(57, 50);
	      this.addLane(58, 57);
	      this.addLane(59, 57);
	      
	      this.addLane(60, 57);
	      this.addLane(64, 62);
	      this.addLane(66, 62);
	      this.addLane(66, 62);
	      this.addLane(66, 65);
	      
	      this.addLane(65, 64);
	      this.addLane(63, 62);
	      this.addLane(67, 62);
	      this.addLane(68, 66);
	      this.addLane(69, 66);
			  
			  this.addLane(69,68);
	      this.addLane(70, 68);
	      this.addLane(71, 70);
	      this.addLane(72, 70);
	      this.addLane(73, 72);
	      
	      this.addLane(74, 73);
	      this.addLane(75, 73);
		  
			
	}
}
