package com.ndimeski.fitnesslab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ExerciseContent extends AppCompatActivity implements View.OnClickListener {

    TextView titleText, countdownText;
    Button btnNext, btnStartJumpingJacks, btnStartPlank, btnStartArmRaises, btnStartSideArmRaises, btnStartPunches, btnStartTricepsStretchLeft, btnStartTricepsStretchRight, btnStartSideHop, btnStartArmScissors, btnStartRopeJumping;
    CountDownTimer countDownTimer;
    LinearLayout jumpingJacks, abdominalCrunches, russianTwist, mountainClimber, legRaises, crunches, plank, finishAbsBegginer, armRaises, sideArmRaises, tricepsDips, diamondPushUps, pushUps, punches, inchworms, tricepsStretchLeft, tricepsStretchRight, finishArmsBegginer, inclinePushUps, widePushUps, kneePushUps, finishChestBegginer, sideHop, squats, backwardLunge, donkeyKickLeft, donkeyKickRight, wallCalfRaises, finishLegsBegginer, rhomboidPulls, armScissors, finishBackBegginer, ropeJumping, burpees, forwardLungeLeft, forwardLungeRight, finishWeightBegginer;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    long timeLeft;
    boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_content);



        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        titleText = findViewById(R.id.txtTitle);

        finishAbsBegginer = findViewById(R.id.FinishAbsBegginer);
        finishArmsBegginer = findViewById(R.id.FinishArmsBegginer);
        finishChestBegginer = findViewById(R.id.FinishChestBegginer);
        finishLegsBegginer = findViewById(R.id.FinishLegsBegginer);
        finishBackBegginer = findViewById(R.id.FinishBackBegginer);
        finishWeightBegginer = findViewById(R.id.FinishWeightBegginer);
        jumpingJacks = findViewById(R.id.JumpingJacks);
        abdominalCrunches = findViewById(R.id.AbdominalCrunches);
        russianTwist = findViewById(R.id.RussianTwist);
        mountainClimber = findViewById(R.id.MountainClimber);
        legRaises = findViewById(R.id.LegRaises);
        crunches = findViewById(R.id.Crunches);
        plank = findViewById(R.id.Plank);
        armRaises = findViewById(R.id.ArmsRaises);
        sideArmRaises = findViewById(R.id.SideArmsRaises);
        tricepsDips = findViewById(R.id.TricepsDips);
        diamondPushUps = findViewById(R.id.DiamondPushUps);
        pushUps = findViewById(R.id.PushUps);
        punches = findViewById(R.id.Punches);
        inchworms = findViewById(R.id.Inchworms);
        tricepsStretchLeft = findViewById(R.id.TricepsStretchLeft);
        tricepsStretchRight = findViewById(R.id.TricepsStretchRight);
        inclinePushUps = findViewById(R.id.InclinePushUps);
        widePushUps = findViewById(R.id.WidePushUps);
        kneePushUps = findViewById(R.id.KneePushUps);
        sideHop = findViewById(R.id.SideHop);
        squats = findViewById(R.id.Squats);
        backwardLunge = findViewById(R.id.BackwardLunge);
        donkeyKickLeft = findViewById(R.id.DonkeyKicksLeft);
        donkeyKickRight = findViewById(R.id.DonkeyKicksRight);
        wallCalfRaises = findViewById(R.id.WallCalfRaises);
        rhomboidPulls = findViewById(R.id.RhomboidPulls);
        armScissors = findViewById(R.id.ArmScissors);
        ropeJumping = findViewById(R.id.RopeJumping);
        burpees = findViewById(R.id.Burpees);
        forwardLungeLeft = findViewById(R.id.ForwardLungeLeft);
        forwardLungeRight = findViewById(R.id.ForwardLungeRight);


        //--Butons
        btnStartJumpingJacks = findViewById(R.id.btnStartJumpingJacks);//
        btnStartJumpingJacks.setOnClickListener(this);
        btnStartPlank = findViewById(R.id.btnStartPlank);//
        btnStartPlank.setOnClickListener(this);
        btnStartArmRaises = findViewById(R.id. btnStartArmsRaises);//
        btnStartArmRaises.setOnClickListener(this);
        btnStartSideArmRaises = findViewById(R.id.btnStartSideArmsRaises);//
        btnStartSideArmRaises.setOnClickListener(this);
        btnStartPunches = findViewById(R.id.btnStartPunches);//
        btnStartPunches.setOnClickListener(this);
        btnStartTricepsStretchLeft = findViewById(R.id.btnStartTricepsStretchLeft);//
        btnStartTricepsStretchLeft.setOnClickListener(this);
        btnStartTricepsStretchRight = findViewById(R.id.btnStartTricepsStretchRight);//
        btnStartTricepsStretchRight.setOnClickListener(this);
        btnStartSideHop = findViewById(R.id.btnStartSideHop);
        btnStartSideHop.setOnClickListener(this);
        btnStartArmScissors = findViewById(R.id.btnStartArmScissors);
        btnStartArmScissors.setOnClickListener(this);
        btnStartRopeJumping = findViewById(R.id.btnStartRopeJumping);
        btnStartRopeJumping.setOnClickListener(this);




        switch (Exercises.E.j){
            case 1:
                titleText.setText(R.string.AbsBeginner);
                jumpingJacks.setVisibility(View.VISIBLE);
                countdownText = findViewById(R.id.txtcountDownJumpingJacks);
                timeLeft = 30000;
                updateTimer();
                break;
            case 2:
                titleText.setText(R.string.ArmsBeginner);
                armRaises.setVisibility(View.VISIBLE);
                countdownText = findViewById(R.id.txtcountDownArmRaises);
                timeLeft = 30000;
                updateTimer();
                break;
            case 3:
                Exercises.E.counter = 1;
                titleText.setText(R.string.ChestBeginner);
                jumpingJacks.setVisibility(View.VISIBLE);
                countdownText = findViewById(R.id.txtcountDownJumpingJacks);
                timeLeft = 30000;
                updateTimer();
                break;
            case 4:
                Exercises.E.counter = 1;
                titleText.setText(R.string.LegsBeginner);
                sideHop.setVisibility(View.VISIBLE);
                countdownText = findViewById(R.id.txtcountSideHop);
                timeLeft = 30000;
                updateTimer();
                break;
            case 5:
                Exercises.E.counter = 1;
                titleText.setText(R.string.BackBeginner);
                jumpingJacks.setVisibility(View.VISIBLE);
                countdownText = findViewById(R.id.txtcountDownJumpingJacks);
                timeLeft = 30000;
                updateTimer();
                break;
            case 6:
                Exercises.E.counter = 1;
                titleText.setText(R.string.WeightBeginner);
                jumpingJacks.setVisibility(View.VISIBLE);
                countdownText = findViewById(R.id.txtcountDownJumpingJacks);
                timeLeft = 30000;
                updateTimer();
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNext:
                if(Exercises.E.j == 1){

                    if(jumpingJacks.getVisibility() == View.VISIBLE) {
                        jumpingJacks.setVisibility(View.GONE);
                        stoptimer();
                        abdominalCrunches.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(abdominalCrunches.getVisibility() == View.VISIBLE){
                        abdominalCrunches.setVisibility(View.GONE);
                        russianTwist.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(russianTwist.getVisibility() == View.VISIBLE){
                        russianTwist.setVisibility(View.GONE);
                        mountainClimber.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(mountainClimber.getVisibility() == View.VISIBLE){
                        mountainClimber.setVisibility(View.GONE);
                        legRaises.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(legRaises.getVisibility() == View.VISIBLE){
                        legRaises.setVisibility(View.GONE);
                        crunches.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(crunches.getVisibility() == View.VISIBLE){
                        crunches.setVisibility(View.GONE);
                        countdownText = findViewById(R.id.txtcountDownPlank);
                        timeLeft = 30000;
                        plank.setVisibility(View.VISIBLE);
                        updateTimer();
                        break;
                    }
                    if(plank.getVisibility() == View.VISIBLE){
                        plank.setVisibility(View.GONE);
                        stoptimer();
                        finishAbsBegginer.setVisibility(View.VISIBLE);
                        btnNext.setText(R.string.Exit);
                        break;
                    }
                    if(finishAbsBegginer.getVisibility() == View.VISIBLE) {
                        startActivity(new Intent(ExerciseContent.this, Home.class));
                        break;
                    }
                }
                if(Exercises.E.j == 2){

                    if(armRaises.getVisibility() == View.VISIBLE) {
                        armRaises.setVisibility(View.GONE);
                        stoptimer();
                        countdownText = findViewById(R.id.txtcountSideArmsRaises);
                        timeLeft = 30000;
                        sideArmRaises.setVisibility(View.VISIBLE);
                        updateTimer();
                        break;
                    }
                    if(sideArmRaises.getVisibility() == View.VISIBLE){
                        sideArmRaises.setVisibility(View.GONE);
                        stoptimer();
                        tricepsDips.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(tricepsDips.getVisibility() == View.VISIBLE){
                        tricepsDips.setVisibility(View.GONE);
                        diamondPushUps.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(diamondPushUps.getVisibility() == View.VISIBLE){
                        diamondPushUps.setVisibility(View.GONE);
                        pushUps.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(pushUps.getVisibility() == View.VISIBLE){
                        pushUps.setVisibility(View.GONE);
                        countdownText = findViewById(R.id.txtcountPunches);
                        timeLeft = 30000;
                        punches.setVisibility(View.VISIBLE);
                        updateTimer();
                        break;
                    }
                    if(punches.getVisibility() == View.VISIBLE){
                        punches.setVisibility(View.GONE);
                        stoptimer();
                        inchworms.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(inchworms.getVisibility() == View.VISIBLE){
                        inchworms.setVisibility(View.GONE);
                        countdownText = findViewById(R.id.txtcountTricepsStretchsLeft);
                        timeLeft = 20000;
                        tricepsStretchLeft.setVisibility(View.VISIBLE);
                        updateTimer();
                        break;
                    }
                    if(tricepsStretchLeft.getVisibility() == View.VISIBLE){
                        tricepsStretchLeft.setVisibility(View.GONE);
                        stoptimer();
                        countdownText = findViewById(R.id.txtcountTricepsStretchRight);
                        timeLeft = 20000;
                        tricepsStretchRight.setVisibility(View.VISIBLE);
                        updateTimer();
                        break;
                    }
                    if(tricepsStretchRight.getVisibility() == View.VISIBLE){
                        tricepsStretchRight.setVisibility(View.GONE);
                        stoptimer();
                        finishArmsBegginer.setVisibility(View.VISIBLE);
                        btnNext.setText(R.string.Exit);
                        break;
                    }
                    if(finishArmsBegginer.getVisibility() == View.VISIBLE){
                        startActivity(new Intent(ExerciseContent.this, Home.class));
                        break;
                    }
                }
                if(Exercises.E.j == 3){
                    if(jumpingJacks.getVisibility() == View.VISIBLE) {
                        jumpingJacks.setVisibility(View.GONE);
                        stoptimer();
                        inclinePushUps.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(inclinePushUps.getVisibility() == View.VISIBLE && Exercises.E.counter == 1) {
                        inclinePushUps.setVisibility(View.GONE);
                        pushUps.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(pushUps.getVisibility() == View.VISIBLE && Exercises.E.counter == 1) {
                        pushUps.setVisibility(View.GONE);
                        widePushUps.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(widePushUps.getVisibility() == View.VISIBLE && Exercises.E.counter == 1) {
                        widePushUps.setVisibility(View.GONE);
                        kneePushUps.setVisibility(View.VISIBLE);
                         Exercises.E.counter = 2;
                        break;
                    }
                    if(kneePushUps.getVisibility() == View.VISIBLE) {
                        kneePushUps.setVisibility(View.GONE);
                        pushUps.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(pushUps.getVisibility() == View.VISIBLE && Exercises.E.counter == 2) {
                        pushUps.setVisibility(View.GONE);
                        inclinePushUps.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(inclinePushUps.getVisibility() == View.VISIBLE && Exercises.E.counter == 2) {
                        inclinePushUps.setVisibility(View.GONE);
                        widePushUps.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(widePushUps.getVisibility() == View.VISIBLE && Exercises.E.counter == 2) {
                        widePushUps.setVisibility(View.GONE);
                        finishChestBegginer.setVisibility(View.VISIBLE);
                        btnNext.setText(R.string.Exit);
                        break;
                    }
                    if(finishChestBegginer.getVisibility() == View.VISIBLE) {
                        startActivity(new Intent(ExerciseContent.this, Home.class));
                        break;
                    }

                }
                if(Exercises.E.j == 4){
                    if(sideHop.getVisibility() == View.VISIBLE) {
                        sideHop.setVisibility(View.GONE);
                        stoptimer();
                        squats.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(squats.getVisibility() == View.VISIBLE && Exercises.E.counter == 1) {
                        squats.setVisibility(View.GONE);
                        backwardLunge.setVisibility(View.VISIBLE);
                        Exercises.E.counter = 1;
                        break;
                    }
                    if(backwardLunge.getVisibility() == View.VISIBLE) {
                        backwardLunge.setVisibility(View.GONE);
                        donkeyKickLeft.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(donkeyKickLeft.getVisibility() == View.VISIBLE) {
                        donkeyKickLeft.setVisibility(View.GONE);
                        donkeyKickRight.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(donkeyKickRight.getVisibility() == View.VISIBLE) {
                        donkeyKickRight.setVisibility(View.GONE);
                        wallCalfRaises.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(wallCalfRaises.getVisibility() == View.VISIBLE) {
                        wallCalfRaises.setVisibility(View.GONE);
                        squats.setVisibility(View.VISIBLE);
                        Exercises.E.counter = 2;
                        break;
                    }
                    if(squats.getVisibility() == View.VISIBLE && Exercises.E.counter == 2) {
                        squats.setVisibility(View.GONE);
                        finishLegsBegginer.setVisibility(View.VISIBLE);
                        btnNext.setText(R.string.Exit);
                        break;
                    }
                    if(finishLegsBegginer.getVisibility() == View.VISIBLE) {
                        startActivity(new Intent(ExerciseContent.this, Home.class));
                        break;
                    }
                }
                if(Exercises.E.j == 5){
                    if(jumpingJacks.getVisibility() == View.VISIBLE) {
                        jumpingJacks.setVisibility(View.GONE);
                        stoptimer();
                        countdownText = findViewById(R.id.txtcountDownArmRaises);
                        timeLeft = 30000;
                        armRaises.setVisibility(View.VISIBLE);
                        updateTimer();
                        break;
                    }
                    if(armRaises.getVisibility() == View.VISIBLE && Exercises.E.counter == 1) {
                        armRaises.setVisibility(View.GONE);
                        stoptimer();
                        rhomboidPulls.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(rhomboidPulls.getVisibility() == View.VISIBLE && Exercises.E.counter == 1) {
                        rhomboidPulls.setVisibility(View.GONE);
                        countdownText = findViewById(R.id.txtcountSideArmsRaises);
                        timeLeft = 30000;
                        sideArmRaises.setVisibility(View.VISIBLE);
                        updateTimer();
                        break;
                    }
                    if(sideArmRaises.getVisibility() == View.VISIBLE && Exercises.E.counter == 1) {
                        sideArmRaises.setVisibility(View.GONE);
                        stoptimer();
                        countdownText = findViewById(R.id.txtcountContdownArmScissors);
                        timeLeft = 30000;
                        armScissors.setVisibility(View.VISIBLE);
                        updateTimer();
                        break;
                    }
                    if(armScissors.getVisibility() == View.VISIBLE && Exercises.E.counter == 1) {
                        armScissors.setVisibility(View.GONE);
                        stoptimer();
                        countdownText = findViewById(R.id.txtcountDownArmRaises);
                        timeLeft = 30000;
                        armRaises.setVisibility(View.VISIBLE);
                        updateTimer();
                        Exercises.E.counter = 2;
                        break;
                    }
                    if(armRaises.getVisibility() == View.VISIBLE && Exercises.E.counter == 2) {
                        armRaises.setVisibility(View.GONE);
                        rhomboidPulls.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(rhomboidPulls.getVisibility() == View.VISIBLE && Exercises.E.counter == 2) {
                        rhomboidPulls.setVisibility(View.GONE);
                        sideArmRaises.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(sideArmRaises.getVisibility() == View.VISIBLE && Exercises.E.counter == 2) {
                        sideArmRaises.setVisibility(View.GONE);
                        finishBackBegginer.setVisibility(View.VISIBLE);
                        btnNext.setText(R.string.Exit);
                        break;
                    }
                    if(finishBackBegginer.getVisibility() == View.VISIBLE) {
                        startActivity(new Intent(ExerciseContent.this, Home.class));
                        break;
                    }
                }
                if(Exercises.E.j == 6){
                    if(jumpingJacks.getVisibility() == View.VISIBLE) {
                        jumpingJacks.setVisibility(View.GONE);
                        stoptimer();
                        countdownText = findViewById(R.id.txtcountDownRopeJumping);
                        timeLeft = 60000;
                        ropeJumping.setVisibility(View.VISIBLE);
                        updateTimer();
                        break;
                    }
                    if(ropeJumping.getVisibility() == View.VISIBLE && Exercises.E.counter == 1) {
                        ropeJumping.setVisibility(View.GONE);
                        stoptimer();
                        burpees.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(burpees.getVisibility() == View.VISIBLE) {
                        burpees.setVisibility(View.GONE);
                        forwardLungeLeft.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(forwardLungeLeft.getVisibility() == View.VISIBLE) {
                        forwardLungeLeft.setVisibility(View.GONE);
                        forwardLungeRight.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(forwardLungeRight.getVisibility() == View.VISIBLE) {
                        forwardLungeRight.setVisibility(View.GONE);
                        mountainClimber.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(mountainClimber.getVisibility() == View.VISIBLE) {
                        mountainClimber.setVisibility(View.GONE);
                        countdownText = findViewById(R.id.txtcountDownRopeJumping);
                        timeLeft = 60000;
                        ropeJumping.setVisibility(View.VISIBLE);
                        updateTimer();
                        Exercises.E.counter = 2;
                        break;
                    }
                    if(ropeJumping.getVisibility() == View.VISIBLE && Exercises.E.counter == 2) {
                        ropeJumping.setVisibility(View.GONE);
                        btnNext.setText(R.string.Exit);
                        finishWeightBegginer.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(finishWeightBegginer.getVisibility() == View.VISIBLE) {
                        startActivity(new Intent(ExerciseContent.this, Home.class));
                        break;
                    }
                }
                break;
            case R.id.btnStartJumpingJacks:
                startstop();
                break;
            case R.id.btnStartPlank:
                startstop();
                break;
            case R.id.btnStartArmsRaises:
                startstop();
                break;
            case R.id.btnStartSideArmsRaises:
                startstop();
                break;
            case R.id.btnStartPunches:
                startstop();
                break;
            case R.id.btnStartTricepsStretchLeft:
                startstop();
                break;
            case R.id.btnStartTricepsStretchRight:
                startstop();
                break;
            case R.id.btnStartSideHop:
                startstop();
                break;
            case R.id.btnStartArmScissors:
                startstop();
                break;
            case R.id.btnStartRopeJumping:
                startstop();
                break;
        }
    }

    public void startstop(){
        if(timerRunning){
            stoptimer();
        }else {
            starttimer();
        }
    }
    public void starttimer(){
        countDownTimer = new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        timerRunning = true;
    }
    public void stoptimer(){
        countDownTimer.cancel();
        timerRunning = false;

    }

    public void updateTimer(){
        int minutes = (int)timeLeft / 60000;
        int seconds = (int)timeLeft % 60000 / 1000;
        String timeleftText;

        timeleftText = "" + minutes;
        timeleftText += ":";
        if(seconds < 10){
            timeleftText += "0";
        }
        timeleftText += seconds;

        countdownText.setText(timeleftText);
    }
}