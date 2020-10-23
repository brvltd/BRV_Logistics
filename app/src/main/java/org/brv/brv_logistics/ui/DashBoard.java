package org.brv.brv_logistics.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.tabs.TabLayout;

import org.brv.brv_logistics.Adapter.ViewPagerAdapter;
import org.brv.brv_logistics.Fragment.MapsFragment;
import org.brv.brv_logistics.Fragment.RegisterFragment;
import org.brv.brv_logistics.Fragment.RegisteredCargoFragment;
import org.brv.brv_logistics.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashBoard extends AppCompatActivity {
    @BindView(R.id.tabId) TabLayout tabLayout;
    @BindView(R.id.viewPagerId) ViewPager viewPager;

    private ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dash_board);
        ButterKnife.bind(this);
        adapter=new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new RegisterFragment(),"");
        adapter.AddFragment(new RegisteredCargoFragment(),"");
        adapter.AddFragment(new MapsFragment(),"");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_create_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_shopping_cart_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_map_24);
    }


}