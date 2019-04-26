package com.itnao.LinearStructure;


/**
 *   基于分治法计算最大子列和问题
 *   1.思路就是将一个问题尽可能的最小化来处理.
 *   2.这个是数据结构中一种处理问题的方式。
 */
public class DivideAndCon {

    public static int DivideAndConquer(int a[],int left,int right){
        // 左侧最大子列和
        int maxLeftSum = 0;
        // 右侧最大子列和
        int maxRightSum = 0;
        // 左侧最大越界子列和
        int maxBorderLeftSum = 0;
        // 右侧最大越界子列和
        int maxBorderRightSum = 0;
        // 中心店
        int center = 0;

        // 用于终止递归操作，当左侧和右侧参数时同一个的时候，证明整个数组长度就是1
        if (left == right){
            if (a[left] > 0){
                return a[left];
            }else {
                return 0;
            }
        }

        //计算中心点
        center = (left+right)/2;

        // 求左侧最大子列和,递归调用
        maxLeftSum = DivideAndConquer(a,left,center);

        // 求右侧最大子列和，递归调用
        maxRightSum = DivideAndConquer(a,center+1,right);

        // 求左侧边界最大值
        int leftBorderSum = 0;
        for (int i= center; i > 0; i-- ){
            leftBorderSum += a[i];
            if (leftBorderSum > maxBorderLeftSum){
                maxBorderLeftSum = leftBorderSum;
            }
        }

        // 求右侧边界最大值
        int rightBorderSum = 0;
        for (int i= center; i < left; i++ ){
            rightBorderSum += a[i];
            if (rightBorderSum > maxBorderRightSum){
                maxBorderRightSum = rightBorderSum;
            }
        }
        return Max3(maxLeftSum,maxRightSum,maxBorderLeftSum+maxBorderRightSum);
    }

    public static int Max3(int A,int B,int C){
        return A>B?A>C?A:C:B>C?B:C;
    }

    public static void main(String[] args) {
        int a[] = {4,-3,5,-2,-1,2,6,-2};
        int i = DivideAndCon.DivideAndConquer(a, 0, 7);
        System.out.println(i);
    }
}
