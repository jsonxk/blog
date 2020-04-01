package com.ashok.commit.common.BRLP2;

import java.io.File;
import java.util.Scanner;

public class Readfile {

    public static Instance Readfile() throws Exception {
        int N;
        int YS;
        int YH;
        int Group;
        int[] Limited_height_vessel = new int[]{};
        String[][] init;
        int[] height_yard;

        String path = "C:\\Users\\goocan\\Desktop\\Bay-3-5-4-8_8.pro";
        File file = new File(path);
        Scanner scn = new Scanner(file, "GBK");
        String jump_line = scn.nextLine();
        N = Integer.parseInt(scn.nextLine());
        jump_line = scn.nextLine();
        YS = Integer.parseInt(scn.nextLine());
        jump_line = scn.nextLine();
        YH = Integer.parseInt(scn.nextLine());
        jump_line = scn.nextLine();
        Group = Integer.parseInt(scn.nextLine());
        jump_line = scn.nextLine();
//		System.out.println(N);
//		System.out.println(YS);
//		System.out.println(YH);
//		System.out.println(Group);
        Limited_height_vessel = new int[Group];
        for (int i = 1; i <= Group; i++) {
            Limited_height_vessel[i-1] = Integer.parseInt(scn.nextLine());
            //System.out.println(Limited_height_vessel[i]);

        }
        height_yard = new int[YS];
        init = new String[YS][YH];
        jump_line = scn.nextLine();
        for (int s = 0; s <= YS-1; s++) {
            jump_line = scn.nextLine();
            String[] str = scn.nextLine().split(" ");
            height_yard[s] = str.length;
            for (int i = 0; i < str.length; i++) {
                int t = i ;
                init[s][t] = str[i];
                //System.out.println(init[s][t]);
            }
        }

        scn.close();

        return new Instance(YS, YH, Group, N, init, Limited_height_vessel, height_yard);
    }
}
