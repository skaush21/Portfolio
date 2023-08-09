package ngordnet.main;

import edu.princeton.cs.algs4.In;

/**
 * Created by hug.
 */
public class FileReaderDemo {
    public static void main(String[] args) {
        /** notes for me
         * in - initializes an input stream from std input
         * readInt - Reads the next token from this input stream, parses it as a int, and returns the int.
         * readString - Reads the next token from this input stream and returns it as a String.
         * readDouble - Reads the next token from this input stream, parses it as a double, and returns the double
         */
        In in = new In("ngordnet/main/example_input_file.txt");

        /* Every time you call a read method from the In class,
         * it reads the next token from the file, assuming it is
         * of the specified type. The In class thinks of the "next"
         * token as whatever follows whitespace. That whitespace
         * may be spaces, tabs, and/or newlines. */

        /* Compare the calls below to the contents of ./ngordnet/main/example_input_file.txt */

        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        String thirdItemInFile = in.readString();
        String fourthItemInFile = in.readString();
        double fifthItemInFile = in.readDouble();

        System.out.println("The file contained "  + firstItemInFile + ", " +
                secondItemInFile + ", " + thirdItemInFile + ", " +
                fourthItemInFile + ", and " + fifthItemInFile);
    }
}
