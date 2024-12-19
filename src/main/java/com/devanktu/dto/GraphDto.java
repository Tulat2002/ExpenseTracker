package com.devanktu.dto;

import com.devanktu.entity.Expense;
import com.devanktu.entity.Income;
import lombok.Data;

import java.util.List;

public class GraphDto {

    private List<Expense> expenseList;

    private List<Income> incomeList;

    public GraphDto(List<Expense> expenseList, List<Income> incomeList) {
        this.expenseList = expenseList;
        this.incomeList = incomeList;
    }

    public GraphDto() {}

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }
}
