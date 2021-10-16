package tdtu.midterm.seminar.topic13;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class NoteFragment extends Fragment implements ISendData {

    View view;
    ListView listNote;
    List<Note> noteList;
    NoteListAdapter noteListAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    CoordinatorLayout coordinatorLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.note_fragment,container,false);
        listNote = (ListView) view.findViewById(R.id.listNote);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorLayout);

        noteList = new ArrayList<Note>();

        for (int i = 0; i< 6 ; i++) {
            String tittle = "Tittle" +i;
            String description = "Description" +i;
            String btnClick = "ACTION";
            noteList.add(new Note(tittle,description,btnClick));
        }

        noteListAdapter = new NoteListAdapter(
                getActivity(),
                R.layout.custom_listview,
                noteList,
                this
        );

        listNote.setAdapter(noteListAdapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.orange,R.color.blue,R.color.black);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                noteListAdapter.setData(noteList);
                noteListAdapter.notifyDataSetChanged();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });



        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_click,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menuThem) {
            CustomDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void CustomDialog(){
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_note_dialog);
        dialog.setCanceledOnTouchOutside(false);

        EditText editTittle = (EditText) dialog.findViewById(R.id.editTittle);
        EditText editDescription = (EditText) dialog.findViewById(R.id.editDescription);
        Button btnThem = (Button) dialog.findViewById(R.id.btnThem);
        Button btnHuy = (Button) dialog.findViewById(R.id.btnHuy);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Tittle = editTittle.getText().toString();
                String Description = editDescription.getText().toString();
                if(Tittle.isEmpty() || Description.isEmpty()){
                    Toast.makeText(getActivity(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    noteList.add(new Note(Tittle,Description,"ACTION"));
                    showSnackBar();
                    dialog.dismiss();
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    private void showSnackBar() {
        Snackbar snackbar = Snackbar.make(coordinatorLayout,"Thêm Thành Công",Snackbar.LENGTH_INDEFINITE).
                setAction("Tắt", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar snackbar1 = Snackbar.make(coordinatorLayout,"Tắt Thành Công", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                    }
                });
        snackbar.show();
    }


    @Override
    public void sendCheck(boolean value , int position) {
        if (value == true ) {
            Snackbar snackbar = Snackbar.make(coordinatorLayout,"Thêm vào Check",Snackbar.LENGTH_INDEFINITE).
                    setAction("Tắt", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Snackbar snackbar1 = Snackbar.make(coordinatorLayout,"Tắt Thành Công", Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                        }
                    });
            snackbar.show();

        } else {
            Snackbar snackbar = Snackbar.make(coordinatorLayout,"Xóa Thành Công",Snackbar.LENGTH_INDEFINITE).
                    setAction("Tắt", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Snackbar snackbar1 = Snackbar.make(coordinatorLayout,"Tắt Thành Công", Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                        }
                    });
            snackbar.show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(), "Màn hình Note được gọi", Toast.LENGTH_SHORT).show();
    }
}
