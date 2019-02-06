package com.example.mahmoudalzoghby.secondaccessroomdb;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahmoudalzoghby.secondaccessroomdb.UserDB.UserDataBase;
import com.example.mahmoudalzoghby.secondaccessroomdb.UserDB.UserEntity;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText t1 , t2;
    private TextView t3 , t4;
    private UserDataBase UDB;
    private UserEntity user;
    private List<UserEntity> userEntityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (EditText) findViewById(R.id.textView2);
        t2 = (EditText) findViewById(R.id.textView3);
        UDB = UserDataBase.getInstance(MainActivity.this);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new UserEntity(t1.getText().toString() , t2.getText().toString());
                new InsertTask(MainActivity.this , user).execute();

                //Toast.makeText(MainActivity.this , "Saved Successfully" ,Toast.LENGTH_LONG).show();

                userEntityList = UserDataBase.getInstance(MainActivity.this).getUserDao().loadAllUsers();

                t3 = (TextView)findViewById(R.id.textView4);
                t4 = (TextView)findViewById(R.id.textView5);

                t3.setText(userEntityList.get(userEntityList.size()-1).getName());
                t4.setText(userEntityList.get(userEntityList.size()-1).getAddress());





            }
        });
    }

    private static class InsertTask extends AsyncTask<Void , Void , Boolean> {
        private WeakReference<MainActivity> weakReference;
        private UserEntity userEntity;

        InsertTask(MainActivity context, UserEntity user) {

            weakReference = new WeakReference<>(context);
            this.userEntity = user;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            long j = weakReference.get().UDB.getUserDao().insert(userEntity);
            Log.e("ID" , String.valueOf(j));
            return null;
        }
    }
}