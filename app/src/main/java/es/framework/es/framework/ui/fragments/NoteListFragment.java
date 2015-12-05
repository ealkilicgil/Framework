package es.framework.es.framework.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

import es.framework.R;
import es.framework.es.framework.db.NoteDataSource;
import es.framework.es.framework.entities.Note;
import es.framework.es.framework.ui.adapters.NoteListAdapter;


public class NoteListFragment extends Fragment {

    private Drawer mDrawer=null;
    private View mRootView=null;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private NoteListAdapter mAdapter;
    private List<Note> mNotes;


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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView=super.onCreateView(inflater, container, savedInstanceState);
        initView();
        return mRootView;
    }

    private void initView() {
        mRecyclerView= (RecyclerView) mRootView.findViewById(R.id.note_recyclerView);
        mLayoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        NoteDataSource notesDatasource=new NoteDataSource();
        mNotes= notesDatasource.SampleTestData();
        mAdapter=new NoteListAdapter(getActivity(),mNotes);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab= (FloatingActionButton) mRootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your action", Snackbar.LENGTH_SHORT).setAction("Action",null).show();

            }
        });
    }

}
