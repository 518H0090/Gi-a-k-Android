package tdtu.midterm.seminar.topic13;

public class Check {
    private String tittle;
    private String description;
    private String btnClick;

    public Check(String tittle, String description, String btnClick) {
        this.tittle = tittle;
        this.description = description;
        this.btnClick = btnClick;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBtnClick() {
        return btnClick;
    }

    public void setBtnClick(String btnClick) {
        this.btnClick = btnClick;
    }
}
