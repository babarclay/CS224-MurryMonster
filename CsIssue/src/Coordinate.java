public class Coordinate {

	private int x;
	private int y;
	private int value;

	public Coordinate(int y,int x) {
		this.x = x;
		this.y = y;	
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getValue(){
		return value;
	}

	public void setValue(int value){
		this.value = value;
	}

	public boolean equals(Coordinate input){
		return (this.x == input.x && this.y == input.y);
	}
}
