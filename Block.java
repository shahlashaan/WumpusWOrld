package wumpus;

public class Block {

	private boolean pitPosition=false;
	private boolean goldPosition=false;
	private boolean wumpusPosition=false;
	private int doubtPit=0;
	private int doubtWumpus=0;
	private boolean blockedLeft=false;
	private boolean blockedRight=false;
	private boolean blockedUp=false;
	private boolean blockedDown=false;
	private boolean left=false;
	private boolean right=false;
	private boolean up=false;
	private boolean down=false;
	private boolean safe=false;
	private boolean visited=false;
	private String environment;
	private String previous="";
	private int blockNo;
	
	
	public Block(String environment,int blockNo) {
		
		this.setEnvironment(environment);
		this.setBlockNo(blockNo);
		
		if(blockNo>=1 && blockNo<=10) {
			setBlockedUp(true);			
		}
		if(blockNo%10==1) {
			setBlockedLeft(true);
		}
		if(blockNo%10==0) {
			setBlockedRight(true);
		}
		if(blockNo>=91 && blockNo<=100) {
			setBlockedDown(true);
		}
			
	}


	public boolean isPitPosition() {
		return pitPosition;
	}


	public void setPitPosition(boolean pitPosition) {
		this.pitPosition = pitPosition;
	}


	public boolean isGoldPosition() {
		return goldPosition;
	}


	public void setGoldPosition(boolean goldPosition) {
		this.goldPosition = goldPosition;
	}


	public boolean isWumpusPosition() {
		return wumpusPosition;
	}


	public void setWumpusPosition(boolean wumpusPosition) {
		this.wumpusPosition = wumpusPosition;
	}


	public int getDoubtPit() {
		return doubtPit;
	}


	public void setDoubtPit(int doubtPit) {
		this.doubtPit = doubtPit;
	}


	public int getDoubtWumpus() {
		return doubtWumpus;
	}


	public void setDoubtWumpus(int doubtWumpus) {
		this.doubtWumpus = doubtWumpus;
	}


	public boolean isBlockedLeft() {
		return blockedLeft;
	}


	public void setBlockedLeft(boolean blockedLeft) {
		this.blockedLeft = blockedLeft;
	}


	public boolean isBlockedRight() {
		return blockedRight;
	}


	public void setBlockedRight(boolean blockedRight) {
		this.blockedRight = blockedRight;
	}


	public boolean isBlockedUp() {
		return blockedUp;
	}


	public void setBlockedUp(boolean blockedUp) {
		this.blockedUp = blockedUp;
	}


	public boolean isBlockedDown() {
		return blockedDown;
	}


	public void setBlockedDown(boolean blockedDown) {
		this.blockedDown = blockedDown;
	}


	public boolean isLeft() {
		return left;
	}


	public void setLeft(boolean left) {
		this.left = left;
	}


	public boolean isRight() {
		return right;
	}


	public void setRight(boolean right) {
		this.right = right;
	}


	public boolean isUp() {
		return up;
	}


	public void setUp(boolean up) {
		this.up = up;
	}


	public boolean isDown() {
		return down;
	}


	public void setDown(boolean down) {
		this.down = down;
	}


	public boolean isSafe() {
		return safe;
	}


	public void setSafe(boolean safe) {
		this.safe = safe;
	}


	public String getEnvironment() {
		return environment;
	}


	public void setEnvironment(String environment) {
		this.environment = environment;
	}


	public String getPrevious() {
		return previous;
	}


	public void setPrevious(String previous) {
		this.previous = previous;
	}


	public int getBlockNo() {
		return blockNo;
	}


	public void setBlockNo(int blockNo) {
		this.blockNo = blockNo;
	}
	
	
	public int senseSomething() {
		if(environment.contains("B")) {
			return 1;			
		}
		if(environment.contains("S")) {
			return 2;			
		}
		if(environment.contains("W")) {
			return 3;			
		}
		if(environment.contains("G")) {
			return 4;			
		}
	
	return 0;
	}


	public boolean isVisited() {
		return visited;
	}


	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
}
