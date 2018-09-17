package Week_2;

/**Unimodality:
 *  An array is called unimodal iff it can be split into an increasing sequence followed by a decreasing sequence. 
 *  1 3 4 5 7 8 10 12 13 14 10 9 6 2
 *  
 *  A weakly unimodal array is one that can be split into a nondecreasing sequence followed by a nonincreasing sequence.
 *  1 3 4 5 7 8 10 10 13 14 10 9 6 2
 *  
 * You are a given a unimodal array of n distinct elements, meaning that its
 * entries are in increasing order up until its maximum element, after which its
 * elements are in decreasing order. Give an algorithm to compute the maximum
 * element that runs in O(log n) time
 * 
 * Variation #1 - 33. Search in Rotated Sorted Array Variation #2 - 153. Find
 * Minimum in Rotated Sorted Array Variation #3 - 154. Find Minimum in Rotated
 * Sorted Array II
 * 
 */
//FIX IT!!
public class FindMaxInUnimodalArray {
	
	 public static int findUniModalMax(int[] nums, int left, int right) {
		 
	        if (nums==null || nums.length==0) { return 0; } 
	        left = 0; right = nums.length-1;
	        while (left < right-1) {  // while (left < right-1) is a useful technique
	            int mid = left + (right-left)/2;
	            if (nums[mid] > nums[mid+1]) {
	            	findUniModalMax(nums, left, mid);
	            	//left = mid; 
	            }
	            else { 
	            	findUniModalMax(nums, mid + 1, right);
	            	//right = mid; 
	            }
	        }
	        if (nums[left] > nums[right]) { return nums[right]; }
	        return nums[left];
	    }

	/*public static Integer uniModalMax(int[] num, int start, int end) {

		if (num == null || num.length == 0)
			return 0;

		if (num.length == 1)

			return num[0];
		
		int max = 0;
		while (start + 1 < end ){
			
			int mid = (start + end) / 2;

			if (num[mid] > num[mid + 1])
				max = uniModalMax (num, start, mid+1);

			else
				max = uniModalMax(num, mid+1, end);
			
		}
		
		return max;
		

	}*/

	public static void main(String[] args) {
		int[] A = {8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1};
		  System.out.println("The maximum element is "+  findUniModalMax(A, 0, A.length-1)); 
		
		

	}

}
