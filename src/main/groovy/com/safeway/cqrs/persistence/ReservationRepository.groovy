package com.safeway.cqrs.persistence

import org.springframework.data.jpa.repository.JpaRepository

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

interface ReservationRepository extends JpaRepository<ReservationView, UUID> {
}

@Table(name = 'reservation')
@Entity(name = 'reservation')
class ReservationView {

    @Id
    UUID id
    String type

    ReservationView(UUID id, String type) {
        this.id = id ?: UUID.randomUUID()
        this.type = type ?: 'Air ticket'
    }

}
