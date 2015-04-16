import java.util.ArrayList;

public class MurryRunner {
//bradbarclay@hotmail.com
	static ArrayList<MurryMonsterTenticle> Heads;

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
		ArrayList<MurryMonsterTenticle> Heads = new ArrayList<MurryMonsterTenticle>();
		Heads.add(new MurryMonsterTenticle());
		Heads.get(0).path.add(new Coordinate(0,0));
		tick();
	}

	public static void tick() {
		for (MurryMonsterTenticle h : Heads) {
			if (h.isAlive) {
				checkAdjecentSpaces(h);
			}

		}
	}

	public static void checkAdjecentSpaces(MurryMonsterTenticle head) {
		Coordinate up = new Coordinate(head.getCurrentPostion().getX(), head
				.getCurrentPostion().getY() - 1);
		Coordinate down = new Coordinate(head.getCurrentPostion().getX(), head
				.getCurrentPostion().getY() + 1);
		Coordinate right = new Coordinate(head.getCurrentPostion().getX() + 1,
				head.getCurrentPostion().getY());
		Coordinate left = new Coordinate(head.getCurrentPostion().getX() - 1,
				head.getCurrentPostion().getY());

		int numberOfAvailablePaths = 0;
		ArrayList<Coordinate> availablePaths = new ArrayList<Coordinate>();

		if (1 == matrix1[up.x][up.y]) {
			availablePaths.add(up);
			numberOfAvailablePaths++;
		} else if (1 == matrix1[down.x][down.y]) {
			availablePaths.add(down);
			numberOfAvailablePaths++;
		} else if (1 == matrix1[right.x][right.y]) {
			availablePaths.add(right);
			numberOfAvailablePaths++;
		} else if (1 == matrix1[left.x][left.y]) {
			availablePaths.add(left);
			numberOfAvailablePaths++;
		}

		// determine action
		if (numberOfAvailablePaths == 0) {
			// kill head
			head.isAlive = false;
		} else if (numberOfAvailablePaths == 1) {
			// Move
			head.path.add(availablePaths.get(0));
		} else if (numberOfAvailablePaths > 1) {
			head.path.addAll(availablePaths);
		}

	}

	public void checkOverlaps() {
		ArrayList<MurryMonsterTenticle> livingHeads = new ArrayList<MurryMonsterTenticle>();

		for (MurryMonsterTenticle head : Heads) {
			if (head.isAlive) {
				livingHeads.add(head);
			}
		}
		// find duplicates
		for (int i = 0; i < livingHeads.size(); i++) {
			for (int j = i + 1; j < livingHeads.size() - 1; j++) {
				if (livingHeads.get(i).equals(livingHeads.get(j))) {
					// set both heads to previous position, create new head at
					// current position.
					livingHeads.get(i).isAlive = false;
					livingHeads.get(j).isAlive = false;
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
