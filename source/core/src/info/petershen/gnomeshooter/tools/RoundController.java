package info.petershen.gnomeshooter.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;

import info.petershen.gnomeshooter.GnomeShooter;
import info.petershen.gnomeshooter.screens.PlayScreen;
import info.petershen.gnomeshooter.sprites.Enemy;

public class RoundController {

	public int round;
	private PlayScreen screen;
	private World world;
	private int enemyCount;
	private int enemiesSpawned;
	private float runTime, minSpawnTime, maxSpawnTime, timeToSpawn, enemyHealth, enemySpeed, enemyTurnSpeed;
	private boolean pickedTime = false;
	private boolean inRound = true;
	private float spawnX;
	private GnomeShooter game;

	public RoundController(World world, PlayScreen screen, GnomeShooter game) {
		this.game = game;
		round = 0;
		this.world = world;
		this.screen = screen;
	}

	public void startRound() {
		Gdx.input.setInputProcessor(null);
		enemyCount = 5 + round * 4;
		minSpawnTime = 2 - round * 0.3f;
		maxSpawnTime = 5 - round * 0.3f;
		enemyHealth = (float) Math.pow(round, 2 ) + 100f;
		enemySpeed = 1f + 0.2f * round;
		enemyTurnSpeed = 1.5f - 0.1f * round;
		enemiesSpawned = 0;
		inRound = true;
		round++;
	}

	public void roundUpdate(float delta, boolean allKilled) {

		runTime += delta;

		if (inRound) {

			if (enemiesSpawned < enemyCount) {

				if (!pickedTime) {

					timeToSpawn = MathUtils.random(minSpawnTime, maxSpawnTime);
					pickedTime = true;
				}

				if (runTime > timeToSpawn) {
					spawnEnemy();
					enemiesSpawned++;
					runTime = 0;
					pickedTime = false;
				}

			} else if (allKilled)
				endRound();
		}
		
		

	}
	
	public void endRound() {
		inRound = false;
		screen.openShop();
		Gdx.input.setInputProcessor(game.shopScreen.stage);
	}

	public void spawnEnemy() {

		if (screen.player.b2body.getPosition().x < 10) {
			spawnX = MathUtils.random(17f, 19f);
			screen.enemies.add(new Enemy(screen, world, spawnX * GnomeShooter.PPM, 60, enemyHealth, enemyTurnSpeed, enemySpeed));
			System.out.println("spawned");
		}

		else {
			spawnX = MathUtils.random(2f, 3f);
			screen.enemies.add(new Enemy(screen, world, spawnX * GnomeShooter.PPM, 60, enemyHealth, enemyTurnSpeed, enemySpeed));
			System.out.println("spawned");
		} 

	}
	
	public void reset() {
		round = 0;
		runTime = 0;
		startRound();
	}
	
	

}
