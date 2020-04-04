class AminoAcidLL { // uses a combination of loops and recursion //
  char aminoAcid;
  String[] codons;
  int[] counts;
  AminoAcidLL next;

  AminoAcidLL(){
  }

  /********************************************************************************************/
  /* Creates a new node, with a given amino acid/codon
   * pair and increments the codon counter for that codon.
   * NOTE: Does not check for repeats!! */
  AminoAcidLL(String inCodon) {
    aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);
    codons = AminoAcidResources.getCodonListForAminoAcid(aminoAcid);
    counts = new int[codons.length];
    incrCodons(inCodon);
    next = null;
  }

  // method to increment the codon counter//
  public void incrCodons(String codon) {
    for (int i = 0; i < codons.length; i++) { // goes through the list //
      if (codons[i].equals(codon)) { // condition to be met in order to increase count //
        counts[i]++;
        break;
      }
    }
  }

  /********************************************************************************************/
  /* Recursive method that increments the count for a specific codon:
   * If it should be at this node, increments it and stops,
   * if not passes the task to the next node.
   * If there is no next node, add a new node to the list that would contain the codon.
   */
  private void addCodon(String inCodon) {
    if (aminoAcid == AminoAcidResources.getAminoAcidFromCodon(inCodon)) { // this node has this codon THEN //
      incrCodons(inCodon);
    }
    if (this.next != null) { // passes to the next node //
      next.addCodon(inCodon);
    }
    else {
      next = new AminoAcidLL(inCodon); // makes a new node //
    }
  }

  /********************************************************************************************/
  /* Shortcut to find the total number of instances of this amino acid */
  private int totalCount() {
    // not recursive and sum up what the value of that is //
    int sum = 0;
    for(int i = 0; i < counts.length; i++) {
      sum += counts[i];
    }
    return sum;
  }

  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int totalDiff(AminoAcidLL inList){
    return Math.abs(totalCount() - inList.totalCount());
  }

  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int codonDiff(AminoAcidLL inList){
    int diff = 0;
    for(int i=0; i<codons.length; i++){
      diff += Math.abs(counts[i] - inList.counts[i]);
    }
    return diff;
  }

  /********************************************************************************************/
  /* Recursive method that finds the differences in **Amino Acid** counts.
   * the list *must* be sorted to use this method */
  public int aminoAcidCompare(AminoAcidLL inList){
    int acCountDiff = 0;
    if(inList.isSorted() && this.isSorted()) { // checks if it is sorted first //
      acCountDiff = aminoAcidCompare(this, inList, acCountDiff); // overloads to the next method that actually takes parameters //
    }
    return acCountDiff;
  }

  public int aminoAcidCompare(AminoAcidLL inList, AminoAcidLL inList2, int acCountDiff) {
    if(inList == null && inList2 == null) { // base case //
      return acCountDiff;
    }
    else if(inList == null) { // if first inList is null, it'll return just the count of inList2 since thats the difference //
      acCountDiff -= inList2.totalCount();
      return aminoAcidCompare(inList, inList2, acCountDiff);
    }
    else if(inList2 == null) { // same as above but with the second inList being null //
      acCountDiff += inList.totalCount();
    }
    else {
      acCountDiff += inList.totalDiff(inList2); // finds the difference between the first and second inList //
      return aminoAcidCompare(inList.next, inList2.next, acCountDiff);
    }
    return acCountDiff;
  }

  /********************************************************************************************/
  /* Same ad above, but counts the codon usage differences
   * Must be sorted. */
  public int codonCompare(AminoAcidLL inList) {
    int codonDiff = 0;
    if (inList.isSorted()) { // checks if it's sorted //
      codonDiff = codonCompare(this, inList, codonDiff); // if it is, overload to the next method //
    }
    return codonDiff;
  }

  public int codonCompare(AminoAcidLL inList, AminoAcidLL inList2, int codonDiff) {
    if(inList == null && inList2 == null) { // if both are null, return codonDiff //
      return codonDiff;
    }
    else if(inList == null) { // if first inList is null, then the diff is the count of inList2 //
      codonDiff += inList.codonDiff(inList2);
      codonCompare(inList, inList2.next, codonDiff);
    }
    else if(inList2 == null) { // same as above but with the second being null //
      codonDiff += inList.codonDiff(inList2);
      return codonCompare(inList.next, inList2, codonDiff);
    }
    codonDiff += inList.codonDiff(inList2);
    return codonCompare(inList.next, inList2.next, codonDiff);
  }

  /********************************************************************************************/
  /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
  public char[] aminoAcidList(){
    char list[] = new char[acList()]; // makes a list of whatever size acList returns //
    aminoAcidList(this, 0, list); // overloads to the next method that takes parameters //
    return list;
  }

  public int acList () { // helper method for aminoAcidList && aminoAcidCounts//
    int x = 0;
    AminoAcidLL iterator = this; // sets iterator as the head to move though the list //
    while(iterator != null) {
      iterator = iterator.next; // moves to the next node //
      x++;
    }
    return x; // returns the count //
  }

  public char[] aminoAcidList(AminoAcidLL head, int count, char[] list ) {
    if(head.next == null) { // base //
      list[count] = head.aminoAcid;
      return list;
    }
    list[count] = head.aminoAcid;
    return aminoAcidList(head.next, count++, list);
  }
  /********************************************************************************************/
  /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
  public int[] aminoAcidCounts(){
    int[] acCount = new int[acList()]; // makes object with helper method //
    aminoAcidCounts(this, 0, acCount); // overload method call //
    return acCount;
  }

  public int[] aminoAcidCounts(AminoAcidLL head, int count, int[] acCount) {
    if(head.next == null) {
      acCount[count] = head.aminoAcid;
      return acCount;
    }
    acCount[count] = head.aminoAcid;
    return aminoAcidCounts(head.next, count++, acCount);
  }


  /********************************************************************************************/
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted() {
    //if it isnt in order, return false //
    //if it's in order, go through whole array checking if previous <= next //
    if(this == null || next == null) {
      return true;
    }
    return this.aminoAcid > next.aminoAcid && next.isSorted();
  }

  /********************************************************************************************/
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence){
    int count = 3;
    if(inSequence.length() < 3) { // accounts for codons < 3 //
      return null;
    }
    AminoAcidLL head = new AminoAcidLL(inSequence.substring(0, 3)); // makes the head //

    while(count < inSequence.length()) {
      if (AminoAcidResources.getAminoAcidFromCodon(inSequence.substring(0, 3)) == '*') { // condition to break //
        break;
      }
      inSequence = inSequence.substring(count);
      head.addCodon(inSequence.substring(0, 3));
    }
    return head;

  }

  /********************************************************************************************/
  // RECEIVED HELP FROM ALAN GARCIA FOR THIS METHOD THROUGH MULTIPLE DISCORD CALLS, it was rough :( lol //
  public static AminoAcidLL sort(AminoAcidLL inList) { // the actual merge sort //

    if (inList == null || inList.next == null) {
      return inList;
    }
    // get the middle of the list
    AminoAcidLL mid = getMiddle(inList);
    AminoAcidLL mNext = mid.next;
    // set the next of middle node to null
    mid.next = null;

    // Apply mergeSort on left list
    AminoAcidLL left = sort(inList);

    // Apply mergeSort on right list
    AminoAcidLL right = sort(mNext);

    // Merge the left and right lists
    AminoAcidLL sortedlist = merge(left, right);
    return sortedlist;
  }
  private static AminoAcidLL getMiddle(AminoAcidLL head){ // helper method for merge sort //
    if(head == null)
      return head;
    AminoAcidLL oneStep = head;
    AminoAcidLL twoStep = head;

    while(twoStep.next != null && twoStep.next.next != null){
      oneStep = oneStep.next;
      twoStep = twoStep.next.next;
    }
    return oneStep;
  }
  public static AminoAcidLL merge(AminoAcidLL a, AminoAcidLL b) { // helper method for merge sort //
    AminoAcidLL result = null;
    /* Base cases */
    if (a == null)
      return b;
    if (b == null)
      return a;

    /* Pick either a or b, and recur */
    if (a.aminoAcid <= b.aminoAcid) {
      result = a;
      result.next = merge(a.next, b);
    }
    else {
      result = b;
      result.next = merge(a, b.next);
    }
    return result;
  }

}