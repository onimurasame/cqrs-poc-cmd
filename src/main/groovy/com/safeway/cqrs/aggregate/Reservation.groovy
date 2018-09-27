package com.safeway.cqrs.aggregate

import com.safeway.cqrs.command.CreateReservation
import com.safeway.cqrs.event.ReservationCreated
import groovy.util.logging.Slf4j
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.eventhandling.EventHandler
import org.axonframework.spring.stereotype.Aggregate

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply

@Aggregate
@Slf4j
class Reservation {

    @AggregateIdentifier
    private UUID id
    private String type

    @CommandHandler
    Reservation(CreateReservation command) {
        log.info("Applying event ${command.class.name}")
        apply(new ReservationCreated([id: command.id, type: command.type]))
    }

    @EventHandler
    protected void on(ReservationCreated event) {
        log.info("On ${event.class.name} event")
        this.id = event.id
        this.type = event.type ?: 'Air Ticket'
    }

}
