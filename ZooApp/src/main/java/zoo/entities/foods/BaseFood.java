package zoo.entities.foods;

public abstract class BaseFood implements Food {
    
    private final int calories;
    private final double price;
    
    protected BaseFood(int calories, double price) {
        this.calories = calories;
        this.price = price;
    }
    
    @Override
    public int getCalories() {
        return this.calories;
    }
    
    @Override
    public double getPrice() {
        return this.price;
    }
}
