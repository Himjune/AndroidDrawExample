package com.example.androiddrawexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import com.example.androiddrawexample.ScreenState;
import com.example.androiddrawexample.DRW;
import com.example.androiddrawexample.BtnsMover;

public class MainActivity extends AppCompatActivity {
    private BtnsMover btnsMover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button);

        // создёем обработчик передвижения кнопки, и передаём ему в прикрепление нашу кнопку
        btnsMover = new BtnsMover(btn);

        // Один раз на запуске проводим тестовые манипуляции, просто чтобы проверить что оно работает
        ScreenState.move(50, 50);
        ScreenState.scale((float)2);

        // Обработчик клика кнопки
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //ScreenState.move(10, 10);
                //ScreenState.scale((float)1.1);
                ScreenState.rotate(15);

                // Находим наш вьюв и заставляем его пререрисоваться
                DRW drw = findViewById(R.id.DRW);
                drw.reDraw();

                // аставляем подвигать кнопку
                btnsMover.reDraw();
            }
        });
    }


}