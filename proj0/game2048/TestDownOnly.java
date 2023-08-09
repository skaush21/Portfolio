package game2048;

import org.junit.Test;

public class TestDownOnly extends TestUtils {
        /** Move tiles down (no merging). */
        @Test
        public void testDownNoMerge() {
            int[][] before = new int[][] {
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 2},
                    {0, 0, 4, 0},
            };
            int[][] after = new int[][] {
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 4, 2},
            };

            model = new Model(before, 0, 0, false);
            String prevBoard = model.toString();
            model.tilt(Side.SOUTH);
            checkModel(after, 0, 0, prevBoard, Side.SOUTH);
        }

        /** A basic merge. */
        @Test
        public void testDownBasicMerge() {
            int[][] before = new int[][] {
                    {0, 0, 0, 0},
                    {0, 0, 2, 0},
                    {0, 0, 2, 0},
                    {0, 0, 0, 0},
            };
            int[][] after = new int[][] {
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 4, 0},
            };

            updateModel(before, 0, 0, false);
            String prevBoard = model.toString();
            model.tilt(Side.SOUTH);
            checkModel(after, 4, 0, prevBoard, Side.SOUTH);
        }

        /** A triple merge. Only the leading 2 tiles should merge. */
        @Test
        public void testDownTripleMerge() {
            int[][] before = new int[][] {
                    {0, 0, 2, 0},
                    {0, 0, 0, 0},
                    {0, 0, 2, 0},
                    {0, 0, 2, 0},
            };
            int[][] after = new int[][] {
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 2, 0},
                    {0, 0, 4, 0},
            };

            updateModel(before, 0, 0, false);
            String prevBoard = model.toString();
            model.tilt(Side.SOUTH);
            checkModel(after, 4, 0, prevBoard, Side.SOUTH);
        }

        /** A tricky merge.
         *
         * The tricky part here is that the 4 tile on the bottom row shouldn't
         * merge with the newly created 4 tile on the top row. If you're failing
         * this test, try seeing how you can ensure that the bottom 4 tile doesn't
         * merge with the newly created 4 tile on top.
         */
        @Test
        public void testDownTrickyMerge() {
            int[][] before = new int[][] {
                    {0, 0, 2, 0},
                    {0, 0, 2, 0},
                    {0, 0, 0, 0},
                    {0, 0, 4, 0},
            };
            int[][] after = new int[][] {
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 4, 0},
                    {0, 0, 4, 0},
            };

            updateModel(before, 0, 0, false);
            String prevBoard = model.toString();
            model.tilt(Side.SOUTH);
            checkModel(after, 4, 0, prevBoard, Side.SOUTH);
        }
    @Test
    public void testTripleMerge2() {
        int[][] before = new int[][]{
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0},
                {4, 0, 0, 0},
        };

        updateModel(before, 0, 0, false);
        String prevBoard = model.toString();
        model.tilt(Side.SOUTH);
        checkModel(after, 4, 0, prevBoard, Side.SOUTH);
    }

}
