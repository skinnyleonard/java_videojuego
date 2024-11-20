package io.github.megamen2.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.FrictionJointDef;
import com.badlogic.gdx.utils.Array;

import io.github.megamen2.Main;
import io.github.megamen2.screens.PlayScreen;

public class Men extends Sprite{
	public enum State{ FALLING, JUMPING, STANDING, RUNNING, DEAD };
	public State currentState;
	public State previousState;
	public World world;
	public Body b2body;
	private InteractiveTileObject tiles;
	private TextureRegion menStand;
	private Animation menRun;
	private Animation menJump;
	private float stateTimer;
	private float ultPos;
	private boolean runningRight; 
	private boolean menIsDead;
	
	public Men(World world, PlayScreen screen) {
		super(screen.getAtlas().findRegion("corre0"));
		this.world = world;
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
		runningRight = true;
		
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for(int i=1; i<4; i++)
			frames.add(new TextureRegion(getTexture(), i*32, 0, 32, 32));

		menRun = new Animation(0.1f, frames);
		frames.clear();
		
		for(int i=5; i<6; i++)
			frames.add(new TextureRegion(getTexture(), i*32, 0, 32, 32));
		
		menJump = new Animation(0.1f, frames);
		
		menStand = new TextureRegion(getTexture(), 0, 0, 32, 32);
		
		defineMen();
		setBounds(0, 0, 64/Main.PPM, 64/Main.PPM);
		setRegion(menStand);
	}
	
	public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
        
    }
	
	
	public TextureRegion getFrame(float dt) {
		currentState = getState();
		
		TextureRegion region;
		switch(currentState) {
//		case DEAD:
//			
//			break;
		case JUMPING:
			region = (TextureRegion) menJump.getKeyFrame(stateTimer);
			break;
		case RUNNING:
			region = (TextureRegion) menRun.getKeyFrame(stateTimer, true);
			break;
		case FALLING:
		case STANDING:
		default:
			region = menStand;
			break;
		}
		if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
			region.flip(true, false);
			runningRight = false;
		}
		else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
			region.flip(true, false);
			runningRight = true;
		}
		
		stateTimer = currentState == previousState ? stateTimer + dt : 0;
		previousState = currentState;
		return region;
	}
	
	public State getState() {
		if(menIsDead)
			return State.DEAD;
		else if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
			return State.JUMPING;
		else if(b2body.getLinearVelocity().y < 0)
			return State.FALLING;
		else if(b2body.getLinearVelocity().x != 0)
			return State.RUNNING;
		else
			return State.STANDING;
	}
	
	public void defineMen() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(1024/Main.PPM, 128/Main.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(16/Main.PPM, 30/Main.PPM);
		fdef.filter.categoryBits = Main.MEN_BIT;
		fdef.filter.maskBits = Main.DEFAULT_BIT | Main.BALLON_BIT;
		
//		fdef.friction=10;
		fdef.shape = shape;
		
		b2body.createFixture(fdef).setUserData("head");
		
		EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/Main.PPM, 7/Main.PPM), new Vector2(2/Main.PPM, 7/Main.PPM));
        fdef.shape = head;
        fdef.isSensor= true;
        
        b2body.createFixture(fdef).setUserData("head");
	}
	
}
