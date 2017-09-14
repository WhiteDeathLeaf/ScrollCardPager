package com.galaxylight.scrollcardpager.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Item
 * {@link BaseItem<T>}
 * Created by gzh on 2017-9-13.
 */

public class Item<T> extends BaseItem<T> {
    private T data;
    private int position;

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (controller == null) {
            //ItemController未初始化
            throw new RuntimeException("ItemController is not init");
        }
        View view = controller.setLogic(context, data, position, mode);
        if (!(view.getRootView() instanceof ImageView)) {
            //ItemView只支持ImageView
            throw new RuntimeException("The ItemView only support ImageView");
        }
        return view;
    }

    /**
     * 初始化数据
     *
     * @param data     数据（适配器所需的数据{@link ScrollCardPagerAdapter#items}）
     * @param position 位置（数据再适配器集合中的下标）
     */
    public void initData(T data, int position) {
        this.data = data;
        this.position = position;
    }
}
