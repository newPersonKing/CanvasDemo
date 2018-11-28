package com.gy.canvasdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.gy.canvasdemo.analyze.gold12.ZRandom;
import com.gy.canvasdemo.view.ArrayView;
import com.gy.canvasdemo.view.other.OnCtrlClickListener;

import java.util.Arrays;

public class ArrayChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayView<String>  view = new ArrayView<String>(this);

        view.setOnCtrlClickListener(new OnCtrlClickListener<ArrayView<String>>() {
            @Override
            public void onAdd(ArrayView<String> view) {
                Log.i("cccccccccc","onadd");
                view.addData(ZRandom.randomCnName());
            }

            @Override
            public void onAddByIndex(ArrayView<String> view) {
                 view.addDataById(view.getSelectIndex(),ZRandom.randomCnName());
            }

            @Override
            public void onRemove(ArrayView<String> view) {
                view.removeData();
            }

            @Override
            public void onRemoveByIndex(ArrayView<String> view) {
                 view.removeData(view.getSelectIndex());
            }

            @Override
            public void onSet(ArrayView<String> view) {
               view.setData(view.getSelectIndex(),ZRandom.randomCnName());
            }

            @Override
            public void onFind(ArrayView<String> view) {
                String data = view.findData(view.getSelectIndex());
                Toast.makeText(ArrayChartActivity.this, data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFindByData(ArrayView<String> view) {
                int[] data = view.findData(view.getSelectData());
                Toast.makeText(ArrayChartActivity.this, Arrays.toString(data), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClear(ArrayView<String> view) {
                view.clearData();
            }
        });
        setContentView(view);
    }
}
