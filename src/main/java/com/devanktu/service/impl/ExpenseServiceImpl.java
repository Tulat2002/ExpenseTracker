package com.devanktu.service.impl;

import com.devanktu.dto.ExpenseDto;
import com.devanktu.entity.Expense;
import com.devanktu.repository.ExpenseRepository;
import com.devanktu.service.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense postExpense(ExpenseDto expenseDto) {
        return saveOrUpdateExpense(new Expense(), expenseDto);
    }

    private Expense saveOrUpdateExpense(Expense expense, ExpenseDto expenseDto) {
        expense.setTitle(expenseDto.getTitle());
        expense.setDescription(expenseDto.getDescription());
        expense.setAmount(expenseDto.getAmount());
        expense.setCategory(expenseDto.getCategory());
        expense.setDate(expenseDto.getDate());
        return this.expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, ExpenseDto expenseDto) {
        Optional<Expense> optionalExpense = this.expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            return saveOrUpdateExpense(optionalExpense.get(), expenseDto);
        }else {
            throw new EntityNotFoundException("Expense is not present with id + " + id);
        }
    }

    public List<Expense> getAllExpenses() {
        return this.expenseRepository.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }

    public Expense getExpenseById(Long id) {
        Optional<Expense> optionalExpense = this.expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            return optionalExpense.get();
        }else {
            throw new EntityNotFoundException("Expense is not present with id: " + id);
        }
    }

    public void deleteExpense(Long id) {
        Optional<Expense> optionalExpense = this.expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            this.expenseRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Expense is not present with id: " + id);
        }
    }

}
