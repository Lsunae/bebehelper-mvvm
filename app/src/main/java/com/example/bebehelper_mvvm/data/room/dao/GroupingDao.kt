package com.example.bebehelper_mvvm.data.room.dao

import androidx.room.*
import com.example.bebehelper_mvvm.data.room.entity.Grouping


@Dao
interface GroupingDao {

    @Insert
    fun insertAll(vararg group: Grouping?)

    /** 그룹 추가 */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGrouping(group: Grouping): Long

    /** 그룹 정보 업데이트 */
    @Update
    fun updateGrouping(group: Grouping)

    /** 그룹 삭제 */
    @Query("DELETE FROM Grouping WHERE id = :id")
    fun deleteGrouping(id: Int)

    /** 그룹 조회 */
    @Query("SELECT * FROM Grouping WHERE id = :id")
    fun getGrouping(id: Int): Grouping

    /** 그룹 목록 조회 */
    @Query("SELECT * FROM Grouping")
    fun getAll(): List<Grouping>
}