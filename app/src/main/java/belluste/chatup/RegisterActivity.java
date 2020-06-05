package belluste.chatup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText mNome;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mPassword2;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mNome = findViewById(R.id.nameReg);
        mEmail = findViewById(R.id.emailReg);
        mPassword = findViewById(R.id.passwordReg);
        mPassword2 = findViewById(R.id.passwordReg2);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void clickSignUp(View view) {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if (nameCheck(mNome.getText().toString()) && emailCheck(email) && passwordCheck1(password) && passwoedCheck2(password, mPassword2.getText().toString())) {
            firebaseReg(email, password);
        } else {
            new AlertDialog.Builder(this).setTitle(R.string.error).setMessage(R.string.messaggio_errore_reg).setPositiveButton("OK", null).create().show();
        }
    }

    private void firebaseReg(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(RegisterActivity.this, R.string.registrazione_successo, Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent main = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(main);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, R.string.registrazione_errore, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void clickLogin(View view) {
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }


    //condizioni di validità per registrazione

    private boolean nameCheck(String name) {
        return name.length()>=6;
    }

    private boolean emailCheck(String email) {
        return email.contains("@");
    }

    private boolean passwordCheck1(String password) {
        if (password.length()>=8) {
            char[] pass = password.toCharArray();
            for (char c : pass) {
                if (Character.isDigit(c)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private boolean passwoedCheck2(String pass1, String pass2) {
        return pass1.equals(pass2);
    }
}
