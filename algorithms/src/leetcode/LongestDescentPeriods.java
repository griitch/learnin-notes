package leetcode;

public class LongestDescentPeriods {

    // https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock/

    public static long getDescentPeriods(int[] prices) {
        int[] dp = new int[prices.length];
        dp[0] = 1;

        int j = 2;
        for(int i = 1 ; i < prices.length ; i++ ) {

            int toAdd = 1;

            if(prices[i-1] - prices[i] == 1){
                toAdd = j++;
            } else {
                j = 2;
            }

            dp[i] = dp[i-1] + toAdd;
        }
        return dp[prices.length - 1];
    }

    public static void main(String[] args) {
        int[] p = {12,11,10,9,8,7,6,5,4,3,4,3,10,9,8,7};

        System.out.println(getDescentPeriods(p));
    }



}
