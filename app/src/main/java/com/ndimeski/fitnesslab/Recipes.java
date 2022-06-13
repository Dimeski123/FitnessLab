package com.ndimeski.fitnesslab;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.dynamic.SupportFragmentWrapper;

public class Recipes extends Fragment {

    View view;
    LinearLayout layout1,layout2,layout3,layout4,layout5,layout6,layout7,layout8,layout9,layout10;
    Fragment fragment = new RecipeContent();
    public static class S{
        public static int i = 0;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recipes, container, false);
        layout1 = view.findViewById(R.id.recepielayout11);
        layout2 = view.findViewById(R.id.recepielayout12);
        layout3 = view.findViewById(R.id.recepielayout13);
        layout4 = view.findViewById(R.id.recepielayout21);
        layout5 = view.findViewById(R.id.recepielayout22);
        layout6 = view.findViewById(R.id.recepielayout23);
        layout7 = view.findViewById(R.id.recepielayout24);
        layout8 = view.findViewById(R.id.recepielayout31);
        layout9 = view.findViewById(R.id.recepielayout32);
        layout10 = view.findViewById(R.id.recepielayout33);

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.i = 1;
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentLayout, fragment).commit();
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.i = 2;
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentLayout, fragment).commit();
            }
        });
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.i = 3;
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentLayout, fragment).commit();
            }
        });
        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.i = 4;
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentLayout, fragment).commit();
            }
        });
        layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.i = 5;
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentLayout, fragment).commit();
            }
        });
        layout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.i = 6;
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentLayout, fragment).commit();
            }
        });
        layout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.i = 7;
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentLayout, fragment).commit();
            }
        });
        layout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.i = 8;
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentLayout, fragment).commit();
            }
        });
        layout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.i = 9;
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentLayout, fragment).commit();
            }
        });
        layout10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.i = 10;
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentLayout, fragment).commit();
            }
        });

        return view;
    }

}