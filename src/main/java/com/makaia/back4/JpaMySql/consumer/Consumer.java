package com.makaia.back4.JpaMySql.consumer;

import com.makaia.back4.JpaMySql.dtos.ComentarioDTO;
import com.makaia.back4.JpaMySql.services.ComentarioService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private final ComentarioService comentarioService;

    @Autowired

    public Consumer(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @RabbitListener(queues = { "commment_created" })
    public void receive(@Payload ComentarioDTO comentarioDTO) {
        System.out.println("Crear un comentario para el usuario " + comentarioDTO.getUsuarioId());

        comentarioService.crearComentario(comentarioDTO);
    }
}
