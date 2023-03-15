package entities;

import static utilities.Constants.PATH_WARRIOR_LIST;
import static utilities.Constants.PlayerConstants.DOWN;
import static utilities.Constants.PlayerConstants.IDLE;
import static utilities.Constants.PlayerConstants.JUMP;
import static utilities.Constants.PlayerConstants.RUNNING_LEFT;
import static utilities.Constants.PlayerConstants.RUNNING_RIGHT;
import static utilities.Helpers.canMove;
import static utilities.Helpers.getAnimations;
import static utilities.Helpers.isSolid;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import main.Game;

public class Player extends Entity {
	private BufferedImage[][] animations = new BufferedImage[PATH_WARRIOR_LIST.length][];
	private int aniTick;
	private double aniIndex;
	private int aniSpeed = 10;
	private int playerAction = JUMP;
	private boolean moving = false;
	private boolean left;
	private boolean jump;
	private boolean right;
	private boolean fall;
	private float runningSpeed = 2.0f;

	// Gravity
	private float airSpeed = 0f;
	private float gravity = 0.04f * Game.SCALE;
	private float jumpingSpeed = -2.00f * Game.SCALE;
	private float fallSpeed = 0.5f * Game.SCALE;
	private boolean inAir = false;

	private int[][] lvlData;

	public Player(float x, float y, int width, int height, int[][] lvlData) {
		super(x, y, width, height);
		this.lvlData = lvlData;
		loadAnimations();
		initHitBox(x, y, width, height);
	}

	public void update() {
		updatePosition();
		updateAnimationTick();
		setAnimation();
	}

	public void render(Graphics g) {
		g.drawImage(animations[playerAction][(int) aniIndex], (int) (hitBox.x), (int) (hitBox.y),
				width, height, null);

		if (Game.DEBUGING) {
			showHitBox(g);
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

			if (aniIndex >= animations[playerAction].length) {
				aniIndex = 0;
			}

		}
	}

	private void setAnimation() {
		int startAni = playerAction;

		if (moving) {
			if (left)
				playerAction = RUNNING_LEFT;
			else if (right)
				playerAction = RUNNING_RIGHT;
			else if (jump)
				playerAction = JUMP;
			else
				playerAction = DOWN;

		} else {
			playerAction = IDLE;
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

		if (jump) {
			jump();
		}

		if (!left && !right && !inAir)
			return;

		float xSpeed = 0;

		if (left && !right) {
			xSpeed = -runningSpeed;
		} else if (right && !left) {
			xSpeed = runningSpeed;
		}

		if (inAir) {
			inAir();
		} else {
			if (!isInFloor()) {
				inAir = true;
			}
		}

		if (canMove(this, hitBox.x + xSpeed, hitBox.y, lvlData)) {
			hitBox.x += xSpeed;
		}

		moving = true;

	}

	private boolean isInFloor() {
		if (!isSolid(hitBox.x, hitBox.y + hitBox.height + 1, lvlData)
				&& (!isSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 1, lvlData))) {
			return false;
		}

		return true;
	}

	private void inAir() {
		if (canMove(this, hitBox.x, hitBox.y + airSpeed, lvlData)) {
			hitBox.y += airSpeed;
			airSpeed += gravity;
			if (airSpeed > 0) {
				setFall(true);
			}
		} else {
			hitBox.y = getEntityY(hitBox, airSpeed);

			if (airSpeed > 0) {
				inAir = false;
				airSpeed = 0;
				setFall(true);
			} else {
				airSpeed = fallSpeed;
			}
		}
	}

	public static float getEntityY(Rectangle2D.Float hitbox, float airSpeed) {
		int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
		if (airSpeed > 0) {
			return hitbox.y;
		}

		// Jumping
		return (currentTile * Game.TILES_SIZE);
	}

	private void jump() {
		if (inAir)
			return;
		inAir = true;
		airSpeed = jumpingSpeed;
	}

	private void loadAnimations() {
		for (int i = 0; i < animations.length; i++) {
			animations[i] = getAnimations(PATH_WARRIOR_LIST[i]);
		}
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isJump() {
		return jump;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
		// if (!this.jump) this.jump = jump;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isFall() {
		return fall;
	}

	public void setFall(boolean fall) {
		this.fall = fall;
	}

	public void resetDirection() {
		left = false;
		right = false;
		fall = false;
		jump = false;
		moving = false;
	}

	public int getPlayerAction() {
		return playerAction;
	}
}
