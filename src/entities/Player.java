package entities;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_CHARACTERS_LIST;
import static utilities.Constants.PATH_FANFARE;
import static utilities.Constants.PATH_GETTING_COIN;
import static utilities.Constants.PATH_PLAYER_DIE;
import static utilities.Constants.PATH_PLAYER_JUMP;
import static utilities.Helpers.canMove;
import static utilities.Helpers.getAnimationsX;
import static utilities.Helpers.isSolid;
import static utilities.Helpers.playSound;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.Game;
import screens.Playing;
import utilities.Constants.Heroes;
import utilities.Constants.PlayerActions;

public class Player extends Entity {
	private BufferedImage[][] animations = new BufferedImage[PATH_CHARACTERS_LIST[0].length][];
	private int aniTick;
	private double aniIndex;
	private int aniSpeed = 10;
	private PlayerActions playerAction = PlayerActions.IDLE;

	private boolean moving = false;
	private boolean left;
	private boolean jump;
	private boolean right;
	private boolean fall;
	private boolean death;
	private View view;
	private boolean stopAnimation = false;
	private boolean gameWon = false;
	private float runningSpeed = 1.2f;
	private Heroes hero = Heroes.PINK_MONSTER;
	private String name;

	private Playing playing;

	public enum View {
		LEFT,
		RIGHT,
	}

	// Gravity
	private float airSpeed = 0f;
	private float gravity = 0.02f * Game.SCALE;
	private float jumpingSpeed = -1.2f * Game.SCALE;
	private float fallSpeed = 0.3f * Game.SCALE; // Originalmente 0.7f
	private boolean inAir = false;
	public float xSpeed = 0;

	public Player(float x, float y, Playing playing) {
		super(x, y, (int) (TILES_SIZE * 0.6), (int) (TILES_SIZE * 0.8));
		this.playing = playing;
		loadAnimations();
		initHitBox(x, y, width, height);
	}

	public void update() {
		updatePosition();
		if (!stopAnimation) {
			updateAnimationTick();
		}
		setAnimation();
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(animations[playerAction.ordinal()][(int) aniIndex],
				(int) (hitBox.x - (width * 0.3)), (int) (hitBox.y - (height * 0.3)),
				TILES_SIZE, TILES_SIZE, null);

		if (Game.DEBUGING) {
			showHitBox(g, null);
		}
	}

	private void updateAnimationTick() {
		aniTick++;

		if (aniTick >= aniSpeed) {
			aniTick = 0;
			if (moving) {
				aniIndex++;
			} else {
				aniIndex = aniIndex + 0.25;
			}

			if (aniIndex >= animations[playerAction.ordinal()].length) {
				if (death) {
					stopAnimation = true;
					playing.gameOver();
					aniIndex--;
				} else {
					aniIndex = 0;
				}
			}

		}
	}

	private void setAnimation() {
		// int startAni = playerAction;
		PlayerActions startAni = playerAction;

		if (moving) {
			if (left)
				playerAction = PlayerActions.RUNNING_LEFT;
			else if (right)
				playerAction = PlayerActions.RUNNING_RIGHT;
			else if (jump)
				playerAction = PlayerActions.JUMP;
			else
				playerAction = PlayerActions.DOWN;

		} else if (death) {
			playerAction = PlayerActions.DIE;
		} else if (view == View.LEFT) {
			playerAction = PlayerActions.IDLE_LEFT;
		} else {
			playerAction = PlayerActions.IDLE;
		}

		if (startAni != playerAction)
			resetAniTick();
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePosition() {
		moving = false;

		if (death) return;

		if (jump) {
			jump();
		}

		if (inAir) {
			inAir();
		} else {
			if (!isInFloor()) {
				inAir = true;
			}
		}

		if (!left && !right && !inAir || gameWon)
			return;

		xSpeed = 0;

		if (left && !right) {
			xSpeed = -runningSpeed;
		} else if (right && !left) {
			xSpeed = runningSpeed;
		}
		// if (inAir) {
		// 	inAir();
		// } else {
		// 	if (!isInFloor()) {
		// 		inAir = true;
		// 	}
		// }

		if (canMove(playing, hitBox.x + xSpeed, hitBox.y)) {
			hitBox.x += xSpeed;
		}

		moving = true;

		if (playing.getFireManager().someIntersect(hero, this)) {
			death = true;
			playing.getMusic().stop();
			playSound(PATH_PLAYER_DIE);
			moving = false;
		}

		if (playing.getLevelManager().intersectDoor(hero, this)) {
			playing.gameWon();
			playing.getMusic().stop();
			playSound(PATH_FANFARE);
			gameWon = true;
			stopAnimation = true;
		}

		if (playing.getCrystalManager().someIntersect(hero, this)) {
			playSound(PATH_GETTING_COIN);
		}

		playing.getSwitchManager().someIntersect(hero, this);
		playing.getLeverManager().someIntersect(hero, this);
	}

	private boolean isInFloor() {
		if (!isSolid(hitBox.x, hitBox.y + hitBox.height + 1, playing)
				&& (!isSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 1, playing))) {
			if (!playing.getPlatformManager().overSome(this)) {
				return false;
			}
		}

		return true;
	}

	private void inAir() {
		if (canMove(playing, hitBox.x, hitBox.y + airSpeed)) {
			hitBox.y += airSpeed;
			airSpeed += gravity;
			if (airSpeed > 0) {
				setFall(true);
			}
		} else {

			if (airSpeed > 0) {
				inAir = false;
				airSpeed = 0;
				setFall(true);
			} else {
				airSpeed = fallSpeed;
			}
		}
	}


	private void jump() {
		if (inAir)
			return;
		inAir = true;
		airSpeed = jumpingSpeed;
		playSound(PATH_PLAYER_JUMP);
	}

	private void loadAnimations() {
		for (int i = 0; i < animations.length; i++) {
			animations[i] = getAnimationsX(PATH_CHARACTERS_LIST[hero.ordinal()][i]);
		}
	}

	public void resetDirection() {
		left = false;
		right = false;
		fall = false;
		jump = false;
		moving = false;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
		view = View.LEFT;
	}

	public boolean isJump() {
		return jump;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
		view = View.RIGHT;
	}

	public boolean isFall() {
		return fall;
	}

	public void setFall(boolean fall) {
		this.fall = fall;
	}

	public float getAirSpeed() {
		return airSpeed;
	}

	public void setHero(Heroes hero) {
		this.hero = hero;
		int[][] lvlData = playing.getLevelManager().getLvlData();
		int tile = hero == Heroes.PINK_MONSTER ? 10 : 11;

		for (int i = 0; i < lvlData.length; i++) {
			for (int j = 0; j < lvlData[i].length; j++) {
				if (lvlData[i][j] == tile) {
					hitBox.x = j * TILES_SIZE;
					hitBox.y = i * TILES_SIZE;
				}
			}
		}
		loadAnimations();
	}

	public void setName(String name) {
		this.name = name;
	}

	public PlayerActions getPlayerAction() {
		return playerAction;
	}

	public void setY(float f) {
		hitBox.y = f;
	}

	public void setX(float f) {
		hitBox.x = f;
	}

	public Heroes getHero() {
		return hero;
	}

	public String getName() {
		return name;
	}
}
