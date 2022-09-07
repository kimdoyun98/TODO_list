package com.example.todo_list.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDoEntity::class], version = 1)
abstract class ToDoDB : RoomDatabase() {
    abstract fun todoDao(): ToDoDAO

    companion object {
        private var INSTANCE: ToDoDB? = null

        fun getInstance(context: Context): ToDoDB? {
            if (INSTANCE == null) {
                // 여러 Thread 가 접근하지 못하도록 Synchronized 사용
                synchronized(ToDoDB::class) {
                    // Room 인스턴스 생성
                    // 데이터 베이스가 갱신될 때 기존의 테이블을 버리고 새로 사용하도록 설정
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ToDoDB::class.java, "todo"
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