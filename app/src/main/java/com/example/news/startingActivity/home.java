package com.example.news.startingActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.NewsActivity.MainActivity;
import com.example.news.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {


    private EditText mEmail, mPassword;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        EditText enterEmail;
        ImageButton cancelButton;
        Button aSend;


        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        Button fLogin = findViewById(R.id.btn_signin);
        mAuth = FirebaseAuth.getInstance();
        TextView mForgotPassword = findViewById(R.id.tvf);
        ImageView upImage = findViewById(R.id.mn_logo);


        View altercustomdialog = LayoutInflater.from(home.this).inflate(R.layout.alterdialog,null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(home.this);

        alertDialog.setView(altercustomdialog);
        cancelButton = (ImageButton) altercustomdialog.findViewById(R.id.cancelID);
        enterEmail = (EditText) altercustomdialog.findViewById(R.id.rstreset);
        aSend = (Button) altercustomdialog.findViewById(R.id.alrtSend);




        Animation rightAnim = AnimationUtils.loadAnimation(this, R.anim.button_anim_right);
        Animation delayrightAnim = AnimationUtils.loadAnimation(this, R.anim.delay_right_button_anim);
        Animation topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        Animation bottomAnim = AnimationUtils.loadAnimation(this, R.anim.delay_button_bottom);
        Animation delaybottom = AnimationUtils.loadAnimation(this, R.anim.delay_bottom_anim);


        mEmail.setAnimation(rightAnim);
        mPassword.setAnimation(delayrightAnim);
        upImage.setAnimation(topAnim);
        fLogin.setAnimation(bottomAnim);
        mForgotPassword.setAnimation(delaybottom);


        fLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password= mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email Is Required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password Is Required");
                    return;
                }

                if (password.length() <6) {
                    mPassword.setError("Password Must Be 6 Character Long");
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(home.this,"Login", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(home.this, "Login Failed" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog dialog = alertDialog.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

                ViewGroup parent = (ViewGroup) v.getParent();
                parent.removeView(v);

                aSend.setOnClickListener(new View.OnClickListener(){


                    @Override
                    public void onClick(View view) {

                        String mail = enterEmail.getText().toString();



                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(home.this, "Reset Link Sent To Your email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(home.this, "Error!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                }
        });
    }
});



















        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        Button signIn = findViewById(R.id.btn_signin);
        TextView signUp = findViewById(R.id.tv_signup);
        TextView skip = findViewById(R.id.skip);
        TextView reset = findViewById(R.id.tvf);


       //Animation Part//
        Animation leftAnim = AnimationUtils.loadAnimation(this, R.anim.button_anim_left);


        signIn.setAnimation(leftAnim);
        signUp.setAnimation(rightAnim);
        reset.setAnimation(rightAnim);

        //Animation Finish//

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                overridePendingTransition(R.anim.slide_right, R.anim.slide_left_out);


            }
        });




        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.slide_left, R.anim.slide_left_out);
                finish();
            }
        });




    }



}



