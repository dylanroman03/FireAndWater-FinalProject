package utilities;

public class Constants {
	private Constants() {}
	
	// public static class Directions {
	// 	private Directions(){}

	// 	public static final int LEFT = 0;
	// 	public static final int UP = 1;
	// 	public static final int RIGHT = 2;
	// }

	public static class PlayerConstants {
		private PlayerConstants() {}

		public static final int JUMP = 0;
		public static final int RUNNING_LEFT = 1;
		public static final int RUNNING_RIGHT = 2;
		public static final int DOWN = 3;
		public static final int IDLE = 4;
	}


	public static final String[] PATH_WARRIOR_LIST = {
			"res/Heros/1 Pink_Monster/Pink_Monster_Jump_8.png",
			"res/Heros/1 Pink_Monster/Pink_Monster_Run_Left_6.png",
			"res/Heros/1 Pink_Monster/Pink_Monster_Run_6.png",
			"res/Heros/1 Pink_Monster/Pink_Monster_Fall_4.png",
			"res/Heros/1 Pink_Monster/Pink_Monster_Idle_4.png",
		};


	public static class FireTypes {
		private FireTypes() {}

		public static final int PURPLE = 2;
		public static final int BLUE = 3;
		public static final int GREEN = 4;
	}

	public static final String PATH_FLOOR_LEVELS = "res/floor.png";
	public static final String PATH_FILE_LEVELS = "levels/";
	public static final String PATH_BLUE_FIRE = "res/Fires/blue/loops/1_burning_loop_8.png";
	public static final String PATH_PURPLE_FIRE = "res/Fires/purple/loops/1_burning_loop_8.png";
	public static final String PATH_GREEN_FIRE = "res/Fires/green/loops/1_burning_loop_8.png";
}
