package com.devanktu.service;

import com.devanktu.dto.IncomeDto;
import com.devanktu.entity.Income;

import java.util.List;

public interface IncomeService {

    Income postIncome(IncomeDto incomeDto);

    List<IncomeDto> getAllIncomes();

    Income updateIncome(Long id, IncomeDto incomeDto);

    Income getIncomeById(Long id);

    void deleteIncome(Long id);
    
}
