package com.ashok.commit.common.BRLP;

public class Solution {
	public int s,h,k,l;
	String blocking;
	public Solution(String blocking, int s, int h, int k, int l) {
		// TODO Auto-generated constructor stub
		this.blocking=blocking;
		this.s=s;
		this.h=h;
		this.k=k;
		this.l=l;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public String getBlocking() {
		return blocking;
	}

	public void setBlocking(String blocking) {
		this.blocking = blocking;
	}
}
