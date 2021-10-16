package tdtu.midterm.seminar.topic13;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class CheckFragment extends Fragment implements ISendCheck{

    View view;
    ListView listView;
    List<Check> checks;
    CheckAdapter adapter;
    Singleton singleton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.check_fragment,container,false);
        listView = (ListView) view.findViewById(R.id.listCheck);
        singleton = Singleton.getInstance();
        checks = singleton.checkListCheckFragment;

        adapter = new CheckAdapter(
                getActivity(),
                android.R.layout.simple_list_item_1,
                checks
        );

//        singleton.checkAdapter = adapter;
        adapter.setSendCheck(this);

        listView.setAdapter(adapter);
        return view;
    }


    @Override
    public void sendCheck(String note) {

    }

    //Khi chuyển lại tab thì sẽ cập nhật dữ liệu
    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "Màn hình check được gọi lại", Toast.LENGTH_SHORT).show();
    }
}
