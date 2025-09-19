package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProvinceEnum {
    // Provinces
    PUNJAB("Punjab"),
    SINDH("Sindh"),
    KHYBER_PAKHTUNKHWA("Khyber Pakhtunkhwa"),
    BALOCHISTAN("Balochistan"),

    // Territories
    ISLAMABAD_CAPITAL_TERRITORY("Islamabad Capital Territory"),
    GILGIT_BALTISTAN("Gilgit-Baltistan"),
    AZAD_JAMMU_KASHMIR("Azad Jammu & Kashmir");

    private final String displayName;

}

