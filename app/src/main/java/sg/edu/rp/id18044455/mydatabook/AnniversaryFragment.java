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


public class AnniversaryFragment extends Fragment {

    TextView tvAnniversary;
    FloatingActionButton fabAnniversary;
    Button btnEditAnniversary;
    DrawerLayout drawerLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View anniversaryFrag = inflater.inflate(R.layout.fragment_anniversary, container, false);

        tvAnniversary = anniversaryFrag.findViewById(R.id.tvAnniversary);
        fabAnniversary = anniversaryFrag.findViewById(R.id.fabAnniversary);
        btnEditAnniversary = anniversaryFrag.findViewById(R.id.btnEditAnniversary);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);

        btnEditAnniversary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.edit_anniversary, null);

                final EditText etAnniversary = viewDialog.findViewById(R.id.etAnniversary);

                if (tvAnniversary.getText().toString().trim().length() != 0){
                    etAnniversary.setText(tvAnniversary.getText().toString());
                }

                android.app.AlertDialog.Builder myBuilder = new android.app.AlertDialog.Builder(getActivity());
                myBuilder.setTitle("Edit Anniversary");
                myBuilder.setView(viewDialog);
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (etAnniversary.getText().toString().trim().length() == 0){
                            etAnniversary.setError("Text is required");
                        }//end of if
                        else{
                            tvAnniversary.setText(etAnniversary.getText().toString());
                        }
                    }
                });

                myBuilder.setNegativeButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });//end of btnEditBio

        fabAnniversary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });




        return anniversaryFrag;
    }//end of onCreateView



    public void onPause() {
        super.onPause();

        String anniversary = tvAnniversary.getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("anniversary", anniversary);
        prefEdit.commit();
    }//end of onPause()


    public void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String anniversary = prefs.getString("anniversary", "");
        tvAnniversary.setText(anniversary);
    }//end of onPause()





}//end of class