package com.galaxylight.scrollcardpager.custom;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Item基类
 * {@link Fragment}
 * Created by gzh on 2017-9-13.
 */

public class BaseItem<T> extends Fragment {
    protected Context context;
    protected ItemController<T> controller;//Item控制器

    @ScrollCardPager.Mode
    int mode;//效果模式

    /**
     * 初始化Context.
     * {@link Fragment#onAttach(Context)}
     *
     * @param context context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    /**
     * 初始化控制器
     * {@link ItemController<T>}
     *
     * @param controller 控制器
     */
    public void initController(ItemController<T> controller) {
        this.controller = controller;
    }
}
