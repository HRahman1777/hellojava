package com.hasibur.hellojava.service;

import com.hasibur.hellojava.model.Laptop;
import com.hasibur.hellojava.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopServiceImpl implements LaptopService{

    @Autowired
    LaptopRepository laptopRepository;

    @Override
    public List<Laptop> allLaptop() {
        return laptopRepository.findAll();
    }

    @Override
    public Laptop saveLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }
}
