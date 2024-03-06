package com.example.todo_list.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ToDoEntity::class, CycleEntity::class], version = 3, exportSchema = false)
@TypeConverters(DayListConverter::class)
abstract class ToDoDataBase : RoomDatabase() {
    abstract fun todoDao(): ToDoDAO
    abstract fun cycleDao(): CycleDAO

    companion object {
        private var INSTANCE: ToDoDataBase? = null

        fun getInstance(context: Context): ToDoDataBase? {
            if (INSTANCE == null) {
                // 여러 Thread 가 접근하지 못하도록 Synchronized 사용
                synchronized(ToDoDataBase::class) {
                    // Room 인스턴스 생성
                    // 데이터 베이스가 갱신될 때 기존의 테이블을 버리고 새로 사용하도록 설정
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ToDoDataBase::class.java, "todo"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            // 만들어지는 DB 인스턴스는 Repository 에서 호출되어 사용
            return INSTANCE

        }
    }
}