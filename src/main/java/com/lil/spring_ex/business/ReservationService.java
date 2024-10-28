package com.lil.spring_ex.business;

import com.lil.spring_ex.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.lil.spring_ex.data.Guest;
import com.lil.spring_ex.data.GuestRepository;
import com.lil.spring_ex.data.Reservation;
import com.lil.spring_ex.data.ReservationRepository;
import com.lil.spring_ex.data.Room;
import com.lil.spring_ex.data.RoomRepository;


@Service
public class ReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationForDate(Date date){
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            System.out.println("Reserva encontrada: " + reservation.getReservationDate());
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getGuestId());
        });
        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Long id : roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }
        roomReservations.sort(new Comparator<RoomReservation>() {
            @Override
            public int compare(RoomReservation o1, RoomReservation o2) {
                if (o1.getRoomName().equals(o2.getRoomName())) {
                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
                }
                return o1.getRoomName().compareTo(o2.getRoomName());
            }
        });
        return roomReservations;
    }

    public List<Guest> getHotelGuests(){
        Iterable<Guest> guests = this.guestRepository.findAll();
        List<Guest> guestList = new ArrayList<>();
        guests.forEach(guestList::add);
        guestList.sort(new Comparator<Guest>() {
            @Override
            public int compare(Guest o1, Guest o2) {
                String lastName1 = o1.getLastName();
                String lastName2 = o2.getLastName();
                if (lastName1 == null && lastName2 == null) {
                    return 0;
                } else if (lastName1 == null) {
                    return -1;
                } else if (lastName2 == null) {
                    return 1;
                } else {
                    int lastNameComparison = lastName1.compareTo(lastName2);
                    if (lastNameComparison != 0) {
                        return lastNameComparison;
                    }
                }
                String firstName1 = o1.getFirstName();
                String firstName2 = o2.getFirstName();
                if (firstName1 == null && firstName2 == null) {
                    return 0;
                } else if (firstName1 == null) {
                    return -1;
                } else if (firstName2 == null) {
                    return 1;
                } else {
                    return firstName1.compareTo(firstName2);
                }
            }
        });
        return guestList;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation
                    .getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getGuestId());
        });
        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Long id : roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }
        roomReservations.sort(new Comparator<RoomReservation>() {
            @Override
            public int compare(RoomReservation o1, RoomReservation o2) {
                if (o1.getRoomName().equals(o2.getRoomName())) {
                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
                }
                return o1.getRoomName().compareTo(o2.getRoomName());
            }
        });
        return roomReservations;
    }

    public List<Room> getHotelRooms() {
        Iterable<Room> rooms = this.roomRepository.findAll();
        List<Room> roomList = new ArrayList<>();
        rooms.forEach(room -> {
            roomList.add(room);
        });
        roomList.sort(new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getRoomNumber().compareTo(o2.getRoomNumber());
            }
        });
        return roomList;
    }

    public void addGuest(Guest guest) {
        if (null == guest){
            throw new RuntimeException("Guest cannot be null");
        }
        this.guestRepository.save(guest);

    }
}