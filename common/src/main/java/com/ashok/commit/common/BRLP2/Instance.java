package com.ashok.commit.common.BRLP2;

public class Instance {
    /**
     * 总数
     */
    public int N;
    /**
     * 目标栈数
     */
    public int YS;
    /**
     * 高
     */
    public int YH;

    /**
     * 目标组数
     */
    public int Group;
    /**
     * 目标货物组别max
     */
    public int[] Limited_height_vessel;
    /**
     * 源货物数组
     */
    public String[][] init;
    /**
     * 源货物max
     */
    public int[] height_yard;

    public Instance(int YS, int YH, int Group, int N, String[][] init, int[] Limited_height_vessel, int[] height_yard) {
        this.YS = YS;
        this.YH = YH;
        this.Group = Group;
        this.N = N;
        this.init = new String[YS][YH];
        this.Limited_height_vessel = Limited_height_vessel;
        this.init = init;
        this.height_yard= height_yard;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public int getYS() {
        return YS;
    }

    public void setYS(int YS) {
        this.YS = YS;
    }

    public int getYH() {
        return YH;
    }

    public void setYH(int YH) {
        this.YH = YH;
    }

    public int getGroup() {
        return Group;
    }

    public void setGroup(int group) {
        Group = group;
    }

    public int[] getLimited_height_vessel() {
        return Limited_height_vessel;
    }

    public void setLimited_height_vessel(int[] limited_height_vessel) {
        Limited_height_vessel = limited_height_vessel;
    }

    public String[][] getInit() {
        return init;
    }

    public void setInit(String[][] init) {
        this.init = init;
    }

    public int[] getHeight_yard() {
        return height_yard;
    }

    public void setHeight_yard(int[] height_yard) {
        this.height_yard = height_yard;
    }
}