package tdtu.midterm.seminar.topic13;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CheckAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Check> checks;
    private Check check;
    ISendCheck sendCheck;

    public void setSendCheck(ISendCheck sendCheck) {
        this.sendCheck = sendCheck;
    }

    public CheckAdapter(Context context, int layout, List<Check> checks) {
        this.context = context;
        this.layout = layout;
        this.checks = checks;
    }

    public void setData(List<Check> checks) {
        this.checks = checks;
    }
    public void setCheckDataInList(Check check) {
        this.check = check;
    }

    @Override
    public int getCount() {
        return checks.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView textTittle,textDescription;
        Button btnClick;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_listview,parent,false);

            viewHolder.textTittle = (TextView) convertView.findViewById(R.id.textTittle);
            viewHolder.textDescription = (TextView) convertView.findViewById(R.id.textDescription);
            viewHolder.btnClick = (Button) convertView.findViewById(R.id.btnClick);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Check check = checks.get(position);

        viewHolder.textTittle.setText(check.getTittle());
        viewHolder.textDescription.setText(check.getDescription());
        viewHolder.btnClick.setText(check.getBtnClick());
        sendCheck.sendCheck("Lên Màn Hình");
        viewHolder.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checks.remove(position);
                notifyDataSetChanged();
                Toast.makeText(v.getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
