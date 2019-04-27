package com.itnao.LinearStructure.Stack.Maze;

import java.util.Random;

/**
 * Created by kaikai on 2019/4/27.
 *
 * 创建一个迷宫是基于网上找到的那个深度优先算法的启发，用自己的方式实现的迷宫生成方法。
 * 1.因为还没学到图，当前只学到线性结构，就不浪费时间去学习深度优先算法，之后会学习到。
 * 2.迷宫的生成方式我采取和找迷宫类似的方式来解决这个问题。
 * 3.首先我们先将迷宫的四面生成墙壁。
 * 4.然后随机的在数组的第二行和第二列生成一个出口和入口，先用临时的变量存储起来，然后根据想要的顺序现将出口存入堆栈，这样子最后路线会基于出栈的顺序从
 * 入口一直到入口的堆栈的顺序实现。
 * 5.然后我们基于出口的信息随机方向寻找下一个位置信息，并将当前的信息入栈存储起来，直到找到入口，然后这是一天完整的迷宫信息。
 */
public class CreateMazeBYStack {

    /**
     * 迷宫的行列长度
     */
    private int mazeSize;

    /**
     * 迷宫存放数组
     */
    private int maze[][];

    /**
     * 入口节点信息
     */
    private MazePosition enterPosition = new MazePosition();

    /**
     * 出口节点信息
     */
    private MazePosition exitPosition = new MazePosition();

    /**
     * 基于给定的大小创建一个正方形形状的二位数组，行列长度相同
     * @param mazeSize 二维数组大小
     */
    public CreateMazeBYStack(int mazeSize) {
        this.mazeSize = mazeSize;
        this.maze = new int[mazeSize][mazeSize];
    }

    public void makeMaze(){

    }

    /**
     * 生成墙壁节点
     * 1.先确定四个坐标点分别是:左上角(0，0)，右下角(mazeSize-1,mazeSize-1)
     * 2.基于左上角信息，我们生成x轴的上部墙壁，我们可以确定y轴不变那么我们就可以循环x轴信息为(x,0)，
     *   同时我们可以基于这个信息生成y轴的左侧墙壁，我们可以确定x轴不变的情况系循环y轴信息为(0，y)。
     *
     *   基于右下角信息，我们生成y轴的右侧墙壁，我们可以确定x轴不变的情况下可以循环y轴的信息为(mazeSize-1,y)，
     *   同时我们可以基于这个信息生成x轴的下部信息，我们可以确定y轴不变的情况下循环x轴的信息为(x,mazeSize-1)。
     */
    public void makeWall(){
        // 因为目前默认生成的是正方形的迷宫，所以不考虑行列长度不一致的问题，生成迷宫的墙壁信息，四个边赋值为1
        for (int i = 0;i < maze.length;i++){
            maze[i][0] = Constant.WALL;
            maze[i][mazeSize - 1] = Constant.WALL;
            maze[0][i] = Constant.WALL;
            maze[mazeSize - 1][i] =Constant.WALL;
        }

    }

    /**
     * 生成随机的的入口坐标和出口坐标信息
     * 1.因为入口和出口都在墙壁内侧的那一行和那一列，所以我们可以确定的是上侧一行和左侧一列的数组X轴的范围在1~8之间，
     *   而下侧和右侧的一列的数组Y轴的范围在1~8之间，所以我们基于这个在1~8之间随机一个两个数值作为出口和入口的信息。
     */
    public void makeEnterAndExit(){
        Random random = new Random();
        // 确定两个不可变的坐标点，限制当前的坐标点在墙壁内侧的那一行和那一列
        int definePoint [] = {1,mazeSize-2};
        // x轴信息只能在1和8之间选一个
        int defineX = definePoint[random.nextInt(definePoint.length)];
        // 随机生成y轴的信息
        int y = random.nextInt(8)+1;
        enterPosition.setRow(defineX);
        enterPosition.setCol(y);

        // y轴信息只能在1和8之间选一个
        int defineY = definePoint[random.nextInt(definePoint.length)];
        // 随机生成x轴的信息
        int x = random.nextInt(8)+1;
        exitPosition.setRow(defineY);
        exitPosition.setCol(x);

    }

    public MazePosition getEnterPosition() {
        return enterPosition;
    }

    public MazePosition getExitPosition() {
        return exitPosition;
    }

    public static void main(String[] args) {
        CreateMazeBYStack createMazeBYStack = new CreateMazeBYStack(10);
        createMazeBYStack.makeWall();
        createMazeBYStack.makeEnterAndExit();
        System.out.println("生成成功");
        System.out.println("入口信息为: x"+createMazeBYStack.getEnterPosition().getRow()+" y"+createMazeBYStack.getEnterPosition().getCol());
        System.out.println("出口信息为: x"+createMazeBYStack.getExitPosition().getRow()+" y"+createMazeBYStack.getExitPosition().getCol());
    }
}
