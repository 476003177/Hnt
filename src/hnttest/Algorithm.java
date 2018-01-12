package hnttest;

import java.awt.*;
import java.util.*;

public class Algorithm {
	
	int zzn=3;                              //����
	int pzn;                                //���Ӹ���
	Vector<Zz> zze;                         //���Ӽ���
	Vector<Pz> pze;                         //���Ӽ���
	Vector<Bz> bz;                          //�ƶ�����
	int bs=1;                               //��¼����  
	
	public Algorithm(int pz)                //���캯��
	{
		this.pzn=pz;
		this.pze=new Vector<Pz>();
		this.zze=new Vector<Zz>();
		this.bz=new Vector<Bz>();
		for(int i=0;i<this.zzn;i++)         //��ʼ������
		{
			Zz zz1=new Zz();
			zz1.zznu=i;
			zz1.pzn=0;
			zz1.pze=new Stack<Pz>();
			zz1.x=Data.zcx+i*Data.zd;
			zz1.y=Data.zcy;
			zz1.heigh=zz1.y;
			this.zze.add(zz1);
		}
		for(int i=0;i<this.pzn;i++)         //��ʼ������
		{
			Pz pz1=new Pz();
			pz1.l=Data.pzlmax-i*(Data.pzlmax-Data.pzlmin)/(this.pzn-1);
			pz1.h=pz1.l*Data.bl;
			pz1.zz=0;
			pz1.pznu=i;
			pz1.x=this.zze.get(0).x;
			pz1.y=(int) (this.zze.get(0).heigh-pz1.h/2);
			this.zze.get(0).heigh=(int) (this.zze.get(0).heigh-pz1.h);
			Color color = new Color(
					(new Double(Math.random() * 128)).intValue() + 128,
					(new Double(Math.random() * 128)).intValue() + 128,
					(new Double(Math.random() * 128)).intValue() + 128);
			pz1.color=color;
			this.zze.get(0).pze.add(pz1);
			this.pze.add(pz1);
		}
		this.zze.get(0).pzn=this.pzn;
	}
	
	
	public void hanoi(int n,int from,int denpend_on,int to)//��n�������ɳ�ʼ���ƶ���Ŀ����(���ý�����)  
	{  
	    if (n==1)move(1,from,to);//ֻ��һ��������ֱ�ӽ������ϵ������ƶ���Ŀ�ĵ�  
	    else  
	    {  
	      hanoi(n-1,from,to,denpend_on);//�Ƚ���ʼ����ǰn-1�����ӽ���Ŀ�����ƶ�����������  
	      move(n,from,to);              //��ʣ�µ�һ�������ƶ���Ŀ������  
	      hanoi(n-1,denpend_on,from,to);//��󽫽������ϵ�n-1�������ƶ���Ŀ������  
	    }  
	}  
	
	public void move(int n,int from,int to) //�����Ϊn��������from�ƶ���to  
	{
//		Zz ydzz=this.zze.get(from-1);
//		Zz mdzz=this.zze.get(to-1);
//		Pz pz=ydzz.pze.pop();
//		ydzz.pzn--;
//		mdzz.pze.push(pz);
//		mdzz.pzn++;
//		pz.zz=mdzz.zznu;
		Bz bz1=new Bz();
		bz1.from=from-1;
		bz1.to=to-1;
		bz1.pz=this.pzn-n;
		this.bz.add(bz1);
//		System.out.println("��"+bs+"��:��"+bz1.pz+"�����Ӵ�"+bz1.from+"������---->"+bz1.to+"������");
//		for(int i=0;i<this.zze.size();i++)
//		{
//			System.out.println("��"+this.zze.get(i).zznu+"�����Ӹ���Ϊ��"+this.zze.get(i).pze.size());
//		}
		this.bs++;
	}  

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int zz=3;
		Algorithm at=new Algorithm(zz);
		at.hanoi(zz, 1, 2, 3);
//		System.out.println("���Ӹ���Ϊ��"+at.zze.size());
//		for(int i=0;i<at.zze.size();i++)
//		{
//			System.out.println("��"+at.zze.get(i).zznu+"�����Ӹ���Ϊ��"+at.zze.get(i).pze.size());
//		}
	}
}

class Zz                                    //������
{
	int pzn;                                //�������ϵ����Ӹ���
	int zznu;                               //���Ӻ�
	int x;                                  //����x
	int y;                                  //����y
	Vector<Pz> pze;                         //�������ϵ����Ӷ�ջ��ֻ�����϶˽���
	int heigh;                              //���Ӷѵ������ĸ߶�
	public void remove()                    //�Ƴ�����
	{
//		System.out.println("��"+this.zznu+"��������"+this.pze.size());
		this.pze.remove(this.pze.size()-1);
		this.csh();
	}
	public void add(Pz pz1)                 //��������
	{
		this.pze.add(pz1);
		this.csh();
	}
	public void csh()
	{
		this.pzn=this.pze.size();
		this.heigh=this.y;
		for(int i=0;i<this.pzn;i++)
		{
			this.heigh=(int) (this.heigh-this.pze.get(i).h);
		}
	}
}

class Pz                                    //������
{
	int pznu;                               //���Ӻ�
	double l;                               //�����ӵĳ�
	double h;                               //�����ӵĿ�
	int zz;                                 //����������
	int x;                                  //����x
	int y;                                  //����y
	Color color;
}

class Bz                                    //�ж�����
{
	int from;                               //Դ������
	int to;                                 //Ŀ������
	int pz;                                 //�ƶ�������
}
