package com.example.user.secondfootballapp.tournament.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.tournament.adapter.ViewPagerCommandInfoAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class CommandInfoActivity extends AppCompatActivity {
    TabLayout tabLayout;
    Logger log = LoggerFactory.getLogger(PersonalActivity.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_command_info);
        final TabLayout tabLayout;
        ViewPager viewPager;
        TextView textView;
        ImageButton imageButton;
        Intent intent;
        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("COMMANDTITLE");
        String title = bundle.getString("COMMANDTITLE1");
//        String title = intent.getExtras().getString("COMMANDTITLE");
        tabLayout = (TabLayout) findViewById(R.id.commandInfoTab);
        viewPager = (ViewPager) findViewById(R.id.commandInfoViewPager);
        textView = (TextView) findViewById(R.id.commandInfoTitle);
        imageButton = (ImageButton) findViewById(R.id.commandInfoCloseButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        textView.setText(title);
        textView.setText("Кубок Бурятии по мини-футболу. Первый этап. Золотая лига");
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
//        setCustomFont();
        Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/manrope_regular.otf");
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //noinspection ConstantConditions
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_text,null);
            tv.setTypeface(tf);
//            tv.setTextColor(getResources().getColor(R.color.colorTabUnChecked));
            tabLayout.getTabAt(i).setCustomView(tv);
        }
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        TextView selectedText = (TextView) tab.getCustomView();
        selectedText.setTextColor(getResources().getColor(R.color.colorWhite));
//        tab.select();
//        tabLayout.setTabTextColors(getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorWhite));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                TextView tv = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tab_text,null);
//                tv.setTextColor(getResources().getColor(R.color.colorAccent));
//                tab.setCustomView(tv);
//                TextView selectedText = (TextView) findViewById(R.id.commandInfoTab);
//                TextView selectedText = (TextView) tab.getText();
                TextView selectedText = (TextView) tab.getCustomView();
                selectedText.setTextColor(getResources().getColor(R.color.colorWhite));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView selectedText = (TextView) tab.getCustomView();
                selectedText.setTextColor(getResources().getColor(R.color.colorTabUnChecked));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }
    private void setupViewPager(ViewPager viewPager){
        ViewPagerCommandInfoAdapter adapter = new ViewPagerCommandInfoAdapter(getSupportFragmentManager());
        adapter.addFragment(new CommandStructureFragment(), "СОСТАВ");
        adapter.addFragment(new CommandMatchFragment(), "МАТЧ");
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
