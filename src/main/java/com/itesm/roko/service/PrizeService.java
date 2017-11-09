package com.itesm.roko.service;

import com.itesm.roko.dao.PrizeDAO;
import com.itesm.roko.domain.Prize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrizeService {

    @Autowired
    private PrizeDAO prizeDAO;

    public Optional<Prize> getPrize(String id){
        return prizeDAO.getById(id);
    }

    public Optional<Prize> insert(Prize prize){
        return prizeDAO.insert(prize);
    }
}
