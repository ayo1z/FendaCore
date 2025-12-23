package io.a.fendacore

import net.byteflux.libby.BukkitLibraryManager
import net.byteflux.libby.Library
import taboolib.common.platform.Plugin
import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration
import taboolib.platform.BukkitPlugin

object FendaCorePlugin : Plugin() {

    @Config("config.yml")
    lateinit var config: Configuration
        private set

    override fun onLoad() {
        val manager = BukkitLibraryManager(BukkitPlugin.getInstance(), "library")

        manager.addRepository("https://maven.aliyun.com/repository/central")

        manager.loadLibrary(
            Library.builder()
                .groupId("com{}github{}ben-manes{}caffeine")
                .artifactId("caffeine")
                .version("3.2.3")
                //.relocate("com{}github{}benmanes{}caffeine", "io{}a{}fendacore{}library{}caffeine")
                .build()
        )
    }
}