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
import com.example.myshare.SavedValues.SharedValues;

public class AddFriendDialogBuilder extends AppCompatDialogFragment {

    private AddFriendDialogBuilderListener mAddFriendDialogBuilderListener;
    private EditText nameEditText;
    private final SharedValues mSharedValues;

    public AddFriendDialogBuilder(SharedValues sharedValues) {
        mSharedValues = sharedValues;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_friend_alert_dialog, null);

        builder.setView(view)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = nameEditText.getText().toString();
                        if (!name.isEmpty()) {
                            mSharedValues.writePeopleCount(mSharedValues.readPeopleCount() + 1);
                            mAddFriendDialogBuilderListener.getFriendName(mSharedValues.readPeopleCount(), name);
                        }
                        mAddFriendDialogBuilderListener.callAnimation();

                    }
                })
                .setNegativeButton("Add Another", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = nameEditText.getText().toString();
                        if (!name.isEmpty()) {
                            mSharedValues.writePeopleCount(mSharedValues.readPeopleCount() + 1);
                            mAddFriendDialogBuilderListener.getFriendName(mSharedValues.readPeopleCount(), name);
                        }
                        mAddFriendDialogBuilderListener.callAddFriendAgain();
                    }
                });
        nameEditText = view.findViewById(R.id.friendNameEditText);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mAddFriendDialogBuilderListener = (AddFriendDialogBuilderListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must be implement NewTransactionDialogListener.");
        }

    }

    public interface AddFriendDialogBuilderListener {
        void getFriendName(int id, String name);

        void callAddFriendAgain();

        void callAnimation();
    }

}
