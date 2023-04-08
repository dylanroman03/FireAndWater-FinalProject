package utilities;

public class Constants {
	private Constants() {
	}

	public enum PlayerActions {
		JUMP,
		RUNNING_LEFT,
		RUNNING_RIGHT,
		DOWN,
		IDLE,
		IDLE_LEFT,
		DIE
	}

	public enum Heroes {
		PINK_MONSTER, DUDE_MONSTER
	}

	public enum FireTypes {
		PURPLE, BLUE, GREEN
	}

	public enum States {
		MENU, CHARACTERS, PLAYING, CREDITS, INSTRUCTIONS
	}

	public static final String PATH_PINK_MONSTER = "res/Heros/1 Pink_Monster/Pink_Monster.png";
	public static final String PATH_DUDE_MONSTER = "res/Heros/2 Owlet_Monster/Owlet_Monster.png";

	public static final String[][] PATH_CHARACTERS_LIST = {
		{
				"res/Heros/1 Pink_Monster/Pink_Monster_Jump_8.png",
				"res/Heros/1 Pink_Monster/Pink_Monster_Run_Left_6.png",
				"res/Heros/1 Pink_Monster/Pink_Monster_Run_6.png",
				"res/Heros/1 Pink_Monster/Pink_Monster_Fall_4.png",
				"res/Heros/1 Pink_Monster/Pink_Monster_Idle_4.png",
				"res/Heros/1 Pink_Monster/Pink_Monster_Idle_Left_4.png",
				"res/Heros/1 Pink_Monster/Pink_Monster_Death_8.png"
		},
		{
				"res/Heros/2 Owlet_Monster/Owlet_Monster_Jump_8.png",
				"res/Heros/2 Owlet_Monster/Owlet_Monster_Run_Left_6.png",
				"res/Heros/2 Owlet_Monster/Owlet_Monster_Run_6.png",
				"res/Heros/2 Owlet_Monster/Owlet_Monster_Fall_4.png",
				"res/Heros/2 Owlet_Monster/Owlet_Monster_Idle_4.png",
				"res/Heros/2 Owlet_Monster/Owlet_Monster_Idle_Left_4.png",
				"res/Heros/2 Owlet_Monster/Owlet_Monster_Death_8.png"
		}
	};

	public static final String PATH_FILE_LEVELS = "levels/";

	public static final String PATH_BLUE_FIRE = "res/Fires/blue/loops/1_burning_loop_8.png";
	public static final String PATH_PURPLE_FIRE = "res/Fires/purple/loops/1_burning_loop_8.png";
	public static final String PATH_GREEN_FIRE = "res/Fires/green/loops/1_burning_loop_8.png";

	public static final String PATH_BLUE_CRYSTAL = "res/Crystals/crystal_blue.png";
	public static final String PATH_PURPLE_CRYSTAL = "res/Crystals/crystal_purple.png";

	public static final String PATH_DOOR = "res/Doors_2.png";
	public static final String PATH_LEVER = "res/Lever_2.png";
	public static final String PATH_SWITCH = "res/Switch_4.png";
	public static final String PATH_FLOOR = "res/floor.png";
	public static final String PATH_BOX = "res/Box.png";

	public static final String PATH_LEVEL_BUILD = "res/Mainlev_Build.png";
	public static final String PATH_BACKGROUND = "res/background1.png";
	public static final String PATH_BACKGROUND_TWO = "res/background4b.png";
	public static final String PATH_BACKGROUND_CERO = "res/background0.png";

	public static final String PATH_DIALOG = "res/Gui/Dialog.png";
	public static final String PATH_PLAY_BTN = "res/Gui/Buttons_2.png";
	public static final String PATH_BUTTONS = "res/Gui/Buttons_3.png";
	public static final String PATH_LINE = "res/Gui/Line.png";
	public static final String PATH_KEYS = "res/Gui/Keyboard.png";

	// Music and Sounds

	public static final String PATH_TOWN_VILLAGE = "res/audio/PGS Fantasy RPG Music Pack/town-village-theme-3.wav";
	public static final String PATH_BATTLE_MUSIC = "res/audio/PGS Fantasy RPG Music Pack/battle-music-1.wav";
	public static final String PATH_FANFARE = "res/audio/PGS Fantasy RPG Music Pack/Success Fanfare.wav";

	public static final String PATH_PLAYER_DIE = "res/audio/Player/death.wav";
	public static final String PATH_GETTING_COIN = "res/audio/Player/gem.wav";
	public static final String PATH_PLAYER_JUMP = "res/audio/Player/jump.wav";

	public static final String PATH_BUTTON_SOUND = "res/audio/GUI/button.wav";
	public static final String PATH_LEVER_PULL = "res/audio/Player/lever pull.wav";



	public static String GetTimePath (int time) {
		return "res/Numbers/" + time + ".png";
	}
}
