package com.example.Stayease.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Stayease.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
