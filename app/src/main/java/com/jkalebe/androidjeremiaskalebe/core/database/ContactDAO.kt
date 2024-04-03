package com.jkalebe.androidjeremiaskalebe.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jkalebe.androidjeremiaskalebe.domain.models.database.ContactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: ContactEntity)

    @Query("SELECT * FROM Contact")
    fun getAllContact(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM Contact WHERE id = :contactId")
    fun getContactById(contactId: Int): Flow<ContactEntity?>

    @Update
    suspend fun updateContact(contact: ContactEntity)
}