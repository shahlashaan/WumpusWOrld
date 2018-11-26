package wumpus;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WumpusEnvironment {

	
		Scanner input=new Scanner(System.in);
		private int noOfPits;   
		private int wumpusPosition;
		private int goldPosition;
		private int position[]; 
		private int breeze_pos[]=new int[110];
		private int stench_pos[]=new int[110];
		
		void createWorld(String wumpusWorld[][]) {
			for(int i=0;i<110;++i)
			{
				breeze_pos[i]=-1;
				stench_pos[i]=-1;
				}
			
			for(int i=0;i<11;++i) 
				for(int j=0;j<11;++j)
					wumpusWorld[i][j]="";
			
			int count=1;
			System.out.println("The positions are as follows.");
			for(int i=1;i<=10;++i)
			{
				System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.print("|\t");
				for(int j=1;j<=10;++j)
					System.out.print((count++)+"\t|\t");
			}
			
			System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\nAgent start position: 91");
			wumpusWorld[10][1]="A"; //agents position

			System.out.println("\nEnter the number of pits.");
			noOfPits=input.nextInt();
			position=new int[noOfPits];
			
			Random rand = new Random();
			ArrayList<Integer> pitPosition=new ArrayList<Integer>();
			
			int temp=rand.nextInt(100) + 1;
			pitPosition.add(temp);
			for(int i=0;i<noOfPits;i++) {
				while(pitPosition.contains(temp)){
					temp=rand.nextInt(100) + 1;
				}
				pitPosition.add(temp);
			}
			
			for(int i=0;i<noOfPits;++i)
				{
				position[i]=pitPosition.get(i);
				show_sense(position[i],1,wumpusWorld);
				}
			
			int tempWump= rand.nextInt(100) + 1;
			while(pitPosition.contains(tempWump) && tempWump==91) {
				tempWump=rand.nextInt(100) + 1;
			}
			
			wumpusPosition=tempWump;
			show_sense(wumpusPosition,2,wumpusWorld);
			
			int tempGold= rand.nextInt(100) + 1;
			while(pitPosition.contains(tempGold) && wumpusPosition == tempGold && tempWump==91) {
				tempGold=rand.nextInt(100) + 1;
			}
			
			goldPosition=tempGold;
			
			
			insertFeatures(wumpusWorld);
		}
		
		
		void show_sense(int obstacle,int option,String wumpusWorld[][])
		{
			int hintUp,hintDown,hintLeft,hintRight;
			hintLeft=obstacle-1;
			hintRight=obstacle+1;
			hintDown=obstacle+10;
			hintUp=obstacle-10;
			
			if(obstacle>=1 && obstacle<=10)
				hintUp=0;
			if(obstacle%10==1)
				hintLeft=0;
			if(obstacle%10==0)
				hintRight=0;
			if(obstacle>=91 && obstacle<=100)
				hintDown=0;
			
			
			if(option==1) //breeze positions
				{breeze_pos[0]=hintLeft;
				breeze_pos[1]=hintRight;
				breeze_pos[2]=hintDown;
				breeze_pos[3]=hintUp;}
			else if(option==2) //stench positions
				{stench_pos[0]=hintLeft;
				stench_pos[1]=hintRight;
				stench_pos[2]=hintDown;
				stench_pos[3]=hintUp;}
			
			int temp1,count;
			
			for(int i=0;i<10;++i)
			{
				if(option==1)
					temp1=breeze_pos[i];
				else
					temp1=stench_pos[i];
				count=0;
				for(int j=1;j<=10;++j) //traverse and put breeze and stench
				{
					for(int k=1;k<=10;++k)
					{
						++count;
						if(count==temp1 && option==1 && !wumpusWorld[j][k].contains("B"))
						{
							wumpusWorld[j][k]+="B";
						}
						else if(count==temp1 && option==2 && !wumpusWorld[j][k].contains("S"))
							wumpusWorld[j][k]+="S";
					}
				}
			}
			 
			//display(w);
		}
		
		
		public void insertFeatures(String wumpusWorld[][])
		{
		int temporaryPosition=0;
		int countGoldrOrWumpusPos=0;
		boolean wumpusPlaced=false,goldPlaced=false;
		for(int i=0;i<noOfPits;++i)
		{
			temporaryPosition=position[i];
			countGoldrOrWumpusPos=0;
			for(int j=1;j<=10;++j)
			{
				for(int k=1;k<=10;++k)
				{
					++countGoldrOrWumpusPos;
					if(countGoldrOrWumpusPos==temporaryPosition)
						wumpusWorld[j][k]+="P";
					else if(countGoldrOrWumpusPos==goldPosition && !goldPlaced)
					{
						wumpusWorld[j][k]+="G";
						goldPlaced=true;
						}
					else if(countGoldrOrWumpusPos==wumpusPosition && !wumpusPlaced)
					{
						wumpusWorld[j][k]+="W";
						wumpusPlaced=true;
						}
				}
			}
		}
		
		displayWorld(wumpusWorld);
		}

		void displayWorld(String wumpusWorld[][])
		{
			System.out.println("\nThe environment for problem is as follows.\n");
			for(int i=1;i<=10;++i)
			{
				System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.print("|\t");
				for(int j=1;j<=10;++j)
					System.out.print(wumpusWorld[i][j]+"\t|\t");
			}
			System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		}
		
		
		
		public int[] getBreeze_pos() {
			return breeze_pos;
		}

		public void setBreeze_pos(int breeze_pos[]) {
			this.breeze_pos = breeze_pos;
		}

		public int[] getStench_pos() {
			return stench_pos;
		}

		public void setStench_pos(int stench_pos[]) {
			this.stench_pos = stench_pos;
		}
}
