// ListTest.java
// A test client for the List ADT

class ListTest{
   public static void main(String[] args){
      List A = new List();
      List B = new List();

      for(int i=1; i<=10; i++){
         A.append(i);
         B.prepend(11-i);
      }
      System.out.println("A = " + A);
      System.out.println("B = " + B);

      for(int i=1; i<=6; i++){
         A.append(B.front());
         B.deleteBack();
      }
      System.out.println("A = " + A);
      System.out.println("B = " + B);
      List C = A.copy();
      System.out.println("C = " + C);
      System.out.println("A " + (A.equals(B)?"equals":"does not equal") + " B");
      System.out.println("A " + (A.equals(C)?"equals":"does not equal") + " C");
   }
}


