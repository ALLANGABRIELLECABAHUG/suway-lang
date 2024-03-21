package com.example.suwaylang;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

    public class MainActivity extends AppCompatActivity {

        private EditText goalBudgetEditText;
        private EditText dailySavedEditText;
        private Button updateButton;
        private TextView remainingBudgetTextView;

        private double goalBudget;
        private double savedAmount = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Initialize UI components
            goalBudgetEditText = findViewById(R.id.goal_budget_edit_text);
            dailySavedEditText = findViewById(R.id.daily_saved_edit_text);
            updateButton = findViewById(R.id.update_button);
            remainingBudgetTextView = findViewById(R.id.remaining_budget_text_view);

            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateSavedAmount();
                }
            });
        }

        private void updateSavedAmount() {
            String goalBudgetStr = goalBudgetEditText.getText().toString();
            String dailySavedStr = dailySavedEditText.getText().toString();

            if (goalBudgetStr.isEmpty() || dailySavedStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both goal budget and daily saved amount.", Toast.LENGTH_SHORT).show();
                return;
            }

            goalBudget = Double.parseDouble(goalBudgetStr);
            double dailySaved = Double.parseDouble(dailySavedStr);

            savedAmount += dailySaved;

            if (savedAmount >= goalBudget) {
                remainingBudgetTextView.setText("Congratulations! You have reached your goal budget.");
            } else {
                double remainingBudget = goalBudget - savedAmount;
                remainingBudgetTextView.setText(String.format("Remaining budget: $%.2f", remainingBudget));
            }

            // Clear input fields
            dailySavedEditText.setText("");
        }
    }