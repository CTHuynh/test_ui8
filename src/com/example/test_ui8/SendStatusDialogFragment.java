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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SendStatusDialogFragment extends DialogFragment implements
		OnItemClickListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater inflater = getActivity().getLayoutInflater();

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
				.setView(inflater.inflate(R.layout.status_report_dialog, null))
				.setTitle(R.string.send_status_report)
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

							}
						});

		Dialog dialog = builder.create();

		String[] report_messages = new String[10];
		ListView messageListView;
		int n = 0;
		int profile = MainActivity.PROFILE_STATUS;
		SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(getActivity());

		for (int i = 0; i < 10; i++) {

			String message = settings.getString("P" + profile + "StatusName"
					+ i, "not_found");
			if (!message.equals("not_found")) {
				report_messages[n] = message;
				n++;
			}
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, report_messages);

		messageListView = (ListView) dialog.findViewById(R.id.status_reports);
		messageListView.setAdapter(adapter);
		messageListView.setOnItemClickListener(this);

		return dialog;
	}

	@Override
	public void onItemClick(AdapterView<?> lV, View view, int pos, long id) {
		Toast.makeText(getActivity(), "Statusmeldung verschickt",
				Toast.LENGTH_SHORT).show();
	}
}