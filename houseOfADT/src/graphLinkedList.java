import java.util.ArrayList;

 public class graphLinkedList{

	Node<graphEdge> lastNode;
	Node<graphEdge> firstNode; 
	 
	private int sizeOfList;
	ArrayList<String> contentsOfList;
	
	graphLinkedList(){
		sizeOfList = 0;
		lastNode = new Node<graphEdge>();
		firstNode = new Node<graphEdge>();
		contentsOfList = new ArrayList<String>();
	}
		
	int getSize() {
		
		return this.sizeOfList;
	}
	
	ArrayList<String> traverseListForward() {
		contentsOfList = new ArrayList<String>();
		if(this.isListEmpty()) {
			System.out.println("*DEBUG * traverseListForward : List is empty. Add items to traverse.");
			return contentsOfList;
		}
		Node<graphEdge> tmp = new Node<graphEdge>();
		tmp = firstNode;
		while(true) { 

			//System.out.println("--> " + tmp.getValueAtNode().getVertexName()); 
			contentsOfList.add(tmp.getValueAtNode().getVertexName());
			
			if(this.getSize() == 1) return contentsOfList;
			
			if(tmp.getnextNode() == null) break;
			else tmp = tmp.getnextNode();
			
		}
		 return contentsOfList;
	}
	
	void dumpLinkedListNodes() {
		contentsOfList = new ArrayList<String>();
		if(this.isListEmpty()) {
			System.out.println("*DEBUG * traverseListForward : List is empty. Add items to traverse.");
			return;// contentsOfList;
		}
		Node<graphEdge> tmp = new Node<graphEdge>();
		tmp = firstNode;
		while(true) { 

			System.out.println("--> " + tmp.getValueAtNode().getVertexName()); 
			contentsOfList.add(tmp.getValueAtNode().getVertexName());
			
			if(this.getSize() == 1) return;// contentsOfList;
			
			if(tmp.getnextNode() == null) break;
			else tmp = tmp.getnextNode();
			
		}
		 return;// contentsOfList;
	}
	
	void traverseListBackward() {
		if(this.isListEmpty()) {
			System.out.println("List is empty. Add items to traverse.");
			return;
		}
		Node<graphEdge> tmp = new Node<graphEdge>();
		tmp = lastNode;
		while(true){

			System.out.println("--> " + tmp.getValueAtNode());
			if(tmp.getprevNode() == null) break;
			else tmp = tmp.getprevNode();
		}
 
	}
	void addNodeToStartOfList(graphEdge nodeValue) {
		Node<graphEdge> tmpNode = new Node<graphEdge>();
		
		if(isListEmpty()) {
			firstNode = (new Node<graphEdge>()).createNode(nodeValue);
			firstNode.setprevNode(null);
			lastNode = firstNode;
			firstNode.setnextNode(firstNode);
		}
		else {

			tmpNode = (new Node<graphEdge>()).createNode(nodeValue);
			tmpNode.setprevNode(null);
			tmpNode.setnextNode(firstNode);
			firstNode.setprevNode(tmpNode);
			firstNode = tmpNode;
			
			if(this.getSize() == 1) lastNode.setprevNode(firstNode);	
			else lastNode.setprevNode(tmpNode);
		 
		}
		this.sizeOfList++;
	}
	
	void addNodeBetweenTwoNodesPos(int positionx, int positiony) {
		/*
		 * unimplemented as of now.
		 */
	}
	void addNodeToList(graphEdge nodeValue) {
		
		Node<graphEdge>tmpNode = new Node<graphEdge>();
		
		if(isListEmpty()) {
			//0
			
			firstNode.setValueAtNode(nodeValue);
			firstNode.setprevNode(null);
			firstNode.setnextNode(null);
		}
		else {
			
			if(this.getSize() > 1) {
				//> 1
				lastNode.nextNode = tmpNode.createNode(nodeValue);  
				lastNode.nextNode.prevNode = lastNode;
				lastNode.nextNode.nextNode = null;
				tmpNode = lastNode.getnextNode();
				lastNode = tmpNode;
			}
			else {
				
				//1
				lastNode.setValueAtNode(nodeValue); 
				lastNode.nextNode = null;
				firstNode.nextNode = lastNode;
				lastNode.prevNode = firstNode;				
			}
 
		}
	
		this.sizeOfList++;
		
	}
	
	void deleteNodeWithValues(Object valueAtNode) {};
	
	graphEdge deleteNodeAtPos(int positionx) {
		
		graphEdge nodeValueAtPos = new graphEdge();
		int traverseCounter = 0;
		//Special case 1 : error 
		if(this.isListEmpty()) {
			System.out.println("Cannot delete an empty list. Remove elements.");
			return null;
		}
		//Special case 2 : error 
		if(positionx > this.sizeOfList) {
			System.out.println("Bad index of delete. Position of delete is greater than size of list.");
			return null; 
		}
		//Special case 3 : error 
		if(positionx == 0) {
			System.out.println("Bad index of delete. Position zero is undefined. List begins from pos 1.");
			return null;
		}
		
		Node<graphEdge> traverseNode = new Node<graphEdge>();
		
		if(positionx == 1 | this.sizeOfList == 1) {
			nodeValueAtPos = this.firstNode.getValueAtNode();
			this.firstNode = new Node<graphEdge>();
			/*this.firstNode = traverseNode.getnextNode();
			this.firstNode.setprevNode(null);*/
			this.sizeOfList = 0;
			return nodeValueAtPos;
		}
		else {
			
			if(positionx == this.sizeOfList) {
				
				traverseNode = this.lastNode;
				nodeValueAtPos = traverseNode.getValueAtNode();
				this.lastNode = traverseNode.getprevNode();
				this.lastNode.setnextNode(null);
				this.sizeOfList--;
				return nodeValueAtPos;
			}
		}
		traverseNode = this.firstNode;
		while(true) {
			traverseCounter++;
			
			if(traverseCounter == positionx) {
				nodeValueAtPos = traverseNode.getValueAtNode();
				traverseNode.getnextNode().setprevNode(traverseNode.getprevNode());
				traverseNode.getprevNode().setnextNode(traverseNode.getnextNode());
				this.sizeOfList--;
				return nodeValueAtPos;
			}
			else traverseNode = traverseNode.getnextNode();
		}
	}
	
	void deleteAllNodesBetweenPos(int positionx, int positiony) {
		
		boolean deleteInvolvesFirstNode = false;
		boolean deleteInvolvesLastNode = false;
		Node<graphEdge> traverseNode = new Node<graphEdge>();
		Node<graphEdge> traverseNodex = new Node<graphEdge>();
		System.out.println("deleteAllNodesBetweenPos: called for " + positionx + " to  " + positiony);
		if(positionx < 1 | positionx > this.getSize() | positionx > this.getSize() |
				positionx >= positiony | positiony < 1) System.out.println("bad values for index. Provide proper range.");
		
		if(positionx == 1) deleteInvolvesFirstNode = true;
		if(positiony == this.getSize()) deleteInvolvesLastNode = true;
		
		if(deleteInvolvesFirstNode & deleteInvolvesLastNode) {
			System.out.println("deleteAllNodesBetweenPos: First and last node involvement case. ");
			while(true) {
				if(this.sizeOfList < 1) break;
				else this.deleteFirstNode();
			}
			return;
		}
		
		traverseNode = firstNode;
		int counter = 1;
		while(true) {
			
			if (positionx == counter) break;
			else {
			//	System.out.println(counter + " --- " + traverseNode.getValueAtNode());  
				traverseNode = traverseNode.getnextNode();
				counter++;
			}
		}
		
		traverseNodex = traverseNode;
		//deleteInvolvesFirstNode
		while(true) {
			
			if (positiony == counter | traverseNode.getnextNode() == null) break;
			else {
			//	System.out.println(counter + " --- " + traverseNode.getValueAtNode());  
				traverseNode = traverseNode.getnextNode();
				counter++;
			}
		}
		
		traverseNodex.setnextNode(traverseNode);
		traverseNode.setprevNode(traverseNodex);

		this.sizeOfList = this.sizeOfList - (positiony - positionx - 1);
	};
	
	boolean isListEmpty() { 
		
		if(this.getSize() == 0) return true;
		else return false;
	}
	
	
	int isIteminList(String value) {

		
		Node<graphEdge> tmp = new Node<graphEdge>();
		tmp = firstNode;
		int position = 0;
		while(true) { 
			 
			if(value == tmp.getValueAtNode().getVertexName()) {
				position++;
				break;
			}
			else {	
				if(this.getSize() == 1) return -1;
				
				if(tmp.getnextNode() == null) return -1;
				else {
					position++;
					tmp = tmp.getnextNode();
				}
			}
		}
		
		
		return position;
		
		
	};
	
	void deleteFirstNode() {
		if(this.sizeOfList > 1) {
			firstNode = firstNode.getnextNode();
			firstNode.setprevNode(null);
			this.sizeOfList--;
		}
		else {
			firstNode = new Node<graphEdge>();
			lastNode = new Node<graphEdge>();
			this.sizeOfList = 0;
		}
		
	}
	
	void deleteLastNode() {
		lastNode = lastNode.getprevNode();
		lastNode.setnextNode(null);
		this.sizeOfList--;
	}
	
	boolean updateNodeValueAtPos(int position, graphEdge newNodeValue) {
		
		int posCounter = 0;
		
		if(position > this.sizeOfList) return false;
		else {
			Node<graphEdge> tmp = new Node<graphEdge>();
			tmp = firstNode;
			while(true) { 
				posCounter++;
				
				if(this.getSize() == 1) {
					firstNode.setValueAtNode(newNodeValue);
					break;
				}
				else {
					
					if(posCounter == position) {
						tmp.setValueAtNode(newNodeValue);
						break;
					}
					
					if(tmp.getnextNode() == null) return false;
					else tmp = tmp.getnextNode();

				}
			}
			
		}
	
		return true;
	}
}
