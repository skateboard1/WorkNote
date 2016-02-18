package com.haoxuan.worknote.fragment.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.haoxuan.worknote.R;

/**
 * Created by skateboard on 16-2-15.
 */
public class AlertDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.network_error_title);
        builder.setMessage(R.string.network_error_message);
        builder.setNeutralButton(R.string.button_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        getActivity().finish();
            }
        });
        return builder.create();
    }
}
