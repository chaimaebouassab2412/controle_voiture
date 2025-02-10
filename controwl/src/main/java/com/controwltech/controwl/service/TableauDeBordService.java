package com.controwltech.controwl.service;

import com.controwltech.controwl.entities.TableauDeBord;
import com.controwltech.controwl.repositories.TableauDeBordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableauDeBordService {

    @Autowired
    private TableauDeBordRepository tableauDeBordRepository;

    public List<TableauDeBord> getAllTableaux() {
        return tableauDeBordRepository.findAll();
    }

    public TableauDeBord getTableauById(Long id) {
        return tableauDeBordRepository.findById(id).orElse(null);
    }

    public TableauDeBord saveTableau(TableauDeBord tableauDeBord) {
        return tableauDeBordRepository.save(tableauDeBord);
    }

    public void deleteTableau(Long id) {
        tableauDeBordRepository.deleteById(id);
    }
}
