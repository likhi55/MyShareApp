package com.example.myshare.DialogBuilders;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.myshare.R;
import com.example.myshare.SavedValues.SharedValues;

public class SelfAddDialogBuilder extends AppCompatDialogFragment {

    private SelfAddDialogBuilderListener mSelfAddDialogBuilderListener;
    private EditText nameEditText;
    private SharedValues mSharedValues;

    public SelfAddDialogBuilder(SharedValues sharedValues) {
        mSharedValues = sharedValues;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_self_alert_dialog, null);

        builder.setView(view)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = nameEditText.getText().toString();
                        if (name.isEmpty()) {
                            Toast.makeText(getActivity(), "Name cannot be empty.", Toast.LENGTH_SHORT).show();
                        } else {
                            mSharedValues.writePeopleCount(1);
                            mSelfAddDialogBuilderListener.getName(mSharedValues.readPeopleCount(), name);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mSelfAddDialogBuilderListener.callAddFriend();
                    }
                });
        nameEditText = view.findViewById(R.id.nameEditText);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mSelfAddDialogBuilderListener = (SelfAddDialogBuilderListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must be implement NewTransactionDialogListener.");
        }

    }

    public interface SelfAddDialogBuilderListener {
        void getName(int id, String name);

        void callAddFriend();
    }

}
