package com.example.dbgreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dbgreen.utils.DBManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.text_1)
    TextView text_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.text_1,R.id.text_2,R.id.text_3})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.text_1:
                startActivity(new Intent(MainActivity.this,ReferencedJoinPropertyAcyivity.class));
                break;
            case R.id.text_2:
                startActivity(new Intent(MainActivity.this,JoinEntityActivity.class));
                break;
            case R.id.text_3:
                startActivity(new Intent(MainActivity.this,JoinPropertiesActivity.class));
                break;
        }

        }
    }

