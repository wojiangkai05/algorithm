package com.itnao.LinearStructure.Stack.Maze;

/**
 * Created by kaikai on 2019/4/27.
 *
 * 迷宫的坐标类
 */
public class MazePosition {

    public MazePosition() {

    }

    public MazePosition(int row, int col, int dir) {
        this.row = row;
        this.col = col;
        this.dir = dir;
    }

    /**
     * 迷宫位置的横坐标
     */
    private int row;

    /**
     * 迷宫位置的纵坐标
     */
    private int col;

    /**
     * 迷宫的位置信息 这里将“东，西，南，北，东南，东北，西南，西北”分别记做“0，1，2，3，4，5，6，7”
     */
    private int dir;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }
}
