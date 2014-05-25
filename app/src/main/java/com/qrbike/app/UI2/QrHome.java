package com.qrbike.app.UI2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.qrbike.app.R;

/**
 * Created by Kingsun on 14-5-17.
 */
public class QrHome extends FragmentActivity {
    private Fragment mContent;
    private SlidingMenu menu;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setTitle("二维码单车管理端");
        initSlidingMenu(savedInstanceState);
    }

    private void initSlidingMenu(Bundle savedInstanceState){
        if(savedInstanceState != null){
            mContent = getSupportFragmentManager().getFragment(savedInstanceState,"mContent");
        }
        if(mContent == null){
            mContent = new Fragment_0();
        }

        //设置主界面视图
        setContentView(R.layout.content_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mContent).commit();
        menu = new SlidingMenu(this);
        menu.setMenu(R.layout.menu_frame);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindWidth(10);//设置SlidingMenu菜单的宽度  貌似没用
        menu.setBehindOffsetRes(R.dimen.slidingmenuoffset);
        menu.setFadeDegree(0.4f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new QrMenu()).commit();


//        menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
/*        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new QrMenu()).commit();
        SlidingMenu menu = getSlidingMenu();
        menu.setMenu(R.layout.menu_frame);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffset(R.dimen.menu_offset);
        menu.setFadeDegree(0.4f);
        menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);*/

    }

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
/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toggle();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
*/

    /**
     * 切换视图
     * @param fragment
     */
    public void switchContent(Fragment fragment) {
        mContent = fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment).commit();
        menu.showContent();
    }

}
