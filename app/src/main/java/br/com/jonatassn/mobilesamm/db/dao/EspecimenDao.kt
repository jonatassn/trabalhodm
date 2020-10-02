package br.com.jonatassn.mobilesamm.db.dao

import androidx.room.*
import br.com.jonatassn.mobilesamm.entities.Especimen

@Dao
interface EspecimenDao {
    @Query("select * from especimens order by id")
    fun getAll() : List<Especimen>

    @Query("select * from especimens where id = :id limit 1 ")
    fun findById(id: Int) : Especimen?

    @Insert
    fun insert(especimen: Especimen) : Long

    @Update
    fun update(especimen: Especimen)

    @Delete
    fun delete(especimen: Especimen)
}