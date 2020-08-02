package jpinales.com.learnit2;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText mEmail, mPass;
    String pass;
    String user;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail= (EditText) findViewById(R.id.txtcam1);
        mPass= (EditText) findViewById(R.id.txtcam2);
        auth= FirebaseAuth.getInstance();
    }

    public void click(View view) {
        mEmail.setText("");
        mPass.setText("");
    }
    public void Signup(View view){
        Intent window = new Intent(MainActivity.this, Register.class);
        startActivity(window);
    }

    public void Login(View view){
        user=mEmail.getText().toString();
        pass=mPass.getText().toString();


        if (user.isEmpty()){
            Toast.makeText(this,"Email is Required", Toast.LENGTH_LONG).show();
        }
        if (pass.isEmpty()){
            Toast.makeText(this,"Password is Required", Toast.LENGTH_LONG).show();
        }else {
            auth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(getApplicationContext(),Welcome.class));
                        Intent window = new Intent(MainActivity.this, Seleccion.class);
                        startActivity(window);
                    }else{
                        Toast.makeText(MainActivity.this, "User o Password Incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    //Reset Password
    public void forget(View view){
        final TextView resetMail = new TextView(view.getContext());
        final AlertDialog.Builder passwordReset = new AlertDialog.Builder(view.getContext());
        user=mEmail.getText().toString();
        passwordReset.setTitle("Password Reset");
        passwordReset.setMessage("Are you sure you want to reset the password of " + user +"?");
        passwordReset.setView(resetMail);

        if (user.isEmpty() ){
            Toast.makeText(this,"Email is Required", Toast.LENGTH_LONG).show();
        }else {
            passwordReset.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Extract the email and sent reset link
                    String mail2 = resetMail.getText().toString();
                    auth.sendPasswordResetEmail(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(MainActivity.this, "Reset Link Was Sent Yo Your Email", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
            passwordReset.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // close the dialog
                }
            });
            passwordReset.create().show();
        }
    }
}
