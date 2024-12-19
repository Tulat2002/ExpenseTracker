package com.devanktu.service.impl;

import com.devanktu.dto.GraphDto;
import com.devanktu.dto.StatsDto;
import com.devanktu.entity.Expense;
import com.devanktu.entity.Income;
import com.devanktu.repository.ExpenseRepository;
import com.devanktu.repository.IncomeRepository;
import com.devanktu.service.StatsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class StatsServiceImpl implements StatsService {

    private final ExpenseRepository expenseRepository;

    private final IncomeRepository incomeRepository;

    public StatsServiceImpl(ExpenseRepository expenseRepository, IncomeRepository incomeRepository) {
        this.expenseRepository = expenseRepository;
        this.incomeRepository = incomeRepository;
    }

    public GraphDto getCharData(){
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(27);

        GraphDto graphDto = new GraphDto();
        graphDto.setExpenseList(this.expenseRepository.findByDateBetween(startDate, endDate));
        graphDto.setIncomeList(this.incomeRepository.findByDateBetween(startDate, endDate));

        return graphDto;
    }

    public StatsDto getStats() {
        Double totalIncome = this.incomeRepository.sumAllAmounts();
        Double totalExpense = this.expenseRepository.sumAllAmounts();

        Optional<Income> optionalIncome = this.incomeRepository.findFirstByOrderByDateDesc();
        Optional<Expense> optionalExpense = this.expenseRepository.findFirstByOrderByDateDesc();

        StatsDto statsDto = new StatsDto();
        statsDto.setExpense(totalExpense);
        statsDto.setIncome(totalIncome);

        optionalIncome.ifPresent(statsDto::setLatestIncome);
        optionalExpense.ifPresent(statsDto::setLatestExpense);

        statsDto.setBalance(totalIncome - totalExpense);

        List<Income> incomeList = this.incomeRepository.findAll();
        List<Expense> expenseList = this.expenseRepository.findAll();

        OptionalDouble minIncome = incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income::getAmount).max();

        OptionalDouble minExpense = expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expense::getAmount).max();

        statsDto.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : null);
        statsDto.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);

        statsDto.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : null);
        statsDto.setMinIncome(minIncome.isPresent() ? minIncome .getAsDouble() : null);

        return statsDto;
    }

}
