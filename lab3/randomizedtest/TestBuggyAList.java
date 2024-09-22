package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> correctList = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();
        correctList.addLast(4);
        correctList.addLast(5);
        correctList.addLast(6);
        buggyAList.addLast(4);
        buggyAList.addLast(5);
        buggyAList.addLast(6);
        assertEquals(correctList.size(),buggyAList.size());
        assertEquals(buggyAList.removeLast(), correctList.removeLast());
        assertEquals(buggyAList.removeLast(), correctList.removeLast());
        assertEquals(buggyAList.removeLast(), correctList.removeLast());
    }
    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> aListNoResizing = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                aListNoResizing.addLast(randVal);
                buggyAList.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            }
            else if (operationNumber == 1) {
                // size
                int sizeOfa = aListNoResizing.size();
                System.out.println("sizea: " + sizeOfa);
                int sizeOfb = buggyAList.size();
                System.out.println("sizeb: " + sizeOfb);
            }
            else if (operationNumber == 2) {
                if (aListNoResizing.size() > 0){
                    int lastOfa = aListNoResizing.getLast();
                    System.out.println("getLasta: " + lastOfa);
                }
                if (buggyAList.size() > 0){
                    int lastOfb = buggyAList.getLast();
                    System.out.println("getLastb: " + lastOfb);
                }
            }
            else if (operationNumber == 3) {
                if (aListNoResizing.size() > 0){
                    int lastOfa = aListNoResizing.removeLast();
                    System.out.println("removeLasta: " + lastOfa);
                }
                if (buggyAList.size() > 0){
                    int lastOfb = buggyAList.removeLast();
                    System.out.println("removeLastb: " + lastOfb);
                }
            }
        }
    }


}
