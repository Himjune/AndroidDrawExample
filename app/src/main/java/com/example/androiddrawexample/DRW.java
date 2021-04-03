// Рукописный View для рисования отрезков

package com.example.androiddrawexample;

import android.graphics.Matrix;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Canvas;

import com.example.androiddrawexample.ScreenState;

public class DRW extends View {
    private Paint paint;
    private float[] initialPoints;

    public DRW(Context context, AttributeSet attrs) {
        super(context, attrs);
                                //   х0  у0   х1  у1   х2   у2  х3  у3
        initialPoints = new float[] {10, 10, 100, 10, 100, 100, 50, 50}; // 8 coordinates, 4 points, 3 lines

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(5);
        paint.setColor(0xffaa7070);
    }

    // В идеальном случае следовало бы завести событие, условный сигнал об изменении ScreenState,
    // по которому все заинтересованные элеементы перерисовывали себя, но я решил не перегружать
    // вас разбираться в этом механизме. Если интересно: https://guides.codepath.com/android/Creating-Custom-Listeners
    // В нашем случае элементов не так много, максимум 2 (только рисовальщик и быть может двигатель кнопок)
    // В них можно завести функции, которые вызывают пересборку элемента. В данном случае это именно перерисовка,
    // которая иницируется командами invalidate() и requestLayout()
    public void reDraw() {
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Берём матрицу преобразований из глобального объекта
        Matrix m = ScreenState.getMatrix();

        // Заготавливаем массив координат для преобразованных точек
        float[] transformedPoints = new float[8];

        // Применяем матрицу к точкам из initialPoints, а результат запишется в transformedPoints
        m.mapPoints(transformedPoints, initialPoints);

        // проходимся по всем отрезка: (p0, p1), (p1, p2), (p2, p3)
        for (int i = 0; i < 3; i = i + 1) {
            canvas.drawLine(transformedPoints[i*2], transformedPoints[i*2+1],
                            transformedPoints[(i+1)*2], transformedPoints[(i+1)*2+1], paint);
        }
    }
}
