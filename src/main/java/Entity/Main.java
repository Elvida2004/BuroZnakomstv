//Алиева
package Entity;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ListCandidates candidates = new ListCandidates();
        File file = new File("./file.txt");
        if (file.createNewFile()) {
            System.out.println("Файл был создан");
        }else {
            System.out.println("Файл уже существует");
        }
        try (FileReader fileReader = new FileReader(file)) {
            Scanner fileScanner = new Scanner(fileReader);
            if (fileScanner.hasNextLine()){
                String str2 = fileScanner.nextLine();
                Gson gson = new Gson();
                candidates= gson.fromJson(str2, ListCandidates.class);}
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        byte menu = 0;
        do {
            Menu.mainMenu();
            try {
                menu = scanner.nextByte();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: Введите корректное число из меню.");
                scanner.nextLine(); // Очищаем буфер ввода
                continue; // Пропускаем оставшийся код в текущей итерации цикла.
            }
            switch (menu) {
                case (1):
                    CandidateEntity candidate = new CandidateEntity();

                    System.out.println("Введите пол");
                    scanner.nextLine(); // Очищаем буфер ввода перед считыванием строки
                    candidate.setGender(scanner.nextLine());
                    System.out.println("Введите регистрационный номер");
                    candidate.setRegistrationNumber(scanner.nextLine());
                    System.out.println("Введите дату регистрации");
                    candidate.setRegistrationDate(scanner.nextLine());
                    System.out.println("Введите сведения о себе");
                    candidate.setAbout(scanner.nextLine());
                    System.out.println("Введите требования к партнеру");
                    candidate.setPartnerRequirements(scanner.nextLine());


                    if (candidates.getData() == null) {
                        List<CandidateEntity> temp = new ArrayList<>();
                        temp.add(candidate);
                        candidates.setData(temp);
                    } else {
                        candidates.getData().add(candidate);
                    }
                    if (CandidateEntity.textCorrect(candidate.getGender())||
                            CandidateEntity.textCorrect(candidate.getRegistrationNumber()) ||
                         CandidateEntity.textCorrect(candidate.getRegistrationDate()) ||
                            CandidateEntity.textCorrect(candidate.getAbout()) ||
                     CandidateEntity.textCorrect(candidate.getPartnerRequirements()))
                     {
                        candidates.add(candidate);
                        System.out.println("Кандидат успешно добавлен");
                    } else {
                        System.out.println("Некорректные данные");
                    }
                    break;

                case (2):
                    candidates.getData().forEach(System.out::println);
                    break;
                case (3):
                    System.out.println("Введите информацию о кандидате для поиска (пол,регистрационный номер, дату регистрации,сведения о себе или требования к партнеру):");
                    scanner.nextLine();
                    String textSearch= scanner.nextLine();
                    List<CandidateEntity> searchResult = new ArrayList<>();
                    for (CandidateEntity c : candidates.getData()) {
                        if ( c.getGender().equalsIgnoreCase(textSearch)||
                                c.getRegistrationNumber().equalsIgnoreCase(textSearch)||
                                c.getRegistrationDate().equalsIgnoreCase(textSearch)||
                                c.getAbout().equalsIgnoreCase(textSearch) ||
                                c.getPartnerRequirements().equalsIgnoreCase(textSearch)){
                            searchResult.add(c);

                        }
                    }
                    if (searchResult.isEmpty()) {
                        System.out.println("Кандидат не найден");
                    } else {
                        searchResult.forEach(System.out::println);
                    }
                    break;
                case (4):
                    System.out.println("Введите информацию о удалемом кандидате (пол,регистрационный номер, дату регистрации,сведения о себе или требования к партнеру):");
                    scanner.nextLine();
                    String deleteBook= scanner.nextLine();

                    List<CandidateEntity> deleteResult = new ArrayList<>();

                    for (CandidateEntity b : candidates.getData()) {
                        if ( b.getGender().equalsIgnoreCase(deleteBook)||
                                b.getRegistrationNumber().equalsIgnoreCase(deleteBook)||
                                b.getRegistrationDate().equalsIgnoreCase(deleteBook)||
                                b.getAbout().equalsIgnoreCase(deleteBook) ||
                                b.getPartnerRequirements().equalsIgnoreCase(deleteBook)){
                            deleteResult.add(b);
                        }
                    }

                    if (deleteResult.isEmpty()) {
                        System.out.println("Кандидат не найден");
                    } else {
                        for (CandidateEntity b : deleteResult) {
                            System.out.println("Удалить кандидата? (Да - удалить, Нет - не удалять)\n" + b);
                            String k = scanner.nextLine();
                            if (k.equalsIgnoreCase("Да")) {
                                candidates.getData().remove(b);
                                System.out.println("Кандидат удален");
                            } else {
                                System.out.println("Кандидат не удален");
                            }
                        }
                    }break;


                case (5): {
                    Gson gson = new Gson();
                    String str = gson.toJson(candidates);
                    try (FileWriter fileWriter = new FileWriter(file)) {
                        fileWriter.write(str);
                        System.out.println("Данные успешно сохранены в файле.");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                }
                default:
                    System.out.println("Ошибка: Введите допустимый номер меню");
                    break;
            }

        }

        while (menu != 5) ;
    }

}