package belluste.chatup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void clickLogin(View view) {
    }

    public void clickRegistrati(View view) {
        Intent reg = new Intent(this, RegisterActivity.class);
        startActivity(reg);
    }
}
