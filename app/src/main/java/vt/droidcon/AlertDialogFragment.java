package vt.droidcon;

/* Created by YAM on 1/22/2015.
 */import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

import com.vt.droidcon.R;


/**
 * Created by Microsoft on 1/12/2015.
 */
public class AlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle(context.getString(R.string.error_title)).setMessage(context.getString(R.string.error_msg))
                .setPositiveButton(context.getString(R.string.error_ok_buttontext), null);

        AlertDialog dialog = builder.create();
        return dialog;
    }
}


