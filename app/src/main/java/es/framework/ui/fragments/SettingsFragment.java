package es.framework.ui.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import es.framework.R;
import es.framework.ui.common.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private View mRootView;
    private RadioGroup mSettingsRadioGroup;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView =inflater.inflate(R.layout.fragment_settings, container, false);
        mSharedPreferences=getActivity().getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE);
        mEditor=mSharedPreferences.edit();
        mSettingsRadioGroup= (RadioGroup) mRootView.findViewById(R.id.radioGroupSettings);
        mSettingsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButtonNotepad:
                        mEditor.putString(Constants.DEFAULT_APP, String.valueOf(Constants.NOTEPAD));
                        mEditor.commit();
                        break;
                    case R.id.radioButtonTodoList:
                        mEditor.putString(Constants.DEFAULT_APP, String.valueOf(Constants.TODO));
                        mEditor.commit();
                        break;
                    case R.id.radioButtonDrawing:
                        mEditor.putString(Constants.DEFAULT_APP, String.valueOf(Constants.DRAWING));
                        mEditor.commit();
                        break;
                    case R.id.radioButtonReminder:
                        mEditor.putString(Constants.DEFAULT_APP, String.valueOf(Constants.REMINDER));
                        mEditor.commit();
                        break;
                    case R.id.radioButtonMovieList:
                        mEditor.putString(Constants.DEFAULT_APP, String.valueOf(Constants.MOVIE));
                        mEditor.commit();
                        break;

                }

            }
        });
        return mRootView;
    }



}
