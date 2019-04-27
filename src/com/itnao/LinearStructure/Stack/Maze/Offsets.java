package com.itnao.LinearStructure.Stack.Maze;

/**
 * Created by kaikai on 2019/4/27.
 * 偏移量信息
 */
public class Offsets {

    public Offsets(int vert, int horiz) {
        this.vert = vert;
        this.horiz = horiz;
    }

    /**
     * 纵向偏移
     */
    private int vert;

    /**
     * 横向偏移
     */
    private int horiz;

    public int getVert() {
        return vert;
    }

    public void setVert(int vert) {
        this.vert = vert;
    }

    public int getHoriz() {
        return horiz;
    }

    public void setHoriz(int horiz) {
        this.horiz = horiz;
    }
}
