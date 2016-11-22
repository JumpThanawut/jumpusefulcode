/**
 * 
 * @author Thanawut Ananpiriyakul
 *
 */
public class JumpJavaLibrary {
	
	/**
	 * Find the sum of all the multiples of 3 or 5 below N.
	 * @param max
	 * @return
	 */
	public static long sumMultiple3and5(long max) {
        max--;
        long sum = 0;
        long numberOf3 = (long)Math.floor(max/3);
        long numberOf5 = (long)Math.floor(max/5);
        long numberOf15 = (long)Math.floor(max/15);
        sum += 3*(numberOf3*(numberOf3+1)/2);
        sum += 5*(numberOf5*(numberOf5+1)/2);
        sum -= 15*(numberOf15*(numberOf15+1)/2);
        return sum;
    }
	
	/**
	 * Find the sum of the even numbers in Fibonacci sequence which have value at most N.
	 * @param n
	 * @return
	 */
	public static long evenSumOfFibo(long n) {
        long a = 1;
        long b = 2;
        long sum = 0;
        while(b <= n) {
            if (b % 2 == 0) sum += b;
            long temp = b;
            b = b + a;
            a = temp;
        }
        return sum;
    }
	
	/**
	 * Find the largest prime factor of a given number.
	 * @param n
	 * @return
	 */
	public static long getLargestPrimeFactor(long n) {
        if (n <= 1) return 1;
        long largestPrimeFactor = 2;
        long i;
        while(n > 1) {
            for (i=largestPrimeFactor; i<=n; i++) {
                if (n % i == 0) {
                    n /= i;
                    largestPrimeFactor = i;
                    break;
                }
                else if(i > Math.sqrt(n)) {
                    return n;
                }
            }
        }
        return largestPrimeFactor;
    }

	/**
	 * Find the largest palindrome number that is a product of two 3-digit number which is less then n.
	 * @param n
	 * @return
	 */
	public static int largestPalindrome(int n) {
        int max = -1;
        for (int i=100; i<1000; i++) {
            for (int j=100; j<1000; j++) {
                int product = i*j;
                if (isPalindrome(product) && product > max && product < n) {
                    max = product;
                }
                else if(product >= n) {
                    break;
                }
            }
        }
        return max;
    }
	
	/**
	 * Check whether the given number is a palindrome or not.
	 * @param n
	 * @return
	 */
	public static boolean isPalindrome(int n) {
        String n_string = n + "";
        if (n_string.length() < 2) return true;
        for (int i=0; i<n_string.length()/2; i++) {
            if (n_string.charAt(i) != n_string.charAt(n_string.length()-1-i)) {
                return false;
            }
        }
        return true;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
