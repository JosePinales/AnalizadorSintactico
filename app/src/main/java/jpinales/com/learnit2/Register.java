package jpinales.com.learnit2;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText mName, mEmail, mPass;
    Button sign;
    TextView login;
    FirebaseAuth auth;
    String  name, email1, pass1;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //linking variables
        mName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mPass = findViewById(R.id.pass);
        sign=  findViewById(R.id.sign);
        login= findViewById(R.id.login1);

        auth = FirebaseAuth.getInstance();

        //Storage
        String sName = mName.getText().toString();
        String sEmail =  mEmail.getText().toString();
        String sPass= mPass.getText().toString();


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mName.getText().toString();
                email1 =  mEmail.getText().toString();
                pass1 = mPass.getText().toString();

                //Is Empty
                if (TextUtils.isEmpty(name)){
                    mName.setError("Full Name is Required");
                    return;
                }

                if (TextUtils.isEmpty(email1)){
                    mEmail.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(pass1)){
                    mPass.setError("Password is Required");
                    return;
                }
                // Register the user in firebase
                auth.createUserWithEmailAndPassword(email1, pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            storeNewUserData();
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();

                           /*
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(),Welcome.class));
                            Intent window = new Intent(Register.this, Video.class);
                            startActivity(window);

                            */
                        }else {
                            Toast.makeText(Register.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    //Store Data Method
    private void storeNewUserData() {
        FirebaseDatabase rootNote = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNote.getReference("User");

       userHelperClass helperClass = new userHelperClass(name, email1,pass1);
       // reference.setValue("first commit");
        reference.child(name).setValue(helperClass);

    }

    //Back to the Log In
    public void backLogin(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

}