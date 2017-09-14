package com.galaxylight.scrollcardpager.custom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * ScrollCardPager 适配器
 * {@link FragmentStatePagerAdapter}
 * Created by gzh on 2017-9-13.
 */

class ScrollCardPagerAdapter extends FragmentStatePagerAdapter {
    private static final int COUNT = Integer.MAX_VALUE;

    private List<Item> items;//数据集合
    private boolean isLoop;//是否循环

    public ScrollCardPagerAdapter(FragmentManager fm, List<Item> items, boolean isLoop) {
        super(fm);
        this.items = items;
        this.isLoop = isLoop;
    }

    /**
     * 获取数据数量
     *
     * @return 数据数量
     */
    private int getSize() {
        return items == null ? 0 : items.size();
    }

    private int getFirstItem() {
        int count = getSize();
        return COUNT / count / 2 * count;
    }

    private int getLastItem(int index) {
        int count = getSize();
        return COUNT / count / 2 * count + index;
    }

    /**
     * 设置效果模式
     *
     * @param mode 模式{@link ScrollCardPager.Mode}
     */
    public void setMode(@ScrollCardPager.Mode int mode) {
        if (items == null || items.isEmpty()) return;
        for (Item item : items) {
            item.mode = mode;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return isLoop ? COUNT : getSize();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, isLoop ? position % getSize() : position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (isLoop) {
            ScrollCardPager scrollCardPager = (ScrollCardPager) container;
            int pos = scrollCardPager.getCurrentItem();
            int i = pos % getSize();
            int j = position % getSize();
            if (scrollCardPager.isNotify) {
                super.destroyItem(container, j, object);
                return;
            }
            if (scrollCardPager.getCurrentMode() == ScrollCardPager.MODE_CARD && j >= i - 2 && j <= i + 2) {
                return;
            }
            super.destroyItem(container, j, object);
            return;
        }
        super.destroyItem(container, position, object);
    }

    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
        if (isLoop) {
            ScrollCardPager scrollCardPager = (ScrollCardPager) container;
            int position = scrollCardPager.getCurrentItem();
            if (position == 0) {
                position = getFirstItem();
            } else if (position == getCount() - 1) {
                final int size = getSize();
                position = getLastItem(position % size);
            }
            scrollCardPager.setCurrentItem(position, false);
        }
    }
}
