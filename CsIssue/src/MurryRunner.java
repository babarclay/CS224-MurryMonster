import java.util.ArrayList;

public class MurryRunner {
	// bradbarclay@hotmail.com
	static ArrayList<MurryMonsterTenticle> Heads;
	static boolean exitFound =false;
	static int numberOfAvailablePaths = 0;

	static int[][] matrix1 = //
	{//
			{ 0, 0, 1, 1, 1, 1, 1, 1 },//
			{ 1, 0, 0, 0, 0, 1, 0, 1 },//
			{ 1, 1, 0, 1, 1, 0, 0, 1 },//
			{ 1, 1, 0, 1, 1, 0, 1, 1 },//
			{ 1, 0, 0, 0, 0, 0, 1, 1 },//
			{ 1, 0, 1, 1, 0, 1, 1, 1 },//
			{ 1, 1, 1, 1, 0, 0, 0, 2 } //
	};

	int[][] matrix2 = {//
	{ 0, 0, 0, 1, 1, 1, 1, 1 },//
			{ 1, 1, 0, 0, 0, 0, 0, 1 },//
			{ 1, 1, 0, 1, 1, 1, 0, 1 },//
			{ 1, 0, 0, 1, 0, 0, 0, 1 },//
			{ 1, 0, 1, 1, 0, 1, 1, 1 },//
			{ 1, 0, 0, 0, 0, 0, 0, 1 },//
			{ 1, 1, 1, 1, 1, 1, 0, 2 } //
	};

	int[][] matrix3 = {//
	{ 0, 1, 0, 0, 1, 1, 1, 1 },//
			{ 0, 1, 0, 1, 1, 1, 0, 1 },//
			{ 0, 0, 0, 0, 1, 0, 0, 0 },//
			{ 1, 0, 1, 1, 1, 0, 1, 1 },//
			{ 0, 0, 1, 1, 1, 0, 1, 1 },//
			{ 1, 0, 1, 1, 0, 0, 0, 0 },//
			{ 1, 0, 0, 0, 0, 1, 1, 2 } //
	};

	public static void main(String[] args) {
		Heads = new ArrayList<MurryMonsterTenticle>();
		Heads.add(0, new MurryMonsterTenticle());
		Heads.get(0).getPath().add(new Coordinate(0, 0));
		while(!exitFound){
				tick();
		}
	}

	public static void tick() {
		for (MurryMonsterTenticle h : Heads) {
			if (h.isAlive()) {
				checkAdjecentSpaces(h);
			}

		}
	}

	public static void checkAdjecentSpaces(MurryMonsterTenticle head) {	
		System.out.println(head.getCurrentPosition().getValue());
		
		Coordinate up = null;
		Coordinate down = null;
		Coordinate right = null;
		Coordinate left = null;

		if (head.getCurrentPosition().getY() >= 1
				&& head.getCurrentPosition().getY() <= 6) {
			up = new Coordinate(head.getCurrentPosition().getX(), head
					.getCurrentPosition().getY() - 1);
			down = new Coordinate(head.getCurrentPosition().getX(), head
					.getCurrentPosition().getY() + 1);
		}

		if (head.getCurrentPosition().getX() >= 0
				&& head.getCurrentPosition().getX() <= 7) {
			right = new Coordinate(head.getCurrentPosition().getX() + 1, head
					.getCurrentPosition().getY());
		}
			if (head.getCurrentPosition().getX() >= 1
					&& head.getCurrentPosition().getX() <= 7) {
			left = new Coordinate(head.getCurrentPosition().getX() - 1, head
					.getCurrentPosition().getY());
			}
		

		ArrayList<Coordinate> availablePaths = new ArrayList<Coordinate>();

		if (up != null) {
			if (0 == matrix1[up.getX()][up.getY()]) {
				availablePaths.add(up);
				numberOfAvailablePaths++;
			}
		}
		
		if (down != null) {
			if (matrix1[down.getX()][down.getY()] == 0) {
				availablePaths.add(down);
				numberOfAvailablePaths++;
			}
		}
		
		if (right != null) {
			if (matrix1[right.getX()][right.getY()] == 0) {
				availablePaths.add(right);
				numberOfAvailablePaths++;
			}
			numberOfAvailablePaths++;
			
		}
		
		if (left != null) {
			if (0 == matrix1[left.getX()][left.getY()]) {
				availablePaths.add(left);
				numberOfAvailablePaths++;
			}
		}

		// determine action
		if (numberOfAvailablePaths == 0) {
			// kill head
			head.killHead();
			System.out.println("A head is ded");
		} else if (numberOfAvailablePaths == 1) {
			// Move
			head.getPath().add(availablePaths.get(0));
		} else if (numberOfAvailablePaths > 1) {
			head.getPath().addAll(availablePaths);
		}

		checkOverlaps();
		if(2 == matrix1[head.getCurrentPosition().getX()][head.getCurrentPosition().getY()]){
			exitFound = true;
			System.out.println("THE MURRY has found the exit");
		}
		
		numberOfAvailablePaths = 0;
	}

	public static void checkOverlaps() {
		ArrayList<MurryMonsterTenticle> livingHeads = new ArrayList<MurryMonsterTenticle>();

		for (MurryMonsterTenticle head : Heads) {
			if (head.isAlive()) {
				livingHeads.add(head);
			}
		}
		// find duplicates
		for (int i = 0; i < livingHeads.size(); i++) {
			for (int j = i + 1; j < livingHeads.size() - 1; j++) {
				if (livingHeads.get(i).equals(livingHeads.get(j))) {
					// set both heads to previous position, create new head at
					// current position.
					livingHeads.get(i).killHead();
					livingHeads.get(j).killHead();
					livingHeads.add(new MurryMonsterTenticle());
					livingHeads.get(livingHeads.size()).addlink(
							livingHeads.get(i));
					livingHeads.get(livingHeads.size()).addlink(
							livingHeads.get(j));

				}
			}
		}
	}
}
