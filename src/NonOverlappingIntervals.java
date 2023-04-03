import java.util.Arrays;

public class NonOverlappingIntervals {

    public eraseOverlappingIntervals(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int maxNumOfValidIntervals = 1;
        int prevEnd = intervals[0][1]; 
        
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] >= prevEnd) {
                maxNumOfValidIntervals++;
                prevEnd = intervals[i][1];
            }
        }
        return intervals.length - maxNumOfValidIntervals;
    }
}