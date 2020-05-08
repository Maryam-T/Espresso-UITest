package com.marand.espressouitest.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.input.DialogInputExtKt;
import com.marand.espressouitest.R;

public class DialogActivity extends AppCompatActivity {
    private static final String TAG = DialogActivity.class.getSimpleName();
    private TextView mText_name;
    private Button mButton_launch_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        initView();
        mButton_launch_dialog.setOnClickListener(view -> showDialog());
    }

// -------------------------------------------------------------------------------------------------

    private void initView() {
        mText_name = findViewById(R.id.text_name);
        mButton_launch_dialog = findViewById(R.id.button_launch_dialog);
    }

    private void showDialog() {
        MaterialDialog dialog = new MaterialDialog(this, MaterialDialog.getDEFAULT_BEHAVIOR());
        dialog.title(null, getString(R.string.text_enter_name));
        DialogInputExtKt.input(dialog, null, null, null,
                null, InputType.TYPE_CLASS_TEXT, 1000,
                true, false, null);
        dialog.positiveButton(null, getString(R.string.text_ok), materialDialog -> {
            setNameToTextView(DialogInputExtKt.getInputField(dialog).getText().toString());
            showToast(buildToastMessage(DialogInputExtKt.getInputField(dialog).getText().toString()));
            return null;
        });
        dialog.show();
    }

    private void setNameToTextView(String name){
        mText_name.setText(name);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public static String buildToastMessage(String name) {
        return "Your name is "+name;
    }
}
