package Entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor

public class CandidateEntity{
    private String gender;
    private String registrationNumber;
    private String registrationDate;
    private String about;
    private String partnerRequirements;
    public static boolean textCorrect(String text) {//корректность ввода данных
        return text.matches("[А-Я][a-я]{0,30}");
    }


    @Override
    public String toString() {
        return "Пол = " + getGender()+   ", " + "\n" +
                "Регистрационный номер = " + getRegistrationNumber()+ ", " + "\n" +
                "Дата регистрации = "  + getRegistrationDate()+  ", " + "\n" +
                "Cведения о себе = " + getAbout() +  ", " + "\n" +
                "Требования к партнеру = " + getPartnerRequirements() +  ". " + "\n";

    }

}





