package com.example.Stayease.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Stayease.entity.Booking;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Save or update booking
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking); // This saves or updates the booking
    }

    // Retrieve all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Retrieve booking by ID
    public Booking findById(Long id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        return bookingOptional.orElse(null); // Return null if booking not found
    }

    // Update booking details
    public Booking updateBooking(Long id, Booking bookingDetails) {
        Optional<Booking> existingBookingOptional = bookingRepository.findById(id);
        if (existingBookingOptional.isPresent()) {
            Booking existingBooking = existingBookingOptional.get();
            // Update booking details
            existingBooking.setName(bookingDetails.getName());
            existingBooking.setEmail(bookingDetails.getEmail());
            existingBooking.setRoomType(bookingDetails.getRoomType());
            existingBooking.setNumberOfRooms(bookingDetails.getNumberOfRooms());
            existingBooking.setNumberOfGuests(bookingDetails.getNumberOfGuests());

            // Save the updated booking
            return bookingRepository.save(existingBooking);
        } else {
            return null; // Return null if booking doesn't exist
        }
    }
}
