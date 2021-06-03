package sg.edu.rp.id18044455.mydatabook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class VaccinationFragment extends Fragment {

    TextView tvVaccination;
    FloatingActionButton fabVaccination;
    Button btnEditVaccination;
    DrawerLayout drawerLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vaccinationFrag = inflater.inflate(R.layout.fragment_vaccination, container, false);

        tvVaccination = vaccinationFrag.findViewById(R.id.tvVaccination);
        fabVaccination = vaccinationFrag.findViewById(R.id.fabVaccination);
        btnEditVaccination = vaccinationFrag.findViewById(R.id.btnEditVaccination);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);

        btnEditVaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.edit_vaccination, null);

                final EditText etVaccination = viewDialog.findViewById(R.id.etVaccination);

                if (tvVaccination.getText().toString().trim().length() != 0){
                    etVaccination.setText(tvVaccination.getText().toString());
                }

                android.app.AlertDialog.Builder myBuilder = new android.app.AlertDialog.Builder(getActivity());
                myBuilder.setTitle("Edit Vaccination");
                myBuilder.setView(viewDialog);
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (etVaccination.getText().toString().trim().length() != 0){
                            tvVaccination.setText(etVaccination.getText().toString());
                        }//end of if
                        else{
                            etVaccination.setError("Text is required");
                        }
                    }
                });

                myBuilder.setNegativeButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });//end of btnEditBio

        fabVaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });


        return vaccinationFrag;
    }//end of onCreateView


    public void onPause() {
        super.onPause();

        String vaccination = tvVaccination.getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("vaccination", vaccination);
        prefEdit.commit();
    }//end of onPause()


    public void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String vaccination = prefs.getString("vaccination", "");
        tvVaccination.setText(vaccination);
    }//end of onPause()






}//end of class