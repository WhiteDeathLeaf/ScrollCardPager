package com.galaxylight.scrollcardpager.custom;

import android.content.Context;
import android.view.View;

import java.io.Serializable;

/**
 * Item控制器（处理UI逻辑）
 * Created by gzh on 2017-9-13.
 */

public interface ItemController<T> extends Serializable {
    //处理UI逻辑
    View setLogic(Context context, T data, int position, @ScrollCardPager.Mode int mode);
}
