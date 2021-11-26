package Movies.AddAstra;

public class Astra {
    private String food;
    private String date;
    private String cal;

    public Astra(String food, String date, String cal) {
        this.food = food;
        this.date = date;
        this.cal = cal;
    }

    public String getCal() {
        return cal;
    }

    public String getDate() {
        return date;
    }

    public String getFood() {
        return food;
    }

    @Override
    public String toString() {
        return "Item: "+this.food+", Best before: "+this.date+", Nutrition: "+this.cal;
    }
}
