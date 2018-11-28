package com.gy.canvasdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gy.canvasdemo.analyze.gold12.ZRandom;
import com.gy.canvasdemo.view.QueueView;
import com.gy.canvasdemo.view.other.CtrlClickAdapter;


public class QueueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QueueView<String> view = new QueueView<>(this);

        view.setOnCtrlClickListener(new CtrlClickAdapter<QueueView<String>>() {
            @Override
            public void onAdd(QueueView<String> view) {
                view.enqueue(ZRandom.randomCnName());
//                view.enqueue(ZRandom.rangeChar(ZRandom.KUO_HAO));
            }

            @Override
            public void onRemove(QueueView<String> view) {
                view.dequeue();
            }

            @Override
            public void onFind(QueueView<String> view) {
                String data = view.front();
                Toast.makeText(QueueActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });
        setContentView(view);
    }
}
