package leetcode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum3 {


    public static void main(String[] args)  {
        foo(new ArrayList<>(),new HashSet<>(), 24,1,4);
    }



    private static void foo(List<List<Integer>> res, Set<Integer> temp, int sum, int num, int size){

        if(num > 9 || sum < 0 ) {
            return;
        }

        if(temp.size() == size) {
            if(sum == 0 ) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }

        // does not work on n = 24 and size = 4

        for (int num1 = num; num1 < 10 ; num1++) {
            temp.add(num1);
            foo(res,temp,sum - num1, num1 + 1,size);
            temp.remove(num1);
            if(sum - num1 < 0 && temp.size() == size - 1) {
                break;
            }
        }

    }
}
