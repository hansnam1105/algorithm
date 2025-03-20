import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        return binary_search(diffs, times, limit);
    }
    
    public int binary_search(int[] diffs, int[] times, long limit){
        int max = 1; int min = 100000;
        while(max <= min){
            int level = (max+min) / 2;
            long mid = calc(diffs, times, level);
            
            if(mid > limit) max = level + 1;
            else min = level - 1;
        }
        return max;
    }
    
    public long calc(int[] diffs, int[] times, int level){
        long ans = 0;
        for(int i=0; i< diffs.length; i++){
            if(diffs[i] <= level)
                ans += times[i];
            else
                ans += (long)(times[i] + times[i-1]) * (long)(diffs[i] - level) + times[i];
        }
        return ans;
    }
}