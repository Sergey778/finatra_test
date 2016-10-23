package com.example.db


import java.sql.{Connection, DriverManager}

import com.twitter.util.Future
import org.postgresql.util.HostSpec

/**
  * Created by Sergey on 23.10.16.
  */
object DB {
  val url = "jdbc:postgresql://localhost:5432/postgres?user=Sergey&password=root"
  val connection = DriverManager.getConnection(url)

  def isValidToken(token: String) = {
    val statement = connection.prepareStatement("SELECT access_token FROM user_access WHERE access_token = ?")
    statement.setString(1, token)
    Future {
      statement.executeQuery()
    } map { resultSet =>
      resultSet.next()
    }
  }

  def createToken(userId: BigInt): Future[String] = {
    val token = java.util.UUID.randomUUID().toString
    val statement = connection.prepareStatement("INSERT INTO user_access VALUES (?, ?)")
    statement.setBigDecimal(2, BigDecimal(userId).bigDecimal)
    statement.setString(1, token)
    Future {
      statement.executeUpdate()
    } map {
      case 1 => token
      case _ => ""
    }
  }
}
