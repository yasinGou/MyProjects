package com.yasin.passwordmanage.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.yasin.passwordmanage.R;
import com.yasin.passwordmanage.database.DataContracs;
import com.yasin.passwordmanage.database.DatabaseTools;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText title;
    private EditText user;
    private EditText password;
    private EditText website;
    private EditText note;
    private Button edit;
    private long id;
    public Button save;
    private String add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = (EditText) findViewById(R.id.title);
        user = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        website = (EditText) findViewById(R.id.website);
        note = (EditText) findViewById(R.id.note);
        edit = (Button) findViewById(R.id.edit);
        save = (Button) findViewById(R.id.save);
        id = getIntent().getLongExtra("recordId", -1);
        add = getIntent().getStringExtra("add");
        if (add != null) {
            edit.setVisibility(View.GONE);
            title.setInputType(EditorInfo.TYPE_CLASS_TEXT);
            title.setEnabled(true);
            user.setInputType(EditorInfo.TYPE_CLASS_TEXT);
            user.setEnabled(true);
            website.setInputType(EditorInfo.TYPE_CLASS_TEXT);
            website.setEnabled(true);
            note.setInputType(EditorInfo.TYPE_CLASS_TEXT);
            note.setEnabled(true);
            password.setInputType(EditorInfo.TYPE_CLASS_TEXT);
            password.setEnabled(true);
        }
        if (id!=-1) {
            Cursor cursor = DatabaseTools.getInstance(this).queryData(id);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    int index = cursor.getColumnIndex(DataContracs.Password.TITLE);
                    if (index != -1) {
                        title.setText(cursor.getString(index));
                    }
                    index = cursor.getColumnIndex(DataContracs.Password.USER);
                    if (index != -1) {
                        user.setText(cursor.getString(index));
                    }
                    index = cursor.getColumnIndex(DataContracs.Password.PASSWOERD);
                    if (index != -1) {
                        password.setText(cursor.getString(index));
                    }
                    index = cursor.getColumnIndex(DataContracs.Password.WEBSITE);
                    if (index != -1) {
                        website.setText(cursor.getString(index));
                    }
                    index = cursor.getColumnIndex(DataContracs.Password.NOTE);
                    if (index != -1) {
                        note.setText(cursor.getString(index));
                    }
                }
                cursor.close();
            }

        }
        edit.setOnClickListener(this);
        save.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit:
                title.setEnabled(true);
                title.setInputType(EditorInfo.TYPE_CLASS_TEXT);
                user.setEnabled(true);
                user.setInputType(EditorInfo.TYPE_CLASS_TEXT);
                website.setEnabled(true);
                website.setInputType(EditorInfo.TYPE_CLASS_TEXT);
                note.setEnabled(true);
                note.setInputType(EditorInfo.TYPE_CLASS_TEXT);
                password.setEnabled(true);
                password.setInputType(EditorInfo.TYPE_CLASS_TEXT);
                break;
            case R.id.save:
                ContentValues values = new ContentValues();
                values.put("title",title.getText().toString());
                values.put("user",user.getText().toString());
                values.put("website",website.getText().toString());
                values.put("password",password.getText().toString());
                values.put("note",note.getText().toString());
                DatabaseTools.getInstance(this).updateData(id, values);
                if (add != null) {
                    ContentResolver resolver = getContentResolver();
                    Uri uri = Uri.parse("content://yasin.passwordmanage.providers.PassContentProvider/password");
                    Uri ret = resolver.insert(uri,values);
                    //  DatabaseTools.getInstance(this).insertData(values);
                    Toast.makeText(DetailActivity.this, "添加成功"+ret, Toast.LENGTH_SHORT).show();
                }else {
                    DatabaseTools.getInstance(this).updateData(id,values);
                    Toast.makeText(DetailActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }

    }
}
