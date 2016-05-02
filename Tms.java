package com.briup.ch14;

import java.util.Scanner;
/**
	业务类
	教师信息管理系统
	单个教师信息保存到教师对象，教师对象保存在数组（不能持久化保存）
*/
public class Tms {
	private Teacher[] tcs;	//存储学生对象的数组
	private int index;		//记录数组中学生的个数

	public Tms(){
		this.tcs = new Teacher[3];
		this.index = 0;
	}
	/**
	  添加学生信息
	  @author zhangsan
	  @param 要添加的教师
	*/
	public void save(Teacher tc){
		//当学生的个数超过数组所能容纳的范围的时候需要对数组进行扩展
		if(index >= tcs.length){
			Teacher[] demo = new Teacher[tcs.length+3];
			System.arraycopy(tcs,0,demo,0,index);
			tcs = demo;
		}
		tcs[index++] = tc;
	}
	/**
	  查询所有教师
	  @author  lisi
	  @return 包含所有教师的数组
	*/
	public Teacher[] findAll(){
		Teacher[] demo = new Teacher[index];
		System.arraycopy(tcs,0,demo,0,index);
		return demo;
	}
	/**
	  通过id查询教师
	  @author wangwu
	  @param id
	  @return
		null	没找到
		stu		查询到的教师
	*/
	public Teacher findById(long id){
		//1,通过id找位置
		int num = findIndexById(id);
		//2,把该位置上的对象返回
		return num==-1?null:tcs[num];
	}
	/**
	  通过id查找索引
	  @author licy
	  @param id
	  @return 
		-1		找不到
		正整数	该教师在数组中的位置
	*/
	public int findIndexById(long id){
		int num = -1;
		for(int i=0;i<index;i++){
			if(tcs[i].getId() == id){
				num = i;
				break;
			}
		}
		return num;
	}


	/**
	  通过id删除教师
	  @author zhaoliu
	  @param id
	  tcs = {
		{1001,terry,12},
		{1003,terry,12},
		{1004,terry,12},
		null,
		null,
		null
	  }
	  index = 3;
	  id = 1002
	  num = 1
	  tcs[1] = tcs[1+1]
	  tcs[2] = tcs[2+1]
	*/
	public void deleteById(long id){
		int num = findIndexById(id);
		for(int i=num;i<index-1;i++){
			tcs[i] = tcs[i+1];
		}
		tcs[--index] = null;
	}
	/**
	  更新教师信息
	  @author guoqi
	  @author 教师对象
	*/
	public void update(Teacher newTc){
        for(int i=0;i<index;i++){
		 if(tcs[i].getId()==newTc.getId()){
		   tcs[i].setName(newTc.getName());
		   tcs[i].setAge(newTc.getAge());
		 }
		}
	 
	}

	/**
		菜单
		@auhor
	*/
	public void menu(){
		System.out.println("***********教师信息管理系统***********");
		System.out.println("*1. 查询所有教师信息");
		System.out.println("*2. 录入教师信息");
		System.out.println("*3. 删除教师信息");
		System.out.println("*4. 查看教师信息");
		System.out.println("*5. 更改教师信息");
		System.out.println("*help. 帮助");
		System.out.println("*exit. 退出");
		System.out.println("**************************************");
	}

	public static void main(String[] args){
		Tms tms = new Tms();	
		Scanner sc = new Scanner(System.in);
		tms.menu();
		while(true){
			System.out.print("请输入功能编号：");
			String option = sc.nextLine();
			switch(option){
				case "1":{//查询所有教师信息
					System.out.println("以下是所有教师的信息：");
					//调用方法查询所有教师信息
					Teacher[] arr = tms.findAll();
					//遍历
					for(Teacher tc : arr){
						System.out.println(tc);
					}
					System.out.println("总计 "+tms.index+"人");
					break;
				}
				case "2":{//录入教师信息
					while(true){
						System.out.println("请输入教师信息【id#name#age】或者输入【break】返回主菜单");
						String tcStr = sc.nextLine();
						if(tcStr.equals("break")){
							break;
						}
						String[] tcArr = tcStr.split("#");
						//将用户的输入进行处理
						long id = Long.parseLong(tcArr[0]);
						String name = tcArr[1];
						int age = Integer.parseInt(tcArr[2]);
						//封装对象
						Teacher tc = new Teacher(id,name,age);
						//调用保存方法
						tms.save(tc);
						System.out.println("录入成功！");
						
					}
					break;
				}
				case "3":{//删除教师信息
					while(true){
						System.out.println("请输入要删除教师的【id】或者输入【break】返回主目录");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						//将字符串id转换为长整型
						long id = Long.parseLong(idStr);
						Teacher tc = tms.findById(id);
						if(tc == null){
							System.out.println("该教师不存在！");
							continue;
						}
						tms.deleteById(id);
						System.out.println("删除成功！");
					}

					break;
				}
				case "4":{//查看教师信息
					while(true){
						System.out.println("请输入要查找教师的【id】或者输入【break】返回主目录");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						//将字符串id转换为长整型
						long id = Long.parseLong(idStr);
						Teacher tc = tms.findById(id);
						if(tc == null){
							System.out.println("该教师不存在！");
							continue;
						}
						System.out.println(tc);
					}
					break;
				}
				case "5": {  
					while(true){
						System.out.println("请输入要修改教师的【id】或者输入【break】返回主目录");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						//将字符串id转换为长整型
						long id = Long.parseLong(idStr);
						Teacher tc = tms.findById(id);
						if(tc == null){
							System.out.println("您要修改的教师信息不存在！");
							continue;
						}
						System.out.println("您要修改的教师信息为："+tc);
						System.out.println("请输入该教师的新信息【name#age】");
						String tcStr = sc.nextLine();
						String[] tcArr=tcStr.split("#");
						String name = tcArr[0];
						int age = Integer.parseInt(tcArr[1]);
						Teacher newTc = new Teacher(id,name,age);
						tms.update(newTc);
						System.out.println("修改成功");

					}
					break;

                    
				    }
					
				case "exit":{
					System.out.println("bye bye!欢迎再次使用！");
					System.exit(0);
				}
				case "help":{
					tms.menu();
					break;
				}
				default:
					System.out.println("输入有误！");
			}
		}
	}
}