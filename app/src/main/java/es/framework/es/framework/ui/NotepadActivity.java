package es.framework.es.framework.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import es.framework.R;
import es.framework.es.framework.ui.common.Constants;

public class NotepadActivity extends AppCompatActivity {

    private Drawer mDrawer = null;
    private int DEFAULT_APP;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Create drawer header
        AccountHeader headResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.drawer_title)
                .build();

        mSharedPreferences=getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE);
        mEditor=mSharedPreferences.edit();

        DEFAULT_APP=mSharedPreferences.getInt(Constants.DEFAULT_APP, 0);
        //build navigation drawer
        mDrawer = new DrawerBuilder()
                .withAccountHeader(headResult)
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.label_notepad).withIcon(FontAwesome.Icon.faw_edit).withIdentifier(Constants.NOTEPAD),
                        new PrimaryDrawerItem().withName(R.string.label_todo_list).withIcon(FontAwesome.Icon.faw_list).withIdentifier(Constants.TODO),
                        new PrimaryDrawerItem().withName(R.string.label_drawing).withIcon(FontAwesome.Icon.faw_pencil).withIdentifier(Constants.DRAWING),
                        new PrimaryDrawerItem().withName(R.string.label_reminder).withIcon(FontAwesome.Icon.faw_clock_o).withIdentifier(Constants.REMINDER),
                        new PrimaryDrawerItem().withName(R.string.label_movie_list).withIcon(FontAwesome.Icon.faw_cloud).withIdentifier(Constants.MOVIE),
                        new PrimaryDrawerItem().withName(R.string.label_settings).withIcon(FontAwesome.Icon.faw_code_fork).withIdentifier(Constants.SETTINGS))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null && drawerItem instanceof Nameable) {
                            String name = ((Nameable) drawerItem).getName().getText(NotepadActivity.this);
                            toolbar.setTitle(name);
                        }
                        if (drawerItem != null) {
                            int indexOfDrawerItem = drawerItem.getIdentifier();
                            onTouchDrawer(indexOfDrawerItem);
                        }
                        return true;

                    }
                })
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                                          @Override
                                          public void onDrawerOpened(View drawerView) {

                                          }

                                          @Override
                                          public void onDrawerClosed(View drawerView) {

                                          }

                                          @Override
                                          public void onDrawerSlide(View drawerView, float slideOffset) {

                                          }
                                      }

                )
                .withFireOnInitialOnClick(true)
                .withSavedInstance(savedInstanceState)
                .build();

             if(DEFAULT_APP>0)
                 onTouchDrawer(DEFAULT_APP);
              else
               onTouchDrawer(Constants.NOTEPAD);
    }

    private void onTouchDrawer(int position){
        switch(position){
            case Constants.NOTEPAD:
              //  startActivity(new Intent(this,NotepadActivity.class));
                break;

            case Constants.DRAWING:
                Toast.makeText(this,"DRAWING",Toast.LENGTH_SHORT).show();break;
            case Constants.TODO:
                Toast.makeText(this,"TODO",Toast.LENGTH_SHORT).show();break;
            case Constants.MOVIE:
                Toast.makeText(this,"MOVIE",Toast.LENGTH_SHORT).show();break;
            case Constants.REMINDER:
                Toast.makeText(this,"REMINDER",Toast.LENGTH_SHORT).show();break;
            case Constants.SETTINGS:
                openFragment(new SettingsFragment(),getString(R.string.settings))
                ;break;
        }
    }

    private void openFragment(Fragment fragment,String title){
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container,fragment)
                .addToBackStack(null)
                .commit();
        getSupportActionBar().setTitle(title);
    }

}
