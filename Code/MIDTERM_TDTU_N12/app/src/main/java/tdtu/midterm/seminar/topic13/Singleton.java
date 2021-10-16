package tdtu.midterm.seminar.topic13;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private static Singleton instane;

//    public CheckAdapter checkAdapter;
    public List<Check> checkListCheckFragment = new ArrayList<>();

    private Singleton(){

    }

    public static Singleton getInstance() {
        if (instane == null){
            instane = new Singleton();
        }
        return instane;
    }


    public void addCheckListCheckFragment(Check check) {
        checkListCheckFragment.add(check);
    }
}
