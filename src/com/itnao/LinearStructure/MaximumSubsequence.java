package com.itnao.LinearStructure;


/**
 *  求最大子列和问题的最优解
 *  采用在线处理的方式进行问题的处理
 *
 *  根据题目要求，可以打印出最大子列和两端的值
 */
public class MaximumSubsequence {

    public static int compute(int a[]){
        if (a.length <= 0){
            return 0;
        }
        // 当前和以及最大的和
        int thisSum = 0;
        int maxSum = 0;
        int leftNum = 0;
        int rightNum = 0;
        for (int i=0;i < a.length; i++){
            if (thisSum == 0){
                // 当当前和为0时，默认第一次加的就是首位的参数
                leftNum = a[i];
                rightNum = 0;
            }else {
                if (a[i] > 0){
                    rightNum = a[i];
                }
            }
            thisSum += a[i];
            if (thisSum > maxSum){
                maxSum = thisSum;
            }else if (thisSum < 0){
                thisSum = 0;
            }

        }
        System.out.println("Left Num :"+leftNum);
        System.out.println("Right Num :"+rightNum);
        return maxSum;
    }

    public static void main(String[] args) {
        int list[] = {-1,3,-2,4,-6,1,6,-1,8,9,-7};
        int compute = compute(list);
        System.out.println("Max Subsequence Sum :"+compute);
    }
}
