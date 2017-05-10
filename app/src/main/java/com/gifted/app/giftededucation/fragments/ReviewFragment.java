package com.gifted.app.giftededucation.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.adapters.OptionsAdapter;
import com.thefinestartist.Base;

public class ReviewFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        OptionsAdapter optionsAdapter;
        View rootView = inflater.inflate(R.layout.fragment_review, container, false);

        ViewPager vp = (ViewPager) getActivity().findViewById(R.id.viewpager);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.review_recycler);

        optionsAdapter = new OptionsAdapter(vp, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Base.getContext(), 5);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(optionsAdapter);


        return rootView;
    }

}
