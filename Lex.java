
import java.io.*;
import java.util.Scanner;


public class Lex {
  
  
  public static void insertionSort(String[] A, List B){
    
    B.append(0);  
    for(int i = 1; i < A.length; i++){
      
      while ( B.getIndex() !=-1 && A[B.getElement()].compareTo(A[i]) < 0){
        B.moveNext();
      }
      if(B.getIndex() == -1){
        B.append(i);
        B.moveTo(0);
      }
      else if ( A[B.getElement()].compareTo(A[i]) > 0){
        B.insertBefore(i);
        B.moveTo(0);
      }
      else if ( A[B.getElement()].compareTo(A[i]) == 0){
        B.insertBefore(i);
        B.moveTo(0);
      }
    }
  }
  
  
  
  
  
  public static void main(String[] args) throws IOException{
    
    /*--Read in File and make a String[] filled with read in tokens--*/  
    Scanner in = null;
    PrintWriter out = null;
    String line = null;
    String[] token = null;
    int  n, lineNumber = 0;
    int size= 0; 
    if(args.length < 2){
      System.out.println("Usage: FileIO infile outfile");
      System.exit(1);
    }
    
    in = new Scanner(new File(args[0]));
    out = new PrintWriter(new FileWriter(args[1]));
    
    while(in.hasNext()){
      in.next();  
      size++;
    }
    String[] words = new String[size];
    
    
    in.close();
    out.close();
    in = new Scanner(new File(args[0]));
    out = new PrintWriter(new FileWriter(args[1]));
    
    while(in.hasNext()){
      for(int j=0; j<words.length; j++){
        words[j]=in.next();
      }
    }
    
    
    /*--using read in String[] named tokens alphebetize words in array--*/    
    
    List old1 = new List();
    List new1 = new List(); 
    
    
    for(int i=0; i<words.length; i++){
      old1.append(i);
    }
    System.out.println(old1);
    insertionSort(words, new1);
    System.out.println(new1);
    
    /* - - print out words into file with indexes of List - -  */
    
    new1.moveTo(0);
    while(new1.getIndex() > -1){
      out.println(words[new1.getElement()]+" ");   
      new1.moveNext();
    }
    
    
    in.close();
    out.close();
  }
}



