package vudinhthanh.wallpaper.wallpaper_store;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tabs.Tab1;
import tabs.Tab2;
import tabs.Tab3;
import tabs.Tab5;

/**
 * Created by Thanh-PC on 8/8/2017.
 */

public class dataFragment extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.tablayout_home,container,false);

        viewPager= (ViewPager) view.findViewById(R.id.container);
        settupViewpage(viewPager);
        tabLayout= (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setCurrentItem(1);
        return view;
    }



    private void settupViewpage(ViewPager viewPager) {
        AdapterSelect ad = new AdapterSelect(getChildFragmentManager());
        ad.addFrapment(new Tab5(),"library");
        ad.addFrapment(new Tab1(), "Featured");
        ad.addFrapment(new Tab2(), "Recent");
        ad.addFrapment(new Tab3(), "Top download");

        viewPager.setAdapter(ad);
    }

    private class AdapterSelect extends FragmentPagerAdapter {
        private  final List<Fragment> fragmentList=new ArrayList<>();
        private  final List<String> mStringListTitle=new ArrayList<>();

        public AdapterSelect(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mStringListTitle.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFrapment(Fragment fragment, String title){
            fragmentList.add(fragment);
            mStringListTitle.add(title);
        }
    }

}
