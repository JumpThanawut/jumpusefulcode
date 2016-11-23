import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * This file contains useful Java code that may be reused in the future.
 * Warning: This may contain solutions from competitions. Use this for
 * reference, not for cheating.
 * 
 * @author Thanawut Ananpiriyakul
 *
 */
public class JumpJavaLibrary {

	/**
	 * Find the sum of all the multiples of 3 or 5 below N.
	 * 
	 * @param max
	 * @return
	 */
	public static long sumMultiple3and5(long max) {
		max--;
		long sum = 0;
		long numberOf3 = (long) Math.floor(max / 3);
		long numberOf5 = (long) Math.floor(max / 5);
		long numberOf15 = (long) Math.floor(max / 15);
		sum += 3 * (numberOf3 * (numberOf3 + 1) / 2);
		sum += 5 * (numberOf5 * (numberOf5 + 1) / 2);
		sum -= 15 * (numberOf15 * (numberOf15 + 1) / 2);
		return sum;
	}

	/**
	 * Find the sum of the even numbers in Fibonacci sequence which have value
	 * at most N.
	 * 
	 * @param n
	 * @return
	 */
	public static long evenSumOfFibo(long n) {
		long a = 1;
		long b = 2;
		long sum = 0;
		while (b <= n) {
			if (b % 2 == 0)
				sum += b;
			long temp = b;
			b = b + a;
			a = temp;
		}
		return sum;
	}

	/**
	 * Find the largest prime factor of a given number.
	 * 
	 * @param n
	 * @return
	 */
	public static long largestPrimeFactor(long n) {
		if (n <= 1)
			return 1;
		long largestPrimeFactor = 2;
		long i;
		while (n > 1) {
			for (i = largestPrimeFactor; i <= n; i++) {
				if (n % i == 0) {
					n /= i;
					largestPrimeFactor = i;
					break;
				} else if (i > Math.sqrt(n)) {
					return n;
				}
			}
		}
		return largestPrimeFactor;
	}

	/**
	 * Find the largest palindrome number that is a product of two 3-digit
	 * number which is less then n.
	 * 
	 * @param n
	 * @return
	 */
	public static int largestPalindrome(int n) {
		int max = -1;
		for (int i = 100; i < 1000; i++) {
			for (int j = 100; j < 1000; j++) {
				int product = i * j;
				if (isPalindrome(product) && product > max && product < n) {
					max = product;
				} else if (product >= n) {
					break;
				}
			}
		}
		return max;
	}

	/**
	 * Check whether the given number is a palindrome or not.
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isPalindrome(int n) {
		String n_string = n + "";
		if (n_string.length() < 2)
			return true;
		for (int i = 0; i < n_string.length() / 2; i++) {
			if (n_string.charAt(i) != n_string.charAt(n_string.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Find factors on a given number. <factor, power>
	 * 
	 * @param n
	 * @return
	 */
	public static TreeMap<Integer, Integer> getFactorMap(int n) {
		TreeMap<Integer, Integer> factorMap = new TreeMap<>();
		int i = 2;
		while (n > 1) {
			if (n % i == 0) {
				if (!factorMap.containsKey(i)) {
					factorMap.put(i, 1);
				} else {
					factorMap.put(i, factorMap.get(i).intValue() + 1);
				}
				n /= i;
				continue;
			} else {
				i++;
			}
			if (i > Math.sqrt(n)) {
				i = n;
			}
		}
		return factorMap;
	}

	/**
	 * Merge two maps with union approach.
	 * 
	 * @param map1
	 * @param map2
	 * @return
	 */
	public static Map<Integer, Integer> mergeMapMax(Map<Integer, Integer> map1, Map<Integer, Integer> map2) {
		Map<Integer, Integer> map = new TreeMap<>();
		for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
			if (map2.containsKey(entry.getKey())) {
				map.put(entry.getKey(), Math.max(entry.getValue().intValue(), map2.get(entry.getKey()).intValue()));
			} else {
				map.put(entry.getKey(), entry.getValue());
			}
		}
		for (Map.Entry<Integer, Integer> entry : map2.entrySet()) {
			if (!map.containsKey(entry.getKey())) {
				map.put(entry.getKey(), entry.getValue());
			}
		}
		return map;
	}

	/**
	 * Find lowest common multiple (lcm) of 1,2,3,...,n.
	 * 
	 * @param n
	 * @return
	 */
	public static int lowestCommonMultiple(int n) {
		Map<Integer, Integer> factorMap = new TreeMap<>();
		for (int i = 2; i <= n; i++) {
			factorMap = mergeMapMax(factorMap, getFactorMap(i));
		}
		int lcm = 1;
		for (Map.Entry<Integer, Integer> entry : factorMap.entrySet()) {
			lcm *= (int) Math.pow((entry.getKey().intValue()), (entry.getValue().intValue()));
		}
		return lcm;
	}

	/**
	 * Find the lowest common multiple of a,a+1,a+2,...,b.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int lowestCommonMultiple(int a, int b) {
		if (b < a)
			return -1;
		int lcm = a;
		for (int i = a; i <= b; i++) {
			lcm = lcm(lcm, i);
		}
		return lcm;
	}

	/**
	 * Find the greatest common divisor of x and y.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static int gcd(int x, int y) {
		if (x == 0 || y == 0) {
			return 0;
		}
		int temp;
		x = Math.abs(x);
		y = Math.abs(y);
		while (y > 0) {
			temp = x;
			x = y;
			y = temp % y;
		}
		return x;
	}

	/**
	 * Find the lowest common multiple of x and y.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static int lcm(int x, int y) {
		if (x == 0 || y == 0) {
			return 0;
		}
		return Math.abs(x) * Math.abs(y) / gcd(x, y);
	}

	/**
	 * Find sum of squares.
	 * 
	 * @param n
	 * @return
	 */
	public static long sumOfSquare(long n) {
		return n * (n + 1) * (2 * n + 1) / 6;
	}

	/**
	 * Find square of sum.
	 * 
	 * @param n
	 * @return
	 */
	public static long squareOfSum(long n) {
		long sum = n * (n + 1) / 2;
		return sum * sum;
	}
	
	

	/**
	 * Example of comparator.
	 * @author tanapiriyakul
	 *
	 */
	private static class StringLengthComparator implements Comparator<String>
	{
	    @Override
	    public int compare(String x, String y)
	    {
	    	System.out.println("\t" + x + " " + y);
	    	return x.length() - y.length();
	    }
	}
	
	/**
	 * Example of PriorityQueue.
	 * @return
	 */
	public static PriorityQueue<String> newPriorityQueueExample() {
		Comparator<String> comparator = new StringLengthComparator();
		PriorityQueue<String> queue = new PriorityQueue<>(comparator);
		return queue;
	}
	
	public static boolean isPrime(int n) {
		n = Math.abs(n);
		if (n == 0 || n == 1) return false;
		if (n == 2) return true;
		if (n % 2 == 0) return false;
		for (int i=3; i<=Math.sqrt(n); i+=2) {
			if (n % i == 0) return false;
		}
		return true;
	}
	
	/**
	 * Return a list of prime number not greater than a given number.
	 * @param n
	 * @return
	 */
	public static int[] getPrimeList(int n) {
		double sqrtOfN = Math.sqrt(n);
		boolean[] board = new boolean[n+1];
		Arrays.fill(board, true);
		board[0] = false;
		board[1] = false;
		for (int i=2; i<=n; i++) {
			if (board[i] == true) {
				int index = i*i;
				if (i > sqrtOfN) {
					continue;
				}
				while (index <= n) {
					board[index] = false;
					index += i;
				}
			}
		}
		List<Integer> primeList = new ArrayList<Integer>();
		for (int i=0; i<=n; i++) {
			if (board[i] == true) {
				primeList.add(i);
			}
		}
		return integerListToIntArray(primeList);
	}
	
	public static int[] integerListToIntArray(List<Integer> integerArray)
	{
	    int[] intArray = new int[integerArray.size()];
	    for (int i=0; i < integerArray.size(); i++)
	    {
	    	intArray[i] = integerArray.get(i).intValue();
	    }
	    return intArray;
	}
	
	public static int[] getEquilibriumIndices(int[] A) {
        // write your code in Java SE 8
        int size = A.length;
        int [] leftCum = new int[size];
        int [] rightCum = new int[size];
        leftCum[0] = A[0];
        for (int i=1; i<size; i++) {
            leftCum[i] = leftCum[i-1] + A[i];
        }
        rightCum[size-1] = A[size-1];
        for (int i=size - 2; i>=0; i--) {
            rightCum[i] = rightCum[i+1] + A[i];
        }
        List<Integer> equilibriumPoints = new ArrayList<>();
        for (int i=0; i<size; i++) {
            if (leftCum[i] == rightCum[i]) {
                equilibriumPoints.add(i);
            }   
        }
        return integerListToIntArray(equilibriumPoints);
    }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		PriorityQueue<String> queue = newPriorityQueueExample();
//		queue.add("hdj");
//		queue.add("al");
//		queue.add("jjjd");
//		queue.add("");
//		while(!queue.isEmpty()) {
//			String element = queue.poll();
//			System.out.println(element);
//		}
		
//		int[] A = {-1, 3, -4, 5, 1, -6, 2, 1};
//		System.out.println( Arrays.toString(getEquilibriumIndices(A)));
		
//		System.out.println(Arrays.toString(getPrimeList(100000000)));
		
//		System.out.println(getPrimeList(1000000).length);
		
//		int[] primeList = getPrimeList(10000000);
//		Set<Integer> primtSortedSet = new TreeSet<>();
//		for (int i=0; i<primeList.length; i++) {
//			primtSortedSet.add(primeList[i]);
//		}
//		for (int i=0; i<primeList[primeList.length-1]; i++) {
//			if (primtSortedSet.contains(i)) {
//				if (!isPrime(i)) {
//					System.out.println("ERROR");
//				}
//			}
//			else {
//				if (isPrime(i)) {
//					System.out.println("ERROR");
//				}
//			}
//		}
		
		
		
		
	}

}
