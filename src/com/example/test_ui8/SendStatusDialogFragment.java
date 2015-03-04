package com.example.test_ui8;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class SendStatusDialogFragment extends DialogFragment implements
		OnItemClickListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		String[] report_messages = new String[10];
		int profile = MainActivity.PROFILE_STATUS;
		SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(getActivity());

		for (int i = 0; i < 10; i++) {
			String message = settings.getString("P" + profile + "StatusName"
					+ i, "");
			report_messages[i] = message;
		}

		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.status_report_dialog, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
				.setView(view)
				.setTitle(R.string.send_status_report)
				.setItems(report_messages,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int item) {
								// Do something with the selection
								Toast.makeText(getActivity(),
										R.string.status_report_sent,
										Toast.LENGTH_SHORT).show();
								
							}
						})
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// do something on cancel button click 
							}
						});

		AlertDialog status_report_dialog = builder.create();
		return status_report_dialog;
	}

	@Override
	public void onItemClick(AdapterView<?> lV, View view, int pos, long id) {
		Toast.makeText(getActivity(), "Statusmeldung verschickt",
				Toast.LENGTH_SHORT).show();
	}
}