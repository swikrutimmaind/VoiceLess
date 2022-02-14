package com.example.voiceless;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {
    //Initialize variable
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable
        bottomNavigation = findViewById(R.id.bottom_navigation);

        //add menu item
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_share));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_info));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                //Initialize fragment
                Fragment fragment = null;

                //Check condition
                switch (item.getId()){
                    case 1:
                        //when id is 1, initialize share fragment
                        fragment = new ShareFragment();
                        break;
                    case 2:
                        //when id is 2, initialize home fragment
                        fragment = new HomeFragment();
                        break;
                    case 3:
                        //when id is 3, initialize contact fragment
                        fragment = new InfoFragment();
                        break;
                }

                //load fragment
                loadFragment(fragment);
            }
        });

        //set home fragment initially selected
        bottomNavigation.show(2,true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Display toast
                if(item.getId()==1){
                    Toast.makeText(getApplicationContext()
                            ,"You Clicked Share"
                            ,Toast.LENGTH_SHORT).show();
                }
                else if(item.getId()==2){
                    Toast.makeText(getApplicationContext()
                            ,"You Clicked Home"
                            ,Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext()
                            ,"You Clicked Info"
                            ,Toast.LENGTH_SHORT).show();
                }
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //Display Toast
                if(item.getId()==1){
                    Toast.makeText(getApplicationContext()
                            ,"You Reselected Share"
                            ,Toast.LENGTH_SHORT).show();
                }
                else if(item.getId()==2){
                    Toast.makeText(getApplicationContext()
                            ,"You Reselected Home"
                            ,Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext()
                            ,"You Reselected Info"
                            ,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        //Replace fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }
}