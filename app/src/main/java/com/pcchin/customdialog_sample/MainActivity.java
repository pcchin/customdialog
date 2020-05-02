package com.pcchin.customdialog_sample;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.pcchin.customdialog.DefaultDialogFragment;
import com.pcchin.customdialog.DismissibleDialogFragment;

public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder defaultDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets the default dialog
        defaultDialog = new AlertDialog.Builder(MainActivity.this)
                .setPositiveButton(android.R.string.ok, (dialog, which) ->
                        Toast.makeText(MainActivity.this, R.string.toast_ok, Toast.LENGTH_SHORT).show())
                .setNegativeButton(android.R.string.cancel, (dialog, which) ->
                        Toast.makeText(MainActivity.this, R.string.toast_cancel, Toast.LENGTH_SHORT).show())
                .setNeutralButton(R.string.text_2, (dialog, which) ->
                        Toast.makeText(MainActivity.this, R.string.toast_learn_more, Toast.LENGTH_SHORT).show());
        findViewById(R.id.button_1).setOnClickListener(this::defaultDialogFragment);
        findViewById(R.id.button_2).setOnClickListener(this::defaultDialogFragmentRotatable);
        findViewById(R.id.button_3).setOnClickListener(this::dismissibleDialogFragment);
        findViewById(R.id.button_4).setOnClickListener(this::dismissibleDialogFragmentShow);
    }

    // Button 1
    public void defaultDialogFragment(View view) {
        // Default Dialog Fragment is displayed
        DefaultDialogFragment fragment = new DefaultDialogFragment(
                setOnShowAndOnDismiss(defaultDialog
                        .setTitle(R.string.button_1)
                        .setMessage(R.string.button_1_text)
                        .create()),
                getSupportFragmentManager(), "DefaultDialogFragment");
        fragment.setRotatable(false);
        fragment.show();
    }

    // Button 2
    public void defaultDialogFragmentRotatable(View view) {
        // Default dialog fragment that can be rotated
        DefaultDialogFragment fragment = new DefaultDialogFragment(
                setOnShowAndOnDismiss(defaultDialog
                        .setTitle(R.string.button_2)
                        .setMessage(R.string.button_2_text)
                        .create()), getSupportFragmentManager());
        fragment.setRotatable(true);
        // Fragment tag can be set separately as well
        fragment.setFragmentTag("DefaultDialogFragment - Rotatable");
        fragment.show();
    }

    // Button 3
    public void dismissibleDialogFragment(View view) {
        // Dialog Fragment that is not automatically dismissed when a button is pressed
        // No neutral button for this one
        DismissibleDialogFragment fragment = new DismissibleDialogFragment(new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.button_3)
                .setMessage(R.string.button_3_text)
                .create());
        fragment.setPositiveButton(getString(android.R.string.ok), v -> {
            Toast.makeText(MainActivity.this, R.string.toast_ok, Toast.LENGTH_SHORT).show();
            fragment.dismiss();
        });
        fragment.setNegativeButton(getString(android.R.string.cancel), v ->
                Toast.makeText(MainActivity.this, R.string.toast_cancel, Toast.LENGTH_SHORT).show());
        // Conventional way
        fragment.show(getSupportFragmentManager(), "dismissibleDialogFragment");
    }

    // Button 4
    public void dismissibleDialogFragmentShow(View view) {
        // DialogFragment that is not automatically dismissed when pressed with onDialogShown overridden
        CustomDismissibleDialogFragment fragment = new CustomDismissibleDialogFragment(defaultDialog
                .setTitle(R.string.button_4)
                .setMessage(R.string.button_4_text)
                .create());
        fragment.setPositiveButton(getString(android.R.string.ok), v -> {
            Toast.makeText(MainActivity.this, R.string.toast_ok, Toast.LENGTH_SHORT).show();
            fragment.dismiss();
        });
        fragment.setNegativeButton(getString(android.R.string.cancel), v ->
                Toast.makeText(MainActivity.this, R.string.toast_cancel, Toast.LENGTH_SHORT).show());
        fragment.show(getSupportFragmentManager(), "CustomDismissibleDialogFragment");
    }

    // Static class for button 4
    public static class CustomDismissibleDialogFragment extends DismissibleDialogFragment {
        // Extension of DismissibleDialogFragment with onDialogShown utilized
        public CustomDismissibleDialogFragment() {
            super();
        }

        CustomDismissibleDialogFragment(AlertDialog alertDialog) {
            // Specify the super that you wish to use
            super(alertDialog);
        }

        @Override
        public void onDialogShown(@NonNull Dialog dialog) {
            Toast.makeText(dialog.getContext(), R.string.toast_on_show, Toast.LENGTH_SHORT).show();
        }
    }

    // Common functions used throughout some of the buttons
    @NonNull
    private AlertDialog setOnShowAndOnDismiss(@NonNull AlertDialog dialog) {
        // Sets the onShowListener and the onDismissListener for the dialog
        dialog.setOnShowListener(dialogInterface ->
                Toast.makeText(MainActivity.this, R.string.toast_on_show, Toast.LENGTH_SHORT).show());
        dialog.setOnDismissListener((dialogInterface -> Toast.makeText(MainActivity.this,
                R.string.toast_on_dismiss, Toast.LENGTH_SHORT).show()));
        return dialog;
    }
}
