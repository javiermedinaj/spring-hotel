package com.lil.spring_ex.util;

import com.lil.spring_ex.business.ReservationService;
import com.lil.spring_ex.business.RoomReservation;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final ReservationService reservationService;
    private final DateUtils dateUtils;

    public AppStartupEvent(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event){
        Date date = this.dateUtils.createDateFormatString("2024-01-01");
        List<RoomReservation> reservations = this.reservationService.getRoomReservationForDate(date);
        reservations.forEach(System.out::println);
    }
}