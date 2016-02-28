package com.example.saito.templatemethod;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView textLog;
    private long sqliteTime;
    private long realmTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        textLog = (TextView)findViewById(R.id.text_log);

        Button button = (Button)findViewById(R.id.button_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteClass sqLite = new SQLiteClass();
                RealmClass realm = new RealmClass();

                sqliteTime = sqLite.Keisoku();
                realmTime = realm.Keisoku();
                textLog.setText("sqliteTime:" + sqliteTime + "/n realmTime" + realmTime);
            }
        });

    }


    private String TAG = getClass().getName();

    public abstract class DBBenchMark {

        abstract public void insert();
        abstract public void update();
        abstract public void delete();

        private String className = getClass().getName();

        public long Keisoku() {
            Calendar calendar = Calendar.getInstance();
            final long start = calendar.getTimeInMillis();
            insert();
            update();
            delete();
            final long finish = calendar.getTimeInMillis() + 1;
            long time = finish - start;
            Log.v(TAG, className + "：計測時間→ " + time);
            return time;
        }
    }

    private class SQLiteClass extends DBBenchMark {
        private String className = getClass().getName();

        public SQLiteClass() {
            // TODO DBインスタンス作成
        }

        @Override
        public void insert() {
            Log.v(TAG, className + "：insert処理");
        }

        @Override
        public void update() {
            Log.v(TAG, className + "：update処理");
        }

        @Override
        public void delete() {
            Log.v(TAG, className +"：delete処理");
        }
    }

    private class RealmClass extends DBBenchMark{
        private String className = getClass().getName();

        public RealmClass() {
            // TODO DBインスタンス作成
        }

        @Override
        public void insert() {
            Log.v(TAG, className + "：insert処理");
        }

        @Override
        public void update() {
            Log.v(TAG, className + "：update処理");
        }

        @Override
        public void delete() { Log.v(TAG, className +"：delete処理"); }
    }
}
