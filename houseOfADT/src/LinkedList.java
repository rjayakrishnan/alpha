import java.util.ArrayList;

/*
 * 
 * 		Written by : Jay Rajendran
 * 		BITS ID : 2018ab04052
 * 		Dated : 4/11/2018
 * 		
 * 		Description : Implementation of a double linked list. 
 * 
 * 		Linked list is tracked using last Node and first node of the list. 
 * 						Size of List is available @ all times.
 * 
 * 		Methods implemented are :
 * 			1 - Add a node.												addNodeToList()
 * 			2 - Delete a node from beginning of list.					deleteNodeAtPos()
 * 			3 - Delete a node from end of list.							deleteAllNodesBetweenPos()
 * 			4 - Delete a node from a specified position. 				deleteFirstNode()
 * 			5 - delete a node between a set of positions ( range ).		deleteLastNode()
 * 			6 - Traverse list from beginning to end of list. 			traverseListForward()
 * 			7 - Traverse list from end to beginning of list. 			traverseListBackward()
 * 	* >>		8 - To be implemented - remove all nodes with a particular value.  		deleteNodeWithValues()
 * 	 		9 - Detect if a specific value exist in list.					isIteminList()  
 */
 public class LinkedList<T>{

	Node<T> lastNode;
	Node<T> firstNode; 
	 
	private int sizeOfList;
	ArrayList<T> contentsOfList;
	
	LinkedList(){
		sizeOfList = 0;
		lastNode = new Node<T>();
		firstNode = new Node<T>();
		contentsOfList = new ArrayList<T>();
	}
		
	int getSize() {
		
		return this.sizeOfList;
	}
	
	ArrayList<T> traverseListForward() {
		contentsOfList = new ArrayList<T>();
		if(this.isListEmpty()) {
			System.out.println("List is empty. Add items to traverse.");
			return contentsOfList;
		}
		Node<T> tmp = new Node<T>();
		tmp = firstNode;
		while(true) { 

			//System.out.println("--> " + tmp.getValueAtNode()); 
			contentsOfList.add(tmp.getValueAtNode());
			
			if(this.getSize() == 1) return contentsOfList;
			
			if(tmp.getnextNode() == null) break;
			else tmp = tmp.getnextNode();
			
		}
		 return contentsOfList;
	}
	
	void traverseListBackward() {
		if(this.isListEmpty()) {
			System.out.println("List is empty. Add items to traverse.");
			return;
		}
		Node<T> tmp = new Node<T>();
		tmp = lastNode;
		while(true){

			System.out.println("--> " + tmp.getValueAtNode());
			if(tmp.getprevNode() == null) break;
			else tmp = tmp.getprevNode();
		}
 
	}
	void addNodeToStartOfList(T nodeValue) {
		Node<T> tmpNode = new Node<T>();
		
		if(isListEmpty()) {
			firstNode = (new Node<T>()).createNode(nodeValue);
			firstNode.setprevNode(null);
			lastNode = firstNode;
			firstNode.setnextNode(firstNode);
		}
		else {

			tmpNode = (new Node<T>()).createNode(nodeValue);
			tmpNode.setprevNode(null);
			tmpNode.setnextNode(firstNode);
			firstNode.setprevNode(tmpNode);
			firstNode = tmpNode;
			
			if(this.getSize() == 1) lastNode.setprevNode(firstNode);	
			else lastNode.setprevNode(tmpNode);
		 
		}
		sizeOfList++;
	}
	
	void addNodeBetweenTwoNodesPos(int positionx, int positiony) {
		/*
		 * unimplemented as of now.
		 */
	}
	void addNodeToList(T nodeValue) {
		
		Node<T> tmpNode = new Node<T>();
		
		if(isListEmpty()) {
			firstNode = (new Node<T>()).createNode(nodeValue);
			firstNode.setprevNode(null);
			lastNode = firstNode;
			firstNode.setnextNode(firstNode);
		}
		else {

			lastNode.nextNode = (new Node<T>()).createNode(nodeValue);
			tmpNode = lastNode;
			lastNode = lastNode.getnextNode();
			
			if(this.getSize() == 1) lastNode.setprevNode(firstNode);	
			else lastNode.setprevNode(tmpNode);
		 
		}
		sizeOfList++;
		
	}
	
	void deleteNodeWithValues(Object valueAtNode) {};
	
	T deleteNodeAtPos(int positionx) {
		
		T nodeValueAtPos;
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
		
		int midwayPoint = Math.round((this.getSize() / 2));
		Node<T> traverseNode = new Node<T>();
		 
		traverseNode = firstNode;
		//Special case 4 : removal of first node
		if(positionx == 1) {			
			nodeValueAtPos = firstNode.getValueAtNode();
			this.deleteFirstNode();
			return nodeValueAtPos;
		}
		//Special case 4 : removal of end node
		if(positionx == this.getSize()) {			
			nodeValueAtPos = lastNode.getValueAtNode();			
			this.deleteLastNode();			
			return nodeValueAtPos;
		}
		int counter = 1;
		//removal of node anywhere in between
		if (midwayPoint > positionx) {
			//begin from start
			
			while(true) {
				
				if (positionx == counter) break;
				else {
				//	System.out.println(counter + " --- " + traverseNode.getValueAtNode());  
					traverseNode = traverseNode.getnextNode();
					counter++;
				}
			}
			nodeValueAtPos = traverseNode.getValueAtNode();
			(traverseNode.getprevNode()).setnextNode(traverseNode.getnextNode());//previous node connected to node in front
			(traverseNode.getnextNode()).setprevNode(traverseNode.getprevNode());//next node is connected to previous node of affected
			
		}
		else {
			counter = this.getSize();
			traverseNode = lastNode;
			//begin from end.
			while(true) {
				
				if (positionx == counter) break;
				else {
				//	System.out.println(counter + " --- " + traverseNode.getValueAtNode());  
					traverseNode = traverseNode.getprevNode();
					counter--;
				}
			}
			//System.out.println("Value @ effected node is " + traverseNode.getValueAtNode());
			nodeValueAtPos = traverseNode.getValueAtNode();
			(traverseNode.getprevNode()).setnextNode(traverseNode.getnextNode());//previous node connected to node in front
			(traverseNode.getnextNode()).setprevNode(traverseNode.getprevNode());//next node is connected to previous node of affected
		}
		this.sizeOfList--;
		
		return nodeValueAtPos;
	}
	
	void deleteAllNodesBetweenPos(int positionx, int positiony) {
		
		boolean deleteInvolvesFirstNode = false;
		boolean deleteInvolvesLastNode = false;
		
		if(positionx < 1 | positionx > this.getSize() | positionx > this.getSize() |
				positionx >= positiony | positiony < 1) System.out.println("bad values for index. Provide proper range.");
		
		if(positionx == 1) deleteInvolvesFirstNode = true;
		if(positionx == this.getSize()) deleteInvolvesLastNode = true;
		
		if(deleteInvolvesFirstNode & deleteInvolvesLastNode) {
			firstNode = null;
			lastNode = null;
			this.sizeOfList = 0;
			return;
		}
		
		Node<T> traverseNode = new Node<T>();
		Node<T> traverseNodex = new Node<T>();
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
			
			if (positiony == counter) break;
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

		
		Node<T> tmp = new Node<T>();
		tmp = firstNode;
		int position = 0;
		while(true) { 
			 
			if(value == tmp.getValueAtNode()) {
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
		
		if(this.getSize() < 2) firstNode = new Node<T>();
		else {
			firstNode = firstNode.getnextNode();
			firstNode.setprevNode(null);
		} 
		this.sizeOfList--;
	}
	
	void deleteLastNode() {
		lastNode = lastNode.getprevNode();
		lastNode.setnextNode(null);
		this.sizeOfList--;
	}
	
	boolean updateNodeValueAtPos(int position, T newNodeValue) {
		
		int posCounter = 0;
		
		if(position > this.sizeOfList) return false;
		else {
			Node<T> tmp = new Node<T>();
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
