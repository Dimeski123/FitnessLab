package com.ndimeski.fitnesslab;

import android.os.Bundle;

import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class RecipeContent extends Fragment {

    View view;
    Fragment fragment;
    ImageView img, backBtn;
    TextView titletxt,ingredientstxt,preparingtxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recipe_content, container, false);
        fragment = new Recipes();
        img = view.findViewById(R.id.imageViewContent);
        titletxt = view.findViewById(R.id.txtViewContent);
        backBtn = view.findViewById(R.id.backButton);
        ingredientstxt = view.findViewById(R.id.txtIngridientsContent);
        preparingtxt = view.findViewById(R.id.txtPreparingContent);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentLayout, fragment).commit();
            }
        });

        switch (Recipes.S.i){
            case 1:
                img.setImageResource(R.drawable.chopped_chilli_chicken_stir_fry_28400_1);
                titletxt.setText(R.string.Title11);
                ingredientstxt.setText(R.string.Ingredients11);
                preparingtxt.setText(R.string.Preparing11);
                break;
            case 2:
                img.setImageResource(R.drawable.minute_steaks_with_romesco_salad_14828_1);
                titletxt.setText(R.string.Title12);
                ingredientstxt.setText(R.string.Ingredients12);
                preparingtxt.setText(R.string.Preparing12);
                break;
            case 3:
                img.setImageResource(R.drawable.steak_corn_and_red_bean_salad_10606_2);
                titletxt.setText(R.string.Title13);
                ingredientstxt.setText(R.string.Ingredients13);
                preparingtxt.setText(R.string.Preparing13);
                break;
            case 4:
                img.setImageResource(R.drawable.eggs_with_lentils_and_spiced_mint_recipe_158121_2);
                titletxt.setText(R.string.Title21);
                ingredientstxt.setText(R.string.Ingredients21);
                preparingtxt.setText(R.string.Preparing21);
                break;
            case 5:
                img.setImageResource(R.drawable.healthier_yellow_chicken_curry_147092_1);
                titletxt.setText(R.string.Title22);
                ingredientstxt.setText(R.string.Ingredients22);
                preparingtxt.setText(R.string.Preparing22);
                break;
            case 6:
                img.setImageResource(R.drawable.hearty_vego_frying_pan_pie_139182_1);
                titletxt.setText(R.string.Title23);
                ingredientstxt.setText(R.string.Ingredients23);
                preparingtxt.setText(R.string.Preparing23);
                break;
            case 7:
                img.setImageResource(R.drawable.chicken_cacciatore_stoup_127119_2);
                titletxt.setText(R.string.Title24);
                ingredientstxt.setText(R.string.Ingredients24);
                preparingtxt.setText(R.string.Preparing24);
                break;
            case 8:
                img.setImageResource(R.drawable.creamy_vegetarian_pot_pies_recipe_164341_1);
                titletxt.setText(R.string.Title31);
                ingredientstxt.setText(R.string.Ingredients31);
                preparingtxt.setText(R.string.Preparing31);
                break;
            case 9:
                img.setImageResource(R.drawable.vegetarian_mushroom_ymeatballsy_169467_2);
                titletxt.setText(R.string.Title32);
                ingredientstxt.setText(R.string.Ingredients32);
                preparingtxt.setText(R.string.Preparing32);
                break;
            case 10:
                img.setImageResource(R.drawable.mousaka_mattp_web_copy_173021_1);
                titletxt.setText(R.string.Title33);
                ingredientstxt.setText(R.string.Ingredients33);
                preparingtxt.setText(R.string.Preparing33);
                break;
        }




        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentLayout, fragment).commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        return view;
    }
}