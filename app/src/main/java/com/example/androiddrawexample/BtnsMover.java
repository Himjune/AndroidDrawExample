package com.example.androiddrawexample;

import android.graphics.Matrix;
import android.widget.Button;

public class BtnsMover {
    private float[] btnPos = new float[] {50, 50};
    private Button btn;

    public BtnsMover (Button btnToControl) {
        btn = btnToControl;
    }

    public void reDraw() {
        Matrix m = ScreenState.getMatrix();

        float[] tPos = new float[2];
        m.mapPoints(tPos, btnPos);

        btn.setX(tPos[0]);
        btn.setY(tPos[1]);
    }
}
