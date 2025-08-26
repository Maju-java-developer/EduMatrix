package dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Status dto.
 *
 * @author sarmad ali shaikh
 * @since 15-05-2023
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusDto {

    private Long totalRecords;
    private Integer totalPages;
    private StatusEnum code;
    private String message;
    private Object data;

    public StatusDto(StatusEnum code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code.value() + "";
    }

    public StatusDto(StatusEnum code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
