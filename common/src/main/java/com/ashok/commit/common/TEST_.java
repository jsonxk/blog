package com.ashok.commit.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TEST_ {
	public static int N;
	public static int YS;
	public static int YH;
	public static int Group;
	public static int[] Limited_height_vessel;
	public static String[][] init;	
	public static int[] height_yard;
	public static ArrayList<task> valid_task_list=new ArrayList<task>();
	public static String Group_ID;
	public static int Group_Number;
	public static void read_instance(File file) throws Exception
	{
		Scanner scn = new Scanner(file);
		String jump_line=scn.nextLine();
		N =Integer.parseInt(scn.nextLine());
		jump_line=scn.nextLine();
		YS =Integer.parseInt(scn.nextLine()); 
		jump_line=scn.nextLine();
		YH =Integer.parseInt(scn.nextLine());
		jump_line=scn.nextLine();
		Group=Integer.parseInt(scn.nextLine());
		jump_line=scn.nextLine();
//		System.out.println(N);
//		System.out.println(YS);
//		System.out.println(YH);
//		System.out.println(Group);
		Limited_height_vessel=new int[Group+1];
		for(int i=1;i<=Group;i++) {
			Limited_height_vessel[i]=Integer.parseInt(scn.nextLine());
			//System.out.println(Limited_height_vessel[i]);
			
		}
		height_yard=new int[YS+1];
		init = new String[YS + 1][YH + 1];
		jump_line=scn.nextLine();
		for (int s = 1; s <= YS; s++)
		{
			jump_line=scn.nextLine();
			String[] str = scn.nextLine().split(" ");
			height_yard[s]=str.length;
			for (int i = 0; i < str.length; i++)
			{
				int t=i+1;
				init[s][t] =str[i];
				//System.out.println(init[s][t]);
			}
		}

		scn.close();

		//return new Node(S, H, P, N, init);
	}
	public static void find_first_target_group(){
		
		for(int i=1;i<=YS;i++) {
			for(int j=1;j<=height_yard[i];j++) {
				
				if(Integer.parseInt(init[i][j].split("_")[1])==0) {
					task valid_task=new task(i,j);
					valid_task_list.add(valid_task);//�±��0��ʼ
				}
			}
		}
		for ( int i = 0; i < valid_task_list.size(); i++){
			//System.out.print(i);
			//System.out.println(init[valid_task_list.get(i).S][valid_task_list.get(i).H]);
			
			}
		int best_target = find_target();
		remove_target(best_target);
	}
	
	private static int find_target() {
		// TODO Auto-generated method stub
		int best_target=Integer.MAX_VALUE;
		int best_i=Integer.MAX_VALUE;
		for ( int i = 0; i < valid_task_list.size(); i++){
			int Number_blocking_mid=height_yard[valid_task_list.get(i).S]-valid_task_list.get(i).H;
			if (best_target>Number_blocking_mid){
				best_target=Number_blocking_mid;
				best_i=i;
			}
		}
		System.out.println(init[valid_task_list.get(best_i).S][valid_task_list.get(best_i).H]);
		return best_i;
		
		
	}
	private static void remove_target(int best_target) {
		// TODO Auto-generated method stub
		if (height_yard[valid_task_list.get(best_target).S]==valid_task_list.get(best_target).H) {
			init[valid_task_list.get(best_target).S][valid_task_list.get(best_target).H]=null;
			height_yard[valid_task_list.get(best_target).S]--;
		}else {
			relocate(best_target);
		}
		
	}
	private static void relocate(int best_target) {
		// TODO Auto-generated method stub
		int best_stack=Integer.MAX_VALUE;
		int[] gap = null;
		for(int i=1;i<=YS;i++) {
			gap[i]=Integer.MAX_VALUE;
		}
		Group_ID=init[valid_task_list.get(best_target).S][valid_task_list.get(best_target).H].split("_")[0];
		Group_Number=Integer.parseInt(init[valid_task_list.get(best_target).S][valid_task_list.get(best_target).H].split("_")[1]);
		for(int k=0;k<height_yard[valid_task_list.get(best_target).S]-valid_task_list.get(best_target).H;k++) {
			for(int i=1;i<=YS;i++) {
				if(i!=valid_task_list.get(best_target).S) {
					for(int j=1;j<=height_yard[j];j++) {
						if(Group_ID==init[i][j].split("_")[0]&&gap[i]<Group_Number-Integer.parseInt(init[i][j].split("_")[1])) {
							gap[i]=Group_Number-Integer.parseInt(init[i][j].split("_")[1]);
							}
						}				
				}
			}
		}
		//best_stack=min(gap    );
	}
	public static void main(String[] args) throws Exception
	{ 
		String path="D:/�ж�/������/����/Data/Gen/Bay-3-3-3-6_0.PRO";
		File file=new File(path);
		read_instance(file);
		find_first_target_group();
		
		//1����layout��ѡ��Ŀ�꼯װ��
		  //��һ����װ��ѡ�񣺱���layout���ҳ��±�Ϊ0�����ӣ��ŵ�Ŀ��������vaild container
		//2���ҳ�Ŀ�꼯װ��
		  //ѡ�������������ٵ���ΪĿ���䣬������û���ӣ�ִ��3.����4
		//3ȡ��Ŀ�꼯װ��
		  //����layout
		 //�ж�layout�Ƿ�Ϊ�գ���5
		  //����Ŀ�����飬2
		//4�ƶ�������
		  //����layout��ֱ��Ŀ��������Ϊ��
		//5����
		
		 
	}
}
