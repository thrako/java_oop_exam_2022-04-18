package zoo.entities.areas;

import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseArea implements Area {
    
    private String name;
    private final int capacity;
    private final Collection<Food> foods;
    private final Collection<Animal> animals;
    
    
    protected BaseArea(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.foods = new ArrayList<>();
        this.animals = new ArrayList<>();
    }
    
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.AREA_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public Collection<Animal> getAnimals() {
        return this.animals;
    }
    
    @Override
    public Collection<Food> getFoods() {
        return this.foods;
    }
    
    @Override
    public int sumCalories() {
        return this.foods.stream()
                .mapToInt(Food::getCalories)
                .sum();
    }
    
    @Override
    public void addAnimal(Animal animal) {
        if (this.animals.size() >= capacity) {
            throw new IllegalStateException(ExceptionMessages.NOT_ENOUGH_CAPACITY);
        }
    
        this.animals.add(animal);
    }
    
    @Override
    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
    }
    
    @Override
    public void addFood(Food food) {
        this.foods.add(food);
    }
    
    @Override
    public void feed() {
        this.animals.forEach(Animal::eat);
    }
    
    @Override
    public String getInfo() {
        StringBuilder strBld = new StringBuilder();
        String newLine = System.lineSeparator();
    
        return strBld
                .append(String.format("%s (%s):", this.name, this.getClass().getSimpleName()))
                    .append(newLine)
                .append(String.format("Animals: %s", getAnimalNames()))
                    .append(newLine)
                .append(String.format("Foods: %s", this.foods.size()))
                    .append(newLine)
                .append(String.format("Calories: %d", sumCalories()))
                .toString();
        
        
    }
    
    private String getAnimalNames() {
        return animals.isEmpty() ? "none" : animals.stream().map(Animal::getName).collect(Collectors.joining(" "));
    }
}
