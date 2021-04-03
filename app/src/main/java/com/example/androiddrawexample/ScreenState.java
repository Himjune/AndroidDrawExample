// Класс для хранения состояния экрана, а именно проделаных преобразований.
// Здесь происходит некоторая ООП магия, о которой можно посмотреть например здесь:
// http://developer.alexanderklimov.ru/android/java/singleton.php
// Основная задача трюка - чтобы в любой части программы у нас был доступ к одному
// и тому же объекту NSTANCE с одной общей матрицей преобразований mtx
// Это как глобальная переменная, только круче и безопаснее.
// Следите в других частях программы, как я обращаюсь к ScreenState

package com.example.androiddrawexample;

import android.graphics.Matrix;

public class ScreenState {
    private static final ScreenState INSTANCE = new ScreenState();

    private Matrix mtx = new Matrix();

    private ScreenState(){
        mtx.reset();
    }

    public static void move(double dx, double dy) {
        INSTANCE.mtx.preTranslate((float)dx, (float)dy);
    }

    public static void scale(double s) {
        INSTANCE.mtx.preScale((float)s, (float)s);
    }

    public static void rotate(double deg) {
        INSTANCE.mtx.preRotate((float) deg);
    }

    public static Matrix getMatrix() {
        return INSTANCE.mtx;
    }

    public static ScreenState getInstance(){
        return INSTANCE;
    }
}
