package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CityEnum {
    KARACHI("Karachi"),
    LAHORE("Lahore"),
    FAISALABAD("Faisalabad"),
    RAWALPINDI("Rawalpindi"),
    GUJRANWALA("Gujranwala"),
    MULTAN("Multan"),
    HYDERABAD("Hyderabad"),
    PESHAWAR("Peshawar"),
    QUETTA("Quetta"),
    ISLAMABAD("Islamabad"),
    BAHAWALPUR("Bahawalpur"),
    SARGODHA("Sargodha"),
    SIALKOT("Sialkot"),
    SUKKUR("Sukkur"),
    LARKANA("Larkana"),
    SAHIWAL("Sahiwal"),
    OKARA("Okara"),
    RAHIM_YAR_KHAN("Rahim Yar Khan"),
    KASUR("Kasur"),
    DERA_GHAZI_KHAN("Dera Ghazi Khan"),
    SHEIKHUPURA("Sheikhupura"),
    GUJRAT("Gujrat"),
    MARDAN("Mardan");
    // add more if needed

    private final String displayName;
}
