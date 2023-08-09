package deque;

import org.junit.Test;
import static org.junit.Assert.*;


    /** Performs some basic linked list tests. */
    public class ArrayDequeTest{

        @Test
        /** Adds a few things to the list, checking isEmpty() and size() are correct,
         * finally printing the results.
         *
         * && is the "and" operation. */
        public void addIsEmptySizeTest() {

            ArrayDeque<String> lld1 = new ArrayDeque<String>();

            assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
            lld1.addFirst("front");

            // The && operator is the same as "and" in Python.
            // It's a binary operator that returns true if both arguments true, and false otherwise.
            assertEquals(1, lld1.size());
            assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

            lld1.addLast("middle");
            assertEquals(2, lld1.size());

            lld1.addLast("back");
            assertEquals(3, lld1.size());

            System.out.println("Printing out deque: ");
            lld1.printDeque();



        }
        private int y;

        @Test
        public void basictestforrem(){
            ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
            lld1.addLast(0);
            lld1.removeLast();
            lld1.addFirst(2);
            lld1.removeLast();
            lld1.addLast(4);
            lld1.get(0);
            y = lld1.get(0);
            lld1.addLast(6);
            lld1.get(0);
            y = lld1.get(0);

            lld1.addLast(8);
            lld1.addFirst(9);
            lld1.get(1);
            y = lld1.get(1);

            lld1.addFirst(11);
            lld1.addFirst(12);
            lld1.addFirst(13);
            lld1.addLast(14);
            int x = lld1.removeFirst();
            assertEquals(13, x);
        }
        @Test
        public void basictestaddrem2(){
            ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
            lld1.addLast(0);
            lld1.isEmpty();
            lld1.addLast(2);
            lld1.addLast(3);
            lld1.addLast(4);
            lld1.addLast(5);
            lld1.addLast(6);
            lld1.addLast(7);
            lld1.addLast(8);
            int x = lld1.removeFirst();//     ==> 8
            //expected:<0> but was:<8>
            assertEquals(0, x);

        }
        @Test
        public void testtest(){
            ArrayDeque<Integer> ArrayDeque = new ArrayDeque<Integer>();
            ArrayDeque.addFirst(0);
            ArrayDeque.addFirst(1);
            ArrayDeque.addFirst(2);
            int x = ArrayDeque.removeFirst();//     ==> 1
            assertEquals(2, x);
        }
        @Test
        public void basictests(){
            ArrayDeque<Integer> ArrayDeque = new ArrayDeque<Integer>();

            ArrayDeque.addLast(0);
            ArrayDeque.addLast(1);
            ArrayDeque.addFirst(2);
            ArrayDeque.addLast(3);
            ArrayDeque.addFirst(4);
            ArrayDeque.removeLast();//
            int o = ArrayDeque.removeLast();//    ==> 3
            ArrayDeque.addLast(6);
            ArrayDeque.addFirst(7);
            ArrayDeque.addLast(8);
            ArrayDeque.addFirst(9);
            int x = ArrayDeque.removeLast();//      ==> 9
            //expected:<8> but was:<9>
            assertEquals(8, x);
        }
        @Test
        public void t2() {
            ArrayDeque<Integer> ArrayDeque = new ArrayDeque<Integer>();

            ArrayDeque.addFirst(0);
            ArrayDeque.addFirst(1);
            ArrayDeque.addLast(2);
            ArrayDeque.addLast(3);
            ArrayDeque.addFirst(4);
            ArrayDeque.removeLast();  //    ==> 3
            ArrayDeque.removeLast();    //  ==> 2
            ArrayDeque.addFirst(7);
            ArrayDeque.addFirst(8);
            ArrayDeque.addFirst(9);
            ArrayDeque.removeLast();      //==> 0
            ArrayDeque.addLast(11);
            ArrayDeque.addLast(12);
            ArrayDeque.removeFirst();     //==> 9
            ArrayDeque.removeLast();//    ==> 12
            ArrayDeque.get(0);//      ==> 8
            ArrayDeque.addFirst(16);
            ArrayDeque.addFirst(17);
            ArrayDeque.addFirst(18);
            int x = ArrayDeque.removeLast();//      ==> 18
            //expected:<11> but was:<18>
            assertEquals(11, x);

        }
        @Test
        public void ADbasicget(){
            ArrayDeque<Integer> ArrayDeque = new ArrayDeque<Integer>();

            ArrayDeque.addLast(0);
            ArrayDeque.addFirst(1);
            ArrayDeque.addLast(2);
            ArrayDeque.addLast(3);
            ArrayDeque.removeFirst(); //    ==> 1
            ArrayDeque.removeLast();//      ==> 3
            ArrayDeque.addLast(6);
            ArrayDeque.addLast(7);
            ArrayDeque.addFirst(8);
            ArrayDeque.get(1);//      ==> 0
            ArrayDeque.removeLast();//      ==> 7
            ArrayDeque.addLast(11);
            ArrayDeque.addLast(12);
            ArrayDeque.addFirst(13);
            ArrayDeque.addFirst(14);
            ArrayDeque.get(1);//      ==> 13
            ArrayDeque.removeFirst();     //==> 14
            ArrayDeque.removeFirst();     //==> 13
            ArrayDeque.addLast(18);
            ArrayDeque.addLast(19);
            ArrayDeque.addFirst(20);
            ArrayDeque.addFirst(21);
            ArrayDeque.removeLast();//      ==> 19
            int x = ArrayDeque.removeLast();//      ==> null
            //expected:<18> but was:<null>
            assertEquals(18, x);
        }
        @Test
        public void ADbasicRandomAddLastRemovelast(){
            ArrayDeque<Integer> x = new ArrayDeque<Integer>();
            x.addLast(0);
            x.addLast(1);
            x.addLast(2);
            x.addLast(3);
            x.addLast(4);
            x.addLast(5);
            x.addLast(6);
            x.addLast(7);
            x.removeLast();
            x.addLast(8);
            x.addLast(9);
            x.addLast(-10);
            x.addLast(11);
            x.addLast(12);
            x.addLast(-13);
            x.addLast(-14);
            x.addLast(-15);
            x.addLast(16);
            x.addLast(17);
            x.addLast(-18);
            x.addLast(-19);
            x.addLast(-20);
            x.addLast(21);
            x.addLast(22);
            x.removeLast();
            x.addLast(-23);
            x.addLast(-24);
            x.addLast(-25);
            x.addLast(26);
            x.addLast(27);
            x.addLast(-28);
            x.addLast(-29);
            x.addLast(-30);
            x.addLast(31);
            x.removeLast();
            x.addLast(32);
            x.addLast(33);
            x.addLast(34);
            int p = x.removeLast();
            x.addLast(35);
            x.addLast(36);
            x.addLast(-37);
            x.addLast(-38);
            x.addLast(-39);
            x.addLast(40);
            x.addLast(41);
            x.addLast(-42);
            x.addLast(-43);
            x.addLast(-44);
            x.addLast(45);
            x.addLast(46);
            x.addLast(-47);
            x.addLast(-48);
            x.addLast(-49);
            x.addLast(50);
            int a = x.removeLast();
            x.addLast(51);
            x.addLast(-52);
            x.addLast(-53);
            x.addLast(-54);
            x.addLast(55);
            x.addLast(56);
            x.addLast(57);
            x.addLast(-58);
            x.addLast(59);
            x.addLast(60);
            x.addLast(-61);
            x.addLast(-62);
            x.addLast(-63);
            assertEquals(50, a);
            assertEquals(34, p);
            /*
            x.addFirst(64);
            x.addFirst(65);
            x.addFirst(-66);
            x.addFirst(-67);
            x.addFirst(-68);
            x.addFirst(69);
            x.addFirst(70);
            x.addFirst(-71);
            x.addFirst(-72);
            x.addFirst(-73);
            x.addFirst(74);
            x.addFirst(75);
            x.addFirst(-76);
            x.addFirst(-77);
            x.addFirst(-78);
            x.addFirst(79);
            x.addFirst(80);
            */
           // assertEquals(34, a);
            //for(int i = 81; i < 500; i++){
              //  x.addFirst(i);
                //if(i == 107){
                  //  int a = x.removeLast();
                    //assertEquals(107, a);

               // }
            //}

        }
        @Test
        /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
        public void addRemoveTest() {

            ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
            // should be empty
            assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

            lld1.addFirst(7);
            // should not be empty
            assertFalse("lld1 should contain 1 item", lld1.isEmpty());

            lld1.removeFirst();
            // should be empty
            assertTrue("lld1 should be empty after removal", lld1.isEmpty());


        }
        @Test
        public void addget(){
            ArrayDeque<Integer> x = new ArrayDeque<>();

            x.addFirst(1);
            x.addFirst(2);
            x.addFirst(3);
            x.addFirst(4);
            x.addFirst(5);


            printfun(x);
        }


        @Test
        public void fillandremoveTest(){

            ArrayDeque<Integer> x = new ArrayDeque<>();
            x.addFirst(1);
            x.addFirst(2);
            x.addFirst(3);
            x.addFirst(4);
            x.addFirst(5);
            x.addFirst(6);
            x.addFirst(-7);
            x.addLast(8);//----
            /*
            x.addLast(9);
            x.addFirst(-10);
            x.addFirst(11);
            x.addFirst(12);
            x.addFirst(-13);
            x.addFirst(-14);
            x.addFirst(-15);
            x.addFirst(16);
            x.addFirst(17);
            x.addFirst(-18);
            x.addFirst(-19);
            x.addFirst(-20);
            x.addFirst(21);
            x.addFirst(22);
            x.addFirst(-23);
            x.addFirst(-24);
            x.addFirst(-25);
            x.addFirst(26);
            x.addFirst(27);
            x.addFirst(-28);
            x.addFirst(-29);
            x.addFirst(-30);
            x.addFirst(31);
            x.addFirst(32);
            System.out.print(x.get(0));
            x.addFirst(-33);
            x.addFirst(-34);
            x.addFirst(-35);
            x.addFirst(36);

             */
            x.removeLast();
            x.removeFirst(); // remove first adds itemslength-1 we dont want that

            x.removeLast();
            x.removeFirst();
            x.removeLast();
            x.removeFirst();
            x.removeLast();
            System.out.println(x.get(0));
            x.removeFirst();
            /*
            System.out.println("first 2" + x.get(0));
            x.removeLast();
            System.out.println("last 3" + x.get(0));

            //System.out.print(x.get(0));
            x.removeLast();

            x.removeFirst();
            System.out.println(x.get(0) + " ");
            x.removeLast();

            System.out.print(x.get(0));
            x.removeFirst();
            x.removeLast();

            x.removeFirst();
            x.removeLast();

            x.removeFirst(); // remove first adds itemslength-1 we dont want that
            x.removeLast();

            x.removeFirst();
            x.removeLast();

            x.removeFirst();
            x.removeLast();

            x.removeFirst();
            x.removeLast();
            x.removeLast();

            x.removeFirst();
            x.removeLast();

            x.removeFirst();
            x.removeLast();

            x.removeFirst();
            x.removeFirst();
            x.removeLast();

            x.removeFirst();
            x.removeLast();
            x.removeFirst();

             */
            x.addFirst(1);
            x.addFirst(2);
            x.addFirst(3);
            x.addFirst(4);
            x.addFirst(5);
            x.addFirst(6);
            x.addFirst(-7);
            x.addLast(8);
           // x.removeFirst();
            //System.out.println(x.get(0));
            //System.out.println(x.get(6));
            int size = x.size();
            String errorMsg = "  Bad size returned when removing from empty deque.\n";
            errorMsg += "  student size() returned " + size + "\n";
            errorMsg += "  actual size() returned 0\n";

            assertEquals(errorMsg, 8, size);

        }
        @Test
        /* Tests removing from an empty deque */
        public void removeEmptyTest() {

            ArrayDeque<Integer> lld1 = new ArrayDeque<>();
            lld1.addFirst(3);
            lld1.addLast(4);

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
        /* Check if you can create ArrayDeque with different parameterized types*/
        public void multipleParamTest() {
            System.out.println();

            ArrayDeque<String>  lld1 = new ArrayDeque<String>();
            ArrayDeque<Double>  lld2 = new ArrayDeque<Double>();
            ArrayDeque<Boolean> lld3 = new ArrayDeque<Boolean>();
            ArrayDeque<Integer> x = new ArrayDeque<Integer>();

            lld1.addFirst("string");
            lld2.addFirst(3.14159);
            lld3.addFirst(true);
            x.addLast(-26);

            lld1.removeFirst();
            lld2.removeFirst();
            lld3.removeFirst();
            x.removeLast();
            //String s = lld1.removeFirst();
            //double d = lld2.removeFirst();
            //boolean b = lld3.removeFirst();
            //Integer i = x.removeLast();

        }

        @Test
        /* check if null is return when removing from an empty ArrayDeque. */
        public void emptyNullReturnTest() {


            ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

            boolean passed1 = false;
            boolean passed2 = false;
            assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
            assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());


        }

        @Test
        /* Add large number of elements to deque; check if order is correct. */
        public void bigLLDequeTest() {
        //test doesnt work for 1a
            System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
/*
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
*/

        }

        /**
         * problem, i am not printing the value but instead this:
         *    x
         * =========
         *    deque.ArrayDeque@5c0369c4
         *    deque.ArrayDeque@5c0369c4
         *
         *    for both addLast and addFirst
         */

        private static void printfun(ArrayDeque<Integer> s){
            System.out.printf("   x \n");
            System.out.printf("=========\n");
            s.printDeque();
        }
        //public E get(int index){

        //}
        @Test
        public void addFirstTest(){
            System.out.println("print the first item in the list.");
            ArrayDeque<Integer> x = new ArrayDeque<>();
           // ArrayDeque<String>  y = new ArrayDeque<String>();
           // ArrayDeque<Double>  z = new ArrayDeque<Double>();

          //  z.addFirst(3.14);
          //  z.addFirst(7.98);
          //  y.addFirst("test1");
          //  y.addFirst("test2");
            x.addFirst(1);
            x.addFirst(2);
            x.addFirst(3);
            x.addFirst(4);
            x.addFirst(5);
            x.addFirst(6);
            x.addFirst(-7);
            x.addFirst(8);
            x.addFirst(9);
            x.addFirst(10);
            x.addFirst(-55);
            x.addFirst(42);
            x.addFirst(24);
            x.addFirst(99);
            x.addFirst(49);
            x.addFirst(32);
            x.addFirst(22);
            x.addFirst(11);
            x.addFirst(25);//
            x.addFirst(-17);
            x.addFirst(452);
            x.addFirst(120);
            x.addFirst(715);
            x.addFirst(-150);
            x.addFirst(650);
            x.addFirst(910);
            printfun(x);
            //System.out.println(x.get(0));

        }
        @Test
        public void addLastTest(){
            System.out.println("print the first item in the list.");
            ArrayDeque<Integer> x = new ArrayDeque<>();

            x.addLast(11);
            x.addLast(12);
            x.addLast(13);
            x.addLast(-14);
            x.addLast(15);
            x.addLast(-16);
            x.addLast(17);
            x.addLast(18);
            x.addLast(19);
            x.addLast(-20);
            x.addLast(21);
            x.addLast(22);
            x.addLast(23);
            x.addLast(24);
            x.addLast(25);
            x.addLast(-26);
            x.addLast(27);
            x.addLast(-28);
            x.addLast(29);
            x.addLast(30);
            x.addLast(31);
            x.addLast(-32);
            x.addLast(33);
            x.addLast(34);
            printfun(x);
            //System.out.println(x.get(0));

        }

    }


