package vudinhthanh.wallpaper.wallpaper_store;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Account extends AppCompatActivity {
    Button btnsu, btnsi;
    private FirebaseAuth mAuth;
    EditText edt1, edt2, edtdkname, edtdkmail, edtdkpass, edtdkpass2;
    Dialog dialog, dialog2;
    TextView txtDisplayname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        setTitle("Account");
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//         hiển thị nút Up ở Home icon
        actionBar.setDisplayHomeAsUpEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        addControll();
        addEvent();
    }

    private void addEvent() {
        btnsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2 = new Dialog(Account.this);
                dialog2.setTitle("Sing Up");
                dialog2.setContentView(R.layout.signup);
                dialog2.show();
                edtdkname = (EditText) dialog2.findViewById(R.id.txtdkname);
                edtdkmail = (EditText) dialog2.findViewById(R.id.txtdkmail);
                edtdkpass = (EditText) dialog2.findViewById(R.id.txtdkpass);
                edtdkpass2 = (EditText) dialog2.findViewById(R.id.txtdkpass2);
                Button btnLoadsignup = (Button) dialog2.findViewById(R.id.btnLoadsignup);

                btnLoadsignup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (edtdkpass.getText().length()==edtdkpass2.getText().length()) {
                            LoadSingup();
                        } else {
                            Toast.makeText(Account.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }
        });


        btnsi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (btnsi.getText() == "Logout") {
                    LogOut();
                }

                else {
                    dialog = new Dialog(Account.this);
                    dialog.setTitle("LogIn");
                    dialog.setContentView(R.layout.singin);
                    dialog.show();
                    edt1 = (EditText) dialog.findViewById(R.id.edtnamei);
                    edt2 = (EditText) dialog.findViewById(R.id.edtpassi);
                    Button btnLoadlogin = (Button) dialog.findViewById(R.id.loadLogin);
                    btnLoadlogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(edt1.getText().length()==0){
                                Toast.makeText(Account.this, "Please fill in the information", Toast.LENGTH_LONG).show();
                            }else {
                                LoadLogin();

                            }

                        }
                    });
                }


            }
        });
    }

    private void LoadSingup() {
        String email=edtdkmail.getText().toString();
        String password =edtdkpass.getText().toString();
       final String username=edtdkname.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Account.this, "Sign up Success", Toast.LENGTH_LONG).show();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(username).build();
                            user.updateProfile(profileUpdates);
                        } else {

                            Toast.makeText(Account.this, "Sign up Fail", Toast.LENGTH_LONG).show();
                        }


                    }
                });

    }

    private void LogOut() {
        FirebaseAuth.getInstance().signOut();
        btnsi.setText("Login");
        txtDisplayname.setText("You have logged out");
    }

    private void LoadLogin() {
        String email = edt1.getText().toString();
        String password = edt2.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
//                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                                    .setDisplayName("aaa").build();
//                            user.updateProfile(profileUpdates);
                            Toast.makeText(Account.this, "Success", Toast.LENGTH_LONG).show();
                            btnsi.setText("Logout");
                            dialog.dismiss();
                            txtDisplayname.setText(user.getDisplayName().toString());


                        } else {
                            edt1.setText("");
                            edt2.setText("");
                            Toast.makeText(Account.this, "Fail", Toast.LENGTH_LONG).show();
                        }


                    }
                });


    }

    private void addControll() {
        txtDisplayname = (TextView) findViewById(R.id.txtdisplayname);
        btnsu = (Button) findViewById(R.id.btnsu);
        btnsi = (Button) findViewById(R.id.btnsi);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            btnsi.setText("Logout");
            txtDisplayname.setText("Hello : " + currentUser.getDisplayName());
        } else {
            btnsi.setText("Login");
            txtDisplayname.setText("You are not logged in");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
