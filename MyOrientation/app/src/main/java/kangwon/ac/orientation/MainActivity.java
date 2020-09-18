package kangwon.ac.orientation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String name;
    EditText editText;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showToast("onCreate called");

        editText = findViewById(R.id.editext1);
        textView2 = findViewById(R.id.textview2);

        Button button = findViewById(R.id.button);
        if(button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editText != null) {
                        name = editText.getText().toString();

                        showToast("Allocate User input value to name variable");
                    }
                }
            });
        }

        if(savedInstanceState != null) {
            if(textView2 != null) {
                name = savedInstanceState.getString("name");
                textView2.setText(name);

                showToast("restore value: " + name);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name", name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        showToast("onDestroy somthing");
    }
    public void showToast(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}