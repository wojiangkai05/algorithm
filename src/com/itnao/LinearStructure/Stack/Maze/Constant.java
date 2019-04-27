package com.itnao.LinearStructure.Stack.Maze;

/**
 * Created by kaikai on 2019/4/27.
 */
public class Constant {

    /**
     * 北
     */
    public static final int NORTH = 0;

    /**
     * 东北
     */
    public static final int NORTH_EAST = 1;

    /**
     * 东
     */
    public static final int EAST = 2;

    /**
     * 东南
     */
    public static final int SOUTH_EAST = 3;

    /**
     * 南
     */
    public static final int SOUTH = 4;

    /**
     * 西南
     */
    public static final int SOUTH_WEST = 5;

    /**
     * 西
     */
    public static final int WEST = 6;

    /**
     * 西北
     */
    public static final int NORTH_WEST = 7;

    /**
     * 可移动的方向,包括八种方向，东，西，南，北，东南，东北，西南，西北
     * 分别用二维数组来表示
     */
    public static final Offsets moveDirection[] = {new Offsets(-1,0),new Offsets(-1,1),new Offsets(0,1),
    new Offsets(1,1),new Offsets(1,0),new Offsets(1,-1),new Offsets(0,-1),new Offsets(-1,-1)
    };

    /**
     * 墙壁数值
     */
    public static final int WALL = 1;

    /**
     * 路径信息
     */
    public static final int PATH = 0;


}
