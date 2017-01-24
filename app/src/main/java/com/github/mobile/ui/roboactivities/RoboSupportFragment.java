package com.github.mobile.ui.roboactivities;

import roboguice.RoboGuice;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.github.kevinsawicki.wishlist.ViewFinder;

import java.io.Serializable;


public abstract class RoboSupportFragment extends Fragment {

    /**
     * View finder bound to the value last specified to
     * {@link #onViewCreated(android.view.View, Bundle)}
     */
    protected ViewFinder finder;


    /**
     * Is this fragment usable from the UI-thread
     *
     * @return true if usable, false otherwise
     */
    protected boolean isUsable() {
        return getActivity() != null;
    }

    /**
     * Get serializable extra from activity's intent
     *
     * @param name
     * @return extra
     */
    @SuppressWarnings("unchecked")
    protected <V extends Serializable> V getSerializableExtra(final String name) {
        Activity activity = getActivity();
        if (activity != null)
            return (V) activity.getIntent().getSerializableExtra(name);
        else
            return null;
    }

    /**
     * Get string extra from activity's intent
     *
     * @param name
     * @return extra
     */
    protected String getStringExtra(final String name) {
        Activity activity = getActivity();
        if (activity != null)
            return activity.getIntent().getStringExtra(name);
        else
            return null;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RoboGuice.getInjector(getActivity()).injectMembersWithoutViews(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RoboGuice.getInjector(getActivity()).injectViewMembers(this);
        finder = new ViewFinder(view);
    }
}
