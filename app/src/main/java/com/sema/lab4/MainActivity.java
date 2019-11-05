package com.sema.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;

import com.sema.lab4.tasks.TaskListContent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addClick(View view){
        EditText taskTitleEditTxt = findViewById(R.id.taskTitle);
        EditText taskDescriptionEditTxt = findViewById(R.id.taskDescription);
        Spinner drawableSpinner = findViewById(R.id.drawableSpinner);
        String taskTitle = taskTitleEditTxt.getText().toString();
        String taskDescription = taskDescriptionEditTxt.getText().toString();
        String selectedImage = drawableSpinner.getSelectedItem().toString();

        if(taskTitle.isEmpty() && taskDescription.isEmpty()){
            TaskListContent.addItem(new TaskListContent.Task("Task" + TaskListContent.ITEMS.size() +1, getString(R.string.default_title), getString(R.string.default_description), selectedImage));

        }
        else{
            if(taskTitle.isEmpty())
                taskTitle = getString(R.string.default_title);
            if(taskDescription.isEmpty())
                taskDescription = getString(R.string.default_description);

            TaskListContent.addItem(new TaskListContent.Task("Task" + TaskListContent.ITEMS.size() +1, taskTitle, taskDescription, selectedImage));
        }

        ((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();

        taskTitleEditTxt.setText("");
        taskDescriptionEditTxt.setText("");

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }
}
