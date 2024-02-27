package com.example.samuraitravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samuraitravel.entity.House;

// JpaRepositoryインターフェースを継承し、CRUD操作メソッドが利用可能
public interface HouseRepository extends JpaRepository<House, Integer> {
}
