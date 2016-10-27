package platformmovements;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ScrPlatform implements Screen, InputProcessor {

    Game game;
    SpriteBatch batch;
    Texture txDino, txPlat;
    SprDino sprDino;
    SprPlatform sprPlatform, sprPlatform2;
    int nScreenWid = Gdx.graphics.getWidth(), nDinoHei, nScreenX;
    Sprite sprBack, sprBack2;
    float fScreenWidth = Gdx.graphics.getWidth(), fScreenHei = Gdx.graphics.getHeight();
    private float fVy;
    private float fVx;
    Vector2 vBackDir, vBackPos;
    OrthographicCamera camBack, camBack2;

    public ScrPlatform(Game _game) {
        game = _game;
        batch = new SpriteBatch();
        txDino = new Texture("Dinosaur.png");
        txPlat = new Texture("Platform.png");
        sprBack = new Sprite(new Texture(Gdx.files.internal("world.jpg")));
        sprBack.setSize(fScreenWidth, fScreenHei);
        //float aspectratio = (float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
        Gdx.input.setInputProcessor((this));
        Gdx.graphics.setDisplayMode(800, 500, false);
        camBack = new OrthographicCamera(fScreenWidth /** aspectratio*/, fScreenHei);
        camBack.position.set(fScreenWidth / 2, fScreenHei / 2, 0);
        sprDino = new SprDino("Dinosaur.png", 0, 0);
        sprPlatform = new SprPlatform("Platform.png", 0, 0);
        sprPlatform2 = new SprPlatform("Platform.png", 0, 0);
        vBackDir = new Vector2(2f, 0f);
        vBackPos = new Vector2(0f, 0f);
        nDinoHei = txDino.getHeight();
        sprPlatform.setX(nScreenWid);
        sprPlatform2.setX(nScreenWid);
        sprPlatform.setY(nDinoHei);
        sprPlatform2.setY(nDinoHei);
    }

    @Override
    public void show() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(1, 0, 1, 1);
        sprDino.update(fVx, fVy);
        sprPlatform.update(sprDino.getX());
        sprPlatform2.update(sprDino.getX());
        camBack.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if ((nScreenX < -Gdx.graphics.getWidth() || nScreenX > Gdx.graphics.getWidth())) {
            nScreenX = 0;
        }
        batch.setProjectionMatrix(camBack.combined);
        batch.draw(sprBack, vBackPos.x, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(sprBack, vBackPos.x - Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(sprBack, vBackPos.x + Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(sprDino.getSprite(), sprDino.getX(), sprDino.getY());
        batch.draw(sprPlatform.getSprite(), sprPlatform.getX(), sprPlatform.getY());
        batch.draw(sprPlatform2.getSprite(), sprPlatform.getX(), sprPlatform.getY());
        batch.end();
        //if (sprDino.getX() > (fScreenWidth/2)) {
            if (sprDino.getX() <= fScreenWidth&&sprDino.getX() >= (fScreenWidth/4)) {
                //nScreenX -= fVx;
                vBackPos.add(vBackDir);
                //camBack.translate(vBackDir);
                //nScreenX += 2;
                System.out.println("Im Here");
                //use camera.translate

            } /*else if (sprBack.getX() < 0) {
             fVx = 0;
             nScreenX = 0;
             }*/
            //nScreenX -= fVx;
        //}


        
    }

    @Override
    public void resize(int i, int i1) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
        sprBack.getTexture().dispose();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.E) {
            System.exit(3);
        } else if (keycode == Input.Keys.UP) {
            fVy = 2;
            
        } else if (keycode == Input.Keys.DOWN) {
            fVy = -2;
        } else if (keycode == Input.Keys.LEFT) {
            fVx = -2;
            vBackDir.set(2, 0);
        } else if (keycode == Input.Keys.RIGHT) {
            fVx = 2;
            vBackDir.set(-2, 0);
        }
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.UP) {
            fVy = 0;            
        } else if (keycode == Input.Keys.DOWN) {
            fVy = 0;
        } else if (keycode == Input.Keys.LEFT) {
            fVx = 0;
        } else if (keycode == Input.Keys.RIGHT) {
            fVx = 0;
        }
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean scrolled(int i) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
