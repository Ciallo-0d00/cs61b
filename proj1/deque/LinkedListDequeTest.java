package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.size() == 0);
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.size() == 0);

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		assertTrue("lld1 should be empty upon initialization", lld1.size() == 0);

		lld1.addFirst(10);
		assertFalse("lld1 should contain 1 item", lld1.size() == 0);

		lld1.removeFirst();
		assertTrue("lld1 should be empty after removal", lld1.size() == 0);
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }
    @Test
    public void randomizedTest() {
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        int N = 10000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
            } else if (operationNumber == 2 && L.size() > 0) {
                int randVal = StdRandom.uniform(0, 100);
                L.addFirst(randVal);
            } else if (operationNumber == 3 && L.size() > 0) {
                L.removeLast();
            } else if (operationNumber == 4 && L.size() > 0) {
                L.removeFirst();
            } else if (operationNumber == 5 && L.size() > 0) {
                // the program should not crash even if going beyond its size limit
                int randVal = StdRandom.uniform(0, L.size()+1);
                L.get(randVal);
            }
        }
    }

    @Test
    public void equalTest(){
        Deque<Integer> lld1 = new LinkedListDeque<Integer>();
        Deque<Integer> lld2 = new LinkedListDeque<Integer>();
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld2.addFirst(1);
        lld2.addFirst(2);
        assertTrue(lld1.equals(lld2));
        assertTrue(lld2.equals(lld1));

        lld1.addFirst(3);
        assertFalse(lld1.equals(lld2));
        assertFalse(lld2.equals(lld1));

        assertFalse(lld1.equals(null));

        Deque<Integer> addr1 = new ArrayDeque<Integer>();
        addr1.addFirst(1);
        addr1.addFirst(2);
        addr1.addFirst(3);
        assertTrue(lld1.equals(addr1));
    }

}
