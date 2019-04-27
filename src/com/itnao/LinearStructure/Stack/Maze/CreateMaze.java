package com.itnao.LinearStructure.Stack.Maze;


import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by kaikai on 2019/4/27.
 * 创建迷宫方法，这个方法是在网上找的，用到了深度优先算法，没学就先没研究，之后学到了在研究。
 * 方法来源 https://blog.csdn.net/u010782875/article/details/79286318
 */
public class CreateMaze {

    private final static int dirUp = 0;
    private final static int dirRight = 1;
    private final static int dirDown = 2;
    private final static int dirLeft = 3;

    private final static int gridWall = 1;
    private final static int gridEmpty = 0;
    private final static int gridBlind = -1;
    private final static int gridPath = 2;

    private int width;
    private int height;
    private MazePoint[][] matrix;
    private int[][] maze;


    /**
     * 构造一个迷宫，初始化迷宫的宽度和高度，同时初始化包含MazePoint的点阵
     */
    public CreateMaze(int height, int width) {
        this.width = width;
        this.height = height;
        this.matrix = new MazePoint[height][width];
        for (int i=0; i<height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = new MazePoint();
            }
            this.maze = new int[2 * height + 1][2 * width + 1];//构建宽为2*height+1、长为2*width+1的迷宫
        }
    }

    /**
     * 检查目标点的上下左右的邻居是否可以被访问，如果目标点超出界限则视为已被访问
     */
    public boolean isNeighborOK(int x, int y, int dir) {
        boolean isNeighborVisited = false;
        switch ( dir ) {
            case dirUp:
                if ( x <= 0 ) {
                    isNeighborVisited = true;  //越界视为已被访问
                }
                else {
                    isNeighborVisited = matrix[x - 1][y].isVisited();
                }
                break;
            case dirRight:
                if ( y >= width - 1 ) {
                    isNeighborVisited = true;
                }
                else {
                    isNeighborVisited = matrix[x][y + 1].isVisited();
                }
                break;
            case dirDown:
                if ( x >= height - 1 ) {
                    isNeighborVisited = true;
                }
                else {
                    isNeighborVisited = matrix[x + 1][y].isVisited();
                }
                break;
            case dirLeft:
                if ( y <= 0 ) {
                    isNeighborVisited = true;
                }
                else {
                    isNeighborVisited = matrix[x][y - 1].isVisited();
                }
                break;
        }
        return !isNeighborVisited;  //若点未被访问过（isNeighborVisited为false），则返回值为true，表示有邻居可以被访问
    }

    /**
     * 检查目标点的上下左右是否至少有一个可以访问
     */
    public boolean isNeighborOK(int x, int y) {
        return (this.isNeighborOK(x, y, dirUp) || this.isNeighborOK(x, y, dirRight) ||
                this.isNeighborOK(x, y, dirDown) || this.isNeighborOK(x, y, dirLeft));
    }

    /**
     * 获得一个可以被访问的方向
     */
    public int getRandomDir(int x, int y) {
        int dir = -1;
        Random rand = new Random();
        if ( isNeighborOK(x, y) ) {
            do {
                dir = rand.nextInt(4);
            //(x,y)的dir方向不可被访问，重新选择方向
            } while ( !isNeighborOK(x, y, dir) );
        }
        return dir;
    }

    /**
     * 将相邻可以访问的点设置为通路
     */
    public void pushWall(int x, int y, int dir) {
        switch ( dir ) {
            case dirUp:
                matrix[x][y].setWallUp(false);
                //将该点与其上面相邻的点设置通路
                matrix[x-1][y].setWallDown(false);
                break;
            case dirRight:
                matrix[x][y].setWallRight(false);
                //将该点与其右面相邻的点设置通路
                matrix[x][y+1].setWallLeft(false);
                break;
            case dirDown:
                matrix[x][y].setWallDown(false);
                //将该点与其下面相邻的点设置通路
                matrix[x+1][y].setWallUp(false);
                break;
            case dirLeft:
                matrix[x][y].setWallLeft(false);
                //将该点与其左面相邻的点设置通路
                matrix[x][y-1].setWallRight(false);
                break;
        }
    }

    /**
     * 深度优先遍历
     */
    public void traversal() {
        int x = 0;
        int y = 0;
        Stack<Integer> stackX = new Stack<Integer>();
        Stack<Integer> stackY = new Stack<Integer>();
        do {
            MazePoint p = matrix[x][y];
            if ( !p.isVisited() ) {
                p.setVisited(true);
            }
            if ( isNeighborOK(x, y) ) {
                //获得一个随机可访问的方向
                int dir = this.getRandomDir(x, y);
                //设置通路
                this.pushWall(x, y, dir);
                //保存坐标信息
                stackX.add(x);
                //保存坐标信息
                stackY.add(y);
                switch ( dir ) {
                    case dirUp:
                        x--;
                        break;
                    case dirRight:
                        y++;
                        break;
                    case dirDown:
                        x++;
                        break;
                    case dirLeft:
                        y--;
                        break;
                }
            }
            else {
                x = stackX.pop();
                y = stackY.pop();
            }
        } while ( !stackX.isEmpty() );
    }

    /**
     * 根据MazePoint点阵右侧和下侧的墙构建迷宫
     */
    public void create() {
        for (int j=0; j<2*width+1; j++) {
            maze[0][j] = gridWall;
        }
        for (int i=0; i<height; i++) {
            maze[2*i+1][0] = gridWall;
            for (int j=0; j<width; j++) {
                maze[2*i+1][2*j+1] = gridEmpty;
                if ( matrix[i][j].isWallRight() ) {
                    maze[2 * i + 1][2 * j + 2] = gridWall;
                }else {
                    maze[2 * i + 1][2 * j + 2] = gridEmpty;
                }
            }
            maze[2*i+2][0] = 1;
            for (int j=0; j<width; j++) {
                if ( matrix[i][j].isWallDown() ) {
                    maze[2 * i + 2][2 * j + 1] = gridWall;
                }else {
                    maze[2 * i + 2][2 * j + 1] = gridEmpty;
                    maze[2 * i + 2][2 * j + 2] = gridWall;
                }
            }
        }
    }


    /**
     * 创建用于自动解析算法用的数组信息
     */
    public void createForAutoCompute() {
        for (int j=0; j<2*width+1; j++) {
            maze[0][j] = gridWall;
        }
        for (int i=0; i<height; i++) {
            maze[2*i+1][0] = gridWall;
            for (int j=0; j<width; j++) {
                maze[2*i+1][2*j+1] = gridEmpty;
                if ( matrix[i][j].isWallRight() ) {
                    maze[2 * i + 1][2 * j + 2] = gridWall;
                }else {
                    maze[2 * i + 1][2 * j + 2] = gridEmpty;
                }
            }
            maze[2*i+2][0] = 1;
            for (int j=0; j<width; j++) {
                if ( matrix[i][j].isWallDown() ) {
                    maze[2 * i + 2][2 * j + 1] = gridWall;
                }else {
                    maze[2 * i + 2][2 * j + 1] = gridEmpty;
                    maze[2 * i + 2][2 * j + 2] = gridWall;
                }
            }
        }
    }

    /**
     * 打印迷宫 图形版
     */
    public void printGraph() {
        System.out.println("图形版迷宫形状如下");
        for (int i=0; i<2*height+1; i++) {
            for (int j = 0; j < 2 * width + 1; j++) {
                if (maze[i][j] == gridWall) {
                    System.out.print("□");
                } else if (maze[i][j] == gridPath) {
                    //通路标记为".",其他设置为"□"
                    System.out.print(".");
                } else {
                    System.out.print("□");
                }
            }
            System.out.println();
        }
    }

    /**
     * 打印迷宫 数字版
     */
    public void printNumber() {
        System.out.println("数字版迷宫形状如下");
        for (int i=0; i<2*height+1; i++) {
            for (int j = 0; j < 2 * width + 1; j++) {
                if (maze[i][j] == gridWall) {
                    System.out.print(maze[i][j]);
                } else if (maze[i][j] == gridPath) {
                    //通路标记为".",其他设置为"□"
                    System.out.print(maze[i][j]);
                } else {
                    System.out.print(maze[i][j]);
                }
            }
            System.out.println();
        }
    }

    /**
     * 在迷宫数组中去获得一个访问的方向
     */
    public int getBreakOutDir(int x, int y) {
        int dir = -1;
        if ( maze[x][y+1] == 0 ) {
            dir = dirRight;
        }else if ( maze[x+1][y] == 0 ) {
            dir = dirDown;
        }else if ( maze[x][y-1] == 0 ) {
            dir = dirLeft;
        }else if ( maze[x-1][y] == 0 ) {
            dir = dirUp;
        }
            return dir;
    }

    /**
     * 找到从点 (1, 1) 到点 (2*height-1, 2*width-1) 的一条路径
     */
    public void findPath() {
        int x = 1;
        int y = 1;
        Stack<Integer> stackX = new Stack<Integer>();
        Stack<Integer> stackY = new Stack<Integer>();
        do {
            int dir = this.getBreakOutDir(x, y);
            if ( dir == -1 ) {
                //方向不存在的设置为盲点
                maze[x][y] = gridBlind;
                x = stackX.pop();
                y = stackY.pop();
            }
            else {
                maze[x][y] = gridPath;
                stackX.add(x);
                //保存通路点坐标信息
                stackY.add(y);
                switch ( dir ) {
                    case dirUp:
                        x--;
                        break;
                    case dirRight:
                        y++;
                        break;
                    case dirDown:
                        x++;
                        break;
                    case dirLeft:
                        y--;
                        break;
                }
            }
        } while ( !(x == 2*height-1 && y == 2*width-1) );
        maze[x][y] = gridPath;
    }

    /**
     * 清除迷宫中的路径
     */
    public void reset() {
        for (int i=0; i<2*height+1; i++) {
            for (int j = 0; j < 2 * width + 1; j++) {
                if (maze[i][j] != gridWall) {
                    maze[i][j] = gridEmpty;
                }
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入n:迷宫的行数为2n+1(包括上下两道墙)");
        int row = scanner.nextInt();
        System.out.println("请输入m:迷宫的列数为2m+1");
        int col = scanner.nextInt();
        CreateMaze maze = new CreateMaze(row, col);
        maze.traversal();  //遍历
        maze.create();     //构建迷宫
        maze.findPath();   //找到路径
        maze.printGraph();     //打印迷宫 图形
        maze.printNumber();  //打印迷宫 数字版
        maze.reset();     //清除迷宫

    }


}
