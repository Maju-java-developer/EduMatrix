package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Pagination request dto.
 *
 * @author Majid.Hussain
 * @since 31-05-2023
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationRequestDto {
    private Integer currentPage;
    private Integer itemsPerPages;
    private String sortBy;
    private String direction;
    private Boolean all;
}
