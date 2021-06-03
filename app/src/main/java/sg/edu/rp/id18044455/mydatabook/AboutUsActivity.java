package sg.edu.rp.id18044455.mydatabook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class AboutUsActivity extends AppCompatActivity {

    ActionBar ab;
    TextView tvAuthors, tvInfo;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        tvAuthors = findViewById(R.id.tvAuthors);
        tvInfo = findViewById(R.id.tvInfo);
        iv = findViewById(R.id.iv);


        String imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/80/Republic_Polytechnic_Logo.jpg";
        iv.setImageResource(R.drawable.ajax_loader);
        Picasso.with(this).load(imageUrl)
                .into(iv, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        iv.setImageResource(R.drawable.error);
                    }
                });


    }//end of onCreate

}//end of class