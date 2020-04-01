package com.ashok.commit.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	public static int N;
	public static int YS;
	public static int YH;
	public static int Group;
	public static int[] Limited_height_vessel;
	public static String[][] init;
	public static int[] height_yard;
	public static ArrayList<task> valid_task_list=new ArrayList<task>();
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
		find_target();
		
	}
	
	private static void find_target() {
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
		//return valid_task_list.get(best_i);
		
		//remove_target(best_i);
		if(height_yard[valid_task_list.get(best_i).S]-valid_task_list.get(best_i).H==0) {
			remove_target(best_i);
			
		}else{
			relocate_blocking(best_i);
		}
	}
	private static void relocate_blocking(int best_i) {
		String Group_ID;
		int Group_Number;
		for(int i=height_yard[valid_task_list.get(best_i).S];i>valid_task_list.get(best_i).H;i--) {
			Group_ID=init[valid_task_list.get(best_i).S][i].split("_")[0];
			Group_Number=Integer.parseInt(init[valid_task_list.get(best_i).S][i].split("_")[1]);
			for(int k=1;k<=YS&&k!=valid_task_list.get(best_i).S;k++) {
				for(int t=1;t<=height_yard[k]&&height_yard[k]<YH;t++) {
					if(init[k][t].split("_")[0]==Group_ID&&Integer.parseInt(init[k][t].split("_")[1])<Group_Number) {
						break;
					}else {
						init[k][height_yard[k]+1]=init[valid_task_list.get(best_i).S][valid_task_list.get(best_i).H];
						height_yard[k]++;
						}
				}
				break;
			}
		}
		
		height_yard[valid_task_list.get(best_i).S]=height_yard[valid_task_list.get(best_i).S]-valid_task_list.get(best_i).H;
		remove_target(best_i);
	}
	private static void remove_target(int i) {	
		height_yard[valid_task_list.get(i).S]--;		
		char[] group_lable;
		group_lable=(init[valid_task_list.get(i).S][valid_task_list.get(i).H].split("_")[0]).toCharArray();
		int group_lable_number=group_lable[0]-'A'+1;
		if(Integer.parseInt(init[valid_task_list.get(i).S][valid_task_list.get(i).H].split("_")[1])<Limited_height_vessel[group_lable_number])
		{
			//String next_group_id=init[valid_task_list.get(i).S][valid_task_list.get(i).H].split("_")[0];
			//int next_group_number=Integer.parseInt(init[valid_task_list.get(i).S][valid_task_list.get(i).H].split("_")[1])+1;
			for(int k=1;i<=YS;i++) {
				for(int j=1;j<=height_yard[i];j++) {
					while(Integer.parseInt(init[k][j].split("_")[1])==Integer.parseInt(init[valid_task_list.get(i).S][valid_task_list.get(i).H].split("_")[1])+1&&init[k][j].split("_")[0]==init[valid_task_list.get(i).S][valid_task_list.get(i).H].split("_")[0]) {
						task next_valid_task=new task(k,j);
						valid_task_list.add(next_valid_task);					
						}
					}
		
		    }
		}
		valid_task_list.remove(i);
		init[valid_task_list.get(i).S][valid_task_list.get(i).H]=null;
		find_target();
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
