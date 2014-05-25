package com.qrbike.app.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import com.qrbike.app.R;

/**
 * Created by Kingsun on 14-5-17.
 */
public class QrHome extends SlidingFragmentActivity{
    private Fragment mContent;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setTitle("二维码单车管理端");
        getActionBar().setLogo(R.drawable.ic_action_github);
        setSlidingActionBarEnabled(true);//设置顶部BAR是否跟随滑动
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setIcon(R.drawable.ic_launcher);
//        initSlidingMenu(savedInstanceState);
        if(savedInstanceState != null){
            mContent = getSupportFragmentManager().getFragment(savedInstanceState,"mContent");
        }
        if(mContent == null){
            mContent = new com.qrbike.app.ui.Fragment_0();
        }
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setBehindWidth(R.dimen.sliding_menu_width);
        slidingMenu.setBehindScrollScale(0.0f);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setBehindWidthRes(R.dimen.shadow_width);
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        setBehindContentView(R.layout.fragment_menu);

        setContentView(R.layout.fragment_content);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_menu, new QrMenu()).commit();

    }
/*
    private void initSlidingMenu(Bundle savedInstanceState){
        if(savedInstanceState != null){
            mContent = getSupportFragmentManager().getFragment(savedInstanceState,"mContent");
        }
        if(mContent == null){
            mContent = new com.qrbike.app.UI2.Fragment_0();
        }

        //设置主界面视图
        setContentView(R.layout.content_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mContent).commit();
        //设置菜单视图
        setBehindContentView(R.layout.menu_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new QrMenu()).commit();

        SlidingMenu su = getSlidingMenu();
        su.setBehindWidth(10);
        su.setShadowWidthRes(R.dimen.shadow_width);
        su.setShadowDrawable(R.drawable.shadow);
        su.setBehindOffset(R.dimen.slidingmenuoffset);
        su.setFadeDegree(0.35f);
        su.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
    }*/

    /**
     * 保存Fragment的状态
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "mContent", mContent);
    }
    /**
     * 菜单按钮点击事件，通过点击ActionBar的Home图标按钮来打开滑动菜单
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toggle();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 切换视图
     * @param fragment
     */
    public void switchContent(Fragment fragment) {
        mContent = fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.left_container, fragment).commit();
        getSlidingMenu().showContent();
    }

}
