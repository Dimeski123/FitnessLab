package com.ndimeski.fitnesslab;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Exercises extends Fragment implements View.OnClickListener{

    View view;
    public static class E{
        public static int j = 0;
        public static int counter = 1;
    }
    Button btnAbsBegginer,btnArmsBegginer,btnChestBegginer,btnLegsBegginer,btnBackBegginer,btnWeightBegginer,btnAbsIntermediate,btnArmsIntermediate,btnChestIntermediate,btnLegsIntermediate,btnBackIntermediate,btnWeightIntermediate;
    ImageView dropDown11,dropDown12,dropDown13,dropDown14,dropDown15,dropDown16,dropDown21,dropDown22,dropDown23,dropDown24,dropDown25,dropDown26;
    LinearLayout hidden11,hidden12,hidden13,hidden14,hidden15,hidden16,hidden21,hidden22,hidden23,hidden24,hidden25,hidden26;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.exercises, container, false);
        //TODO:Napraj gi site klikovi so eden onClick Metod!!
        //Declaring DrowDowns and Hidden Layouts

        dropDown11 = view.findViewById(R.id.imgDropDown11);
        dropDown12 = view.findViewById(R.id.imgDropDown12);
        dropDown13 = view.findViewById(R.id.imgDropDown13);
        dropDown14 = view.findViewById(R.id.imgDropDown14);
        dropDown15 = view.findViewById(R.id.imgDropDown15);
        dropDown16 = view.findViewById(R.id.imgDropDown16);
        dropDown21 = view.findViewById(R.id.imgDropDown21);
        dropDown22 = view.findViewById(R.id.imgDropDown22);
        dropDown23 = view.findViewById(R.id.imgDropDown23);
        dropDown24 = view.findViewById(R.id.imgDropDown24);
        dropDown25 = view.findViewById(R.id.imgDropDown25);
        dropDown26 = view.findViewById(R.id.imgDropDown26);
        //--
        hidden11 = view.findViewById(R.id.hiddenLayout11);
        hidden12 = view.findViewById(R.id.hiddenLayout12);
        hidden13 = view.findViewById(R.id.hiddenLayout13);
        hidden14 = view.findViewById(R.id.hiddenLayout14);
        hidden15 = view.findViewById(R.id.hiddenLayout15);
        hidden16 = view.findViewById(R.id.hiddenLayout16);
        hidden21 = view.findViewById(R.id.hiddenLayout21);
        hidden22 = view.findViewById(R.id.hiddenLayout22);
        hidden23 = view.findViewById(R.id.hiddenLayout23);
        hidden24 = view.findViewById(R.id.hiddenLayout24);
        hidden25 = view.findViewById(R.id.hiddenLayout25);
        hidden26 = view.findViewById(R.id.hiddenLayout26);
        //--
        btnAbsBegginer = view.findViewById(R.id.btnAbsBegginer);
        btnChestBegginer = view.findViewById(R.id.btnChestBegginer);
        btnArmsBegginer = view.findViewById(R.id.btnArmsBegginer);
        btnLegsBegginer = view.findViewById(R.id.btnLegsBegginer);
        btnBackBegginer = view.findViewById(R.id.btnBackBegginer);
        btnWeightBegginer = view.findViewById(R.id.btnWeightBegginer);
        btnAbsIntermediate = view.findViewById(R.id.btnAbsIntermediate);
        btnChestIntermediate = view.findViewById(R.id.btnChestIntermediate);
        btnArmsIntermediate = view.findViewById(R.id.btnArmsIntermediate);
        btnLegsIntermediate = view.findViewById(R.id.btnLegsIntermediate);
        btnBackIntermediate = view.findViewById(R.id.btnBackIntermediate);
        btnWeightIntermediate = view.findViewById(R.id.btnWeightIntermediate);



        //TODO: Ako ima vreme dodaj objasnuvanje za sekoja vezba!!!
        //OnClick Listeners for every Dropdown

        dropDown11.setOnClickListener(this);
        dropDown12.setOnClickListener(this);
        dropDown13.setOnClickListener(this);
        dropDown14.setOnClickListener(this);
        dropDown15.setOnClickListener(this);
        dropDown16.setOnClickListener(this);
        dropDown21.setOnClickListener(this);
        dropDown22.setOnClickListener(this);
        dropDown23.setOnClickListener(this);
        dropDown24.setOnClickListener(this);
        dropDown25.setOnClickListener(this);
        dropDown26.setOnClickListener(this);
        //--
        btnAbsBegginer.setOnClickListener(this);
        btnArmsBegginer.setOnClickListener(this);
        btnChestBegginer.setOnClickListener(this);
        btnLegsBegginer.setOnClickListener(this);
        btnBackBegginer.setOnClickListener(this);
        btnWeightBegginer.setOnClickListener(this);
        btnAbsIntermediate.setOnClickListener(this);
        btnArmsIntermediate.setOnClickListener(this);
        btnChestIntermediate.setOnClickListener(this);
        btnLegsIntermediate.setOnClickListener(this);
        btnBackIntermediate.setOnClickListener(this);
        btnWeightIntermediate.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgDropDown11:
                if(hidden11.getVisibility() == View.VISIBLE){
                    hidden11.setVisibility(View.GONE);
                }
                else{
                    hidden11.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.imgDropDown12:
                if(hidden12.getVisibility() == View.VISIBLE){
                    hidden12.setVisibility(View.GONE);
                }
                else{
                    hidden12.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.imgDropDown13:
                if(hidden13.getVisibility() == View.VISIBLE){
                    hidden13.setVisibility(View.GONE);
                }
                else{
                    hidden13.setVisibility(View.VISIBLE);
                }
                break  ;
            case R.id.imgDropDown14:
                if(hidden14.getVisibility() == View.VISIBLE){
                    hidden14.setVisibility(View.GONE);
                }
                else{
                    hidden14.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.imgDropDown15:
                if(hidden15.getVisibility() == View.VISIBLE){
                    hidden15.setVisibility(View.GONE);
                }
                else{
                    hidden15.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.imgDropDown16:
                if(hidden16.getVisibility() == View.VISIBLE){
                    hidden16.setVisibility(View.GONE);
                }
                else{
                    hidden16.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.imgDropDown21:
                if(hidden21.getVisibility() == View.VISIBLE){
                    hidden21.setVisibility(View.GONE);
                }
                else{
                    hidden21.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.imgDropDown22:
                if(hidden22.getVisibility() == View.VISIBLE){
                    hidden22.setVisibility(View.GONE);
                }
                else{
                    hidden22.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.imgDropDown23:
                if(hidden23.getVisibility() == View.VISIBLE){
                    hidden23.setVisibility(View.GONE);
                }
                else{
                    hidden23.setVisibility(View.VISIBLE);
                }
                break  ;
            case R.id.imgDropDown24:
                if(hidden24.getVisibility() == View.VISIBLE){
                    hidden24.setVisibility(View.GONE);
                }
                else{
                    hidden24.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.imgDropDown25:
                if(hidden25.getVisibility() == View.VISIBLE){
                    hidden25.setVisibility(View.GONE);
                }
                else{
                    hidden25.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.imgDropDown26:
                if(hidden26.getVisibility() == View.VISIBLE){
                    hidden26.setVisibility(View.GONE);
                }
                else{
                    hidden26.setVisibility(View.VISIBLE);
                }
                break;
                //--
            case R.id.btnAbsBegginer:
                startActivity(new Intent(getActivity(),ExerciseContent.class));
                E.j = 1;
                break;
            case R.id.btnArmsBegginer:
                startActivity(new Intent(getActivity(),ExerciseContent.class));
                E.j = 2;
                break;
            case R.id.btnChestBegginer:
                startActivity(new Intent(getActivity(),ExerciseContent.class));
                E.j = 3;
                break;
            case R.id.btnLegsBegginer:
                startActivity(new Intent(getActivity(),ExerciseContent.class));
                E.j = 4;
                break;
            case R.id.btnBackBegginer:
                startActivity(new Intent(getActivity(),ExerciseContent.class));
                E.j = 5;
                break;
            case R.id.btnWeightBegginer:
                startActivity(new Intent(getActivity(),ExerciseContent.class));
                E.j = 6;
                break;
            case R.id.btnAbsIntermediate:
                startActivity(new Intent(getActivity(),ExerciseContent.class));
                E.j = 7;
                break;
            case R.id.btnArmsIntermediate:
                startActivity(new Intent(getActivity(),ExerciseContent.class));
                E.j = 8;
                break;
            case R.id.btnChestIntermediate:
                startActivity(new Intent(getActivity(),ExerciseContent.class));
                E.j = 9;
                break;
            case R.id.btnLegsIntermediate:
                startActivity(new Intent(getActivity(),ExerciseContent.class));
                E.j = 10;
                break;
            case R.id.btnBackIntermediate:
                startActivity(new Intent(getActivity(),ExerciseContent.class));
                E.j = 11;
                break;
            case R.id.btnWeightIntermediate:
                startActivity(new Intent(getActivity(),ExerciseContent.class));
                E.j = 12;
                break;

        }
    }
}