import java.io.*;
import java.math.BigDecimal;
import java.nio.file.*;
import java.util.*;

public class Task2 {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Передайте два пути к файлам: <окружность> <точки>");
            return;
        }

        try {
            List<String> circleLines = Files.readAllLines(Paths.get(args[0]));
            String[] coordinates = circleLines.get(0).split(" ");
            BigDecimal centerX = new BigDecimal(coordinates[0].trim());
            BigDecimal centerY = new BigDecimal(coordinates[1].trim());
            BigDecimal radius = new BigDecimal(circleLines.get(1).trim());

            BigDecimal radiusSquared = radius.multiply(radius);

            List<String> pointsLine = Files.readAllLines(Paths.get(args[1]));

            for (String s : pointsLine) {
                String[] points = s.split(" ");
                BigDecimal x = new BigDecimal(points[0]);
                BigDecimal y = new BigDecimal(points[1]);

                BigDecimal dx = x.subtract(centerX);
                BigDecimal dy = y.subtract(centerY);
                BigDecimal distanceSquared = dx.multiply(dx).add(dy.multiply(dy));

                int cmp = distanceSquared.compareTo(radiusSquared);

                if (cmp == 0) {
                    System.out.println(0);
                } else if (cmp < 0) {
                    System.out.println(1);
                } else {
                    System.out.println(2);
                }
            }

        } catch (IOException | NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Ошибка при чтении файлов или данных: " + e.getMessage());
        }
    }
}