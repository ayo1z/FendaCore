package io.a.fendacore.module.birthday

import com.github.benmanes.caffeine.cache.Caffeine
import io.a.fendacore.database.DatabaseConnection
import io.a.fendacore.module.birthday.data.BirthdayData
import taboolib.module.database.ColumnOptionSQL
import taboolib.module.database.ColumnTypeSQL
import taboolib.module.database.Table
import java.util.concurrent.TimeUnit

object BirthdayManager {

    private val cache = Caffeine.newBuilder()
        .expireAfterWrite(6, TimeUnit.HOURS)
        .maximumSize(1000)
        .build<String, BirthdayData?>()

    val table = Table("player_birthday", DatabaseConnection.host) {
        add("uuid") {
            type(ColumnTypeSQL.VARCHAR, 36) {
                options(ColumnOptionSQL.PRIMARY_KEY, ColumnOptionSQL.NOTNULL)
            }
        }

        // 生日日期
        add("birthday") {
            type(ColumnTypeSQL.DATE) {
                options(ColumnOptionSQL.KEY)
            }
        }

        // 上次设置生日的时间戳
        add("last_set_time") {
            type(ColumnTypeSQL.BIGINT) {
                options(ColumnOptionSQL.NOTNULL)
            }
        }

        // 今年已领取福利次数
        add("claim_count") {
            type(ColumnTypeSQL.INT) {
                options(ColumnOptionSQL.NOTNULL)
            }
        }

        // 玩家是否关闭生日歌
        add("song_disabled") {
            type(ColumnTypeSQL.BOOLEAN) {
                options(ColumnOptionSQL.NOTNULL)
            }
        }
    }

    fun loadBirthdayData(uuid: String): BirthdayData? {
        return cache.get(uuid) { key ->
            table.select(DatabaseConnection.dataSource) {
                where("uuid" eq key)
            }.firstOrNull {
                BirthdayData(
                    // SQL Date? → LocalDate?（数据库可能为 NULL，所以用 ?.）
                    birthday = getDate("birthday")?.toLocalDate(),
                    lastSetTime = getLong("last_set_time"),
                    claimCount = getInt("claim_count"),
                    songDisabled = getBoolean("song_disabled")
                )
            }
        }
    }

    fun saveBirthdayData(uuid: String, data: BirthdayData) {
        table.insert(DatabaseConnection.dataSource, "uuid", "birthday", "last_set_time", "claim_count", "song_disabled") {
            value(
                uuid,
                data.birthday,
                data.lastSetTime,
                data.claimCount,
                data.songDisabled
            )

            onDuplicateKeyUpdate {
                data.birthday?.let { update("birthday", it) }
                update("last_set_time", data.lastSetTime)
                update("claim_count", data.claimCount)
                update("song_disabled", data.songDisabled)
            }
        }

        cache.put(uuid, data)
    }
}