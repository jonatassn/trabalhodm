package br.com.jonatassn.mobilesamm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.jonatassn.mobilesamm.db.dao.EspecimenDao
import br.com.jonatassn.mobilesamm.entities.Especimen

@Database(entities = arrayOf(Especimen::class), version = 2)
abstract class AppDatabase : RoomDatabase(){
    abstract fun especimenDao() : EspecimenDao
}