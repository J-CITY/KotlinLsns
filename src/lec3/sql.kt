//INSTALL THIS:: org.xerial:sqlite-jdbc:3.28.0

package lec3

import java.sql.*


class sql {

    fun create(fileName: String) {
        val url = "jdbc:sqlite:C:/Users/333da/Desktop/Lsns/KotlinLsns/$fileName"

        val sql = ("CREATE TABLE IF NOT EXISTS employees (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " capacity real\n"
                + ");")

        try {
            val conn = DriverManager.getConnection(url)
            val stmt: Statement = conn.createStatement()
            stmt.execute(sql)
        } catch (e: SQLException) {
            println(e.message)
        }

    }

    private fun connect(fileName: String): Connection? {
        val url = "jdbc:sqlite:C:/Users/333da/Desktop/Lsns/KotlinLsns/$fileName"
        var conn: Connection? = null
        try {
            conn = DriverManager.getConnection(url)
        } catch (e: SQLException) {
            println(e.message)
        }
        return conn
    }

    fun insert(name: String?, capacity: Double) {
        val sql = "INSERT INTO employees(name, capacity) VALUES(?,?)"
        try {
            val conn: Connection? = this.connect("db.db")
            val pstmt: PreparedStatement? = conn?.prepareStatement(sql)
            pstmt?.setString(1, name)
            pstmt?.setDouble(2, capacity)
            pstmt?.executeUpdate()
        } catch (e: SQLException) {
            println(e.message)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            sql().create("db.db")
        }
    }
}