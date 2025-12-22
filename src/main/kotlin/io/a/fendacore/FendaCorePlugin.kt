package io.a.fendacore

import taboolib.common.platform.Plugin
import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration

object FendaCorePlugin : Plugin() {

    @Config("config.yml")
    lateinit var config: Configuration
        private set
}