package info.petershen.gnomeshooter.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import com.badlogic.gdx.physics.box2d.World;

import info.petershen.gnomeshooter.GnomeShooter;
import info.petershen.gnomeshooter.scenes.Hud;
import info.petershen.gnomeshooter.sprites.Ak;
import info.petershen.gnomeshooter.sprites.Arm;
import info.petershen.gnomeshooter.sprites.Barret;
import info.petershen.gnomeshooter.sprites.Bullet;
import info.petershen.gnomeshooter.sprites.Enemy;
import info.petershen.gnomeshooter.sprites.MachineGun;
import info.petershen.gnomeshooter.sprites.Pistol;
import info.petershen.gnomeshooter.sprites.Player;
import info.petershen.gnomeshooter.sprites.Revolver;
import info.petershen.gnomeshooter.sprites.Shotgun;
import info.petershen.gnomeshooter.sprites.Smg;
import info.petershen.gnomeshooter.sprites.WeaponBase;
import info.petershen.gnomeshooter.tools.B2WorldCreator;
import info.petershen.gnomeshooter.tools.RoundController;
import info.petershen.gnomeshooter.tools.WorldContactListener;

public class PlayScreen implements Screen {
	
	public ShopScreen shopScreen;

	public static float gravity = -45f;
	public static float jumpForce = 45f;
	public static float leftMoveForce = -3.5f;
	public static float rightMoveForce = 3.5f;
	
	private B2WorldCreator b2world;

	public GnomeShooter game;
	private OrthographicCamera gamecam;
	private Hud hud;

	private ShapeRenderer sr;

	private World world;
	private Box2DDebugRenderer b2dr;

	public Player player;
	public Enemy enemy;
	public Arm arm;

	public Vector3 mouse;
	public ArrayList<Bullet> bullets;
	public ArrayList<Enemy> enemies;
	public RoundController roundController;

	public int weaponIndex = 0;
	
	public int money;

	public WeaponBase currentWeapon, pistol, smg, ak, machine, revolver, barret, shotgun;

	public ArrayList<WeaponBase> weaponList;
		
	private Box2DDebugRenderer debugRenderer;
	

	public final short GROUP_PLAYER = -1;

	public PlayScreen(GnomeShooter game) {

		debugRenderer = new Box2DDebugRenderer();
		
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		this.game = game;
		gamecam = new OrthographicCamera();
		gamecam.setToOrtho(false, 600 / GnomeShooter.PPM, 350 / GnomeShooter.PPM);
		gamecam.translate(0, 16 / GnomeShooter.PPM);

		sr = new ShapeRenderer();
		
		world = new World(new Vector2(0, gravity), true);
		b2dr = new Box2DDebugRenderer();

		player = new Player(world, this);
		arm = new Arm(world, this);
		
		pistol = new Pistol(world, this);
		smg = new Smg(world, this);
		ak = new Ak(world, this);
		machine = new MachineGun(world, this);
		revolver = new Revolver(world, this);
		barret = new Barret(world, this);
		shotgun = new Shotgun(world, this);
		
		
		weaponList = new ArrayList<WeaponBase>();
		weaponList.add(pistol);
		weaponList.add(smg);
		weaponList.add(ak);
		weaponList.add(machine);
		weaponList.add(revolver);
		weaponList.add(barret);
		weaponList.add(shotgun);
		
		currentWeapon = weaponList.get(weaponIndex);
		
		b2world = new B2WorldCreator(world, this);

		world.setContactListener(new WorldContactListener(this));
		
		roundController = new RoundController(world, this, game);
		roundController.startRound();
		
		hud = new Hud(game.batch, this);
	}

	@Override
	public void show() {

	}

	public void handleInput(float delta) {

		if(Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
			game.setScreen(game.shopScreen);
			Gdx.input.setInputProcessor(game.shopScreen.stage);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)
				&& ((player.touchingEnemy || player.touchingGround) || player.timesJumped < 2)) {
			player.b2body.applyLinearImpulse(new Vector2(0, jumpForce), player.b2body.getWorldCenter(), true);
			player.timesJumped++;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A))
			player.b2body.applyLinearImpulse(new Vector2(leftMoveForce, 0), player.b2body.getWorldCenter(), true);
		if (Gdx.input.isKeyPressed(Input.Keys.D))
			player.b2body.applyLinearImpulse(new Vector2(rightMoveForce, 0), player.b2body.getWorldCenter(), true);
		
		if (Gdx.input.isTouched() && currentWeapon.isAuto)
			currentWeapon.shoot();
		else if (Gdx.input.justTouched() && !currentWeapon.isAuto)
			currentWeapon.shoot();
		if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
			currentWeapon.reload();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
			
			weaponIndex++;
			if (weaponIndex == weaponList.size())
				weaponIndex = 0;
			currentWeapon = weaponList.get(weaponIndex);
		}

		mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		gamecam.unproject(mouse);

	}

	public void update(float delta) {

	
		handleInput(delta);


		gamecam.position.x = player.b2body.getPosition().x;
		gamecam.update();

		player.update(delta);
		arm.update(delta);
		currentWeapon.update(delta);

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update(delta);
			if (bullets.get(i).shouldRemove()) {
				bullets.get(i).world.destroyBody(bullets.get(i).b2body);
				bullets.remove(i);

				i--;
			}
		}

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update(delta);
			if (enemies.get(i).shouldRemove()) {
				enemies.get(i).world.destroyBody(enemies.get(i).b2body);
				money += enemies.get(i).reward;
				enemies.remove(i);
				

				i--;
			}
		}
		
		roundController.roundUpdate(delta, enemies.size() == 0);

	}

	

	@Override
	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(135/255f, 206/255f, 250/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	

		game.batch.setProjectionMatrix(gamecam.combined);

		

		game.batch.begin();
		for (int i = 0; i < b2world.ground.size(); i++) {
			b2world.ground.get(i).draw(game.batch);
		}
		player.draw(game.batch);
		arm.draw(game.batch);
		currentWeapon.draw(game.batch);
		if (currentWeapon.isShooting)
			currentWeapon.flash.draw(game.batch);

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(game.batch);
			enemies.get(i).setColor(Color.WHITE);
		}
		game.batch.end();
		
		sr.setProjectionMatrix(gamecam.combined);
		
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).drawBar(sr);
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).drawBullet(sr);
		}

		
		
		//debugRenderer.render(world, gamecam.combined);

		currentWeapon.reset();

		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

		hud.stage.draw();
		hud.update(delta);
	 
		checkIfLost();	

		world.step(1 / 120f, 6, 2);
		

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() { 
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {

		world.dispose();
		b2dr.dispose();
		hud.dispose();
		sr.dispose();
		// TODO Auto-generated method stub

	}
	
	public void openShop() {
		game.setScreen(game.shopScreen);
	}
	
	public void checkIfLost() {
		if (player.health <- 0) {
			game.setScreen(game.loseScreen);
		}
			
	}
	
	public void restartGame() {
		player.reset();
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).world.destroyBody(bullets.get(i).b2body);
			bullets.remove(i);
		}
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).world.destroyBody(enemies.get(i).b2body);
			enemies.remove(i);
		}
		
		roundController.reset();
		
		for (int i = 0; i < weaponList.size(); i++) {
			weaponList.get(i).resetGame();
		}
		
	}

}
