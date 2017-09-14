package com.galaxylight.scrollcardpager.custom;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 滑动效果
 * {@link ViewPager.PageTransformer}
 * Created by gzh on 2017-9-13.
 */

public class ItemTransformer implements ViewPager.PageTransformer {
    private int maxOffsetX;//x轴最大偏移量
    private float scale;//缩放比例

    private ViewPager viewPager;

    public ItemTransformer(int maxOffsetX, float scale) {
        this.maxOffsetX = maxOffsetX;
        this.scale = scale;
    }

    @Override
    public void transformPage(View page, float position) {
        if (viewPager == null) {
            viewPager = (ViewPager) page.getParent();
        }
        int leftWidth = page.getLeft() - viewPager.getScrollX();
        int center = leftWidth + page.getMeasuredWidth() / 2;
        int offsetX = center - viewPager.getMeasuredWidth() / 2;
        float offsetScale = (float) offsetX * scale / viewPager.getMeasuredWidth();
        float scaleCom = 1 - Math.abs(offsetScale);
        if (scaleCom > 0) {
            page.setScaleX(scaleCom);
            page.setScaleY(scaleCom);
            page.setTranslationX(-maxOffsetX * offsetScale);
        }
    }
}
