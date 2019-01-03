import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class studentHashDriver {
	int[][][] alphaConversionList; 
	String[] reverseConversionList;
	String delimiter;
	int currentYear; 
	StudentHash records;  
	
	studentHashDriver(String delimiterInit){
		delimiter = delimiterInit;
		currentYear = Calendar.getInstance().get(Calendar.YEAR);
		records = new StudentHash();
		reverseConversionList = new String[25819];
		alphaConversionList = new int[27][27][27];
		this.loadConversionList();
	}
	boolean testIncomingData(String studentRecord) {
		String year, deptNum, studentNumber; 
		int yearInt, DeptASCIILeft, DeptASCIIMid, DeptASCIIRight;
		float cgpi;
		String[] studentRecordSplit;
		
		if(studentRecord == "" | studentRecord.isEmpty()) return false;
		
		if((studentRecord.indexOf(delimiter) < 1)) return false;
		else studentRecordSplit = studentRecord.split(delimiter);
		
		if(!(studentRecordSplit[0].length() == 11))
			return false;
		
		year = studentRecordSplit[0].substring(0, 4);
		deptNum = studentRecordSplit[0].substring(4, 7);
		studentNumber = studentRecordSplit[0].substring(7, 11);
		yearInt = Integer.valueOf(year);
		
		if(yearInt < 1990 | yearInt > this.currentYear) return false;
		
		char[] deptChars = deptNum.toCharArray();
		DeptASCIILeft = deptChars[0];
		DeptASCIIMid = deptChars[1];
		DeptASCIIRight = deptChars[2];
		
		cgpi = Float.valueOf(studentRecordSplit[1]);
		
		Integer studentNumberInt = Integer.valueOf(studentNumber);
		
		if(studentNumberInt == 0) return false;
		
		if(cgpi > 10.00f | cgpi < 0.00f) return false;
		
		if((DeptASCIILeft > 64 & DeptASCIILeft < 91) & (DeptASCIIMid > 64 & DeptASCIIMid < 91) & 
				(DeptASCIIRight > 64 & DeptASCIIRight < 91)) return true;
		else return false;
			
	}
	
	void loadConversionList() {
		char[] alphabets = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		
		int i,j,k;
		int sq = 31 * 31;
		int temp = 0;
		alphaConversionList[0][0][0] = -999;
		for(char chi : alphabets) {
			i = chi - 64;

			for(char chj : alphabets) {
				
				j = chj - 64;
				for(char chk : alphabets) {
					
					k = chk - 64;
					temp = i + (j * 31) + (k * sq);
					alphaConversionList[i][j][k] = temp;
					
					reverseConversionList[temp] = String.valueOf(chi) + String.valueOf(chj) + String.valueOf(chk);
				}
				
			}
		}//ends for loop all of them
		
	}
	int generateAlphaValue(String deptAbbr) {
		char letters[];
		letters =  deptAbbr.toCharArray();
		return this.alphaConversionList[letters[0] - 64][letters[1] - 64][letters[2] - 64];
	}
	
	void insertStudentRec(String studentId, float CGPA) {
		pseudoSkewedTree pst = new pseudoSkewedTree();
		
		int hashValue = this.generateAlphaValue(studentId.substring(4, 7));
		
		String YEAR = studentId.substring(0, 4);
		int yyyy = Integer.valueOf(YEAR);
		
		pst = this.records.hashTable.get(hashValue);
		
		if(pst == null) pst = new pseudoSkewedTree();
			
		if(yyyy > (this.currentYear - 10) & yyyy < (this.currentYear - 5))
			pst.addNodeToRight(CGPA, studentId);
		else
			pst.addNodeToLeft(CGPA, studentId);
		
		this.records.hashTable.set(hashValue,pst);	
	}
	void newCourseList(float CGPAFrom, float CGPATo) {
		File fd = new File("courseOffer.txt");
		FileWriter fr;
		BufferedWriter bw;
		pseudoSkewedTree pst;  
		Node tmppstNode = new Node();
		StringBuffer dupCheckBuffer = new StringBuffer();
		float tmpCGPA;
		try {
			fr = new FileWriter(fd);
			bw = new BufferedWriter(fr);
			
			for(int counter = 993; counter <= 25818; counter++) {
				pst = this.records.hashTable.get(counter);
				
				if(pst == null) continue;
				else {
					
					if(pst.numberOfNodes < 1) continue;
					
					tmppstNode = pst.rightNode;
					while(!tmppstNode.nodeKey.equalsIgnoreCase("HEADER-TAIL")) {						
						
						if(dupCheckBuffer.indexOf(tmppstNode.getkeyAtNode()) > 0) {
							tmppstNode = tmppstNode.getnextNode();
							continue;
						}
						else dupCheckBuffer.append(tmppstNode.getkeyAtNode()+ ",");
						
						tmpCGPA = tmppstNode.getValueAtNode();
						
						if((tmpCGPA > CGPAFrom) & (tmpCGPA < CGPATo)) {
							int graduationYr = Integer.valueOf(tmppstNode.getkeyAtNode().substring(0, 4)) + 5;
							bw.write(tmppstNode.getkeyAtNode() + "," + tmppstNode.getValueAtNode() + "," + (graduationYr) + "\n");
						}
						tmppstNode = tmppstNode.getnextNode();
						
					}
				}
			}
			bw.close();
			fr.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	void hallOfFame(float CGPA) {
		File fd = new File("halloffame.txt");
		FileWriter fr;
		BufferedWriter bw;	
		pseudoSkewedTree pst;  
		Node tmppstNode = new Node();
		float tmpCGPA;
		StringBuffer dupCheckBuffer = new StringBuffer();
		StringBuffer hallofameBuffer = new StringBuffer();
		try {
			fr = new FileWriter(fd);
			bw = new BufferedWriter(fr);
			
			for(int counter = 993; counter <= 25818; counter++) {
				pst = this.records.hashTable.get(counter);
				
				if(pst == null) continue;
				else {
					tmppstNode = pst.rightNode;
					while(!tmppstNode.nodeKey.equalsIgnoreCase("HEADER-TAIL")) {
						
						
						if(dupCheckBuffer.indexOf(tmppstNode.getkeyAtNode()) > 0) {
							tmppstNode = tmppstNode.getnextNode();
							continue;
						}
						else dupCheckBuffer.append(tmppstNode.getkeyAtNode()+ ",");
						
						tmpCGPA = tmppstNode.getValueAtNode();
						if(tmpCGPA > CGPA)
							hallofameBuffer.append(tmppstNode.getkeyAtNode() + "," + tmppstNode.getValueAtNode() + "\n");
							//bw.write(tmppstNode.getkeyAtNode() + "," + tmppstNode.getValueAtNode() + "\n");
						
						tmppstNode = tmppstNode.getnextNode();
						
					}
					
					dupCheckBuffer = new StringBuffer();
					tmppstNode = pst.leftNode;
					while(!tmppstNode.nodeKey.equalsIgnoreCase("HEADER-TAIL")) {
						
						if(dupCheckBuffer.indexOf(tmppstNode.getkeyAtNode()) > 0) {
							tmppstNode = tmppstNode.getnextNode();
							continue;
						}
						else dupCheckBuffer.append(tmppstNode.getkeyAtNode() + ",");
						
						tmpCGPA = tmppstNode.getValueAtNode();
						
						if(tmpCGPA > CGPA)
							hallofameBuffer.append(tmppstNode.getkeyAtNode() + "," + tmppstNode.getValueAtNode() + "\n");
							
							//bw.write(tmppstNode.getkeyAtNode() + "," + tmppstNode.getValueAtNode() + "\n");
						
							
						tmppstNode = tmppstNode.getnextNode();
						
					}
					
				}
			}
			bw.write(hallofameBuffer.toString());
			bw.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void depAvg() {
		File fd = new File("halloffame.txt");
		FileWriter fr;
		BufferedWriter bw;	
		pseudoSkewedTree pst;  
		Node tmppstNode = new Node();
		float tmpCGPA;
		try {
			fr = new FileWriter(fd);
			bw = new BufferedWriter(fr);
			

			
			bw.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public static void main(String[] args) {

		studentHashDriver hashTableTest = new studentHashDriver(",");
		 
		
		String studentRecord = "";
		BufferedReader br;
		File fd = new File("First.txt");
		FileReader fr;
		try {
			fr = new FileReader(fd);
			br = new BufferedReader(fr);
			
			while ((studentRecord = br.readLine()) != null) {
				
				String[] studentRecordValues = studentRecord.split(hashTableTest.delimiter);
				
				if(!hashTableTest.testIncomingData(studentRecord)) continue;
				else 
					hashTableTest.insertStudentRec(studentRecordValues[0], Float.valueOf(studentRecordValues[1]));
			}
			
			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//hashTableTest.hallOfFame(8.5F);
		hashTableTest.newCourseList(7.00F, 7.50F);
	}

}
