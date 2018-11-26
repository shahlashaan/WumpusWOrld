package wumpus;



public class AgentExpedition {
	static int scream=0;
	static int score=0;
	static int complete=0;
	

	boolean check(Block t)
	{
		int temp=t.senseSomething();
		if(temp==1 || temp==2)
			return false;
		
		return true;
		}
	
	public void calculatePitDoubt(Block block[],int pos) {
		int value;
		if(!block[pos].isBlockedRight() && !block[pos+1].isSafe()) {
			value=block[pos+1].getDoubtPit();
			value++;
			block[pos+1].setDoubtPit(value);
		}
		if(!block[pos].isBlockedUp() && (pos-10)>=1 && !block[pos-10].isSafe()) { //up
			value=block[pos-10].getDoubtPit();
			value++;
			block[pos-10].setDoubtPit(value);
		}
		if(!block[pos].isBlockedLeft() && !block[pos-1].isSafe()) {
			value=block[pos-1].getDoubtPit();
			value++;//left
			block[pos-1].setDoubtPit(value);
		}
		if(!block[pos].isBlockedDown() && (pos+10)<=100 && !block[pos+10].isSafe()) {
			value=block[pos+10].getDoubtPit();
			value++; //down
			block[pos+10].setDoubtPit(value);
		}
		
		block[pos].setSafe(true);
		
	
	}
	
	public void calculateWumpusDoubt(Block block[],int pos) {
		int value;
		if(!block[pos].isBlockedRight() && !block[pos+1].isSafe()) {
			value=block[pos+1].getDoubtWumpus();
			value++;
			block[pos+1].setDoubtWumpus(value);
		}
		if(!block[pos].isBlockedUp() && (pos-10)>=1 && !block[pos-10].isSafe()) { //up
			value=block[pos-10].getDoubtWumpus();
			value++;
			block[pos-10].setDoubtWumpus(value);
		}
		if(!block[pos].isBlockedLeft() && !block[pos-1].isSafe()) {
			value=block[pos-1].getDoubtWumpus();
			value++;//left
			block[pos-1].setDoubtWumpus(value);
		}
		if(!block[pos].isBlockedDown() && (pos+10)<=100 && !block[pos+10].isSafe()) {
			value=block[pos+10].getDoubtWumpus();
			value++; //down
			block[pos+10].setDoubtWumpus(value);
		}
		
		block[pos].setSafe(true);
	
	
	}
	
	
	
	public void expedition() {
		// TODO Auto-generated method stub
		WumpusEnvironment world=new WumpusEnvironment();
		String worldGrid[][]=new String[11][11];
		world.createWorld(worldGrid);
		
		System.out.println("\n\nFinding the solution...");
		
		Block block[]=new Block[101];
		int blockNo=1;
		for(int i=1;i<11 && blockNo<101;++i) {
			for(int j=1;j<11;++j){
				if(blockNo>100) {
					break;}
				block[blockNo]=new Block(worldGrid[i][j],blockNo);
				++blockNo;
				}
			}
		
		block[91].setSafe(true);
		block[91].setVisited(true);
		
		int pos=91;
		int condition;
		int limit=0;
		String temp1,temp2;
		do
			{
			++limit;
			condition=-1;
			
			if(block[pos].getEnvironment().contains("G"))
				{
				complete=1;
				System.out.println("Gold Found!!");
				break;
				}
			
			
			if(!block[pos].isBlockedRight() && !block[pos].isRight() && block[pos+1].getDoubtPit()<1 && block[pos+1].getDoubtWumpus()<1 && !block[pos+1].isPitPosition() && !block[pos+1].isWumpusPosition() && !(block[pos].getPrevious().contains("r") && 
					(!block[pos].isLeft() || !block[pos].isUp() || !block[pos].isDown()) && check(block[pos])))
			{
				////////////beginning state breeze=0,doubt_breeze=0,stench=0,doubt_stench=0,no wumpus,moves right
				
				temp1="l";
				
				block[pos].setRight(true);;
				++pos;
				System.out.println("\nfront pos="+pos);
				++score;
				
				String backValue=block[pos].getPrevious();
				block[pos].setPrevious(backValue+temp1);
				
				condition=block[pos].senseSomething();
				if(condition==3)
					{complete=1;
					break;}
				else if(condition==1 && !block[pos].isVisited()) //breeze found
				{


					calculatePitDoubt(block,pos);
				}
					
										
				else if(condition==2 && !block[pos].isVisited()) //stench found
					
				
				{
					
					calculateWumpusDoubt(block,pos);
				}
				
				else if(condition==0)
					block[pos].setSafe(true);
				
				
				block[pos].setVisited(true);
				}
		
			else if(!block[pos].isBlockedLeft() && !block[pos].isLeft() && block[pos-1].getDoubtPit()<1 && block[pos-1].getDoubtWumpus()<1 && !block[pos-1].isPitPosition() && !block[pos-1].isWumpusPosition() && !(block[pos].getPrevious().contains("l") && 
							(!block[pos].isRight() || !block[pos].isUp() || !block[pos].isDown()) && check(block[pos])))
			{
				temp1="r";
				
				block[pos].setLeft(true);
				pos=pos-1;
				System.out.println("\nback pos= "+pos);
				++score;

				
			
				String backValue=block[pos].getPrevious();
				block[pos].setPrevious(backValue+temp1);
				
				
				
				condition=block[pos].senseSomething();
				if(condition==3)
					{complete=1;
					break;}
				else if(condition==1 && !block[pos].isVisited())
					{
					
				

					calculatePitDoubt(block,pos);
					}
				else if(condition==2 && !block[pos].isVisited())
				{
					
				
					calculateWumpusDoubt(block, pos);
				}
				else if(condition==0)
					block[pos].setSafe(true);
				
				
				block[pos].setVisited(true);
				
			}
			
			else if(!block[pos].isBlockedUp() && !block[pos].isUp() && (pos-10)>=1 && block[pos-10].getDoubtPit()<1 && block[pos-10].getDoubtWumpus()<1 && !block[pos-10].isPitPosition() && !block[pos-10].isWumpusPosition() && !(block[pos].getPrevious().contains("u") && 
						(!block[pos].isLeft() || !block[pos].isRight() || !block[pos].isDown()) && check(block[pos]))) {
	
					temp1="d";
					/////////////////////
					block[pos].setUp(true);;
					pos=pos-10;
					System.out.println("\nUp pos= "+pos);
					++score;
					
					String backValue=block[pos].getPrevious();
					block[pos].setPrevious(backValue+temp1);
					
					condition=block[pos].senseSomething();
					if(condition==3)
						{complete=1;
						break;}
					else if(condition==1 && !block[pos].isVisited())
					{
						
					

						calculatePitDoubt(block,pos);
						}
					else if(condition==2 && !block[pos].isVisited())
					{

					
						calculateWumpusDoubt(block, pos);
					}
					else if(condition==0)
						block[pos].setSafe(true);
					
					
					block[pos].setVisited(true);
					
				}
			
				else if(!block[pos].isBlockedDown() && !block[pos].isDown() && (pos+10)<=100 && block[pos+10].getDoubtPit()<1 && block[pos+10].getDoubtWumpus()<1 && !block[pos+10].isPitPosition() && !block[pos+10].isWumpusPosition()) {
					temp1="u";
					////////////////
					block[pos].setDown(true);
					pos=pos+10;
					System.out.println("\ndown pos= "+pos);
					++score;
					
					
					String backValue=block[pos].getPrevious();
					block[pos].setPrevious(backValue+temp1);
				
					condition=block[pos].senseSomething();
					if(condition==3)
						{complete=1;
						break;}
					else if(condition==1 && !block[pos].isVisited()) {
						calculatePitDoubt(block,pos);

					

					}
					else if(condition==2 && !block[pos].isVisited())
					{

					
						calculateWumpusDoubt(block, pos);
					}
					else if(condition==0)
						block[pos].setSafe(true);
					
					
					block[pos].setVisited(true);
				}
				else if(limit>100) {

					int temp3=pos;
					int flag_1=0,flag2=0,flag3=0,flag4=0;
					
					System.out.println("\nCurrently at position "+temp3+".\nThinking....");
					
					//if(!(t[pos].back.contains("r") && (t[pos].l!=1 || t[pos].u!=1 || t[pos].d!=1) && check(t[pos]) ))
					while(block[pos].isVisited() && !block[pos].isBlockedRight())// right
						{
						++pos;
						++score;
						}
					
					
					if(block[pos].isPitPosition() || block[pos].isWumpusPosition() || (block[pos].isBlockedRight() && block[pos].isVisited() && !block[pos].isSafe()))
						{
						//System.out.println("\nUnsuccessful at pos "+pos);
						pos=temp3;
						//System.out.println("\nBack at pos "+pos);
						flag_1=1;
						} 
					
					if(flag_1==0)
					//block[pos].back+="l";
					{String backValue=block[pos].getPrevious();
					block[pos].setPrevious(backValue+"l");}

					//if(!(t[pos].back.contains("u") && (t[pos].l!=1 || t[pos].r!=1 || t[pos].d!=1) && check(t[pos])  ))
					while(pos-10>=1 && !block[pos].isBlockedUp() && block[pos].isVisited()) // go up
						{
						pos-=10;
						++score;
						}
					
					if(block[pos].isPitPosition() || block[pos].isWumpusPosition() || (block[pos].isBlockedUp() && block[pos].isVisited()  && !block[pos].isSafe()))
						{
						//System.out.println("\nUnsuccessful at pos "+pos);
						pos=temp3;
						//System.out.println("\nBack at pos "+pos);
						flag3=1;
						} 
					
					if(flag3==0) {
						String backValue=block[pos].getPrevious();
						block[pos].setPrevious(backValue+"d");

						
					}
					
					while(block[pos].isVisited() && !block[pos].isBlockedLeft()) //go left
						{
						--pos;
						++score;
						}
					
					if(block[pos].isPitPosition() || block[pos].isWumpusPosition() || (block[pos].isBlockedLeft() && block[pos].isVisited() && !block[pos].isSafe()))
						{
						
						pos=temp3;
						
						flag2=1;
						} 
					
					if(flag2==0) {
						
						String backValue=block[pos].getPrevious();
						block[pos].setPrevious(backValue+"r");
					}
					
					
					//if(!(t[pos].back.contains("d") && (t[pos].l!=1 || t[pos].r!=1 || t[pos].u!=1) && check(t[pos])  ))
					while(pos+10<=100 && !block[pos].isBlockedDown() && block[pos].isVisited()) //go down
						{
						pos+=10;
						++score;
						}
					
					if(block[pos].isPitPosition() || block[pos].isWumpusPosition() || (block[pos].isBlockedDown() && block[pos].isVisited() && !block[pos].isSafe()))
						{
						
						pos=temp3;
						
						flag4=1;
						} 
					
					
					if(flag4==0) {
						
						String backValue=block[pos].getPrevious();
						block[pos].setPrevious(backValue+"u");
					}
					block[pos].setSafe(true);;
					block[pos].setVisited(true);;
					System.out.println("reached at position "+pos);
					limit=0;
					
				}
			if(block[pos].getEnvironment().contains("W") && scream!=1)
				{
				score+=100;
				scream=1;
				block[pos].setSafe(true);;
				System.out.println("\n\nWumpus killed >--0-->");
				block[pos].getEnvironment().replace("W"," ");
				for(int l=1;l<=100;++l)
					{
					block[l].setDoubtWumpus(0);
					block[l].getEnvironment().replace("S"," ");
					}
				}
		
			if(block[pos].getEnvironment().contains("P"))
				{
				score+=50;
				block[pos].setPitPosition(true);
				System.out.println("\n\nFallen in pit of position "+pos+".");
				}
		
			for(int k=1;k<=100;++k)
				{
				if(block[k].getDoubtPit()==1 && block[k].getDoubtWumpus()==1)
					{
					block[k].setDoubtPit(0);;
					block[k].setDoubtWumpus(0);
					block[k].setSafe(true);;
					}
				}
		
			for(int y=1;y<=100;++y)
				{
				if(block[y].getDoubtWumpus()>1)
					{
					block[y].setDoubtWumpus(1);
					for(int h=1;h<=100;++h)
						{
						if(h!=y)
							{
							block[h].setDoubtWumpus(0);;
							block[h].getEnvironment().replace("S"," ");
							}
						}
					
					}
				
				}
		
		///////////////////////////
			for(int y=1;y<=100;++y)
				{
				if(block[y].getDoubtPit()>1)
					{block[y].setPitPosition(true);
					//System.out.println("\nPit confirmed at position "+y);
					}
				}
			try{Thread.sleep(200);}catch(Exception p){}
			}while(complete==0);
			if(complete==1)
			{
			
			score*=-1;
			score+=2000;
			}
			System.out.println("The score of the agent till he reaches gold is "+score+".\nNow he will return back following the best explored path.");
			
	}
		
		

}
