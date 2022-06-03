package ru.geekbrains.march.market.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Модель страницы")
public class PageDto<T> {

    @Schema(description = "Список элементов на странице", required = true, example = "1 Конфеты 100.00 Еда")
    private List<T> content;

    @Schema(description = "Заданное количечство элементов на странице", required = true, example = "5")
    private int size;

    @Schema(description = "Фактическое количество эоементво на странице", required = true, example = "2")
    private int numberOfElements;

    @Schema(description = "Общее количество элементов", required = true, example = "10")
    private long totalElements;

    @Schema(description = "Общее количество страниц", required = true, example = "3")
    private int totalPages;

    public PageDto() {
    }

    public PageDto(List<T> content, int size, int numberOfElements, long totalElements, int totalPages) {
        this.content = content;
        this.size = size;
        this.numberOfElements = numberOfElements;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
