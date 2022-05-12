package com.example.fullstackv2.local

import com.example.fullstackv2.CustomNameDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            schema = CustomNameDatabase.Schema,
            context = androidContext().applicationContext,
            name = "movie.db"
        )
    }
}