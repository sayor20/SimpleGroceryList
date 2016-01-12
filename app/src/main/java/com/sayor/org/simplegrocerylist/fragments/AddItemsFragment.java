package com.sayor.org.simplegrocerylist.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.sayor.org.simplegrocerylist.R;
import com.sayor.org.simplegrocerylist.models.Grocery;
import com.sayor.org.simplegrocerylist.models.GroceryList;

public class AddItemsFragment extends android.support.v4.app.DialogFragment {

    public interface ListUpdateCallback {
        public void rvUpdateCallback();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        final View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add, null);
        final EditText etName = (EditText) v.findViewById(R.id.etName);
        final EditText etDesc = (EditText) v.findViewById(R.id.etDesc);
        final EditText etQty = (EditText) v.findViewById(R.id.etQty);
        final TextInputLayout til = (TextInputLayout) (v.findViewById(R.id.til));

        AlertDialog ad = new AlertDialog.Builder(getActivity()).setTitle("Add Items")
                .setView(v)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GroceryList groceryList = GroceryList.get(getActivity());
                        groceryList.addGrocery(new Grocery(etName.getText().toString(), etDesc.getText().toString(),
                                Integer.parseInt(etQty.getText().toString().length()>0?etQty.getText().toString():"-1"), false));
                        mListener.rvUpdateCallback();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        ad.show();

        final View positiveAction = ad.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveAction.setEnabled(false);

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    til.setErrorEnabled(false);
                    positiveAction.setEnabled(true);
                } else {
                    positiveAction.setEnabled(false);
                    til.setErrorEnabled(true);
                    til.setError("Please enter name");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return ad;
    }

    // Use this instance of the interface to deliver action events
    ListUpdateCallback mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (ListUpdateCallback) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
