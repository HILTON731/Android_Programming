package kangwon.ac.myintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
//                Context: 버튼을 예시로 들면 버튼이 포함된 주변 정보를 가진 것 (버튼이 포함된 레이아웃, 환경정보...), UI객체가 있으면 무조건 Context객체를 전달받도록 함
//                getApplicationContext: 앱에서 사용되는 공통 Context 참조
//                this: Activity객체 자체를 참조
                startActivityForResult(intent, 101); // Activity에 대한 응답을 받고싶은 경우, requestCode를 통해 어떤 Activity의 응답인지 구분 가능

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        menu --> main activity로 들어갈때 호출
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101){
            if(data != null){
                String name = data.getStringExtra("name");
                if(name != null){
                    Toast.makeText(this, "응답으로 받은 데이터 : "+name, Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}