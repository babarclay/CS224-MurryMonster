import java.util.ArrayList;

public class MurryMonsterTenticle {
	
	private ArrayList<Coordinate> path;
	private boolean isAlive;
	private ArrayList<MurryMonsterTenticle> link;
	
	public MurryMonsterTenticle(){
		path = new ArrayList<Coordinate>();
		isAlive = true;
		link = new ArrayList<MurryMonsterTenticle>();
	}
	
	public void addlink(MurryMonsterTenticle linkInput) {
		link.add(linkInput);
		this.getPath().add(linkInput.getPath().get(linkInput.getPath().size()));
	}

	
	public MurryMonsterTenticle getLink(int index) {
		return link.get(index);
	}

	public Coordinate getCurrentPosition() {
		return getPath().get(getPath().size());
	}

	public void killHead(){
		this.isAlive=false;
	}
	
	public boolean isAlive(){
		return this.isAlive;
	}

	public ArrayList<Coordinate> getPath() {
		return path;
	}

	public void setPath(ArrayList<Coordinate> path) {
		this.path = path;
	}
}
