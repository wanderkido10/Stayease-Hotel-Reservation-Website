package com.example.Stayease.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Stayease.entity.Booking;
import com.example.Stayease.repository.BookingService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000")  // Allow the frontend to access the backend
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // POST: Create a new booking
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking createdBooking = bookingService.saveBooking(booking);
        return ResponseEntity.status(201).body(createdBooking);  // Respond with status 201 (Created)
    }

    // GET: Retrieve all bookings
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);  // Respond with status 200 (OK) and list of bookings
    }
    @PutMapping("/api/bookings/{id}")
public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails) {
    Booking booking = bookingService.findById(id);
    if (booking == null) {
        return ResponseEntity.notFound().build();
    }

    // Update booking details
    booking.setName(bookingDetails.getName());
    booking.setEmail(bookingDetails.getEmail());
    booking.setRoomType(bookingDetails.getRoomType());
    booking.setNumberOfRooms(bookingDetails.getNumberOfRooms());
    booking.setNumberOfGuests(bookingDetails.getNumberOfGuests());

    bookingService.saveBooking(booking);
    return ResponseEntity.ok(booking);
}
}