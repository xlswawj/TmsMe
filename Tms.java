package com.briup.ch14;

import java.util.Scanner;
/**
	ҵ����
	��ʦ��Ϣ����ϵͳ
	������ʦ��Ϣ���浽��ʦ���󣬽�ʦ���󱣴������飨���ܳ־û����棩
*/
public class Tms {
	private Teacher[] tcs;	//�洢ѧ�����������
	private int index;		//��¼������ѧ���ĸ���

	public Tms(){
		this.tcs = new Teacher[3];
		this.index = 0;
	}
	/**
	  ���ѧ����Ϣ
	  @author zhangsan
	  @param Ҫ��ӵĽ�ʦ
	*/
	public void save(Teacher tc){
		//��ѧ���ĸ������������������ɵķ�Χ��ʱ����Ҫ�����������չ
		if(index >= tcs.length){
			Teacher[] demo = new Teacher[tcs.length+3];
			System.arraycopy(tcs,0,demo,0,index);
			tcs = demo;
		}
		tcs[index++] = tc;
	}
	/**
	  ��ѯ���н�ʦ
	  @author  lisi
	  @return �������н�ʦ������
	*/
	public Teacher[] findAll(){
		Teacher[] demo = new Teacher[index];
		System.arraycopy(tcs,0,demo,0,index);
		return demo;
	}
	/**
	  ͨ��id��ѯ��ʦ
	  @author wangwu
	  @param id
	  @return
		null	û�ҵ�
		stu		��ѯ���Ľ�ʦ
	*/
	public Teacher findById(long id){
		//1,ͨ��id��λ��
		int num = findIndexById(id);
		//2,�Ѹ�λ���ϵĶ��󷵻�
		return num==-1?null:tcs[num];
	}
	/**
	  ͨ��id��������
	  @author licy
	  @param id
	  @return 
		-1		�Ҳ���
		������	�ý�ʦ�������е�λ��
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
	  ͨ��idɾ����ʦ
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
	  ���½�ʦ��Ϣ
	  @author guoqi
	  @author ��ʦ����
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
		�˵�
		@auhor
	*/
	public void menu(){
		System.out.println("***********��ʦ��Ϣ����ϵͳ***********");
		System.out.println("*1. ��ѯ���н�ʦ��Ϣ");
		System.out.println("*2. ¼���ʦ��Ϣ");
		System.out.println("*3. ɾ����ʦ��Ϣ");
		System.out.println("*4. �鿴��ʦ��Ϣ");
		System.out.println("*5. ���Ľ�ʦ��Ϣ");
		System.out.println("*help. ����");
		System.out.println("*exit. �˳�");
		System.out.println("**************************************");
	}

	public static void main(String[] args){
		Tms tms = new Tms();	
		Scanner sc = new Scanner(System.in);
		tms.menu();
		while(true){
			System.out.print("�����빦�ܱ�ţ�");
			String option = sc.nextLine();
			switch(option){
				case "1":{//��ѯ���н�ʦ��Ϣ
					System.out.println("���������н�ʦ����Ϣ��");
					//���÷�����ѯ���н�ʦ��Ϣ
					Teacher[] arr = tms.findAll();
					//����
					for(Teacher tc : arr){
						System.out.println(tc);
					}
					System.out.println("�ܼ� "+tms.index+"��");
					break;
				}
				case "2":{//¼���ʦ��Ϣ
					while(true){
						System.out.println("�������ʦ��Ϣ��id#name#age���������롾break���������˵�");
						String tcStr = sc.nextLine();
						if(tcStr.equals("break")){
							break;
						}
						String[] tcArr = tcStr.split("#");
						//���û���������д���
						long id = Long.parseLong(tcArr[0]);
						String name = tcArr[1];
						int age = Integer.parseInt(tcArr[2]);
						//��װ����
						Teacher tc = new Teacher(id,name,age);
						//���ñ��淽��
						tms.save(tc);
						System.out.println("¼��ɹ���");
						
					}
					break;
				}
				case "3":{//ɾ����ʦ��Ϣ
					while(true){
						System.out.println("������Ҫɾ����ʦ�ġ�id���������롾break��������Ŀ¼");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						//���ַ���idת��Ϊ������
						long id = Long.parseLong(idStr);
						Teacher tc = tms.findById(id);
						if(tc == null){
							System.out.println("�ý�ʦ�����ڣ�");
							continue;
						}
						tms.deleteById(id);
						System.out.println("ɾ���ɹ���");
					}

					break;
				}
				case "4":{//�鿴��ʦ��Ϣ
					while(true){
						System.out.println("������Ҫ���ҽ�ʦ�ġ�id���������롾break��������Ŀ¼");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						//���ַ���idת��Ϊ������
						long id = Long.parseLong(idStr);
						Teacher tc = tms.findById(id);
						if(tc == null){
							System.out.println("�ý�ʦ�����ڣ�");
							continue;
						}
						System.out.println(tc);
					}
					break;
				}
				case "5": {  
					while(true){
						System.out.println("������Ҫ�޸Ľ�ʦ�ġ�id���������롾break��������Ŀ¼");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						//���ַ���idת��Ϊ������
						long id = Long.parseLong(idStr);
						Teacher tc = tms.findById(id);
						if(tc == null){
							System.out.println("��Ҫ�޸ĵĽ�ʦ��Ϣ�����ڣ�");
							continue;
						}
						System.out.println("��Ҫ�޸ĵĽ�ʦ��ϢΪ��"+tc);
						System.out.println("������ý�ʦ������Ϣ��name#age��");
						String tcStr = sc.nextLine();
						String[] tcArr=tcStr.split("#");
						String name = tcArr[0];
						int age = Integer.parseInt(tcArr[1]);
						Teacher newTc = new Teacher(id,name,age);
						tms.update(newTc);
						System.out.println("�޸ĳɹ�");

					}
					break;

                    
				    }
					
				case "exit":{
					System.out.println("bye bye!��ӭ�ٴ�ʹ�ã�");
					System.exit(0);
				}
				case "help":{
					tms.menu();
					break;
				}
				default:
					System.out.println("��������");
			}
		}
	}
}