package com.example.myshare.DialogBuilders;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DeleteItemDialogBuilder extends AppCompatDialogFragment {

    private DeleteItemDialogBuilderListener mDeleteItemDialogBuilderListener;
    private String title;
    private int position;

    public DeleteItemDialogBuilder(String title, int position) {
        this.title = title;
        this.position = position;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete Item")
                .setMessage("Do you want to delete this item?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDeleteItemDialogBuilderListener.deleteItem(title, position);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mDeleteItemDialogBuilderListener = (DeleteItemDialogBuilderListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must be implement NewTransactionDialogListener.");
        }

    }

    public interface DeleteItemDialogBuilderListener {
        void deleteItem(String title, int position);
    }
}
