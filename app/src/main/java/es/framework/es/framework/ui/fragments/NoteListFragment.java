package es.framework.es.framework.ui.fragments;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import es.framework.R;


public class NoteListFragment extends Fragment {

    private Drawer mDrawer=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create drawer header
        AccountHeader headResult=new AccountHeaderBuilder()
                .withActivity(this.getActivity())
                .withHeaderBackground(R.drawable.drawer_title)
                .build();

        //build navigation drawer
        mDrawer=new DrawerBuilder()
                .withAccountHeader(headResult)
                .withActivity(this.getActivity())
               // .withToolbar()
                .withActionBarDrawerToggle(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Notepad").withIcon(FontAwesome.Icon.faw_edit).withIdentifier(1),
                        new PrimaryDrawerItem().withName("Todo List").withIcon(FontAwesome.Icon.faw_list).withIdentifier(2),
                        new PrimaryDrawerItem().withName("Drawing").withIcon(FontAwesome.Icon.faw_pencil).withIdentifier(3),
                        new PrimaryDrawerItem().withName("Reminder").withIcon(FontAwesome.Icon.faw_clock_o).withIdentifier(4),
                        new PrimaryDrawerItem().withName("Movie List").withIcon(FontAwesome.Icon.faw_cloud).withIdentifier(5),
                        new PrimaryDrawerItem().withName("Settings").withIcon(FontAwesome.Icon.faw_code_fork).withIdentifier(5))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                          @Override
                          public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                              return true;
                          }
                      }
                )
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
                })
                .withFireOnInitialOnClick(true)
                .withSavedInstance(savedInstanceState)
                .build();
    }




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
