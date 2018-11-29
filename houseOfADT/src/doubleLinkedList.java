
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
 * 	* >>		9 - To be implemented - 	Detect if a specific value exist in list.		isIteminList()  
 */
public class doubleLinkedList{

	doubleLinkedListNode lastNode;
	doubleLinkedListNode firstNode; 
	 
	private int sizeOfList;
	
	doubleLinkedList(){
		sizeOfList = 0;
		lastNode = new doubleLinkedListNode();
		firstNode = new doubleLinkedListNode();
		
	}
		
	int getSize() {
		
		return this.sizeOfList;
	}
	void traverseListForward() {
		if(this.isListEmpty()) {
			System.out.println("List is empty. Add items to traverse.");
			return;
		}
		doubleLinkedListNode tmp = new doubleLinkedListNode();
		tmp = firstNode;
		while(true) { 

			System.out.println("--> " + tmp.getValueAtNode()); 
			
			if(this.getSize() == 1) break;
				
			if(tmp.nextNode == null) break;
			else tmp = tmp.nextNode;
			
		}
		 
	}
	
	void traverseListBackward() {
		if(this.isListEmpty()) {
			System.out.println("List is empty. Add items to traverse.");
			return;
		}
		doubleLinkedListNode tmp = new doubleLinkedListNode();
		tmp = lastNode;
		while(true){

			System.out.println("--> " + tmp.getValueAtNode());
			if(tmp.prevNode == null) break;
			else tmp = tmp.prevNode;
		}
 
	}
	
	void addNodeToList(Object nodeValue) {
		
		doubleLinkedListNode tmpNode = new doubleLinkedListNode();
		
		if(isListEmpty()) {
			firstNode = doubleLinkedListNode.createNode(nodeValue);
			firstNode.prevNode = null;
			lastNode = firstNode;
			firstNode.nextNode = firstNode;
		}
		else {

			lastNode.nextNode = doubleLinkedListNode.createNode(nodeValue);
			tmpNode = lastNode;
			lastNode = lastNode.nextNode;
			
			if(this.getSize() == 1) lastNode.prevNode = firstNode;	
			else lastNode.prevNode = tmpNode;
		 
		}
		sizeOfList++;
		
	}
	
	void deleteNodeWithValues(Object valueAtNode) {};
	
	void deleteNodeAtPos(int positionx) {
		//Special case 1 : error 
		if(this.isListEmpty()) {
			System.out.println("Cannot delete an empty list. Remove elements.");
			return;
		}
		//Special case 2 : error 
		if(positionx > this.sizeOfList) {
			System.out.println("Bad index of delete. Position of delete is greater than size of list.");
			return;
		}
		//Special case 3 : error 
		if(positionx == 0) {
			System.out.println("Bad index of delete. Position zero is undefined. List begins from pos 1.");
			return;
		}
		
		int midwayPoint = Math.round((this.getSize() / 2));
		doubleLinkedListNode traverseNode = new doubleLinkedListNode();
		//doubleLinkedList modifiedList = new doubleLinkedList();
		traverseNode = firstNode;
		//Special case 4 : removal of first node
		if(positionx == 1) {
			
			firstNode = traverseNode.nextNode;
			(traverseNode.nextNode).prevNode = null;
			this.sizeOfList--;
			return;
		}
		//Special case 4 : removal of end node
		if(positionx == this.getSize()) {
			traverseNode = lastNode;
			lastNode = traverseNode.prevNode;
			lastNode.nextNode = null;
			this.sizeOfList--;
			return;
		}
		int counter = 1;
		//removal of node anywhere in between
		if (midwayPoint > positionx) {
			//begin from start
			
			
			while(true) {
				
				if (positionx == counter) break;
				else {
				//	System.out.println(counter + " --- " + traverseNode.getValueAtNode());  
					traverseNode = traverseNode.nextNode;
					counter++;
				}
			}
			System.out.println("Value @ effected node is " + traverseNode.getValueAtNode());
			(traverseNode.prevNode).nextNode = traverseNode.nextNode;//previous node connected to node in front
			(traverseNode.nextNode).prevNode = traverseNode.prevNode;//next node is connected to previous node of affected
			
		}
		else {
			counter = this.getSize();
			traverseNode = lastNode;
			//begin from end.
			while(true) {
				
				if (positionx == counter) break;
				else {
				//	System.out.println(counter + " --- " + traverseNode.getValueAtNode());  
					traverseNode = traverseNode.prevNode;
					counter--;
				}
			}
			System.out.println("Value @ effected node is " + traverseNode.getValueAtNode());
			(traverseNode.prevNode).nextNode = traverseNode.nextNode;//previous node connected to node in front
			(traverseNode.nextNode).prevNode = traverseNode.prevNode;//next node is connected to previous node of affected
		}
		this.sizeOfList--;
		
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
		
		doubleLinkedListNode traverseNode = new doubleLinkedListNode();
		doubleLinkedListNode traverseNodex = new doubleLinkedListNode();
		traverseNode = firstNode;
		int counter = 1;
		while(true) {
			
			if (positionx == counter) break;
			else {
			//	System.out.println(counter + " --- " + traverseNode.getValueAtNode());  
				traverseNode = traverseNode.nextNode;
				counter++;
			}
		}
		
		traverseNodex = traverseNode;
		//deleteInvolvesFirstNode
		while(true) {
			
			if (positiony == counter) break;
			else {
			//	System.out.println(counter + " --- " + traverseNode.getValueAtNode());  
				traverseNode = traverseNode.nextNode;
				counter++;
			}
		}
		
		traverseNodex.nextNode = traverseNode;
		//if(deleteInvolvesLastNode)
		traverseNode.prevNode = traverseNodex;
		//deleteInvolvesLastNode
		this.sizeOfList = this.sizeOfList - (positiony - positionx - 1);
	};
	
	boolean isListEmpty() { 
		
		if(this.getSize() == 0) return true;
		else return false;
	}
	
	void isIteminList(Object value) {};
	
	void deleteFirstNode() {
		
		firstNode = firstNode.nextNode;
		firstNode.prevNode = null;
		this.sizeOfList--;
	}
	
	void deleteLastNode() {
		lastNode = lastNode.prevNode;
		lastNode.nextNode = null;
		this.sizeOfList--;
	}
	
}
