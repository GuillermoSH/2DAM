package com.docencia.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "booking")
public class Booking {
    @Id
    @Column(name = "id")
    private String id;

    @OneToOne
    @Column(name = "room_id")
    private String roomId;

    @OneToOne
    @Column(name = "guest_id")
    private String guestId;

    @Column(name = "check_in")
    private String checkIn;

    @Column(name = "check_out")
    private String checkOut;
}
