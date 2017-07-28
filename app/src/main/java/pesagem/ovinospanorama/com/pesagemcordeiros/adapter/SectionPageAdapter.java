package pesagem.ovinospanorama.com.pesagemcordeiros.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class SectionPageAdapter extends FragmentPagerAdapter {


    private List<String> fragmentTitleList = new ArrayList<String>();
    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    public SectionPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public String getPageTitle(int position){
        return fragmentTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }
}
