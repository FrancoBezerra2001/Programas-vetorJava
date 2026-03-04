public class StairClimbing {
    
    public static int climbStairsRecursive(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        int[] memo = new int[n + 1];
        memo[1] = 1;
        memo[2] = 2;
        
        return climbStairsHelper(n, memo);
    }
    
    private static int climbStairsHelper(int n, int[] memo) {
        if (memo[n] != 0) {
            return memo[n];
        }
        
        memo[n] = climbStairsHelper(n - 1, memo) + climbStairsHelper(n - 2, memo);
        return memo[n];
    }
    
    public static int climbStairs(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        int prev2 = 1; 
        int prev1 = 2; 
        int current = 0;
        
        for (int i = 3; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        
        return current;
    }
    
    public static void main(String[] args) {
        
        System.out.println("n=1: " + climbStairs(1)); 
        System.out.println("n=2: " + climbStairs(2)); 
        System.out.println("n=3: " + climbStairs(3)); 
        System.out.println("n=4: " + climbStairs(4)); 
        System.out.println("n=5: " + climbStairs(5)); 
        System.out.println("n=10: " + climbStairs(10)); 
    }
}