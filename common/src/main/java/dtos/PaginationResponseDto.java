package dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Pagination response Dto.
 *
 * @author Majid.Hussain
 * @since 31-05-2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginationResponseDto {
    private StatusEnum code;
    private String message;
    private Long totalRecords;
    private Integer totalPages;
    private List data;

    public String getCode() {
        return code.value() + "";
    }
}
