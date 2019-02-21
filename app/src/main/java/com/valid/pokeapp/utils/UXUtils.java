package com.valid.pokeapp.utils;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;

public class UXUtils {

    public static void showAlertDialog(Context context, String title, String message, String neutralBtnDesc) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, neutralBtnDesc,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}
