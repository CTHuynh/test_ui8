package com.example.test_ui8;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.EditText;

public class CreateProfileDialogFragment extends DialogFragment {

	protected Intent mIntent;
	protected Intent mExitIntent;

	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		LayoutInflater inflater = getActivity().getLayoutInflater();

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setView(inflater.inflate(R.layout.profile_create_dialog, null));
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

		builder.setTitle(R.string.enter_name)
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								EditText f = (EditText) getDialog()
										.findViewById(R.id.profile_name);
								String input = f.getText().toString();

								MainActivity.PROFILE_LIST.add(input);									
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