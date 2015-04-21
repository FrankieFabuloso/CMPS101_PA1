/////////////////////////////////////////////////////////////////////////////////////////
//
//   This file constitutes the solution to the first excercise in the ADT handout.
//   Recall that all ADT operations should state their own preconditions in a comment
//   block, and then check that those conditions are satisfied.  If a precondition is
//   violated, the ADT operation should cause the program to quit with a useful error
//   message.  See functions getFront() and Dequeue() below for examples on how to do
//   this. Observe that I've added two other operations called equals() and copy()
//   respectively.  equals() compares two queues for equality and returns an appropriate
//   boolean value.  Note that equals() does not overide Object's equals() method. copy()
//   creates a new List object identical to it's argument List.
//
/////////////////////////////////////////////////////////////////////////////////////////

// List.java
// An integer queue ADT

class List{
  
  private class Node{
    // Fields
    int data;
    Node next;
    Node prev;
    // Constructor
    Node(int data) { this.data = data; next = null; prev = null;}
    // toString:  Overides Object's toString method.
    public String toString() { return String.valueOf(data); }
  }
  
  // Fields
  private Node front;
  private Node back;
  private Node cursor;
  private int length;
  private int index;
  
  // Constructor
  public List(){ 
    front = back = cursor = null; 
    length = 0; 
  } // create an empty queue
  
  
  // Access Functions //////////////////////////////////////////////////////////////////
  
  // isEmpty(): 
  // returns true if this is an empty queue, false otherwise
  boolean isEmpty() { return length==0; }
  
  // getLength(): 
  // returns number of elements in List
  int length() { return length; }
  
  //getIndex(): 
  // returns the index of the cursor element in this list, or -1 if index is UD
  int getIndex(){ return index; }
  
  // Front(): 
  //Pre: length()>0 
  // returns front element in this queue
  int front(){
    if( this.isEmpty() ){
      throw new RuntimeException("List Error: front() called on empty List");
    }
    return front.data;
  }
  
  // Back():
  // Returns back element in this List. 
  //Pre: length()>0  
  int back(){ 
    if( this.isEmpty() ){
      throw new RuntimeException("List Error: back() called on empty List");
    }
    return back.data;
  }
  
  // int getElement();
  // Returns cursor element in this list
  int getElement() {
    if( this.isEmpty() ){
      throw new RuntimeException("List Error: getElement() called on empty List");
    }
    if( this.getIndex() < 0 ){
      throw new RuntimeException("List Error: getEmelent() called on out of bounds cursor");
    }
    if ( this.getIndex() == 0) {
      return this.back();
    } else {return cursor.data;}
  } 
  
  // boolean equals(List L)
  // Returns true if this List and L are the same integer  
  // sequence. The cursor is ignored in both lists.  
  boolean equals(List L) {
    boolean flag  = true;
    Node N = this.front;
    Node M = L.front;
    
    if(this.length==L.length ){
      while( flag && N!=null){
        flag = (N.data==M.data);
        N = N.next;
        M = M.next;
      }
      return flag;
    }else{
      return false;
    }
  }
  
  // Manipulation Procedures ///////////////////////////////////////////////////////////
  
  // clear()
  void clear() {
    front = back = cursor = null;
    length = 0;
    index = -1;
  }
  
  // moveto() : 
  // moves cursor to index location i by setting it to front initially
  // then moving over i # of times
  void moveTo(int i)  {
    index=i;
    cursor=this.back;
   // if(length==0){ 
   //  return;
   // }
    for(int j=0; j<i; j++){
      if (cursor.next == null) {
        index = -1;
        return;
      }else{
        cursor=cursor.next;
      }
    }
  }
  
  // If 0<getIndex()<=length()-1, moves the cursor one step toward the  
  // front of the list. If getIndex()==0, cursor becomes undefined.  
  // If getIndex()==-1, cursor remains undefined. This operation is  
  // equivalent to moveTo(getIndex()-1).
  void movePrev(){
    if (cursor.prev == null ){
      index=-1;
    }else{
      this.moveTo(getIndex()-1); 
    }
  }
  
  // If 0<=getIndex()<length()-1, moves the cursor one step toward the  
  // back of the list. If getIndex()==length()-1, cursor becomes  
  // undefined. If index==-1, cursor remains undefined. This  
  // operation is equivalent to moveTo(getIndex()+1). 
  void moveNext(){
    if( cursor == null || index == -1){
      index=-1;
    }else{
      this.moveTo(getIndex()+1);
    }
  }
  
  // prepend(): appends data to back of this queue
  void prepend(int data){
    Node node = new Node(data);
    if( this.isEmpty() ) { front = back = node; index = 0;}
    else {
      node.next = back; 
      back.prev = node; 
      back = node; 
      cursor = back;
    }
    length++;
  }
  
  // append(): deletes element from front of this queue
  void append(int data){
    Node node = new Node(data); //make new node
    if( this.isEmpty() ) { front = back = node; index = 0;}
    else {
      node.prev = front; //assign new nodes prev pointer to point at front node
      front.next = node; // assign fronts next pointer to point at new node
      front = node; //reassign where front is pointing to 
    } 
    length++;
  }
  // insertBefore(int data):
  //Pre: length()>0, getIndex()>=0  
  // Inserts new element before cursor element in this  
  void insertBefore(int data){
    Node node = new Node(data);
    if( this.isEmpty() ) {
      throw new RuntimeException("List Error: insertBefore() called on empty list.");
    }
    if( this.getIndex() < 0 ) {
      throw new RuntimeException("List Error: insertBefore() called on undefined cursor");
    }
    if(this.getIndex()==0){ 
      this.prepend(data);
      return;
    }
    Node before = cursor.prev;
    before.next= node;
    node.prev=before;
    node.next = cursor; 
    cursor.prev = node; 
    length++;
  }
  
  
  // Pre: length()>0, getIndex()>=0  
  // Inserts new element after cursor element in this 
  void insertAfter(int data){
    Node node = new Node(data); //make new node
    if( this.isEmpty()) { 
      throw new RuntimeException("List Error: insertAfter() called on empty list.");
    }
    if( this.getIndex() < 0 ) {
      throw new RuntimeException("List Error: insertAfter() called on undefined cursor");
    }
    if(this.getIndex()== this.length()-1){ 
      this.append(data);
      return;
    }
    Node after = cursor.next;
    after.prev= node;
    node.next=after;
    node.prev = cursor; 
    cursor.next = node; 
    length++;
  }
  
  // Pre: length()>0  
  // Deletes the front element in this List.
  void deleteFront(){ 
    if( this.length<=1){
      throw new RuntimeException("List Error: deleteFront() called on one item list, use clear()");
      
    }
    Node N=front;
    front=front.prev;
    front.next=null;
    N.prev=null;
    length--;
  }
  
  //Pre: length()>0 
  // Deletes the back element in this List.  
  void deleteBack(){
    if( this.length<=1){
      throw new RuntimeException("List Error: deleteBack() called on one item list, use clear()");
    }
    Node N=back;
    back=back.next;
    back.prev=null;
    N.next=null;
    length--;
  }
  
  // Pre: length()>0, getIndex()>=0 
  // Deletes cursor element in this List. Cursor is undefined after this  
  // operation.    
  void delete(){
    if( this.isEmpty() ){
      throw new RuntimeException("List Error: delete() called on empty List");
    } 
    if( this.getIndex() < 0){
      throw new RuntimeException("List Error: delete() called on undefined pointer");
    } 
    if (this.getIndex() == 0){
      this.deleteBack();
      return;
    }
    if (this.getIndex() == length()-1){
      this.deleteFront();
      return; 
    }
    Node F= cursor.next;
    Node B=cursor.prev;
    cursor.prev=null;
    cursor.next=null;
    cursor=null;  
    B.next=F;
    F.prev=B;
    index=-1;
    length--;
  }
  
  
  // Other Functions //////////////////////////////////////////////////////////////////
  
  // toString():  overides Object's toString() method.
  public String toString(){
    String str = "";
    for(Node N=back; N!=null; N=N.next){
      str += N.toString() + " ";
    }
    return str;
  }
  
  //copy(): returns a new List identical to this one.
  List copy(){
    List Q = new List();
    Node N = this.back;
    
    while( N!=null ){
      Q.append(N.data);
      N = N.next;
    }
    Q.index = -1;
    return Q;
  }
  
  // List concat(List L){
  
  //   }
}


