package com.safeway.cqrs.controller

import com.safeway.cqrs.event.CreateReservationCommand
import groovy.util.logging.Slf4j
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import java.util.concurrent.CompletableFuture

@RestController
@Slf4j
class ReservationController {

    private final CommandGateway commandGateway

    @Autowired
    ReservationController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway
    }

    @PostMapping('/reservations')
    CompletableFuture<Object> createReservation(@RequestBody String type) {
        log.info("Request creation of ${type} reservation")

        UUID reservationId = UUID.randomUUID()

        CreateReservationCommand createReservationCommand = new CreateReservationCommand([id: reservationId, type: type])

        return commandGateway.send(createReservationCommand)

    }

}
