package guiPack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GVSUMap {
	
	private List<MapNode> nodeList;
	
	private List<Edge> edgeList;

	public GVSUMap(){
		nodeList = new ArrayList<MapNode>();
		edgeList = new ArrayList<Edge>();
		this.initialize();
	}


	private void addNode(int x, int y, String info){
		MapNode temp = new MapNode(x, y, info);
		nodeList.add(temp);
	}
	
	private void addNode(int x, int y, int info) {
		MapNode temp = new MapNode(x, y, Integer.toString(info));
		nodeList.add(temp);
	}
 
	
	/***************************************************************************
	 * @return nodeList: List<MapNode>
	 **************************************************************************/
	public List<MapNode> getNodeList(){
		return nodeList;
	}
	
	/***************************************************************************
	 * @return edgeList: List<Edge>
	 **************************************************************************/
	public List<Edge> getEdgeList(){
		return edgeList;
	}
	
	/***************************************************************************
     * Looks up a node by the nodeInfo member of MapNode from the original map.
     * 
     * @param info int: The nodeInfo of the node to be looked up
     * 
     * @return MapNode: The node desired
     **************************************************************************/
	public MapNode getNode(int info) {
		for (MapNode node: nodeList) {
			if (node.getNodeInfo().equals(Integer.toString(info))) {
				return node;
			}		
		}
		//error checking required
		return null;
	}
	
	/***************************************************************************
     * Overload of getNode to take a string as parameter instead of integer.
     * (functionally identical)
     * 
     * @param info String: The nodeInfo of the node to be looked up
     * 
     * @return MapNode: The node desired
     **************************************************************************/
	public MapNode getNode(final String info) {
		for (MapNode node: nodeList) {
			if (node.getNodeInfo().equals(info)) {
				return node;
			}		
		}
		//error checking required
		return null;
	}
	
	public void addLane(int srcNodeInfo, int destNodeInfo) {
		MapNode sourceNode = null;
		MapNode destinationNode = null;
		for (MapNode node : nodeList) {
			if (node.getNodeInfo().equals(Integer.toString(srcNodeInfo))) {
				sourceNode = node;
				break;
			}
		}

		for (MapNode node : nodeList) {
			if (node.getNodeInfo().equals(Integer.toString(destNodeInfo))) {
				destinationNode = node;
				break;
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
	
	public void addLane(String srcNodeInfo, String destNodeInfo) {
		MapNode sourceNode = null;
		MapNode destinationNode = null;
		for (MapNode node : nodeList) {
			if (node.getNodeInfo().equals(srcNodeInfo)) {
				sourceNode = node;
			}
		}

		for (MapNode node : nodeList) {
			if (node.getNodeInfo().equals(destNodeInfo)) {
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
		throw new RuntimeException();
	}

	private void initialize() {
		//TODO rewrite edges between path points and buildings
		
		
		this.addNode(388, 62, 77);
		this.addNode(390, 101, 78);
		this.addNode(402, 123, 79);
		this.addNode(414, 136, 80);
		this.addLane(77, 78);
		this.addLane(78, 79);
		this.addLane(79, 80);
		this.addNode(428, 124, 81);
		this.addLane(80, 81);
		this.addNode(445, 133, 82);
		this.addLane(81, 82);
		this.addNode(457, 118, 83);
		this.addLane(82, 83);


		this.addNode(472, 121, 3);
		this.addLane(83, 3);
		this.addNode(442, 153, 1);
		this.addLane(80, 1);
		this.addLane(82, 1);
		this.addNode(462, 162, 2);
		this.addLane(1, 2);
		this.addLane(2, 3);
		this.addNode(490, 124, 4);
		this.addLane(3, 4);
		this.addNode(491, 141, 5);
		this.addLane(4, 5);
		this.addNode(503, 142, 6);
		this.addLane(5, 6);
		this.addNode(496, 150, 7);
		this.addLane(7, 5);
		this.addLane(7, 6);
		this.addNode(497, 170, 8);
		
		this.addLane(7, 8);
		this.addNode(492, 180, 9);
		this.addNode(480, 174, 10);
		this.addNode(483, 188, 11);
		
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
		this.addLane("81", "Alumni House");
				
		this.addNode(558, 167, 19);
		this.addNode(544, 180, 20);
		this.addNode(557, 177, 22);
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

		this.addNode(588, 174, 25);
		this.addNode(594, 165, 26);
		this.addNode(582, 187, 27);
		this.addNode(591, 198, 28);
		this.addNode(577, 190, 29);
		this.addNode(599, 199, 30);
		this.addNode(607, 197, 31);
		this.addNode(607, 154, 32);
		this.addNode(567, 128, 33);
		this.addNode(597, 122, 34);
		this.addNode(603, 138, 35);
		this.addNode(631, 196, 36);
		this.addNode(632, 168, 37);
		this.addNode(624, 161, 38);
		this.addNode(651, 166, 39);
		this.addNode(656, 176, "Edward J. Frey LC");
		this.addNode(663, 184, 41);
		this.addNode(655, 191, 42);
		this.addNode(669, 200, 43);
		this.addNode(678, 192, "Grace O. Kisler LC");
		this.addNode(670, 216, 45);
		this.addNode(656, 211, "North C. LC");
		this.addNode(680, 223, "Robert C. Pew LC");
		this.addNode(666, 230, 48);
		this.addNode(685, 242, 49);
		this.addNode(688, 255, 50);
		this.addNode(706, 258, 51);
		this.addNode(742, 262, 52);
		this.addNode(702, 246, "William Kill Patrick LC");
		this.addNode(746, 252, "Dale Stafford LC");
		this.addNode(742, 276, "Max Swansin LC");
		this.addNode(702, 270, "Seedman LC");
		this.addNode(662, 255, 57);
		this.addNode(663, 244, "Auther Hills LC");
		this.addNode(665, 266, "William Packard LC");
		this.addNode(653, 253, 60);
		this.addNode(634, 236, 61);
		this.addNode(620, 230, 62);
		this.addNode(617, 218, "Robert Kliener Commons");
		this.addNode(602, 229, 64);
		this.addNode(577, 231, 65);
		this.addNode(592, 255, 66);
		this.addNode(620, 254, "Greg Olsin Kisler LC");
		this.addNode(583, 279, 68);
		this.addNode(592, 279, 69);
		this.addNode(587, 320, 70);
		this.addNode(610, 320, 71);
		this.addNode(598, 376, 72);
		this.addNode(612, 378, 73);
		this.addNode(614, 390, 74);
		
		//this.addNode(641, 376, 69);

		this.addLane(19, 20);
		this.addLane(19, 21);
		this.addLane(19, 22);
		this.addLane(22, 23);
		this.addLane(22, 24);
		this.addLane(24, 25);
		this.addLane(25, 26);

		this.addLane(25, 27);
		this.addLane(24, 27);
		this.addLane(25, 28);
		this.addLane(27, 28);
		this.addLane(24, 29);
		this.addLane(24, 29);
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
		this.addLane(39, 37);
		this.addLane("Edward J. Frey LC", "39");

		this.addLane(41, 42);
		this.addLane(42, 36);
		this.addLane(43, 41);
		this.addLane(43, 42);
		this.addLane("Grace O. Kisler LC", "43");

		this.addLane(45, 43);
		this.addLane("North C. LC", "43");
		this.addLane("North C. LC", "45");
		this.addLane("Robert C. Pew LC", "45");
		this.addLane(48, 45);

		this.addLane(49, 48);
		this.addLane(50, 49);
		this.addLane(52, 51);
		this.addLane("William Kill Patrick LC", "51");
		this.addLane("Dale Stafford LC", "52");

		this.addLane("Max Swansin LC", "52");
		this.addLane("Seedman LC", "51");
		this.addLane(57, 50);
		this.addLane("Auther Hills LC", "57");
		this.addLane("William Packard LC", "57");

		this.addLane(61, 62);
		this.addLane(61, 60);
		this.addLane(61, 36);
		this.addLane(61, 48);
		
		this.addLane(60, 57);
		this.addLane(64, 62);
		this.addLane(66, 62);
		this.addLane(66, 65);

		this.addLane(65, 64);
		this.addLane("Robert Kliener Commons", "62");
		this.addLane("Greg Olsin Kisler LC", "62");
		this.addLane(68, 66);
		this.addLane(69, 66);

		this.addLane(69,68);
		this.addLane(70, 68);
		this.addLane(71, 70);
		this.addLane(72, 70);
		this.addLane(73, 72);

		this.addLane(74, 73);
		this.addLane(75, 73);

		/**
		 * Add Nodes 
		 * Class Room loactions
		 */
		
		this.addNode(584, 334, "Mackinaw");
		
		this.addNode(550, 360, "Manitou");
		
		this.addNode(578, 390, "Fresh Food Dinning");
		
		this.addNode(547, 418, "Padnos");
		
		this.addNode(514, 419, "Loutit");
		
		this.addNode(511, 438, "Henry Hall");
		
		this.addNode(499, 553, "Student Services");
		
		this.addNode(507, 483, "Cook-Dewitt Center");
		
		this.addNode(501, 504, "Clocktower");
		
		this.addNode(500, 523, "Kirkoff");
		
		this.addNode(471, 515, "Library");
		
		this.addNode(521, 578, "Preforming Arts");
		
		this.addNode(612, 511, "Au Sable Hall");
		
		this.addNode(617, 546, "Lake Huron Hall");
		
		this.addNode(604, 571, "Seidman house");
		
		this.addNode(573, 578, "Lake Superior");
		
		this.addNode(584, 604, "Lake Michigan");
		
		this.addNode(612, 608, "Lake Ontario");
		
		this.addNode(699, 611, "Calder");
		
		
		/**
		 * Add Paths from calder to Mackinaw
		 */
		
		
		this.addNode(658, 603, 84); 
		
		this.addLane("84", "Calder");
		
		this.addLane("84", "Lake Ontario");
		
		this.addNode(587, 592, 85);
		
		this.addLane("Lake Ontario", "85");
		
		this.addLane("Lake Michigan", "85");
		
		this.addNode(587, 571, 86);
		
		this.addLane("Seidman house", "86");
		
		this.addLane("Lake Superior", "86");
		
		this.addNode(586, 557, 87);
		
		this.addLane("86", "87");
		
		this.addNode(625, 562, 88);
		
		this.addLane("87", "88");
		
		this.addNode(586, 528, 89);
		
		this.addLane("87", "89");
		
		this.addLane("Zumberge", "89");
		
		this.addNode(587, 514, 90);
		
		this.addLane("89", "90");
		
		this.addLane("Au Sable Hall", "90");
		
		this.addNode(582, 454, 91);
		
		this.addNode(558, 398, 93);
		
		this.addLane("91", "92");
		
		this.addLane("91", "90");
		
		this.addLane("93", "Padnos");
		
		this.addLane("93", "Fresh");
		
		this.addNode(561, 417, 92);
		
		this.addNode(599, 385, 94);
		
		this.addNode(598, 361, 95);
		
		this.addNode(609, 361, "Holden Hocker");
		
		this.addLane("95", "Holden Hocker");
		
	}
}
