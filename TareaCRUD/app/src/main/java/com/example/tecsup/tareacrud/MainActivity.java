package com.example.tecsup.tareacrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    TextView lst;
    EditText studentid;
    EditText studentname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lst = (TextView) findViewById(R.id.list);
        studentid = (EditText) findViewById(R.id.studentID);
        studentname = (EditText) findViewById(R.id.studentName);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);


    }
    public void addStudent (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        int id = Integer.parseInt(studentid.getText().toString());
        String name = studentname.getText().toString();
        Student student = new Student(id,name);
        dbHandler.addHandler(student);
        studentid.setText("");
        studentname.setText("");
    }

    public void findStudent (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Student student = dbHandler.findHandler(studentname.getText().toString());
        if (student != null) {
            lst.setText(String.valueOf(student.getID()) +" "+ student.getStudentName());

        } else {
            lst.setText("No se encontraron coincidencias\n");
        }
    }

    public void loadStudents(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        lst.setText(dbHandler.loadHandler());
        studentid.setText("");
        studentname.setText("");
    }

    public void deleteStudent(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null,
                null, 1);
        boolean result = dbHandler.deleteHandler(Integer.parseInt(
                studentid.getText().toString()));
        if (result) {
            studentid.setText("");
            studentname.setText("");
            lst.setText("Registro actualizado");
        } else
            studentid.setText("No se encontraron coincidencias\n");
    }

    public void updateStudent(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null,
                null, 1);
        boolean result = dbHandler.updateHandler(Integer.parseInt(
                studentid.getText().toString()), studentname.getText().toString());
        if (result) {
            studentid.setText("");
            studentname.setText("");
            lst.setText("Registro actualizado");
        } else
            studentid.setText("No se encontraron coincidencias\n");
    }
}