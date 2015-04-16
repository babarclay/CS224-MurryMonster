import java.util.ArrayList;

public class MurryMonsterTenticle {

	ArrayList<Coordinate> path;
	boolean isAlive;
	ArrayList<MurryMonsterTenticle> link;
	
	public MurryMonsterTenticle(){
		ArrayList<Coordinate> path = new ArrayList<Coordinate>();
		boolean isalive = true;
		link = new ArrayList<MurryMonsterTenticle>();
	}
	
	public void addlink(MurryMonsterTenticle linkInput) {
		link.add(linkInput);
		this.path.add(linkInput.path.get(linkInput.path.size()));
	}

	
	public MurryMonsterTenticle getLink(int index) {
		return link.get(index);
	}

	public Coordinate getCurrentPostion() {
		return path.get(path.size());
	}

}
