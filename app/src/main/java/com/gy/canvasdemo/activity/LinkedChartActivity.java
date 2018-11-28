package com.gy.canvasdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gy.canvasdemo.analyze.gold12.ZRandom;
import com.gy.canvasdemo.view.LinkedView;
import com.gy.canvasdemo.view.other.OnCtrlClickListener;

import java.util.Arrays;

public class LinkedChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinkedView<String> view = new LinkedView<>(this);

        view.setOnCtrlClickListener(new OnCtrlClickListener<LinkedView<String>>() {
            @Override
            public void onAdd(LinkedView<String> view) {
//                view.addData(ZRandom.randomOf3Name());
                view.addData(ZRandom.randomCnName());
            }

            @Override
            public void onAddByIndex(LinkedView<String> view) {
                view.addDataById(view.getSelectIndex(), ZRandom.randomCnName());
            }

            @Override
            public void onRemove(LinkedView<String> view) {
                view.removeData();
            }


            @Override
            public void onRemoveByIndex(LinkedView<String> view) {
                view.removeData(view.getSelectIndex());
            }

            @Override
            public void onSet(LinkedView<String> view) {
                view.setData(view.getSelectIndex(), ZRandom.randomCnName());
            }

            @Override
            public void onFind(LinkedView<String> view) {
                String data = view.findData(view.getSelectIndex());
                Toast.makeText(LinkedChartActivity.this, data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFindByData(LinkedView<String> view) {
                int[] data = view.findData(view.getSelectData());
                Toast.makeText(LinkedChartActivity.this, Arrays.toString(data), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClear(LinkedView<String> view) {
                view.clearData();
            }



        });

        setContentView(view);

    }
}
