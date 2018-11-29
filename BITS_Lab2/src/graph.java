

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/*
 * LAB MODULE 2:
 * 	functions completed : 
		   *1. Add Vertex (Airport / City)
			2. Remove Vertex (Airport / City)
		   *3. Add Edge (Source, Destination and Airfare)
			*4. Remove Edge
			5. Display Adjacency Matrix
			*6. Display Edges
			7. BFS
			8. DFS
			*9. Check Flight Availability
 * 
 */
public class graph {
		
		ArrayList<GraphNode> vertexList; 
		Scanner scannerObject = new Scanner(System.in);
		int[][] AM;//Adjacency Matrix
		graph() {
			vertexList = new ArrayList<GraphNode>();
			AM = new int[10][10];
		}

		
		void processInput(String input) {
			String[] srcTxt;
			switch(input) {
			
/*done*/		case "1":
					System.out.println("\n\t\t Add a vertex option selected.");
					System.out.print("\t\t Enter your Vertex . ");
					String vertexName = scannerObject.nextLine();
					if(vertexName.isEmpty())
					    System.out.println("\t\t Bad Vertex Entry. retry.");
					else {
						addVertex(vertexName.toUpperCase());
					}
					
					break;
/* :-( */	case "2": 
					System.out.println("\n\t\t Remove a vertex option selected.");
					System.out.print("\t\t Enter the vertex to be removed : ");
					String vertexname = scannerObject.nextLine();
					
					deleteVertex(vertexname);

					break;
/*done*/		case "3":
					System.out.println("\n\t\t Add an Edge option selected.");
					System.out.print("\t\t Enter the edge in the format - [from to edgevalue] : ");
				
					String edgeDescriptor = scannerObject.nextLine().trim();			
				    String[] edgeValues = edgeDescriptor.split(" ");
					
					if((edgeValues.length - 3) != 0)
					    System.out.println("\t\t Bad entry. Expected is 3 terms. [Fromcity Tocity #]");
					else addEdge(edgeValues[0].toUpperCase(), edgeValues[1].toUpperCase(),Integer.valueOf(edgeValues[2]));
					break;
/*DONE*/		case "4":
					System.out.print("\n\t\t Removal of an edge selected. Enter source and destination.");
					srcTxt = scannerObject.nextLine().trim().split(" ");
					
					if(srcTxt.length == 2) {
						removeEdge(srcTxt[0].toUpperCase(), srcTxt[1].toUpperCase());
						removeEdge(srcTxt[1].toUpperCase(), srcTxt[0].toUpperCase());
					}
					else System.out.println("\t\t Not in proper Format. Re-enter");
					
					break;					
/* done */	case "5"://DISPLAY ADJACENCY MATRIX
					System.out.print("\n\t\t A D J A C E N C Y 		M A T R I X");
					printAM();
					break;
/*DONE*/		case "6": 	
					System.out.println("\n\t\t Display Edges option selected.");
					displayEdges();
					break;		
			case "8": 	//DFS
					System.out.println("\n\t\t!  DFS ACTIVATE !.");
					System.out.print("\n\t  DFS to begin from which NODE - ");
					String dfsVName = scannerObject.nextLine();
					if(this.VertexPos(dfsVName) < 0) System.out.println(" bad Vertex! Does not exist!");
					else DFS(dfsVName);
					break;
			case "7": 	//BFS
					System.out.println("\n\t\t!  BFS ACTIVATE !.");
					System.out.print("\n\t  BFS to begin from which NODE - ");
					String bfsVName = scannerObject.nextLine();
					if(this.VertexPos(bfsVName) < 0) System.out.println(" bad Vertex! Does not exist!");
					else BFS(bfsVName);					
					break;	
/* done */	case "9":
					System.out.print("\t\t Check Flight Availability selected.\n\t\t Enter source and Destination . ");
					
					srcTxt = scannerObject.nextLine().trim().split(" ");
					
					if(srcTxt.length == 2)
						flightAvailability(srcTxt[0].toUpperCase(), srcTxt[1].toUpperCase());
					else System.out.println("\t\t Not in proper Format. Re-enter");
					
					break;
			default: 
				System.out.println("\t\t Wrong input was given. This is not a valid operation on graph.");
				break;	
			}
			return;
		}

		private void BFS(String bfsVName) {
			// TODO Auto-generated method stub
			int sizeOfVL = this.vertexList.size(), posVName = this.VertexPos(bfsVName);
			// TODO Auto-generated method stub
			boolean[] nodeVisit = new boolean[sizeOfVL];
			Queue<Integer> queue = new Queue<Integer>();
			int qElement;
			
			queue.enQueue(posVName);
			
			nodeVisit[posVName] = true;
			System.out.print("==> BFS : ");
			while(!queue.isEmpty()) {
				qElement = queue.deQueue();
				System.out.print(" \t " + this.vertexList.get(qElement).getVertexName());
				
				for(int x = 0; x < sizeOfVL; x++) {
					if(this.AM[qElement][x] > 0 & !nodeVisit[x]) {
						queue.enQueue(x);
						nodeVisit[x] = true;
					}
					else continue;
				}
			}
		}


		private void DFS(String vName) {
			int sizeOfVL = this.vertexList.size(), posVName = this.VertexPos(vName);
			// TODO Auto-generated method stub
			boolean[] nodeVisit = new boolean[sizeOfVL];
			Stack<Integer> stack = new Stack<Integer>();
			int stackElement;
			stack.push(posVName);
			nodeVisit[posVName] = true;
			System.out.print("==> DFS : ");
			while(!stack.isEmpty()) {
				stackElement = stack.pop();
				System.out.print(" \t " + this.vertexList.get(stackElement).getVertexName());
				
				for(int x = 0; x < sizeOfVL; x++) {
					if(this.AM[stackElement][x] > 0 & !nodeVisit[x]) {
						stack.push(x);
						nodeVisit[x] = true;
					}
					else continue;
				}
			}
		}


		private void printAM() {
			// TODO Auto-generated method stub
			String horizontalPrint = "";
			System.out.print("\n");

			System.out.print("\t  ");
			 
			for(GraphNode node : this.vertexList)
				System.out.print(node.getVertexName() + "\t");
			 	
		 	
			for(int y = 0; y < this.vertexList.size(); y++) {
				horizontalPrint = "\n\t" + this.vertexList.get(y).getVertexName() + "";
			
				for(int x = 0; x < this.vertexList.size(); x++) {
					if(this.AM[y][x] < 1 ) horizontalPrint = horizontalPrint.concat("  " + "0" + "  ");
					else horizontalPrint = horizontalPrint.concat("  " + String.valueOf(this.AM[y][x]) + "  ");
				}
				System.out.print(horizontalPrint);
			}
		}


		private void flightAvailability(String source, String destination) {
			// TODO Auto-generated method stub
			int positionSInVL = this.VertexPos(source);
			int positionDInVL = this.VertexPos(destination);
			
			if(positionSInVL < 0 | positionDInVL < 0) 
				System.out.println("\t\t One or BOTH vertices are non-existent.!");
			else {
				int flightPrice = this.AM[positionSInVL][positionDInVL];
				if (flightPrice < 0) 
					System.out.println("\t\t There exists no connection between the 2 vertices.");
				else
					System.out.println("\t\tFlight price is " + flightPrice);
			}
		}


		private void deleteVertex(String fromVertex) {
			// TODO Auto-generated method stub
			int vertexposOfSource = -1;//, vertexposOfChild = -1;
			vertexposOfSource = this.VertexPos(fromVertex);
			Node<graphEdge> tmpNode = new Node<graphEdge>();
			
			System.out.println("deleteVertex: deleteVertex called for " +fromVertex + " vertexposOfSource = " + vertexposOfSource);
			if(vertexposOfSource < 0) {
				System.out.println("\t\t The vertex  " + fromVertex.toUpperCase() + " does not seem to exist!");
				return;
			}
			
			int SizeOfList = this.vertexList.get(vertexposOfSource).vertexEdgeList.getSize();
			
			if(SizeOfList < 1) 
				    System.out.println("\t\t There are no edges for this vertex -  " + fromVertex.toUpperCase() + " - to remove. Removing vertex now.");		
			else {
				
				System.out.println("deleteVertex: Before linkedlistSize is " + this.vertexList.get(vertexposOfSource).vertexEdgeList.getSize());
				//remove all nodes fromVertex
				tmpNode = this.vertexList.get(vertexposOfSource).vertexEdgeList.firstNode;
				while(true) {
					
					this.removeEdge(tmpNode.getValueAtNode().getVertexName(), fromVertex);
					
					if(tmpNode.getnextNode() == null) break;
					else tmpNode = tmpNode.getnextNode(); 
					
				}
			}
			
			this.vertexList.get(vertexposOfSource).vertexEdgeList.deleteAllNodesBetweenPos(1, SizeOfList);
			this.vertexList.get(vertexposOfSource).clearNode();
			this.vertexList.remove(vertexposOfSource);
			this.AM  = remapAM(vertexposOfSource, this.AM.length);
			
			System.out.println("--------dumping out details of vertices---------");
			for(int x = 0; x < this.vertexList.size(); x++) {
				
				System.out.println(this.vertexList.get(x).getVertexName() + " contains " + this.vertexList.get(x).vertexEdgeList.getSize());
			}
		}


		private int[][] remapAM(int positionInVL, int AMSize) {
			// TODO Auto-generated method stub
			int[][] tmpAM = new int[AMSize][AMSize];
			
			for(int counterx = 0;counterx < positionInVL;counterx++) {
				for(int countery = 0;countery < AMSize - 1;countery++) {
					
					if(countery >= positionInVL) tmpAM[counterx][countery] = this.AM[counterx][countery + 1];
					else tmpAM[counterx][countery] = this.AM[counterx][countery];
				}
			}
			
			for(int counterx = positionInVL;counterx < AMSize - 1;counterx++) {
				for(int countery = 0;countery < AMSize - 1;countery++) {
					if(countery >= positionInVL) tmpAM[counterx][countery] = this.AM[counterx + 1][countery + 1];
					else tmpAM[counterx][countery] = this.AM[counterx + 1][countery];
			
				}
			}
			return tmpAM;
		}


		private void removeEdge(String fromVertex, String toVertex) {
			// TODO Auto-generated method stub
			  int vertexposOfSource = -1, vertexposOfDestination = -1;
			  int positionWithInEdgeList = 0;
			  
			  graphEdge deletedVertex = new graphEdge();
			  Node<graphEdge> tmpNode = new Node<graphEdge>();
			  
			  vertexposOfSource = this.VertexPos(fromVertex);
			  vertexposOfDestination = this.VertexPos(toVertex);
			  
			  if(vertexposOfSource < 0 | vertexposOfDestination < 0) {
				  System.out.println("\t\t One or both vertices is non-existent.");
				  return;
			  }
			  
			  if(this.AM[vertexposOfSource][vertexposOfDestination] < 0) {
				    System.out.println("\t\t There is no edge connection between the two vertices " + fromVertex + " & " + toVertex);
				    return;
			  }
			  else{	
				  	this.AM[vertexposOfSource][vertexposOfDestination] = -1;
			    		tmpNode = this.vertexList.get(vertexposOfSource).vertexEdgeList.firstNode;
			    		
			    		while(true) {
			    			positionWithInEdgeList++;
			    			
			    			if(tmpNode.getValueAtNode().getVertexName().equalsIgnoreCase(toVertex)) {
			    				deletedVertex = this.vertexList.get(vertexposOfSource).vertexEdgeList.deleteNodeAtPos(positionWithInEdgeList);
			    				System.out.println("==> Edge from " + fromVertex + " to " + deletedVertex.getVertexName() + "removed.");
			    				return;
			    			}
			    			else {
					    		if(tmpNode.getnextNode() == null) break;	
					    		else tmpNode = tmpNode.getnextNode();	
			    			}	
			    		}
			  }
		}


		public void Startup() {
			// TODO Auto-generated method stub
			String name;
			while(true) {
				System.out.println("\t \t\tL A B  - M O D U L E   2 \n\t\t\t- - - - - - - - - - - - - \n" +
						"\t\t1. Add Vertex (Airport / City)\n" + 
						"\t\t2. Remove Vertex (Airport / City)\n" + 
						"\t\t3. Add Edge (Source, Destination and Airfare)\n" + 
						"\t\t4. Remove Edge\n" + 
						"\t\t5. Display Adjacency Matrix\n" + 
						"\t\t6. Display Edges\n" + 
						"\t\t7. BFS\n" + 
						"\t\t8. DFS\n" + 
						"\t\t9. Check Flight Availability\n " + 
						"\t\t0. exit.");
				System.out.print("\n\n\t\t Enter your choice - ");
				name = scannerObject.nextLine();
				
				if(name.equalsIgnoreCase("0")) {
					System.out.println("\t\t Good Bye.");
					break;
				}
				else {
					this.processInput(name);
				}
				System.out.println("\n\t\tPress any KEY to continue.");
				name = scannerObject.nextLine();
			}
		}
		
		private int VertexPos(String vertexName) {
			  int vertexCounter = -1;
			  for(GraphNode element : this.vertexList) {
			    
				  vertexCounter++;
			    
			    if(element.getVertexName().equalsIgnoreCase(vertexName)) 
			      return vertexCounter;
			    else continue;
			  }
			  return -1;
		}
		
		private void addVertex(String newVertex) {
			  // TODO Auto-generated method stub
			  int sizeOfVL = vertexList.size();
			  if(sizeOfVL > 0) {
			    
			    for(GraphNode element : this.vertexList) {
			      
			      if(element.getVertexName().equalsIgnoreCase(newVertex)) {
			    	  	System.out.println("\t\t Vertex rejected.");
			    	  	return;
			      }
			      else continue;
			    }

			  }
			  
			  GraphNode newVertexNode = new GraphNode();
			  newVertexNode.setVertexName(newVertex);
			  this.vertexList.add(newVertexNode);
			  sizeOfVL = vertexList.size();
			  System.out.println("\t\t New vertex accepted.");
			  
			  if(sizeOfVL >= this.AM.length) this.AM = copyResizeAM(AM,sizeOfVL);
			  else {
				  	for(int counterx = 0; counterx < this.AM.length; counterx++)
				  		Arrays.fill(this.AM[counterx],sizeOfVL - 1,sizeOfVL,-1);
			  }
			  
		}		
		private int[][] copyResizeAM(int[][] oldAM, int sizeOfVL) {
			// TODO Auto-generated method stub
			
			int newSize = 2 * oldAM.length;
			int[][] tmpArray = new int[newSize][newSize];
			for(int counterx = 0;counterx < oldAM.length; counterx++) {
				for(int countery = 0; countery < oldAM.length; countery++) {
					tmpArray[counterx][countery] = oldAM[counterx][countery];
				}
				Arrays.fill(tmpArray[counterx], sizeOfVL - 1, sizeOfVL, -1);
			}
			Arrays.fill(tmpArray[sizeOfVL], 0, sizeOfVL, -1);
			return tmpArray;
		}


		private void addEdge(String fromVertex, String toVertex, int edgeWeight) {
			
			int posFromVertex = -1, posToVertex = -1;
			int posOfToVertexInLL = -1, posOfFromVertexInLL = -1;
			
			ArrayList<String> fromEdgeList = new ArrayList<String>();
			ArrayList<String> toEdgeList   = new ArrayList<String>();
			
			posFromVertex = this.VertexPos(fromVertex);
			posToVertex = this.VertexPos(toVertex);
			
			if(posFromVertex < 0 | posToVertex < 0) {
					System.out.println("\t\tOne or both of the nodes supplied is an inValid vertex. "
					        + "Add a new vertex before"
					        +" adding edge.");
					return;
			}
			else {
					 
					fromEdgeList = this.vertexList.get(posFromVertex).getEdgeList().traverseListForward();
					toEdgeList = this.vertexList.get(posToVertex).getEdgeList().traverseListForward();
				
					if(fromEdgeList.contains(toVertex) & toEdgeList.contains(fromVertex) ) {
						posOfToVertexInLL = fromEdgeList.indexOf(toVertex) + 1;
						posOfFromVertexInLL = toEdgeList.indexOf(fromVertex) + 1;
						graphEdge edgeObject = new graphEdge();
						
						edgeObject.setWeight(edgeWeight);
						edgeObject.setVertexName(toVertex);

						this.vertexList.get(posFromVertex).vertexEdgeList.updateNodeValueAtPos(posOfToVertexInLL, edgeObject);
						
						edgeObject = new graphEdge();
						edgeObject.setWeight(edgeWeight);
						edgeObject.setVertexName(fromVertex);
						this.vertexList.get(posToVertex).vertexEdgeList.updateNodeValueAtPos(posOfFromVertexInLL, edgeObject);
					}
					else {
						graphEdge newGraphNodeWithEdge = new graphEdge();
						
						newGraphNodeWithEdge.setVertexName(toVertex);
						newGraphNodeWithEdge.setWeight(edgeWeight);
												
						this.vertexList.get(posFromVertex).vertexEdgeList.addNodeToList(newGraphNodeWithEdge);
	
						newGraphNodeWithEdge = new graphEdge();
						newGraphNodeWithEdge.setVertexName(fromVertex);
						newGraphNodeWithEdge.setWeight(edgeWeight);

						this.vertexList.get(posToVertex).vertexEdgeList.addNodeToList(newGraphNodeWithEdge);
					}
			}
			this.AM[posFromVertex][posToVertex] = edgeWeight;
			this.AM[posToVertex][posFromVertex] = edgeWeight;
			System.out.println("--------dumping out details of vertices---------");
			for(int x = 0; x < this.vertexList.size(); x++) {
				
				System.out.println(this.vertexList.get(x).getVertexName() + " contains " + this.vertexList.get(x).vertexEdgeList.getSize());
			}
		/*	this.vertexList.get(posFromVertex).getEdgeList().dumpLinkedListNodes();
			System.out.println(" First node for " + fromVertex + " is " + this.vertexList.get(posFromVertex).vertexEdgeList.firstNode.getValueAtNode().getVertexName());
			
			if(this.vertexList.get(posFromVertex).vertexEdgeList.getSize() > 1)
			System.out.println(" Last node for " + fromVertex + " is " + this.vertexList.get(posFromVertex).vertexEdgeList.lastNode.getValueAtNode().getVertexName());
			else System.out.println(" No last node yet for " + fromVertex);
		*/
		}
		


		private void displayEdges() {
			// TODO Auto-generated method stub
		  
		  if (this.vertexList.size() < 1) {
		    System.out.println("\t\t No vertex has been added yet. ! No Edges !");
		    return;
		  }
		  
		  for(int counterx = 0; counterx < this.vertexList.size(); counterx++) {
			  
			  for(int countery = 0; countery < this.vertexList.size(); countery++) {
				  
				  if(this.AM[counterx][countery] > -1) {
					  System.out.println("\t\t" + this.vertexList.get(counterx).getVertexName() + " -> " + this.vertexList.get(countery).getVertexName() );
				  }
			  }
				  
		  }
		   
		}
}
