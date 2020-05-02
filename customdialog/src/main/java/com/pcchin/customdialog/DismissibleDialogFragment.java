package com.pcchin.customdialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

/** A dialog fragment which would not be dismissed on a button press unless otherwise specified.
 * This would override all previous buttons set in the AlertDialog as well as the OnShowListener of the AlertDialog.
 * This class accepts both android.app.AlertDialog and androidx.appcompat.app.AlertDialog. **/
public class DismissibleDialogFragment extends DefaultDialogFragment {
    private String positiveButtonText, negativeButtonText, neutralButtonText;
    private Button.OnClickListener positiveButtonListener, negativeButtonListener, neutralButtonListener;

    //****** Start of constructors ******//
    // These constructors only allow android.app.AlertDialog and androidx.appcompat.app.AlertDialog to be used

    /** Default constructor. **/
    public DismissibleDialogFragment() {
        // Default constructor.
    }

    /** Default constructor.
     * @param dialog The dialog to be displayed. **/
    public DismissibleDialogFragment(android.app.AlertDialog dialog) {
        this(dialog, null);
    }

    /** Default constructor.
     * @param dialog The dialog to be displayed.
     * @param manager The fragment manager used to display the fragment. **/
    public DismissibleDialogFragment(android.app.AlertDialog dialog, FragmentManager manager) {
        this(dialog, manager, null);
    }

    /** Default constructor.
     * @param dialog The dialog to be displayed.
     * @param manager The fragment manager used to display the fragment.
     * @param tag The tag used for the fragment. **/
    public DismissibleDialogFragment(android.app.AlertDialog dialog, FragmentManager manager, String tag) {
        super(dialog, manager, tag);
    }

    /** Default constructor.
     * @param dialog The dialog to be displayed. **/
    public DismissibleDialogFragment(androidx.appcompat.app.AlertDialog dialog) {
        this(dialog, null);
    }

    /** Default constructor.
     * @param dialog The dialog to be displayed.
     * @param manager The fragment manager used to display the fragment. **/
    public DismissibleDialogFragment(androidx.appcompat.app.AlertDialog dialog, FragmentManager manager) {
        this(dialog, manager, null);
    }

    /** Default constructor.
     * @param dialog The dialog to be displayed.
     * @param manager The fragment manager used to display the fragment.
     * @param tag The tag used for the fragment. **/
    public DismissibleDialogFragment(androidx.appcompat.app.AlertDialog dialog, FragmentManager manager, String tag) {
        super(dialog, manager, tag);
    }
    
    //****** Start of overridden functions ******//

    /** Sets the onShowListener for the dialog, which sets the listeners for the buttons. **/
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                // Both are the same, its just that one is for the old AlertDialog and
                // another is for the new AlertDialog.
                if (dialogInterface instanceof android.app.AlertDialog) {
                    android.app.AlertDialog alertDialog =
                            (android.app.AlertDialog) dialogInterface; // Reduce redundant casting

                    Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    positiveButton.setText(positiveButtonText);
                    positiveButton.setOnClickListener(positiveButtonListener);

                    Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                    negativeButton.setText(negativeButtonText);
                    negativeButton.setOnClickListener(negativeButtonListener);

                    Button neutralButton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
                    neutralButton.setText(neutralButtonText);
                    neutralButton.setOnClickListener(neutralButtonListener);

                    onDialogShown(alertDialog);
                } else if (dialogInterface instanceof androidx.appcompat.app.AlertDialog) {
                    androidx.appcompat.app.AlertDialog alertDialog =
                            (androidx.appcompat.app.AlertDialog) dialogInterface; // Reduce redundant casting

                    Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    positiveButton.setText(positiveButtonText);
                    positiveButton.setOnClickListener(positiveButtonListener);

                    Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                    negativeButton.setText(negativeButtonText);
                    negativeButton.setOnClickListener(negativeButtonListener);

                    Button neutralButton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
                    neutralButton.setText(neutralButtonText);
                    neutralButton.setOnClickListener(neutralButtonListener);

                    onDialogShown(alertDialog);
                } else {
                    throw new IllegalStateException("Dialog provided is not an instance of " +
                            "android.app.AlertDialog or androidx.appcompat.app.AlertDialog");
                }
            }
        });
        return dialog;
    }

    //****** Start of custom functions ******//
    
    /** Sets the listener for the positive button.
     * @param text The text for the button.
     * @param listener The OnClickListener for the button. **/
    public void setPositiveButton(String text, Button.OnClickListener listener) {
        positiveButtonText = text;
        positiveButtonListener = listener;
    }

    /** Sets the listener for the negative button.
     * @param text The text for the button.
     * @param listener The OnClickListener for the button. **/
    public void setNegativeButton(String text, Button.OnClickListener listener) {
        negativeButtonText = text;
        negativeButtonListener = listener;
    }

    /** Sets the listener for the neutral button.
     * @param text The text for the button.
     * @param listener The OnClickListener for the button. **/
    public void setNeutralButton(String text, Button.OnClickListener listener) {
        neutralButtonText = text;
        neutralButtonListener = listener;
    }
    
    /** Set the function that would be run on the OnShowListener.
     * This function would run after the buttons are set.
     * If you wish to run a function within the onShowListener,
     * override this function within a static class that extends this function.
     * @param dialog The current AlertDialog **/
    public void onDialogShown(Dialog dialog) {
        // Stub method
    }
}
