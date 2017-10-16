package com.galaxylight.scrollcardpager.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.galaxylight.scrollcardpager.listener.OnCardItemClickListener;
import com.galaxylight.scrollcardpager.R;
import com.galaxylight.scrollcardpager.custom.ItemController;
import com.galaxylight.scrollcardpager.custom.ScrollCardPager;

/**
 * 自定义控制器
 * T Integer
 * Created by Administrator on 2017-9-14.
 */

public class MyIntegerController implements ItemController<Integer> {
    @Override
    public View setLogic(final Context context, Integer data, final int position, @ScrollCardPager.Mode int mode) {
        View view = LayoutInflater.from(context).inflate(R.layout.pager_item, null);
        ImageView imageView = view.findViewById(R.id.imageView);
        Glide.with(context).load(data).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.clicked(position);
            }
        });
        return view;
    }

    private OnCardItemClickListener listener;

    public void setOnCardItemClickListener(OnCardItemClickListener listener) {
        this.listener = listener;
    }
}
