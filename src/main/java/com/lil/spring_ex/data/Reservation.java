package com.lil.spring_ex.data;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table (name = "RESERVATION")
public class Reservation {
    @Id
    @Column(name = "RESERVATION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long reservationId;
    @Column(name = "ROOM_ID")
    private long roomId;
    @Column(name = "GUEST_ID")
    private long guestId;
    @Column(name = "RES_DATE")
    private Date reservationDate;

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getGuestId() {
        return guestId;
    }

    public void setGuestId(long guestId) {
        this.guestId = guestId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date resDate) {
        this.reservationDate = resDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", roomId=" + roomId +
                ", guestId=" + guestId +
                ", resDate=" + reservationDate +
                '}';
    }
}
