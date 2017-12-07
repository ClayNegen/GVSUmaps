package guiPack;

import java.util.ArrayList;
import java.util.List;

/*******************************************************************************
 * This class creates the hard copy of the graph of GVSU Allendale.
 * 
 * @author Douglas Wallin
 ******************************************************************************/
public class GVSUMap {
	
	/** A list of all nodes in the graph. Used by the engine controller.*/
	private List<MapNode> nodeList;
	
	/**	A list of all edges in the graph. Used by the engine controller. */
	private List<Edge> edgeList;

	/***************************************************************************
	 * Constructor for the hard-coded graph(map) of the GVSU Allendale campus.
	 * Creates an edge list and a node list
	 **************************************************************************/
	public GVSUMap() {
		nodeList = new ArrayList<MapNode>();
		edgeList = new ArrayList<Edge>();
		this.initialize();
	}


	/***************************************************************************
	 * Creates and adds a MapNode to this GVSUMap.
	 * 
	 * @param x int: The x coordinate of the node
	 * @param y int: The y coordinate of the node
	 * @param info String: A building name
	 **************************************************************************/
	private void addNode(final int x, final int y, final String info) {
		MapNode temp = new MapNode(x, y, info);
		nodeList.add(temp);
	}
	
	/***************************************************************************
	 * Creates and adds a MapNode to this GVSUMap.
	 * 
	 * @param x int: The x coordinate of the node
	 * @param y int: The y coordinate of the node
	 * @param info int: A numeric identifier
	 **************************************************************************/
	private void addNode(final int x, final int y, final int info) {
		MapNode temp = new MapNode(x, y, Integer.toString(info));
		nodeList.add(temp);
	}
 
	
	/***************************************************************************
	 * @return nodeList: List<MapNode>
	 **************************************************************************/
	public List<MapNode> getNodeList() {
		return nodeList;
	}
	
	/***************************************************************************
	 * @return edgeList: List<Edge>
	 **************************************************************************/
	public List<Edge> getEdgeList() {
		return edgeList;
	}
	
	/***************************************************************************
     * Looks up a node by the nodeInfo member of MapNode from the original map.
     * 
     * @param info int: The nodeInfo of the node to be looked up
     * 
     * @return MapNode: The node desired
     **************************************************************************/
	public MapNode getNode(final int info) {
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
	
	/***************************************************************************
	 * This method is used by initialize to create an edge in both directions
	 * between two nodes. It looks up the nodes by the nodeInfo field. Looks
	 * up by int instead of String
	 * 
	 * @param srcNodeInfo int: The nodeInfo of the source node.
	 * @param destNodeInfo int: The nodeInfo of the destination node
	 **************************************************************************/
	public void addLane(final int srcNodeInfo, final int destNodeInfo) {
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
	
	/***************************************************************************
	 * This method is used by initialize to create an edge in both directions
	 * between two nodes. It looks up the nodes by the nodeInfo field.
	 * 
	 * @param srcNodeInfo String: The nodeInfo of the source node.
	 * @param destNodeInfo String: The nodeInfo of the destination node
	 **************************************************************************/
	public void addLane(final String srcNodeInfo, final String destNodeInfo) {
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

	/***************************************************************************
	 * This method creates a hard-coded copy of the Allendale campus. Adds all
	 * nodes and edges manually.
	 **************************************************************************/
	private void initialize() {
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
		this.addNode(577, 198, 29);
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
		this.addNode(620, 236, 62);
		this.addNode(617, 218, "Robert Kliener Commons");
		this.addNode(602, 229, 64);
		this.addNode(577, 231, 65);
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
		
		this.addNode(583, 249, 66);
		this.addLane(65, 64);
		this.addLane(66, 65);
		this.addLane(66, 64);
		this.addNode(573, 229, "Paul A Johnson Living Center");
		this.addLane("65", "Paul A Johnson Living Center");
		this.addLane(50, 51);
		this.addLane("62", "Robert Kliener Commons");
		this.addNode(593, 257, 68);
		this.addNode(608, 248, 69);
		this.addLane(68, 66);
		this.addLane(68, 69);
		this.addLane(69, 62);
		this.addNode(583, 259, 70);
		this.addLane(70, 68);
		this.addLane(70, 66);
		this.addNode(575, 264, 71);
		this.addLane(71, 70);
		this.addNode(566, 261, 72);
		this.addLane(72, 71);
		this.addNode(566, 240, 73);
		this.addLane(73, 72);
		this.addNode(565, 221, 74);
		this.addLane(74, 73);
		this.addNode(560, 203, 75);
		this.addLane(75, 21);
		this.addLane(75, 20);
		this.addLane(75, 74);
		this.addLane(9, 16);
		this.addNode(534, 227, 76);
		this.addLane(76, 16);
		this.addLane(76, 74);
		this.addNode(535, 244, 85);
		this.addLane(85, 76);
		this.addNode(533, 294, 86);
		this.addLane(86, 85);
		this.addNode(566, 290, 87);
		this.addLane(87, 70);
		this.addLane(87, 86);
		this.addLane(29, 27);
		this.addNode(450, 221, 88);
		this.addLane(88, 14);
		this.addNode(418, 229, 89);
		this.addLane(89, 88);
		this.addNode(410, 204, 90);
		this.addLane(90, 89);
		this.addNode(384, 192, 91);
		this.addLane(91, 90);
		this.addNode(377, 200, "Ravine Center");
		this.addLane("91", "Ravine Center");
		this.addNode(578, 295, 92);
		this.addLane(92, 87);
		this.addNode(592, 283, "James M Copeland Living Center");
		this.addLane("92", "James M Copeland Living Center");
		this.addNode(586, 309, 93);
		this.addLane(93, 92);
		this.addNode(590, 327, 94);
		this.addLane(94, 93);
		this.addNode(605, 320, "Kenneth W Robinson Living Center");
		this.addLane("94", "Kenneth W Robinson Living Center");
		this.addNode(599, 334, 95);
		this.addLane(95, 94);
		this.addNode(609, 338, 96);
		this.addLane(96, 95);
		this.addNode(598, 345, 97);
		this.addLane(97, 96);
		this.addLane(97, 95);
		this.addNode(631, 342, 98);
		this.addLane(98, 96);
		this.addNode(644, 343, 99);
		this.addLane(99, 98);
		this.addNode(646, 357, 100);
		this.addLane(100, 99);
		this.addNode(651, 370, 101);
		this.addLane(101, 100);
		this.addNode(663, 369, "Maple Living Center");
		this.addLane("101", "Maple Living Center");
		this.addNode(649, 376, 102);
		this.addLane(102, 101);
		this.addNode(641, 377, 103);
		this.addLane(103, 102);
		this.addNode(640, 385, "Oak Living Center");
		this.addLane("103", "Oak Living Center");
		this.addNode(626, 375, 104);
		this.addLane(104, 103);
		this.addNode(614, 379, 105);
		this.addLane(104, 105);
		this.addNode(611, 389, "Pine Living Center");
		this.addLane("105", "Pine Living Center");
		this.addNode(599, 379, 106);
		this.addLane(105, 106);
		this.addLane(106, 97);
		this.addNode(595, 386, 107);
		this.addLane(106, 107);
		this.addNode(569, 385, "The Commons");
		this.addLane("107", "The Commons");
		this.addNode(590, 374, 108);
		this.addLane(108, 106);
		this.addNode(570, 371, 109);
		this.addLane(109, 108);
		this.addNode(573, 349, 110);
		this.addLane(110, 109);
		this.addNode(572, 343, "Mackinaw Hall");
		this.addLane("110", "Mackinaw Hall");
		this.addNode(560, 349, 111);
		this.addLane(111, 110);
		this.addLane(111, 109);
		this.addNode(558, 370, 112);
		this.addLane(112, 111);
		this.addLane(112, 110);
		this.addLane(112, 109);
		this.addNode(528, 346, 113);
		this.addLane(113, 111);
		this.addLane(113, 86);
		this.addNode(520, 365, 114);
		this.addLane(113, 114);
		this.addLane(114, 112);
		this.addNode(497, 400, 115);
		this.addLane(115, 114);
		this.addNode(529, 401, "Seymour & Esther Padnos Hall of Science");
		this.addLane("115", "Seymour & Esther Padnos Hall of Science");
		this.addNode(559, 402, 116);
		this.addLane(116, 112);
		this.addNode(582, 402, 117);
		this.addLane(117, 107);
		this.addNode(497, 422, 118);
		this.addLane(118, 115);
		this.addNode(513, 419, "Loutit Lecture Hall");
		this.addLane("Loutit Lecture Hall", "118");
		this.addNode(471, 420, 119);
		this.addLane(119, 115);
		this.addLane(119, 118);
		this.addNode(470, 448, 120);
		this.addLane(120, 119);
		this.addNode(485, 448, "Student Services Building");
		this.addLane("120", "Student Services Building");
		this.addNode(560, 414, 121);
		this.addLane(121, 116);
		this.addLane(121, 117);
		this.addNode(557, 423, 122);
		this.addLane(122, 121);
		this.addNode(565, 423, 123);
		this.addLane(123, 121);
		this.addLane(123, 122);
		this.addNode(583, 454, 124);
		this.addLane(124, 123);
		this.addNode(524, 441, 125);
		this.addLane(125, 122);
		this.addNode(513, 447, 126);
		this.addLane(126, 125);
		this.addNode(497, 449, 128);
		this.addLane(128, 126);
		this.addNode(496, 478, 127);
		this.addLane(127, 128);
		this.addLane("128", "Student Services Building");
		this.addLane(127, 126);
		this.addNode(482, 382, 129);
		this.addLane(129, 115);
		this.addNode(469, 373, 130);
		this.addLane(130, 129);
		this.addNode(450, 341, "Field House");
		this.addLane("130", "Field House");
		this.addNode(514, 288, 131);
		this.addLane(131, 86);
		this.addNode(467, 266, 132);
		this.addLane(132, 131);
		this.addNode(441, 257, 133);
		this.addLane(133, 132);
		this.addNode(418, 258, 134);
		this.addLane(134, 133);
		this.addLane(134, 89);
		this.addNode(444, 446, 135);
		this.addLane(135, 119);
		this.addLane(135, 120);
		this.addNode(469, 467, 136);
		this.addLane(136, 135);
		this.addNode(477, 486, 137);
		this.addLane(137, 136);
		this.addLane(137, 127);
		this.addNode(497, 500, 138);
		this.addLane(138, 127);
		this.addNode(484, 499, 139);
		this.addLane(139, 137);
		this.addLane(139, 138);
		this.addNode(450, 499, 140);
		this.addLane(140, 137);
		this.addLane(140, 139);
		this.addNode(438, 498, 141);
		this.addLane(141, 140);
		this.addNode(433, 492, 142);
		this.addLane(142, 141);
		this.addNode(422, 490, 143);
		this.addLane(143, 142);
		this.addNode(429, 462, 144);
		this.addLane(144, 143);
		this.addLane(144, 135);
		this.addNode(506, 481, "Cook-DeWitt Center");
		this.addLane("127", "Cook-DeWitt Center");
		this.addNode(478, 510, 145);
		this.addLane(145, 139);
		this.addNode(470, 511, 146);
		this.addLane(146, 140);
		this.addLane(146, 145);
		this.addNode(489, 511, 147);
		this.addLane(147, 139);
		this.addLane(147, 145);
		this.addNode(497, 517, 148);
		this.addLane(148, 138);
		this.addLane(148, 147);
		this.addNode(467, 541, 149);
		this.addLane(149, 146);
		this.addNode(459, 552, 150);
		this.addLane(150, 149);
		this.addNode(487, 555, 151);
		this.addLane(151, 150);
		this.addNode(487, 541, "Kirkhof Center");
		this.addLane("151", "Kirkhof Center");
		this.addNode(495, 561, 152);
		this.addLane(152, 151);
		this.addNode(422, 497, 153);
		this.addLane(153, 143);
		this.addNode(421, 505, 154);
		this.addLane(154, 153);
		this.addNode(433, 504, 155);
		this.addLane(155, 154);
		this.addLane(155, 141);
		this.addNode(328, 496, 156);
		this.addLane(156, 153);
		this.addNode(427, 531, 157);
		this.addLane(157, 154);
		this.addNode(443, 560, 158);
		this.addLane(158, 157);
		this.addLane(158, 150);
		this.addNode(481, 606, 159);
		this.addLane(159, 158);
		this.addNode(330, 427, 160);
		this.addLane(160, 156);
		this.addNode(344, 411, 161);
		this.addLane(161, 160);
		this.addNode(351, 398, 162);
		this.addLane(162, 161);
		this.addNode(354, 376, 163);
		this.addLane(163, 162);
		this.addNode(407, 308, 164);
		this.addNode(415, 287, 165);
		this.addLane(165, 164);
		this.addLane(165, 134);
		this.addNode(388, 330, 166);
		this.addLane(166, 164);
		this.addLane(166, 163);
		this.addNode(371, 319, "Kelly Family Sports Center");
		this.addLane("166", "Kelly Family Sports Center");
		this.addNode(517, 500, 167);
		this.addLane(167, 138);
		this.addNode(535, 513, 168);
		this.addLane(168, 167);
		this.addNode(550, 513, 169);
		this.addLane(169, 168);
		this.addNode(511, 528, 170);
		this.addLane(170, 168);
		this.addNode(511, 541, 171);
		this.addLane(171, 170);
		this.addNode(516, 549, 172);
		this.addLane(172, 171);
		this.addNode(535, 556, 173);
		this.addLane(173, 172);
		this.addNode(524, 565, 174);
		this.addLane(174, 172);
		this.addLane(174, 173);
		this.addLane(174, 152);
		this.addNode(562, 509, 186);
		this.addLane(186, 169);
		this.addNode(573, 510, 175);
		this.addLane(175, 186);
		this.addNode(583, 469, 176);
		this.addLane(176, 175);
		this.addLane(176, 124);
		this.addNode(588, 479, 177);
		this.addLane(177, 176);
		this.addNode(587, 509, 178);
		this.addLane(178, 175);
		this.addLane(178, 177);
		this.addNode(604, 502, 179);
		this.addLane(179, 178);
		this.addLane(179, 177);
		this.addNode(612, 508, "AuSable Hall");
		this.addLane("179", "AuSable Hall");
		this.addNode(586, 513, 180);
		this.addLane(180, 178);
		this.addNode(586, 526, 181);
		this.addLane(181, 180);
		this.addNode(598, 527, 182);
		this.addLane(182, 181);
		this.addLane(182, 180);
		this.addNode(580, 520, 183);
		this.addLane(183, 180);
		this.addNode(579, 526, 184);
		this.addLane(184, 183);
		this.addLane(184, 181);
		this.addNode(572, 526, 185);
		this.addLane(185, 184);
		this.addLane(185, 175);
		this.addNode(561, 527, "James H Zumberge Hall");
		this.addLane("185", "James H Zumberge Hall");
		this.addNode(586, 541, 187);
		this.addLane(187, 181);
		this.addLane(187, 182);
		this.addNode(624, 526, 188);
		this.addLane(188, 182);
		this.addNode(623, 539, "Lake Huron Hall");
		this.addLane("188", "Lake Huron Hall");
		this.addNode(598, 554, 189);
		this.addLane(189, 187);
		this.addNode(585, 565, 190);
		this.addLane(190, 187);
		this.addLane(190, 189);
		this.addNode(606, 564, "Seidman HouseLemmen Library");
		this.addLane("190", "Seidman HouseLemmen Library");
		this.addNode(574, 553, 191);
		this.addLane(191, 187);
		this.addLane(191, 189);
		this.addLane(191, 190);
		this.addNode(566, 553, 192);
		this.addLane(192, 191);
		this.addNode(548, 553, 193);
		this.addLane(193, 192);
		this.addLane(193, 173);
		this.addNode(550, 531, 194);
		this.addLane(194, 193);
		this.addLane(194, 169);
		this.addNode(547, 574, 195);
		this.addLane(195, 174);
		this.addLane(195, 193);
		this.addNode(548, 586, 196);
		this.addLane(196, 195);
		this.addNode(584, 591, 197);
		this.addLane(197, 190);
		this.addLane(197, 196);
		this.addNode(584, 606, "Lake Michigan Hall");
		this.addLane("197", "Lake Michigan Hall");
		this.addNode(354, 220, 198);
		this.addLane(198, 89);
		this.addNode(315, 196, 199);
		this.addLane(199, 198);
		this.addNode(296, 174, 200);
		this.addLane(200, 199);
		this.addNode(279, 187, "Laker Stadium");
		this.addLane("200", "Laker Stadium");
		this.addNode(495, 596, 201);
		this.addLane(201, 159);
		this.addLane(201, 152);
		this.addNode(508, 593,
				"Performing Arts Center & Louis Armstrong Theatre");
		this.addLane("201", "Performing Arts Center & Louis Armstrong Theatre");
		this.addNode(594, 593, 202);
		this.addLane(202, 197);
		this.addNode(594, 587, 203);
		this.addLane(203, 202);
		this.addNode(611, 597, 204);
		this.addLane(204, 203);
		this.addNode(624, 597, 205);
		this.addLane(205, 204);
		this.addNode(623, 609, "Lake Ontario Hall");
		this.addLane("205", "Lake Ontario Hall");
		this.addNode(659, 599, 206);
		this.addLane(206, 205);
		this.addNode(684, 599, 207);
		this.addLane(207, 206);
		this.addNode(689, 586, 208);
		this.addLane(208, 207);
		this.addNode(700, 586, 209);
		this.addLane(209, 208);
		this.addNode(716, 576, "Alexander Calder Residence");
		this.addLane("209", "Alexander Calder Residence");
		this.addNode(691, 604, 210);
		this.addLane(210, 207);
		this.addNode(706, 604, "Alexander Calder Fine Arts Center");
		this.addLane("210", "Alexander Calder Fine Arts Center");
	}
}
