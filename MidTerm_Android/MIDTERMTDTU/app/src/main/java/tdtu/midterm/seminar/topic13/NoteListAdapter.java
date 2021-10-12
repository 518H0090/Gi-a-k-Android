package tdtu.midterm.seminar.topic13;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class NoteListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Note> list;
    private ViewHolder viewHolder;
    private int position;
    ISendData sendData;
    Singleton singleton;
    List<Check> listTemp;

    public NoteListAdapter(Context context, int layout, List<Note> list, ISendData sendData) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        this.sendData = sendData;
    }

    public void setData(List<Note> list){
        this.list = list;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int getCount() {
        return this.list.size();
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

        setPosition(position);

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

        Note note = list.get(position);

        viewHolder.textTittle.setText(note.getTittle());
        viewHolder.textDescription.setText(note.getDescription());
        viewHolder.btnClick.setText(note.getBtnClick());
        singleton = Singleton.getInstance();
        listTemp = new ArrayList<>();

        viewHolder.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_option, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId() ){
                            case R.id.menuDanhDau:
//                                Toast.makeText(v.getContext(), "Check", Toast.LENGTH_SHORT).show();
                                sendData.sendCheck(true,position);
                                singleton.addCheckListCheckFragment(new Check(note.getTittle(),note.getDescription(),"Remove"));
                                break;
                            case R.id.menuXoaNote:
                                removeDialog(v,position);
                                break;
                            case R.id.menuSua:
                                EditSua(v,position);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        return convertView;
    }

    private void EditSua(View v,int position) {
        Dialog dialog = new Dialog(v.getContext());
        dialog.setContentView(R.layout.custom_dialog_edit);
        EditText editTittleUpdate = (EditText) dialog.findViewById(R.id.editTittleUpdate);
        EditText editDescriptionUpdate = (EditText) dialog.findViewById(R.id.editDescriptionUpdate);
        Button btnSua = (Button) dialog.findViewById(R.id.btnSua);
        Button btnHuyUp = (Button) dialog.findViewById(R.id.btnHuyUp);

        btnHuyUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tittle = editTittleUpdate.getText().toString();
                String description = editDescriptionUpdate.getText().toString();
                list.set(position,new Note(tittle,description,"ACTION"));
                notifyDataSetChanged();
                Toast.makeText(v.getContext(), "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        dialog.show();
    }

    private void removeDialog(View view,int position) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
        dialog.setTitle("Xác nhận xóa");
        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list.remove(position);
                notifyDataSetChanged();
                sendData.sendCheck(false,position);
            }
        });

        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.show();
    }


}
