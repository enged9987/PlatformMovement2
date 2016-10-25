package platformmovements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprDino {

    private float fX, fY, fVx, fVy;
    String sFile;
    Texture txImg;
    private Sprite sprImg;

    public SprDino(String _sFile, float _fX, float _fY) {
        sFile = _sFile;
        fX = _fX;
        fY = _fY;
        txImg = new Texture(sFile);
        sprImg = new Sprite(txImg);
    }

    void update(float _fVx, float _fVy) {
        fVx = _fVx;
        fVy = _fVy;
        fX += fVx;
        fY += fVy;
    }

    public Sprite getSprite() {
        return sprImg;
    }

    //@Override
    public float getX() {
        return fX;
    }

    //@Override
    public float getY() {
        return fY;
    }
}
