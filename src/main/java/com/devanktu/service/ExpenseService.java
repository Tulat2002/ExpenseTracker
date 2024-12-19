package com.devanktu.service;

import com.devanktu.dto.ExpenseDto;
import com.devanktu.entity.Expense;

import java.util.List;

public interface ExpenseService {

    Expense postExpense(ExpenseDto expenseDto);

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    Expense updateExpense(Long id, ExpenseDto expenseDto);

    void deleteExpense(Long id);

}
