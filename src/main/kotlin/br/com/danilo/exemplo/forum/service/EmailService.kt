package br.com.danilo.exemplo.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {

    fun notificar(){
        val message = SimpleMailMessage()

        message.setSubject("")
        message.setText("")
        message.setTo("")

        javaMailSender.send(message)
    }

}