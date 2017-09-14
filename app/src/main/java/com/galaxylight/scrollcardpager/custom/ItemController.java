package com.galaxylight.scrollcardpager.custom;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Item控制器（处理UI逻辑）
 * Implements this ItemController<T> for set UILogic
 * Created by gzh on 2017-9-13.
 */

public interface ItemController<T> {
    /**
     * 处理UI逻辑
     *
     * @param context  context
     * @param data     数据（适配器所需的数据{@link ScrollCardPagerAdapter#items}）
     * @param position 位置（数据再适配器集合中的下标）
     * @param mode     显示的效果模式{@link ScrollCardPager.Mode}
     * @return {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}Item子视图
     */
    View setLogic(Context context, T data, int position, @ScrollCardPager.Mode int mode);
}
