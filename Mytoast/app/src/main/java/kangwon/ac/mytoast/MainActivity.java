package kangwon.ac.mytoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toastview = Toast.makeText(getApplicationContext(), "Toast mesage", Toast.LENGTH_LONG);
                toastview.setGravity(Gravity.TOP|Gravity.LEFT, 400, 800);
//                toastview.setView();
                toastview.show();
            }
        });
    }
}