package dev.mcabsan.passwordvalidator

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatest.{EitherValues, TryValues}

import scala.util.Success

class PasswordTest extends AnyFlatSpec with should.Matchers with EitherValues {

  val correctPassword = "Passsw0rd_"

  "Password" should "be invalid if contains 8 characters or less" in {
    val password = Password.from("Pasw0rd_")

    password.isLeft should be(true)
    password.left.value should be(Result("Password must contain more than 8 characters."))
  }

  it should "be invalid if there is no any uppercase letter" in {
    val password = Password.from("passsw0rd_")

    password.isLeft should be(true)
    password.left.value should be(Result("Password must contain at least an upper letter."))
  }

  it should "be invalid if there is no any lowercase letter" in {
    val password = Password.from("PASSSW0RD_")

    password.isLeft should be(true)
    password.left.value should be(Result("Password must contain at least a lower letter."))
  }

  it should "be invalid if there is no any number" in {
    val password = Password.from("Passsword_")

    password.isLeft should be(true)
    password.left.value should be(Result("Password must contain at least a digit."))
  }

  it should "be invalid if there is no any underscore" in {
    val password = Password.from("Passsw0rdd")

    password.isLeft should be(true)
    password.left.value should be(Result("Password must contain at least an underscore."))
  }

  it should "should be valid if pass of rules" in {
    val password = Password.from(correctPassword)

    password.isRight should be(true)
  }
}
