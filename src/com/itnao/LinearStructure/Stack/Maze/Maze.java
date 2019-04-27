package com.itnao.LinearStructure.Stack.Maze;

import com.itnao.LinearStructure.Stack.ArrayStack.ArrayStack;

/**
 * Created by kaikai on 2019/4/27.
 *
 * 迷宫问题
 *
 * 处理迷宫问题，采用堆栈数据结构来解决这个问题，首先我们明确为什么用堆栈来解决这个问题
 * 1.求解迷宫的问题采用的是“回溯法”，即从入口出发尝试各种可能，直到找到出口为止。
 * 2.在从一个位置尝试走到下一个位置时，有八种可能的走法，即东，西，南，北，东南，东北，西南，西北，八个路径。
 * 3.求解迷宫路径可以从入口开始尝试各个方向以找到下一个位置，并进而在新的位置在尝试所有可能找下一个位置。当在某个位置尝试所有可能找不到
 * 新位置时，说明进入了“死胡同”需要返回，即“回溯”。为了记住回溯的位置，需要采用一种数据结构来保存和恢复从前尝试过的潜在路径的位置信息，这个数据结构应该
 * 具有“先入后出“的特点，因为就是堆栈。
 */
public class Maze {

    /**
     * 行
     */
    private int Row;

    /**
     * 列
     */
    private int Col;

    /**
     * 方向
     */
    private int Dir;

    /**
     * 迷宫
     */
    private int maze[][];

    /**
     * 用来存放已经走过的路径坐标，当走过相应的路径后，在此数组中记录当前的坐标值为1
     */
    private int mark[][];

    /**
     * 迷宫的大小
     */
    private int mazeSize;


    /**
     * 记录当前是否找到迷宫的完整路径
     */
    private boolean isFoung = false;

    /**
     * 记录当前是否找到迷宫的完整路径
     */
    public Maze(int mazeSize, int[][] maze) {
        this.mazeSize = mazeSize;
        this.maze = maze;
    }

    /**
     * 计算整个迷宫的实际出口路径，因为堆栈是先进后出的，所以我们反向来计算整个迷宫的路径，从出口开始往入口计算
     * @param exitRow 出口的横坐标
     * @param exitCOL 出口的纵坐标
     */
    public void path(int exitRow,int exitCOL){

        // 首先生成一个迷宫大小的堆栈来存放路径信息
        ArrayStack<MazePosition> stack = new ArrayStack<>(mazeSize);
        // 先将出口的坐标信息压人栈中
        MazePosition mazePosition = new MazePosition(exitRow,exitCOL,Constant.NORTH);
        // 压栈
        stack.push(mazePosition);

        // 如果栈空证明找不到路径的信息，如果isFoung为true就证明已经找到了路径信息
        while (!stack.isEmpty() && !isFoung){

            // 将栈中的信息弹出，基于这个信息寻找路径信息
            MazePosition pop = stack.pop();
            // 行坐标
            Row = pop.getRow();
            // 列坐标
            Col = pop.getCol();
            // 方向信息
            Dir = pop.getDir();

            while (Dir < 8 && !isFoung){
                // 计算出基于当前方向的下一个位置的横坐标信息
                int nextRow = Row + Constant.moveDirection[Dir].getVert();
                // 计算出基于当前方向的下一个位置的纵坐标信息
                int nextCOL = Col + Constant.moveDirection[Dir].getHoriz();
                // 如果当前坐标点为 (1,1)，证明出口已经找到
                if (nextRow == 1 && nextCOL == 1){
                    isFoung = true;
                }else {
                    // 当当前坐标不等于且没有走过我们就进行路径的查找，查找next的坐标是不是路径信息
                    if (maze[nextRow][nextCOL] != 1 && mark[nextRow][nextCOL] != 1){
                       // 首先将此路径标记为走过
                       mark[nextRow][nextCOL] = 1;
                       // 并且将当前位置和下一个方向压栈
                       pop.setRow(Row);
                       pop.setCol(Col);
                       pop.setDir(Dir + 1);
                       stack.push(pop);
                       // 更新当前位置，从方向0开始
                       Row = nextRow;
                       Col = nextCOL;
                       Dir = 0;
                    }else {
                        // 若此路不通，则在当前位置尝试下一个方向
                        ++Dir ;
                    }
                }
            }
        }

        if (isFoung){
            System.out.println("找到迷宫的路径，详细路径信息如下");
            System.out.println("行   列");
            System.out.println("1   1");
            System.out.println(Row+"   "+Col);
            // 当栈不为空的时候，将栈中的数据一次输出
            if (!stack.isEmpty()){
                MazePosition pop = stack.pop();
                System.out.println(pop.getRow()+"   "+pop.getCol());
            }
        }else {
            System.out.println("当前迷宫无解，尽力了！");
        }

    }

    public static void main(String[] args) {
//        int mazeArray[][] = new int[10][10];
//
//        Maze maze = new Maze(100,);
    }
}
