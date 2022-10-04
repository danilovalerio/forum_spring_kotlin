package br.com.danilo.exemplo.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {

    fun notificar(autor: String){
        val message = SimpleMailMessage()

        message.setSubject("[Forum] Resposta Recebida")
        message.setText("Olá, seu tópico tem uma nova resposta, vamos conferir?")
        message.setTo(autor)

        javaMailSender.send(message)
    }

}