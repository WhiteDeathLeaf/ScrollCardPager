package com.galaxylight.scrollcardpager.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.galaxylight.scrollcardpager.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * ScrollCardPager
 * {@link ViewPager}
 * Created by gzh on 2017-9-13.
 */

public class ScrollCardPager extends ViewPager {
    private static final int COUNT = 6;
    public static final int MODE_NORMAL = 0;//普通效果模式
    public static final int MODE_CARD = 1;//卡片效果模式

    @IntDef({MODE_NORMAL, MODE_CARD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    private int offset;//偏移量
    private float scale;//缩放比例
    private boolean isLoop = false;//是否循环
    public boolean isNotify;//是否刷新

    //padding
    private int itemPaddingTop;
    private int itemPaddingBottom;
    private int itemPaddingLeft;
    private int itemPaddingRight;

    @Mode
    private int currentMode = MODE_CARD;//默认卡片效果

    private ItemTransformer itemTransformer;//滑动效果

    public ScrollCardPager(Context context) {
        this(context, null);
    }

    public ScrollCardPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setClipToPadding(false);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScrollCardPager);
        int padding = typedArray.getDimensionPixelOffset(R.styleable.ScrollCardPager_item_padding, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, metrics));
        itemPaddingTop = getPaddingTop();
        itemPaddingBottom = getPaddingBottom();
        itemPaddingLeft = getPaddingLeft();
        itemPaddingRight = getPaddingRight();
        setPadding(itemPaddingLeft + padding, itemPaddingTop, itemPaddingRight + padding, itemPaddingBottom);
        int margin = typedArray.getDimensionPixelOffset(R.styleable.ScrollCardPager_item_margin, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, metrics));
        setPageMargin(margin);
        offset = typedArray.getDimensionPixelOffset(R.styleable.ScrollCardPager_item_offset, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, metrics));
        isLoop = typedArray.getBoolean(R.styleable.ScrollCardPager_item_loop, isLoop);
        scale = typedArray.getFloat(R.styleable.ScrollCardPager_item_scale, 0.38f);
        typedArray.recycle();
    }

    /**
     * 设置滑动效果
     *
     * @param offset 偏移量
     * @param scale  缩放比例
     */
    public void setItemTransformer(float offset, float scale) {
        int itemOffset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, offset, getResources().getDisplayMetrics());
        itemTransformer = new ItemTransformer(itemOffset, scale);
        setPageTransformer(false, itemTransformer);
    }

    /**
     * 设置padding
     *
     * @param padding padding
     */
    public void setItemPadding(float padding) {
        int itemPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, padding, getResources().getDisplayMetrics());
        setPadding(itemPaddingLeft + itemPadding, itemPaddingTop, itemPaddingRight + itemPadding, itemPaddingBottom);
    }

    /**
     * 设置margin
     *
     * @param margin margin
     */
    public void setItemMargin(float margin) {
        int itemMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, margin, getResources().getDisplayMetrics());
        setPageMargin(itemMargin);
    }

    /**
     * 根据模式切换显示效果
     *
     * @param mode 模式{@link Mode}
     */
    public void notifyByMode(@Mode int mode) {
        currentMode = mode;
        isNotify = true;
        ScrollCardPagerAdapter adapter = (ScrollCardPagerAdapter) getAdapter();
        adapter.setMode(currentMode);
        setAdapter(adapter);
        isNotify = false;
    }

    /**
     * 获取当前效果模式
     *
     * @return {@link #currentMode}当前效果模式
     */
    public int getCurrentMode() {
        return currentMode;
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        if (!(adapter instanceof ScrollCardPagerAdapter)) {
            //ScrollCardPager只支持ScrollCardPagerAdapter适配器
            throw new RuntimeException("ScrollCardPager only support ScrollCardPagerAdapter");
        }
        super.setAdapter(adapter);
    }

    /**
     * 初始化配置
     *
     * @param fm         管理器{@link FragmentManager}
     * @param controller 控制器{@link ItemController<T>}
     * @param data       数据（适配器所需的数据{@link ScrollCardPagerAdapter#items}）
     * @param <T>        T
     */
    public <T> void init(FragmentManager fm, ItemController<T> controller, List<T> data) {
        List<Item> items = getItems(controller, data, isLoop);
        if (itemTransformer == null) {
            itemTransformer = new ItemTransformer(offset, scale);
            setPageTransformer(false, itemTransformer);
        }
        ScrollCardPagerAdapter adapter = new ScrollCardPagerAdapter(fm, items, isLoop);
        setAdapter(adapter);
    }

    /**
     * 获取Item
     *
     * @param controller 控制器{@link ItemController<T>}
     * @param data       数据（适配器所需的数据{@link ScrollCardPagerAdapter#items}）
     * @param isLoop     是否循环{@link #isLoop}
     * @param <T>        T
     * @return Item集合
     */
    @Nullable
    private <T> List<Item> getItems(ItemController<T> controller, List<T> data, boolean isLoop) {
        List<Item> items = new ArrayList<>();
        int dataSize = data.size();
        boolean isExpand = isLoop && dataSize < COUNT;
        int radio = COUNT / dataSize < 2 ? 2 : COUNT / dataSize;
        int size = isExpand ? dataSize * radio : dataSize;
        for (int i = 0; i < size; i++) {
            int position = isExpand ? i % dataSize : i;
            T t = data.get(position);
            Item<T> item = new Item<>();
            item.initController(controller);
            item.initData(t, position);
            items.add(item);
        }
        return items;
    }
}
