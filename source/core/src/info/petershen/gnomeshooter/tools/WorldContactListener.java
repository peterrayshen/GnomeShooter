package info.petershen.gnomeshooter.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import info.petershen.gnomeshooter.screens.PlayScreen;
import info.petershen.gnomeshooter.sprites.Bullet;
import info.petershen.gnomeshooter.sprites.Enemy;
import info.petershen.gnomeshooter.sprites.Player;

public class WorldContactListener implements ContactListener {

	private PlayScreen screen;

	public WorldContactListener(PlayScreen screen) {
		this.screen = screen;
	}

	@Override
	public void beginContact(Contact contact) {

		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();

		if (fixA.getUserData() instanceof Bullet && fixB.getUserData() instanceof Enemy) {

			((Enemy) fixB.getUserData()).hit(((Bullet) fixA.getUserData()).damage);
			screen.money += ((Bullet) fixA.getUserData()).damage / 2;
			((Bullet) fixA.getUserData()).health--;
			((Bullet) fixA.getUserData()).damage = ((Bullet) fixA.getUserData()).damage / 2;

		}
		if (fixB.getUserData() instanceof Bullet && fixA.getUserData() instanceof Enemy) {

			((Enemy) fixA.getUserData()).hit(((Bullet) fixB.getUserData()).damage);
			screen.money += ((Bullet) fixB.getUserData()).damage / 2;
			((Bullet) fixB.getUserData()).health--;
			((Bullet) fixB.getUserData()).damage = ((Bullet) fixB.getUserData()).damage / 2;
		}

		if (fixB.getUserData() instanceof Player && fixA.getUserData() instanceof Enemy) {

			((Player) fixB.getUserData()).touchingEnemy = true;
			((Player) fixB.getUserData()).enemiesTouching++;

		}

		if (fixA.getUserData() instanceof Player && fixB.getUserData() instanceof Enemy) {

			((Player) fixA.getUserData()).touchingEnemy = true;
			((Player) fixA.getUserData()).enemiesTouching++;
		}

		if (fixB.getUserData() instanceof Player && fixA.getUserData() == "ground") {

			((Player) fixB.getUserData()).touchingGround = true;
		}

		if (fixA.getUserData() instanceof Player && fixB.getUserData() == "ground") {

			((Player) fixA.getUserData()).touchingGround = true;
		}

		if (fixB.getUserData() instanceof Bullet && fixA.getUserData() == "ground") {

			((Bullet) fixB.getUserData()).remove = true;

		}

		if (fixA.getUserData() instanceof Bullet && fixB.getUserData() == "ground") {

			((Bullet) fixA.getUserData()).remove = true;
		}

		

	}

	@Override
	public void endContact(Contact contact) {

		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();

		if (fixB.getUserData() instanceof Player && fixA.getUserData() instanceof Enemy) {

			((Player) fixB.getUserData()).touchingEnemy = false;
			((Player) fixB.getUserData()).enemiesTouching--;
		}

		if (fixA.getUserData() instanceof Player && fixB.getUserData() instanceof Enemy) {

			((Player) fixA.getUserData()).touchingEnemy = false;
			((Player) fixA.getUserData()).enemiesTouching--;
		}

		if (fixB.getUserData() instanceof Player && fixA.getUserData() == "ground") {

			((Player) fixB.getUserData()).touchingGround = false;
			((Player) fixB.getUserData()).timesJumped = 0;
		}

		if (fixA.getUserData() instanceof Player && fixB.getUserData() == "ground") {

			((Player) fixA.getUserData()).touchingGround = false;
			((Player) fixA.getUserData()).timesJumped = 0;
		}
	}
	// TODO Auto-generated method stub

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();

		if (fixB.getUserData() instanceof Bullet && fixA.getUserData() instanceof Enemy) {

			contact.setEnabled(false);

		}

		if (fixB.getUserData() instanceof Enemy && fixA.getUserData() instanceof Bullet) {

			contact.setEnabled(false);

		}

		if (fixB.getUserData() instanceof Player && fixA.getUserData() instanceof Enemy) {

			contact.setEnabled(false);
		}

		if (fixA.getUserData() instanceof Player && fixB.getUserData() instanceof Enemy) {

			contact.setEnabled(false);
		}

		if (fixB.getUserData() instanceof Enemy && fixA.getUserData() == "boundary") {

			contact.setEnabled(false);

		}

		if (fixA.getUserData() instanceof Enemy && fixB.getUserData() == "boundary") {

			contact.setEnabled(false);
		}

		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
