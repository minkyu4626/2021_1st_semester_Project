package com.example.layoutexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 위젯들
    Button Button_loginPage_login;
    Button Button_loginPage_singUp;
    EditText EditText_loginPage_IDInput;
    EditText EditText_loginPage_PasswordInput;
    TextView TextView_loginPage_validUser;

    //존재하는 회원과 그 회원의 비밀번호 (임시 사용 변수)
    String correctID = "minkyu4626";
    String correctPW = "1234";

    Boolean notNullInput = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        // 위젯 불러오기
        Button_loginPage_login = findViewById(R.id.Button_loginPage_login);
        Button_loginPage_singUp = findViewById(R.id.Button_loginPage_singUp);
        EditText_loginPage_IDInput = findViewById(R.id.EditText_loginPage_IDInput);
        EditText_loginPage_PasswordInput = findViewById(R.id.EditText_loginPage_PasswordInput);
        TextView_loginPage_validUser = findViewById(R.id.TextView_loginPage_validUser);

        // 입력 된 아이디가 데이터베이스에 존재하는 아이디인지 확인하고
        // 1) 존재하는 경우
        //    입력 된 비밀번호가 일치하는치 확인한다.
        //    일치하는 경우 로그인이 되어 데이터베이스로부터 회원정보를 받아 다음 intant로 넘어갈 때 같이 넘긴다.
        //    일치하지 않는 경우 textview에 "존재하지 않는 회원이거나 비밀번호가 일치하지 않습니다!"를 띄움
        // 2) 존재하지 않는 경우
        //    textview에 "존재하지 않는 회원이거나 비밀번호가 일치하지 않습니다!" 를 띄운다"

        EditText_loginPage_IDInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    Log.d("input: ", s.toString());
                    notNullInput = true;
                } else {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        EditText_loginPage_PasswordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    notNullInput = true;
                    Log.d("input: ", s.toString());
                } else {
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        Button_loginPage_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // input이 null인 경우 아래의 toString()함수에서 버그가 발생하므로 notNullInput 변수로 미리 체크한다.
                if (!notNullInput) {
                    TextView_loginPage_validUser.setText("ID, Password를 입력하세요!");
                    return;
                }

                // 각각 ID, password 값을 가지고 있는 변수
                String ID = EditText_loginPage_IDInput.getText().toString();
                String Password = EditText_loginPage_PasswordInput.getText().toString();

                Log.d("ID: ", ID);
                Log.d("PW: ", Password);
                // 서버에서 아이디 비번을 확인하여 일치하는 회원이 존재하고 비번이 맞을 때만 로그인이 되게,
                // 아닌 경우에는 "존재하지 않는 회원이거나 비밀번호가 일치하지 않습니다!" 출력
                /*
                    21.03.31 - 현재는 서버가 구현 되지 않은 상태에서 시연하므로 임의의
                    변수(correctID, correctPW를 통해 확인하도록 구현하겠음)
                */


                if (ID.equals(correctID) && Password.equals(correctPW)) {
                    Log.d("correct", "로그인 성공!");
                    // ID가 일치하는 경우 password도 확인
                    Intent intent = new Intent(MainActivity.this, Userpage.class);
                    startActivity(intent);
                } else {
                    TextView_loginPage_validUser.setText("존재하지 않는 회원이거나 비밀번호가 일치하지 않습니다!!");
                }
            }
        });

        // sing up button 클릭 시 화면 전환 구현
        Button_loginPage_singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SingUpPage_Activity.class);
                startActivity(intent);
            }
        });
    }
}