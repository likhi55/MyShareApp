package com.example.myshare.DialogBuilders;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.myshare.R;

public class NewTransactionsDialog extends AppCompatDialogFragment {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private NewTransactionDialogListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.transaction_title_and_description, null);

        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity
                        String title = titleEditText.getText().toString();
                        String description = descriptionEditText.getText().toString();
                        mListener.returnTitleAndDescription(title, description);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the negative button event back to the host activity
                    }
                });

        titleEditText = view.findViewById(R.id.titleEditText);
        descriptionEditText = view.findViewById(R.id.descriptionEditText);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (NewTransactionDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must be implement NewTransactionDialogListener.");
        }

    }

    public interface NewTransactionDialogListener {
        void returnTitleAndDescription(String title, String description);
    }
}
