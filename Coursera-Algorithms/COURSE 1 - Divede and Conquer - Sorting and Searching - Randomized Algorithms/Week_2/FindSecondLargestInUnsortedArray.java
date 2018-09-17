package Week_2;

import java.util.Arrays;

/**
 * Week 2 - Optional Theory Problems
 * 
 * You are given as input an unsorted array of n distinct numbers, where n is a power of 2. 
 * Give an algorithm that identifies the second-largest number in the array, 
 * and that uses at most n +log_2 n - 2 comparisons. 
 * 
 * Variation #1 - Amazon
 * Given 2 sorted arrays of n elements A,B. 
 * Find the k-th smallest element in the union of A and B in O(log k) time. 
 * You can assume that there are no duplicate elements.
 * 
 * Variation #2 - Kth Smallest Element - 215. Kth Largest Element in an Array
 * 				 230. Kth Smallest Element in a BST - https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
 * 				 378. Kth Smallest Element in a Sorted Matrix - https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
 * 				 668. Kth Smallest Number in Multiplication Table -  https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/description/
 * 				 719. Find K-th Smallest Pair Distance - https://leetcode.com/problems/find-k-th-smallest-pair-distance/description/
 * */

/* Thoughts:
 *  Reference: http://webcache.googleusercontent.com/search?q=cache:http://users.csc.calpoly.edu/~dekhtyar/349-Fall2017/lectures/lec03.349.pdf
 *  Tournament Sorting
 * 1. Start with comparing elements of the n element array in odd and even positions
 * and determining largest element of each pair. This step requires n/2 comparisons. 
 * Now you've got only n/2 elements. Continue pairwise comparisons to get n/4, n/8, ..elements.
 * Stop when the largest element is found. This step requires a total of n/2 + n/4 + n/8 + ... + 1 = n-1 comparisons.
 * 
 * 2. During previous step, the largest element was immediately compared with log2 (n) other elements. 
 * You can determine the largest o these elements in log2 (n) -1 comparisons 
 * That would be the second largest number in array.
 
The (tight) lower bound is n+lgn−2 (where lgn means log2n).

I'll prove tightness first: that this can be achieved. First find the maximum using a "tennis tournament" structure:
first compare the n elements in pairs, then compare the n/2 "winners" in pairs, and so on. 
(Unpaired elements get a bye to the next round.) Since every element except the winner loses exactly once, 
this takes n−1 comparisons. But now note that the second largest element must be one which lost to the winner, 
as it couldn't have been defeated by any other element. 
So you need to find the maximum among all the (up to) lg n elements that were defeated by the winner, 
and finding this maximum can be done in lg n - 1.

We can prove this is a lower bound as well. Let the number of elements that lost to the maximum be m.

Firstly, you need to find the maximum, since one cannot be sure some element is the second maximum 
without knowing which element is the maximum. Further, for each element that lost to the maximum (m of them),
this comparison was useless in determining whether it was or not the second maximum. 
In other words, all elements other than the maximum must lose at least once, 
and all but one (m−1) of the elements that directly lost to the maximum must lose at least once more,
no matter in which order the comparisons were made. So we need at least n−1+m−1=n+m−2 comparisons.

To state the same thing differently: n−2 of the elements must be found less than the second-largest element 
— comparisons with the largest element do not help here — 
plus m of them must lose directly to the maximum by definition, so we need at least n−2+m=n+m−2 comparisons.

This proves the n+ lgn −2 lower bound if we show that m >= lgn

Example: array of 8 numbers [10,9,5,4,11,100,120,110].

Comparisons on level 1: [10,9] ->10 [5,4]-> 5, [11,100]->100 , [120,110]-->120.

Comparisons on level 2: [10,5] ->10 [100,120]->120.

Comparisons on level 3: [10,120]->120.

Maximum is 120. It was immediately compared with: 10 (on level 3), 100 (on level 2), 110 (on level 1).

Step 2 should find the maximum of 10, 100, and 110. Which is 110. That's the second largest element.
*/

/** Related Topic: Divide and Conquer, Binary Search */

public class FindSecondLargestInUnsortedArray {

	private static int findSecondRecursive(int n, int[] A) {
		int[] firstCompared = findMaxTournament(0, n - 1, A); // n-1
																// comparisons;
		int[] secondCompared = findMaxTournament(2, firstCompared[0] - 1, firstCompared); // log2(n)-1
																							// comparisons.
		// Total comparisons: n+log2(n)-2;
		return secondCompared[1];
	}

	private static int[] findMaxTournament(int low, int high, int[] A) {
		if (low == high) {
			int[] compared = new int[2];
			compared[0] = 2;
			compared[1] = A[low];
			return compared;
		}
		int[] compared1 = findMaxTournament(low, (low + high) / 2, A);
		int[] compared2 = findMaxTournament((low + high) / 2 + 1, high, A);
		if (compared1[1] > compared2[1]) {
			int k = compared1[0] + 1;
			int[] newcompared1 = new int[k];
			System.arraycopy(compared1, 0, newcompared1, 0, compared1[0]);
			newcompared1[0] = k;
			newcompared1[k - 1] = compared2[1];
			return newcompared1;
		}
		int k = compared2[0] + 1;
		int[] newcompared2 = new int[k];
		System.arraycopy(compared2, 0, newcompared2, 0, compared2[0]);
		newcompared2[0] = k;
		newcompared2[k - 1] = compared1[1];
		return newcompared2;
	}

	private static void printarray(int[] a) {
		for (int i : a) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// Demo.
		System.out.println("Origial array: ");
		int[] A = { 10, 4, 5, 8, 7, 2, 12, 3, 1, 6, 9, 11 };
		printarray(A);
		int secondMax = findSecondRecursive(A.length, A);
		Arrays.sort(A);
		System.out.println("Sorted array(for check use): ");
		printarray(A);
		System.out.println("Second largest number in A: " + secondMax);
	}

}
