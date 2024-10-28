package com.lil.spring_ex.webservice;


import com.lil.spring_ex.business.ReservationService;
import com.lil.spring_ex.business.RoomReservation;
import com.lil.spring_ex.data.Guest;
import com.lil.spring_ex.data.Room;
import com.lil.spring_ex.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebServiceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public WebServiceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(path="/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(name="date", required = false) String dateString) {
        Date date = this.dateUtils.createDateFormatString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);
    }

    @GetMapping("/guests")
    public List<Guest> getGuests(){
        return this.reservationService.getHotelGuests();
    }

    @PostMapping("/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(@RequestBody Guest guest) {
        System.out.println("Received Guest: " + guest);
        this.reservationService.addGuest(guest);
    }

    @GetMapping("/rooms")
    public List<Room> getRooms(){
        return this.reservationService.getHotelRooms();
    }
}
