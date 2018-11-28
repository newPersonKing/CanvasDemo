package com.gy.canvasdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gy.canvasdemo.analyze.gold12.ZRandom;
import com.gy.canvasdemo.view.SingleLinkedView;
import com.gy.canvasdemo.view.other.OnCtrlClickListener;

import java.util.Arrays;

public class SingleLinkedChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SingleLinkedView<String> view = new SingleLinkedView<>(this);


        view.setOnCtrlClickListener(new OnCtrlClickListener<SingleLinkedView<String>>() {
            @Override
            public void onAdd(SingleLinkedView<String> view) {
//                view.addData(ZRandom.randomOf3Name());
                view.addData(ZRandom.randomCnName());
            }

            @Override
            public void onAddByIndex(SingleLinkedView<String> view) {
                view.addDataById(view.getSelectIndex(), ZRandom.randomCnName());
            }

            @Override
            public void onRemove(SingleLinkedView<String> view) {
                view.removeData();
            }


            @Override
            public void onRemoveByIndex(SingleLinkedView<String> view) {
                view.removeData(view.getSelectIndex());
            }

            @Override
            public void onSet(SingleLinkedView<String> view) {
                view.setData(view.getSelectIndex(), ZRandom.randomCnName());
            }

            @Override
            public void onFind(SingleLinkedView<String> view) {
                String data = view.findData(view.getSelectIndex());
                Toast.makeText(SingleLinkedChartActivity.this, data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFindByData(SingleLinkedView<String> view) {
                int[] data = view.findData(view.getSelectData());
                Toast.makeText(SingleLinkedChartActivity.this, Arrays.toString(data), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClear(SingleLinkedView<String> view) {
                view.clearData();
            }


        });

        setContentView(view);

    }
}
