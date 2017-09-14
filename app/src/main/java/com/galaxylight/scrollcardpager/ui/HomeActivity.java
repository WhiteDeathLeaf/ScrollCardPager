package com.galaxylight.scrollcardpager.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.galaxylight.scrollcardpager.R;
import com.galaxylight.scrollcardpager.controller.MyController;
import com.galaxylight.scrollcardpager.custom.ScrollCardPager;
import com.galaxylight.scrollcardpager.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

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

        MyController myController = new MyController();
        myController.setOnCardItemClickListener(CardItemClickListener);

        scrollCardPager.init(getSupportFragmentManager(), myController, ImageUtil.getFile(this));
    }

    private MyController.OnCardItemClickListener CardItemClickListener = new MyController.OnCardItemClickListener() {
        @Override
        public void clicked(int position) {
            Toast.makeText(HomeActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
        }
    };

    //drawable图片
    public List<Integer> getResData(int count) {
        List<Integer> dataList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            dataList.add(R.drawable.ic_launcher);
        }
        return dataList;
    }

    //网络图片地址
    public List<String> getUrlData() {
        List<String> dataList = new ArrayList<>();
        dataList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3689295147,509373218&fm=26&gp=0.jpg");
        dataList.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3213173117,1110903080&fm=26&gp=0.jpg");
        dataList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1781594915,1366698269&fm=26&gp=0.jpg");
        return dataList;
    }
}
