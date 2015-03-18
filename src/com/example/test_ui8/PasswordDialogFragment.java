package com.example.test_ui8;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordDialogFragment extends DialogFragment {

	OnPasswordCheckListener mCallback;
	int mKey;
	
	public PasswordDialogFragment(int key){
		mKey=key;
	}
	
    public interface OnPasswordCheckListener {
        public void onCheckPassword(boolean pw, int key);
    }
	
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnPasswordCheckListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    
    
    
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		LayoutInflater inflater = getActivity().getLayoutInflater();

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setView(inflater.inflate(R.layout.password_dialog, null));
		builder.setTitle(R.string.login_required)
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								EditText f = (EditText) getDialog()
										.findViewById(R.id.password);
								String input = f.getText().toString();

								SharedPreferences settings = PreferenceManager
										.getDefaultSharedPreferences(getActivity());
								String pw = settings.getString("Password",
										"");

								if (input.equals(pw)) {
									mCallback.onCheckPassword(true, mKey);
								} else {
									mCallback.onCheckPassword(false, mKey);
									Toast.makeText(getActivity(),
											R.string.wrong_pw,
											Toast.LENGTH_SHORT).show();
								}
							}
						})
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {		
							
							}
						});
		Dialog dialog=builder.create();
		
		//get soft keyboard when dialog is created
		dialog.getWindow().setSoftInputMode (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		
		//prevents user from closing dialog by touching outside
		dialog.setCanceledOnTouchOutside(false);
		return dialog;
	}


}