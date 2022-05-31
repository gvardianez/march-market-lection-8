package ru.geekbrains.march.market.core.converters;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.PageDto;

@Component
public class PageConverter {

    public <T> PageDto<T> entityToDto(Page<T> page){
        return new PageDto<>(page.getContent(), page.getSize(), page.getNumberOfElements(), page.getTotalElements(), page.getTotalPages());
    }
}
