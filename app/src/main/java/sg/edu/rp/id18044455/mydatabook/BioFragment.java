package sg.edu.rp.id18044455.mydatabook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class BioFragment extends Fragment {

    TextView tvBio;
    FloatingActionButton fabBio;
    Button btnEditBio;
    DrawerLayout drawerLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View bioFrag = inflater.inflate(R.layout.fragment_bio, container, false);

        tvBio = bioFrag.findViewById(R.id.tvBio);
        fabBio = bioFrag.findViewById(R.id.fabBio);
        btnEditBio = bioFrag.findViewById(R.id.btnEditBio);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);

        btnEditBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.edit_bio, null);

                final EditText etBio = viewDialog.findViewById(R.id.etBio);

                if (tvBio.getText().toString().trim().length() != 0){
                    etBio.setText(tvBio.getText().toString());
                }

                android.app.AlertDialog.Builder myBuilder = new android.app.AlertDialog.Builder(getActivity());
                myBuilder.setTitle("Edit Bio");
                myBuilder.setView(viewDialog);
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String bio = etBio.getText().toString();
                        if (bio.trim().length() != 0){
                            tvBio.setText(bio);
                        }//end of if
                        else{
                            etBio.setError("Text is required");
                        }
                    }
                });

                myBuilder.setNegativeButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });//end of btnEditBio

        fabBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });



        return bioFrag;
    }//end of onCreateView


    public void onPause() {
        super.onPause();

        String bio = tvBio.getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("bio", bio);
        prefEdit.commit();
    }//end of onPause()


    public void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String bio = prefs.getString("bio", "");
        tvBio.setText(bio);
    }//end of onPause()


}//end of class