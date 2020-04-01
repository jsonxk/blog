package com.ashok.commit.common.BRLP2;

/**
 * Description: TODO
 * Author: xk
 * Date: 2020/3/29 12:11
 */
public class BRLP2 {

    public static void main(String[] args) throws Exception {

        Instance in = Readfile.Readfile();
        BRLPMain.findNode(in);
    }
}
