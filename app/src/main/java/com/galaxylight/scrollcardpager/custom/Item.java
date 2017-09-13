package com.galaxylight.scrollcardpager.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

/**
 * Item
 * Created by gzh on 2017-9-13.
 */

public class Item<T extends Serializable> extends BaseItem<T> {
    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        ItemController<T> controller = (ItemController<T>) bundle.getSerializable(ITEM_CONTROLLER);
        int position = bundle.getInt(ITEM_POSITION);
        T data = (T) bundle.getSerializable(ITEM_DATA);
        if (controller == null) {
            throw new RuntimeException("controller is not init !");
        }
        return controller.setLogic(context, data, position, mode);
    }

    /**
     * 初始化数据
     * @param data 数据
     * @param position 位置
     */
    public void initData(T data, int position) {
        Bundle bundle = getArguments();
        if (bundle == null) {
            bundle = new Bundle();
            setArguments(bundle);
        }
        bundle.putInt(ITEM_POSITION, position);
        bundle.putSerializable(ITEM_DATA, data);
    }
}
