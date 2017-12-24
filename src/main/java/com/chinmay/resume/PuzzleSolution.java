package com.chinmay.resume;



/*
 *  This code passed every test case including inputs:
 *  ABCD A=--- B---> C<--- D>---
 *  ABCD A---> B---< C>--- D---=
 *  ABCD A->-- B-=-- C-<-- D>--- 
 *  ABCD A---> B>--- C---< D---=
 */


public class PuzzleSolution {
	
	static int index;
	static String letterString;
	
    static char[][] puzzleMatrix;
    static Node[] arrayOfNodes;
    static int num;
    

	public String solution(String puzzle) {
		
		num = 4;
	    puzzleMatrix = new char[num][num];
	    arrayOfNodes = new Node[num];
	    
	    index = 0;
	    letterString = "ABCD";
	    
	    puzzle = puzzle.replaceAll("[A-Za-z:\\s]+", "");  
	    
	  
	    
	    // initialize puzzleMatrix 
	    for(int i = 0; i < num; i++) {
			for(int j =0; j < num && index < puzzle.length(); j++) {
				
				puzzleMatrix[i][j] = puzzle.charAt(index);
				index++;
				
			}
		}
	    
	    // make array of nodes
	    for(int i = 0; i < num; i++) {
	    		  Node node = new Node(letterString.charAt(i));
	    		  arrayOfNodes[i] = node; 	
	    }
	    
	    
	    /* After we intialize array of Nodes with Nodes of letter A,B,C,D
	     * we parse the puzzle matrix and form a doubly linked list relation between the 
	     * the nodes internally.
	     * we go on building a sorted doublylinkedlist (decreasing) as we parse the puzzle matrix
	     * checking for relations between each letter e.g A>B, B < C;
	     * */
	    
	    
	    for(int i = 0; i < num; i++) {
	    	
				for(int j =0; j < num ; j++) {
					
					Node first = arrayOfNodes[i];
					Node second = arrayOfNodes[j];
					char sign = puzzleMatrix[i][j];
											
							switch(sign) {
							
								case '>':
									
									   //link nodes first being the greatest and second being less than first
										first.next = second;
										second.prev = first;
									    break;
								
								
								case '<':
									
									    // if the second node is greater than the first node
										second.next = first;
										first.prev = second;
										break;
										
								case '=':
									  
									    continue;
									    
								case '-':
									
										continue;
										
								default:
										continue;
								
							}				
								
				}
		}
	    
	    
	    
	    /*
	     * We use the same puzzle matrix to fill out all the relations between all the letters
	     * We know every letter is equal to itself so we fill the diagonal elements with '='
	     * For every other relationship b/w letters we use the doubly linked list.
	     * For every letter we check right and left elements less than and greater than the
	     * the letter respectively.
	     * And according to the relations we fill out the puzzleMatrix (solution) and print it out.
	     */
	    
	    
	    for(int i = 0; i < num; i++) {
	    	
			for(int j =0; j < num ; j++) {
				
				
							if(i==j) {
										
								puzzleMatrix[i][j] = '=';
										
							}
							else {
								
								Node n = arrayOfNodes[i];
									
								// traverse the right side of the array
								while(n.next !=  null) {
									
										n = n.next;
										int p = letterString.indexOf(n.data);
										
										puzzleMatrix[i][p] = '>';
									
									
								}
								
								 n = arrayOfNodes[i];
								
								// traverse the left side of the array
									while(n.prev !=  null) {
										
											n = n.prev;
											int p = letterString.indexOf(n.data);
											
											puzzleMatrix[i][p] = '<';
										
										
									}
								
							}		
				
			   }
	   }
	    
	    
	    //append and return the solution
	    
	    StringBuffer puzzleSolution = new StringBuffer(" ABCD\n");
	    
	    for(int i = 0; i < num; i++) {
	    	
	    	    puzzleSolution.append(letterString.charAt(i));
	    	
			for(int j =0; j < num ; j++) {
				
				
				puzzleSolution.append(puzzleMatrix[i][j]);
			
			}
			
			 puzzleSolution.append("\n");
		}
	    
	    
	    
	    
	    return puzzleSolution.toString();
		

	}
	

}
