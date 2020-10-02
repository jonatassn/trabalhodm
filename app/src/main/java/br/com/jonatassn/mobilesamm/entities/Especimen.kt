package br.com.jonatassn.mobilesamm.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "especimens")
data class Especimen(
    @ColumnInfo(name = "nickname")
    var nickname : String ,
    @ColumnInfo(name = "age")
    var age : Int,
    @ColumnInfo(name = "sex")
    var sex : String,
    @ColumnInfo(name = "biometric_info")
    @SerializedName("biometric_info")
    var biometricInfo : String,
    @ColumnInfo(name = "tag_number")
    var tagNumber : Long,
    @ColumnInfo(name = "done")
    var done : Boolean
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0L
}