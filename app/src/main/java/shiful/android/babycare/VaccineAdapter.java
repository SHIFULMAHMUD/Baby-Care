package shiful.android.babycare;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class VaccineAdapter extends ArrayAdapter<Vac> {
    private Context mContext;
    private List<Vac> vaccinesList = new ArrayList<>();


    public VaccineAdapter(@NonNull Context context, @LayoutRes ArrayList<Vac> list) {
            super(context, 0 , list);
            mContext = context;
            vaccinesList = list;
        }


        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listItem = convertView;
            if(listItem == null)
                listItem = LayoutInflater.from(mContext).inflate(R.layout.vac_list_items,parent,false);

            final Vac currentVaccine = vaccinesList.get(position);


            TextView name = (TextView) listItem.findViewById(R.id.textView2);
            name.setText(currentVaccine.getmVaccineName());

            TextView dose = (TextView) listItem.findViewById(R.id.textView3);
            dose.setText(currentVaccine.getmDoseName());

            TextView startdate = (TextView) listItem.findViewById(R.id.textView5);
            startdate.setText(currentVaccine.getmStartDate());

            TextView enddate = (TextView) listItem.findViewById(R.id.textView7);
            enddate.setText(currentVaccine.getmEndDate());

            TextView status = (TextView) listItem.findViewById(R.id.txt_status);
            status.setText(currentVaccine.getmStatus());

            return listItem;
        }
    }