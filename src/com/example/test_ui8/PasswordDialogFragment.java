package com.example.test_ui8;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordDialogFragment extends DialogFragment {

	protected Intent mIntent;
	protected Intent mExitIntent;

	PasswordDialogFragment(Intent intent, Intent exitIntent) {
		mIntent = intent;
		mExitIntent = exitIntent;
	}

	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		LayoutInflater inflater = getActivity().getLayoutInflater();

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setView(inflater.inflate(R.layout.password_dialog, null));
		builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					getActivity().finish();
					return true;
				}
				return false;
			}
		});

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
									Toast.makeText(getActivity(),
											R.string.login_successful,
											Toast.LENGTH_SHORT).show();
									MainActivity.LOGIN_STATUS = true;
									startActivity(mIntent);
									if (mIntent.getBooleanExtra(
											"com.example.test_ui8.EXIT", false)) {
										getActivity().finish();
									}
								} else {
									Toast.makeText(getActivity(),
											R.string.wrong_pw,
											Toast.LENGTH_SHORT).show();
									getActivity().finish();
									startActivity(mExitIntent);
								}
							}
						})
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								if (mExitIntent.getBooleanExtra(
										"com.example.test_ui8.EXIT", false)) {
									Toast.makeText(getActivity(),
											R.string.abort_login,
											Toast.LENGTH_SHORT).show();
									getActivity().finish();
								}
							}
						});
		Dialog dialog=builder.create();
		//prevents user from closing dialog by touching outside
		dialog.setCanceledOnTouchOutside(false);
		return dialog;
	}


}