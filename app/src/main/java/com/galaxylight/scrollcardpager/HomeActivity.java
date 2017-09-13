package com.galaxylight.scrollcardpager;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.galaxylight.scrollcardpager.custom.ItemController;
import com.galaxylight.scrollcardpager.custom.ScrollCardPager;
import com.galaxylight.scrollcardpager.custom.ScrollCardPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ScrollCardPager scrollCardPager;
    private List<String> data;
    private String s1 = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3689295147,509373218&fm=26&gp=0.jpg";
    private String s2 = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3213173117,1110903080&fm=26&gp=0.jpg";
    private String s3 = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1781594915,1366698269&fm=26&gp=0.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        data=new ArrayList<>();
        data.add(s1);
        data.add(s2);
        data.add(s3);
        scrollCardPager = (ScrollCardPager) findViewById(R.id.scp);
        scrollCardPager.init(getSupportFragmentManager(),new MyController(),data);
    }
    class MyController implements ItemController<String>{
        @Override
        public View setLogic(Context context, String data, int position, @ScrollCardPager.Mode int mode) {
            View view=getLayoutInflater().inflate(R.layout.pager_item,null);
            ImageView imageView=view.findViewById(R.id.imageView);
            Glide.with(context).load(data).into(imageView);
            return view;
        }
    }
}
