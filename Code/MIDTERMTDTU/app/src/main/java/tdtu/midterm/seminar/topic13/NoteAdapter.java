package tdtu.midterm.seminar.topic13;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class NoteAdapter extends FragmentStateAdapter {

    public NoteAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new NoteFragment();
            case 1:
                return new CheckFragment();
            default:
                return new NoteFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
