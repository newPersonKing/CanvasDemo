package com.gy.canvasdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gy.canvasdemo.analyze.gold12.ZRandom;
import com.gy.canvasdemo.view.StackView;
import com.gy.canvasdemo.view.other.CtrlClickAdapter;


public class StackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StackView<String> view = new StackView<>(this);

        view.setOnCtrlClickListener(new CtrlClickAdapter<StackView<String>>() {
            @Override
            public void onAdd(StackView<String> view) {
//                view.enqueue(ZRandom.randomCnName());
                view.push(ZRandom.rangeChar(ZRandom.KUO_HAO));
            }

            @Override
            public void onRemove(StackView<String> view) {
                view.pop();
            }

            @Override
            public void onFind(StackView<String> view) {
                String data = view.peek();
                Toast.makeText(StackActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });


        setContentView(view);

    }
}
