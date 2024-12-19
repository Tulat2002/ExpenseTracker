package com.devanktu.service.impl;

import com.devanktu.dto.ExpenseDto;
import com.devanktu.dto.IncomeDto;
import com.devanktu.entity.Expense;
import com.devanktu.entity.Income;
import com.devanktu.repository.IncomeRepository;
import com.devanktu.service.IncomeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;

    public IncomeServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public Income postIncome(IncomeDto incomeDto) {
        return this.saveOrUpdateIncome(new Income(), incomeDto);
    }

    public Income updateIncome(Long id, IncomeDto incomeDto) {
        Optional<Income> optionalIncome = this.incomeRepository.findById(id);
        if (optionalIncome.isPresent()) {
            return saveOrUpdateIncome(optionalIncome.get(), incomeDto);
        }else {
            throw new EntityNotFoundException("Expense is not present with id + " + id);
        }
    }

    private Income saveOrUpdateIncome(Income income, IncomeDto incomeDto) {
        income.setTitle(incomeDto.getTitle());
        income.setDescription(incomeDto.getDescription());
        income.setAmount(incomeDto.getAmount());
        income.setCategory(incomeDto.getCategory());
        income.setDate(incomeDto.getDate());
        return this.incomeRepository.save(income);
    }

    public List<IncomeDto> getAllIncomes() {
        return this.incomeRepository.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .map(Income::getIncomeDto)
                .collect(Collectors.toList());
    }

    public Income getIncomeById(Long id) {
        Optional<Income> optionalIncome = this.incomeRepository.findById(id);
        if (optionalIncome.isPresent()) {
            return optionalIncome.get();
        }else {
            throw new EntityNotFoundException("Income is not present with id: " + id);
        }
    }

    public void deleteIncome(Long id) {
        Optional<Income> optionalIncome = this.incomeRepository.findById(id);
        if (optionalIncome.isPresent()) {
            this.incomeRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Expense is not present with id: " + id);
        }
    }

}
