package util;

import dtos.PaginationRequestDto;
import dtos.PaginationResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

public class PaginationUtils {

    public static Pageable createPageRequest(PaginationRequestDto paginationRequestDto) {
        if (paginationRequestDto == null) {
            paginationRequestDto = new PaginationRequestDto();
        }
        Integer page = ObjectUtils.isEmpty(paginationRequestDto.getCurrentPage()) ? 0 : paginationRequestDto.getCurrentPage();
        Integer itemsPerPage = ObjectUtils.isEmpty(paginationRequestDto.getItemsPerPages()) ? 50 : paginationRequestDto.getItemsPerPages();
        String sortBy = ObjectUtils.isEmpty(paginationRequestDto.getSortBy()) ? "createdDate" : paginationRequestDto.getSortBy();
        String direction = ObjectUtils.isEmpty(paginationRequestDto.getDirection()) ? "ASC" : paginationRequestDto.getDirection();
        return PageRequest.of(page,
                itemsPerPage,
                (direction.equalsIgnoreCase("DESC") ||
                        direction.equalsIgnoreCase("DSC") ?
                        Sort.Direction.DESC : Sort.Direction.ASC), sortBy);
    }

    /**
     * createPageRequest for return record with pagination
     *
     * @param currentPage
     * @param itemPerPage
     * @return Pageable
     * @author Majid.Hussain
     * @since 14-05-2024
     */
    public static Pageable createPageRequest(Integer currentPage, Integer itemPerPage) {
        Integer page = ObjectUtils.isEmpty(currentPage) ? 0 : currentPage;
        Integer itemsPerPage = ObjectUtils.isEmpty(itemPerPage) ? 50 : itemPerPage;
        return PageRequest.of(page,
                itemsPerPage);
    }

    /**
     * Creates a pagination response DTO from a list of data items.
     * This method calculates the total number of pages, determines the starting and ending indices for slicing the data list,
     * and then slices the list according to the current page and items per page. It handles cases where the current page
     * exceeds the total number of pages by returning an empty list.
     *
     * @param data         The list of data items to paginate.
     * @param currentPage  The index of the current page (zero-based). Defaults to 0 if null.
     * @param itemPerPages The number of items per page. Defaults to 50 if null.
     * @return A PaginationResponseDto containing the paginated sublist of data items, total pages, and total records.
     * @author Majid.Hussain
     * @since 01-07-2024
     */
    public static PaginationResponseDto createPageRequest(List<?> data, Integer currentPage, Integer itemPerPages) {
        Integer totalRecords = data.size();
        Integer itemsPerPage = itemPerPages == null ? 50 : itemPerPages;
        int totalPages = (int) Math.ceil((double) totalRecords / itemsPerPage);
        currentPage = currentPage == null ? 0 : currentPage;

        // Apply pagination
        int startItem = currentPage * itemsPerPage;
        if (startItem >= totalRecords) {
            return ResponseUtil.createPaginationResponseDto(Collections.emptyList(), totalPages, totalRecords.longValue());
        }

        int endItem = Math.min(startItem + itemsPerPage, data.size());
        return ResponseUtil.createPaginationResponseDto(data.subList(startItem, endItem), totalPages, totalRecords.longValue());
    }

    /**
     * Builds a standardized pagination response DTO from a Spring Data {@link Page} object.
     * <p>
     * This method is typically used in service or controller layers to wrap paginated results
     * in a consistent response format for APIs.
     * </p>
     *
     * @param page the {@link Page} object containing paginated data
     * @return a {@link PaginationResponseDto} containing the total pages, total records, and content
     * @author Majid.Hussain
     * @since 2025-05-20
     */
    public static PaginationResponseDto buildPaginationResponse(Page<?> page) {
        PaginationResponseDto paginationResponseDto = new PaginationResponseDto();
        paginationResponseDto.setTotalPages(page.getTotalPages());
        paginationResponseDto.setTotalRecords(page.getTotalElements());
        paginationResponseDto.setData(page.getContent());
        return paginationResponseDto;
    }

}
