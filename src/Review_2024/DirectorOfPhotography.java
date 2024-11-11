public class DirectorOfPhotography {
    /**
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */

    public int getArtisticPhotographCount(int N, String C, int X, int Y) {
        int count = 0;
        char[] cells = C.toCharArray();

        // Arrays to store prefix sums for 'P' and 'B'
        int[] prefixP = new int[N + 1];
        int[] prefixB = new int[N + 1];

        // Build prefix sums for photographers ('P') and backdrops ('B')
        for (int i = 1; i <= N; i++) {
            prefixP[i] = prefixP[i - 1] + (cells[i - 1] == 'P' ? 1 : 0);
            prefixB[i] = prefixB[i - 1] + (cells[i - 1] == 'B' ? 1 : 0);
        }

        // Iterate over all positions to find actors ('A')
        for (int i = 0; i < N; i++) {
            if (cells[i] == 'A') {
                // Direction -1 (left to right)
                // Photographers to the left of actor
                int leftP = Math.max(0, i - Y);
                int rightP = i - X;
                int numPhotographers = 0;
                if (rightP >= leftP) {
                    numPhotographers = prefixP[rightP + 1] - prefixP[leftP];
                }

                // Backdrops to the right of actor
                int leftB = i + X;
                int rightB = Math.min(N - 1, i + Y);
                int numBackdrops = 0;
                if (rightB >= leftB) {
                    numBackdrops = prefixB[rightB + 1] - prefixB[leftB];
                }

                count += numPhotographers * numBackdrops;

                // Direction +1 (right to left)
                // Photographers to the right of actor
                leftP = i + X;
                rightP = Math.min(N - 1, i + Y);
                numPhotographers = 0;
                if (rightP >= leftP) {
                    numPhotographers = prefixP[rightP + 1] - prefixP[leftP];
                }

                // Backdrops to the left of actor
                leftB = Math.max(0, i - Y);
                rightB = i - X;
                numBackdrops = 0;
                if (rightB >= leftB) {
                    numBackdrops = prefixB[rightB + 1] - prefixB[leftB];
                }

                count += numPhotographers * numBackdrops;
            }
        }

        return count;
    }
}
