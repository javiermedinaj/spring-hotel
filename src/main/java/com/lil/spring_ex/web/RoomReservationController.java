package com.lil.spring_ex.web;

import com.lil.spring_ex.business.ReservationService;
import com.lil.spring_ex.business.RoomReservation;
import com.lil.spring_ex.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
@RequestMapping("/reservations")
public class RoomReservationController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public RoomReservationController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(@RequestParam(value = "date", required = false) String dateString, Model model){
        Date date = this.dateUtils.createDateFormatString(dateString);
        Iterable<RoomReservation> roomReservations = this.reservationService.getRoomReservationForDate(date);
        model.addAttribute("roomReservations", roomReservations);
        return "roomres";
    }

}
