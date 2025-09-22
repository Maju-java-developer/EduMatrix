package util;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class DynamicFilterUtil {
    // Filters names
    public static final String ACADEMIC_YEAR_FILTER = "academicYearFilter";

    public static <T> MappingJacksonValue applyFilter(List<T> data, List<String> fields, String filterId) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(new HashSet<>(fields));
        FilterProvider filters = new SimpleFilterProvider().addFilter(filterId, filter);
        MappingJacksonValue mapping = new MappingJacksonValue(data);
        mapping.setFilters(filters);
        return mapping;
    }

}

