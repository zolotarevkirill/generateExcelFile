import java.util.Random;
import com.github.javafaker.Faker;

public class Player {
    String name;
    Integer point;

    public Player() {
        this.name = generateRandomName();
        this.point = generateRandomPoints();
    }

    public String getName() {
        return name;
    }

    public Integer getPoint() {
        return point;
    }

    private static Integer generateRandomPoints(){
        Random random = new Random();
        int randomNumber = random.nextInt(150000);
        return randomNumber;
    }

    private static String generateRandomName() {
        Faker faker = new Faker();
        return faker.name().fullName();
    }
}