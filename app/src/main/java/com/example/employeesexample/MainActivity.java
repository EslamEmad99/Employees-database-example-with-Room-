package com.example.employeesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.employess_tv);

        EmployeeDatabase database = EmployeeDatabase.getInstance(this);
        EmployeeDao dao = database.employeeDao();

//        Employee employee2 = new Employee("Ahmed");
//        Employee employee3 = new Employee("Mohamed");
//        Employee employee4 = new Employee("Ali");
//        Employee employee5 = new Employee("ibrahim");
//        dao.insert(employee2);
//        dao.insert(employee3);
//        dao.insert(employee4);
//        dao.insert(employee5);

        List<Employee> employees = dao.getAllEmployees();

        Employee employee = employees.get(employees.size() - 1);
//        employee.setName("Hasssan");
//        dao.update(employee);

        dao.delete(employee);

        for (int i= 0; i < employees.size(); i++){
            textView.append("name : " + employees.get(i).getName() + "\n");
        }
    }
}