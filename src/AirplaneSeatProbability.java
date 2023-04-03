/**
 * Airplane Seat Assignment Probability 
 * 
 * Problem: 
 * n passengers board an airplane with exactly n seats. The first passenger has lost the ticket and picks a seat randomly.
 * But after that, the rest of passengers will:
 * 1. Take their own seat if it is still available,
 * 2. Pick other seats randomly when they find their seat occupied
 * What is the probability that the n-th person can get his own seat?
 * 
 * Key insights:
 * 1. The last person has only 1 seat. That seat can either be his/her own seat or someone else's seat. So probability that it's his/her
 * own seat is 1/2. 
 * 2. The last seat can only be the last person's seat or person 1's seat because when everyone else sat down either someone 
 * took person 1's seat or did not. 
 * 
 * Time complexity: O(1);
 * Space complexity: O(1);
 */
public class AirplaneSeatProbability {

	public double nthPersonGetsNthSeat(int n) {
		return n == 1 ? 1 : .5;
	}
}
