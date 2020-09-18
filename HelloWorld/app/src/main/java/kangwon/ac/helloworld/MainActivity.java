package kangwon.ac.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
//    onCreate: activity가 생성됐을때 자동으로 호출되는 메소드
//    화면 회전이 발생시 자동 호출
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        res 폴더에 있는 내용들을 쉽게 사용하기 위해 생성
//        R: res folder
//        layout: layout folder
//        현재 activity가 실행되고 관리할 화면을 activity_main을 통해서 만들어라
//        resource id: 특정 파일을 지칭하기위한 이름

        //System.out.println("onCreate 메소드 호출");
        Log.d("test", "call onCreate");
    }

    public void onButton1Clicked(View v){
//        버튼을 추가한 후 해당 버튼에 대한 동작을 지정한 것
        Toast.makeText(this, "Correct 1 button pressed", Toast.LENGTH_LONG).show();
//        makeText: 버튼 클릭에 대해 피드백을 주는 메서드
    }

    public void onButton2Clicked(View v){
//        웹 브라우저 화면 전환
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(intent);
    }

    public void onButton3Clicked(View v){
//        전화화면 전환
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1000-1000"));
        startActivity(intent);
    }

    @Override
    // onCreate method 호출 이후 자동 호출
    // Activity가 정지 --> 활동상태로 돌아올때 호출
    protected void onStart() {
        super.onStart();

        Log.d("test", "call onStart");
    }

    @Override
//    onStart method 호출 이후 자동 호출
//    Activity가 일시 정지 되었다가 다시 돌아올때 호출(Pop up)
    protected void onResume() {
        super.onResume();

        Log.d("test", "call onResume");
    }

    @Override
//    Activity가 정지 상태가 되었다가 활동 상태로 돌아갈 때 onStart method 전에 호출
    protected void onRestart() {
        super.onRestart();

        Log.d("test", "call onRestart");
    }

    @Override
//    Activity가 일시 정지 상태가 될때 호출
//    화면상에서 완전히 사라지거나 현재 화면 위에 작은 팝업 창같은 것이 나타날때 호출
    protected void onPause() {
        super.onPause();

        Log.d("test", "call onPause");
    }

    @Override
//    Activity가 화면에서 사라질때 호출
    protected void onStop() {
        super.onStop();

        Log.d("test", "call onStop");
    }

    @Override
//    현재 액티비티의 수행이 완전히 종료되어 메모리상에서 제거될 때 호출
    protected void onDestroy() {
        super.onDestroy();

        Log.d("test", "call onDestroy");
    }
}