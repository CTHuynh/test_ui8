package com.example.test_ui8;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

public class DeleteProfileDialogFragment extends DialogFragment {

	protected Intent mIntent;
	protected Intent mExitIntent;
	protected int mItem;
	public ArrayList<String> local_list;
	private ListView lv;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		local_list = (ArrayList<String>) MainActivity.PROFILE_LIST.clone();
		local_list.remove(0);
		local_list.remove(0);

		CharSequence[] cs = local_list.toArray(new CharSequence[local_list
				.size()]);
		int key = 0;
//		LayoutInflater inflater = getActivity().getLayoutInflater();
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setSingleChoiceItems(cs, key++,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						mItem = item;
					}
				});

		builder.setTitle(R.string.delete_profile)
				.setPositiveButton(R.string.delete,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Toast.makeText(
										getActivity(),
										"Lokales Prolfil \""
												+ MainActivity.PROFILE_LIST
														.get(mItem + 2)
												+ "\" gelöscht.",
										Toast.LENGTH_SHORT).show();
								MainActivity.PROFILE_LIST.remove(mItem + 2);
							}
						})
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

							}
						});

		Dialog dialog = builder.create();

		// get soft keyboard when dialog is created
		dialog.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

		// prevents user from closing dialog by touching outside
		dialog.setCanceledOnTouchOutside(false);
		return dialog;
	}
}