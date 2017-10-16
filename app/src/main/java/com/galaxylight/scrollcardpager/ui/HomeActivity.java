package com.galaxylight.scrollcardpager.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.galaxylight.scrollcardpager.controller.MyFileController;
import com.galaxylight.scrollcardpager.controller.MyIntegerController;
import com.galaxylight.scrollcardpager.controller.MyStringController;
import com.galaxylight.scrollcardpager.listener.OnCardItemClickListener;
import com.galaxylight.scrollcardpager.R;
import com.galaxylight.scrollcardpager.custom.ScrollCardPager;
import com.galaxylight.scrollcardpager.util.ImageUtil;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.scp)
    ScrollCardPager scrollCardPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        MyStringController myStringController = new MyStringController();
        myStringController.setOnCardItemClickListener(cardItemClickListener);
        scrollCardPager.init(getSupportFragmentManager(), myStringController, ImageUtil.getUrlData());
    }

    private OnCardItemClickListener cardItemClickListener = new OnCardItemClickListener() {
        @Override
        public void clicked(int position) {
            Toast.makeText(HomeActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setIconEnable(menu, true);
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_url://从网络获取
                MyStringController myStringController = new MyStringController();
                myStringController.setOnCardItemClickListener(cardItemClickListener);
                scrollCardPager.init(getSupportFragmentManager(), myStringController, ImageUtil.getUrlData());
                break;
            case R.id.home_local://从本地获取
                MyIntegerController myIntegerController = new MyIntegerController();
                myIntegerController.setOnCardItemClickListener(cardItemClickListener);
                scrollCardPager.init(getSupportFragmentManager(), myIntegerController, ImageUtil.getResData(6));
                break;
            case R.id.home_phone://从手机获取
                MyFileController myFileController = new MyFileController();
                myFileController.setOnCardItemClickListener(cardItemClickListener);
                scrollCardPager.init(getSupportFragmentManager(), myFileController, ImageUtil.getFile(this));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setIconEnable(Menu menu, boolean enable) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, enable);
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
                }
            }
        }
    }
}
