package dk.dragphotoviewdemo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by dongjie.guo on 2017/2/7.
 */

public class MyViewPagerAdapter extends PagerAdapter {
    private ArrayList< View > viewContainer;
    private ArrayList< String > titleContainer;
    private String[] titles;

    public MyViewPagerAdapter(ArrayList< View > viewContainer, ArrayList< String > titleContainer) {
        this.viewContainer = viewContainer;
        this.titleContainer = titleContainer;
    }

    public MyViewPagerAdapter(ArrayList< View > viewContainer, String[] titles) {
        this.viewContainer = viewContainer;
        this.titles = titles;
    }

    public MyViewPagerAdapter(ArrayList< View > viewContainer) {
        this.viewContainer = viewContainer;
    }

    @Override
    public int getCount() {
        return viewContainer.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(viewContainer.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(viewContainer.get(position));
        return viewContainer.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titleContainer != null) {
            return titleContainer.get(position);
        }
        if (titles != null) {
            return titles[position];
        }
        return super.getPageTitle(position);
    }
}