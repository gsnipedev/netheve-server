package com.gsnipedev.netheve.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class NetheveServerApplication

fun main(args: Array<String>) {
	runApplication<NetheveServerApplication>(*args)
}

