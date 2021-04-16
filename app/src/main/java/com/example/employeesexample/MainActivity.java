package com.example.employeesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private List<Employee> employees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.employess_tv);

        EmployeeDatabase database = EmployeeDatabase.getInstance(this);
        EmployeeDao dao = database.employeeDao();

        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.insert(new Employee("Eslam" , 6000));
                employees = dao.getAllEmployees();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i= 0; i < employees.size(); i++){
                            textView.append("name : " + employees.get(i).getName() + " salary : " + employees.get(i).getSalary() + "\n");
                        }
                    }
                });
            }
        }).start();
    }
}