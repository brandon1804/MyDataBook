package sg.edu.rp.id18044455.mydatabook;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class DrawerListAdapter extends ArrayAdapter<String> {


    private ArrayList<String> drawerList;

    private Context context;
    private TextView tvListItem;
    private ImageView ivListItem;

    public DrawerListAdapter(Context context, int resource,
                       ArrayList<String> objects) {
        super(context, resource, objects);
        drawerList = objects;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row, parent,
                false);


        tvListItem = rowView.findViewById(R.id.tvListItem);
        ivListItem = rowView.findViewById(R.id.ivListItem);


        String currItem = drawerList.get(position);
        tvListItem.setText(currItem);

        if(currItem.equals("Bio")){
            ivListItem.setImageResource(R.drawable.ic_baseline_info_24);
        }
        else if(currItem.equals("Vaccination")){
            ivListItem.setImageResource(R.drawable.ic_baseline_edit_24);
        }
        else if(currItem.equals("Anniversary")){
            ivListItem.setImageResource(R.drawable.ic_baseline_calendar_today_24);
        }
        else if(currItem.equals("About Us")){
            ivListItem.setImageResource(R.drawable.ic_baseline_star_24);
        }

        return rowView;
    }
}
