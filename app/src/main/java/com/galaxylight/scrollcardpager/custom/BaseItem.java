package com.galaxylight.scrollcardpager.custom;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Item基类
 * Created by gzh on 2017-9-13.
 */

public class BaseItem<T> extends Fragment {
    protected static final String ITEM_CONTROLLER = "item_controller";
    protected static final String ITEM_POSITION = "item_position";
    protected static final String ITEM_DATA = "item_data";

    protected Context context;

    @ScrollCardPager.Mode
    int mode;//效果模式

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    //初始化控制器
    public void initController(ItemController<T> controller) {
        Bundle bundle = getArguments();
        if (bundle == null) {
            bundle = new Bundle();
            setArguments(bundle);
        }
        bundle.putSerializable(ITEM_CONTROLLER, controller);
    }
}
