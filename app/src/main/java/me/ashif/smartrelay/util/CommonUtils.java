package me.ashif.smartrelay.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by asif on 27/2/17.
 */

public class CommonUtils {
    private static Toast sToast;

    public static void displayToast(String toast,Context context) {
        if (sToast != null) {
            sToast.cancel();
        }
        sToast = Toast.makeText(context, toast, Toast.LENGTH_SHORT);
        sToast.show();

    }
}
