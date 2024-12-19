package com.devanktu.dto;

import java.time.LocalDate;

public class IncomeDto {

    private Long id;

    private String title;

    private Integer amount;

    private LocalDate date;

    private String category;

    private String description;

//    public IncomeDto(Long id, String title, Integer amount, LocalDate date, String category, String description) {
//        this.id = id;
//        this.title = title;
//        this.amount = amount;
//        this.date = date;
//        this.category = category;
//        this.description = description;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
