package util;

import dtos.PaginationResponseDto;
import dtos.StatusDto;
import enums.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Objects;


public class ResponseUtil {

    /**
     * @param exception
     * @return response
     */
    public static ResponseEntity<Object> returnResponse(RuntimeException exception) {
        StatusDto statusDto = new StatusDto();
        statusDto.setCode(StatusEnum.FAILURE);
        statusDto.setMessage(exception.getMessage());
        exception.printStackTrace();
        return new ResponseEntity<>(statusDto, HttpStatus.OK);
    }

    /**
     * @param exception
     * @return response
     */
    public static ResponseEntity<Object> returnResponse(Exception exception) {
        StatusDto statusDto = new StatusDto();
        statusDto.setCode(StatusEnum.FAILURE);
        statusDto.setMessage(exception.getMessage());
        exception.printStackTrace();
        return new ResponseEntity<>(statusDto, HttpStatus.OK);
    }

    public static ResponseEntity<Object> returnResponseWithCustomMessage(Integer code, String message, Object data) {
        StatusDto statusDto = new StatusDto();
        statusDto.setCode(StatusEnum.SUCCESS);
        statusDto.setMessage(message);
        statusDto.setData(data);

        return new ResponseEntity<>(statusDto, HttpStatus.valueOf(code));
    }

    public static <T> PaginationResponseDto createPaginationResponseDto(Page<T> page) {
        PaginationResponseDto responseDto = new PaginationResponseDto();
        responseDto.setCode(StatusEnum.SUCCESS);
        responseDto.setMessage(StatusEnum.SUCCESS.getReasonPhrase());
        responseDto.setData(page.getContent());
        responseDto.setTotalPages(page.getTotalPages());
        responseDto.setTotalRecords(page.getTotalElements());
        return responseDto;
    }

    public static PaginationResponseDto createPaginationResponseDto(List<?> data, Integer totalPages, Long recordSize) {
        PaginationResponseDto responseDto = new PaginationResponseDto();
        responseDto.setCode(StatusEnum.SUCCESS);
        responseDto.setMessage(StatusEnum.SUCCESS.getReasonPhrase());
        responseDto.setData(data);
        responseDto.setTotalPages(totalPages);
        responseDto.setTotalRecords(recordSize);
        return responseDto;
    }

    /**
     * @param object
     * @return response
     */
    public static ResponseEntity<Object> returnResponse(Object object) {
        StatusDto statusDto = new StatusDto();
        statusDto.setCode(StatusEnum.SUCCESS);
        statusDto.setMessage(StatusEnum.SUCCESS.getReasonPhrase());
        statusDto.setData(object);
        return new ResponseEntity<>(statusDto, HttpStatus.OK);
    }

    /**
     * ResponseUtils for ByteArrayOutputStream
     *
     * @param byteArrayOutputStream
     * @param reportName
     * @return byte[]
     * @author Majid.Hussain
     * @since 01-10-2024
     */
    public static ResponseEntity<?> returnResponse(ByteArrayOutputStream byteArrayOutputStream, String reportName) {
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + Objects.requireNonNullElse(reportName, "report") + ".pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    /**
     * @param paginationResponseDto
     * @return response
     */
    public static ResponseEntity<Object> returnResponse(PaginationResponseDto paginationResponseDto) {
        paginationResponseDto.setCode(StatusEnum.SUCCESS);
        paginationResponseDto.setMessage(StatusEnum.SUCCESS.getReasonPhrase());
        return new ResponseEntity<>(paginationResponseDto, HttpStatus.OK);
    }

    /**
     * @param statusDto
     * @return response
     */
    public static ResponseEntity<Object> returnResponse(StatusDto statusDto) {
        return new ResponseEntity<>(statusDto, HttpStatus.OK);
    }
}
