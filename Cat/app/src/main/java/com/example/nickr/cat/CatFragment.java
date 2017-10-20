package com.example.nickr.cat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * Created by nickr on 18.10.2017.
 */


public class CatFragment extends Fragment {

    private View rootView;
    private Image image;

    public CatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cat, container, false);
        image = getArguments().getParcelable("image");
        Log.d("FUCK2", image.getUrl().toString());
        ImageView iv = rootView.findViewById(R.id.iv_cat);
        Picasso.with(getContext()).load(image.getUrl()).fit().centerCrop().into(iv);
        return rootView;
    }

}