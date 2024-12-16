import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome!");
        List<Player> players = generatePlayers(30);
        Collections.sort(players, Comparator.comparingInt(Player::getPoint).reversed());
        printPlayers(players);
        generateExcelFile(players);
    }

    private static List<Player> generatePlayers(int count){
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < count; i++){
            Player player = new Player();
            players.add(player);
        }

        return players;
    }

    private static void printPlayers(List<Player> players){
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.println((i + 1) + ". " + player.getName() + ": " + player.getPoint() + " очков");
        }
    }

    private static void generateExcelFile(List<Player> players){
        // Создание нового Excel-файла
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Players");

        // Запись заголовков столбцов
        Row headerRow = sheet.createRow(0);
        Cell nameHeader = headerRow.createCell(0);
        nameHeader.setCellValue("Name");
        Cell pointsHeader = headerRow.createCell(1);
        pointsHeader.setCellValue("Points");

        // Запись данных игроков в Excel-файл
        int rowNum = 1;
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            Row row = sheet.createRow(rowNum++);
            Cell nameCell = row.createCell(0);
            nameCell.setCellValue(player.getName());
            Cell pointsCell = row.createCell(1);
            pointsCell.setCellValue(player.getPoint());
        }

        // Сохранение Excel-файла
        try (FileOutputStream outputStream = new FileOutputStream("players.xlsx")) {
            workbook.write(outputStream);
            System.out.println("Excel file created successfully.");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}