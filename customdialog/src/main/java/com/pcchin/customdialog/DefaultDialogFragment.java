package com.pcchin.customdialog;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

/** A plain DialogFragment which allows for a Dialog to be displayed. **/
public class DefaultDialogFragment extends DialogFragment {
    private boolean rotatable;
    private String tag;
    private Dialog dialog;
    private FragmentManager manager;

    //****** Start of constructors ******//

    /** Default constructor. **/
    public DefaultDialogFragment() {
        // Default constructor.
        setRetainInstance(true);
    }

    /** Default constructor.
     * @param dialog The dialog to be displayed. **/
    public DefaultDialogFragment(Dialog dialog) {
        this(dialog, null);
    }

    /** Default constructor.
     * @param dialog The dialog to be displayed.
     * @param manager The fragment manager used to display the fragment. **/
    public DefaultDialogFragment(Dialog dialog, FragmentManager manager) {
        this(dialog, manager, null);
    }

    /** Default constructor.
     * @param dialog The dialog to be displayed.
     * @param manager The fragment manager used to display the fragment.
     * @param tag The tag used for the fragment. **/
    public DefaultDialogFragment(Dialog dialog, FragmentManager manager, String tag) {
        this.dialog = dialog;
        this.manager = manager;
        this.tag = tag;
        this.rotatable = true;
        setRetainInstance(true);
    }

    //****** Start of overridden functions ******//

    /** Dismiss the dialog if the last one is still showing and the dialog is not rotatable. **/
    @Override
    public void onStart() {
        if (!rotatable && dialog != null && dialog.isShowing()) {
            dismiss();
        }
        super.onStart();
    }

    /** Returns the set dialog. **/
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (dialog == null) {
            super.setShowsDialog(false);
        }
        return dialog;
    }

    /** Show the AlertDialog if the manager has been set. **/
    public void show() {
        if (manager == null) {
            throw new IllegalStateException("Fragment manager cannot be null");
        } else {
            super.show(manager, getFragmentTag());
        }
    }

    /** Show the AlertDialog through the method without the constructor. **/
    @Override
    public void show(@NonNull FragmentManager manager, String tag) {
        this.manager = manager;
        setFragmentTag(tag);
        show();
    }

    //****** Start of getters and setters ******//

    /** Gets whether the dialog instance would be retained when rotated. **/
    public boolean isRotatable() {
        return rotatable;
    }

    /** Sets whether the fragment is rotatable. The default is set to true. **/
    public void setRotatable(boolean rotatable) {
        this.rotatable = rotatable;
    }

    /** Sets a custom tag for the fragment. **/
    public void setFragmentTag(String tag) {
        this.tag = tag;
    }

    /** Gets the tag for the fragment. **/
    public String getFragmentTag() {
        return this.tag;
    }
}
