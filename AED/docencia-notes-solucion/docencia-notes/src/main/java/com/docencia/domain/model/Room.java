package com.docencia.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="room")
public class Room {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "number")
    private String number;

    @Column(name = "type")
    private String type;

    @Column(name = "price_per_night")
    private Float pricePerNight;

    @ManyToOne
    @Column(name = "hotel_id")
    private String hotelId;
}
