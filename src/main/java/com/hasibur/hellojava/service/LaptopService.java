package com.hasibur.hellojava.service;

import com.hasibur.hellojava.model.Laptop;

import java.util.List;

public interface LaptopService {
    public List<Laptop> allLaptop();
    public Laptop saveLaptop(Laptop laptop);
    public void deleteLaptop(Long id);
    public Laptop findLaptop(Long id);
}
