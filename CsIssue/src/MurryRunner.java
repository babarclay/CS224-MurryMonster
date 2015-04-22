import java.util.ArrayList;

public class MurryRunner {
	// bradbarclay@hotmail.com
	static ArrayList<MurryMonsterTenticle> Heads;
	static boolean exitFound = false;
	static ArrayList<Coordinate> availablePaths = new ArrayList<Coordinate>();

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
		while (!exitFound) {
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
		System.out.println("Head is currently positioned at : (" + head.getCurrentPosition().getY() + ","
				+ head.getCurrentPosition().getX() + ")");

		Coordinate up = null;
		Coordinate down = null;
		Coordinate right = null;
		Coordinate left = null;

		if ((head.getCurrentPosition().getY() >= 0 && head.getCurrentPosition()
				.getY() <= 6)) {
			if (head.getCurrentPosition().getY() >= 1) {
				up = new Coordinate(head.getCurrentPosition().getY() - 1, head
						.getCurrentPosition().getX());
				System.out.println("Up is an option");
			}
			down = new Coordinate(head.getCurrentPosition().getY() + 1, head
					.getCurrentPosition().getX());
			System.out.println("Down is an option");
		}

		if (head.getCurrentPosition().getX() >= 0
				&& head.getCurrentPosition().getX() <= 7) {
			right = new Coordinate(head.getCurrentPosition().getY(), head
					.getCurrentPosition().getX() + 1);
			System.out.println("Right is an option");
		}
		if (head.getCurrentPosition().getX() >= 1
				&& head.getCurrentPosition().getX() <= 7) {
			left = new Coordinate(head.getCurrentPosition().getY(), head
					.getCurrentPosition().getX() - 1);
			System.out.println("Left is an option");
		}

		if (head.previousElement() == null) {
			checkMovement(up, down, left, right, head);
		} else if (!(head.getCurrentPosition().equals(head.previousElement()))) {
			checkMovement(up, down, left, right, head);
		}

		// determine action
		if (availablePaths.size() == 0) {
			// kill head
			head.killHead();
			System.out.println("A head is ded");
		} else if (availablePaths.size() == 1) {
			// Move
			head.getPath().add(availablePaths.get(0));
			System.out.println("moving to: (" + availablePaths.get(0).getY()
					+ "," + availablePaths.get(0).getX() + ")");
		} else if (availablePaths.size() > 1) {
			head.killHead();
			for(Coordinate c : availablePaths){
				MurryMonsterTenticle newHead = new MurryMonsterTenticle();
				newHead.addParentCoordinates(head);
				newHead.getPath().add(c);
				
			}
		}

		availablePaths.clear();

		checkOverlaps();
		if (2 == matrix1[head.getCurrentPosition().getY()][head
				.getCurrentPosition().getX()]) {
			exitFound = true;
			System.out.println("THE MURRY has found the exit");
		}

	}

	public static void checkMovement(Coordinate up, Coordinate down,
			Coordinate left, Coordinate right, MurryMonsterTenticle head) {
		if (down != null) {
			if (matrix1[down.getY()][down.getX()] == 0) {
				System.out.println("Down is an available move at: (" + down.getY() + ","
						+ down.getX() + ")");

				if ((head.getCurrentPosition().equals(new Coordinate(0, 0)))) {
					availablePaths.add(down);
				} else if (!(head.previousElement().equals(down))) {
					availablePaths.add(down);
				}
			}
		}
		if (up != null) {
			if (0 == matrix1[up.getY()][up.getX()]) {
				System.out.println("Up is available at: (" + up.getY()
						+ "," + up.getX()+")");
				
				if ((head.getCurrentPosition().equals(new Coordinate(0, 0)))) {
					availablePaths.add(up);
				} else if (!(head.previousElement().equals(up))) {
					availablePaths.add(up);
				}
			}
		}
		if (right != null) {
			if (matrix1[right.getY()][right.getX()] == 0) {
				System.out.println("Right is available at: (" + right.getY()
						+ "," + right.getX()+")");
				if ((head.getCurrentPosition().equals(new Coordinate(0, 0)))) {
					availablePaths.add(right);
				} else if (!(head.previousElement().equals(right))) {
					availablePaths.add(right);
				}
			}
		}

		if (left != null) {
			if (0 == matrix1[left.getY()][left.getX()]) {
				System.out.println("Left is available at: (" + left.getY()
						+ "," + left.getX() + ")");
				if ((head.getCurrentPosition().equals(new Coordinate(0, 0)))) {
					availablePaths.add(left);
				} else if (!(head.previousElement().equals(left))) {
					availablePaths.add(left);
				}
			}
		}
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
					livingHeads.get(livingHeads.size()).addParentCoordinates(
							livingHeads.get(i));
					livingHeads.get(livingHeads.size()).addParentCoordinates(
							livingHeads.get(j));

				}
			}
		}
	}
}
