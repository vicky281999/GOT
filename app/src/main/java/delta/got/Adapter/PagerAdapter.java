package delta.got.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import delta.got.Fragments.TabHistory;
import delta.got.Fragments.TabSearch;

public class PagerAdapter extends FragmentStatePagerAdapter{

    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm,int mNumberofTabs) {
        super(fm);
        this.mNoOfTabs = mNumberofTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TabSearch tab1 = new TabSearch();
                return tab1;
            case 1:
                TabHistory tab2 = new TabHistory();
                return tab2;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
