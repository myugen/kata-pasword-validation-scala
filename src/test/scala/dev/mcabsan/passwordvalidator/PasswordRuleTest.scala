package dev.mcabsan.passwordvalidator

import dev.mcabsan.passwordvalidator.{AtLeastOneDigitPasswordRule, Result}
import org.scalatest.OptionValues
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatest.prop.Tables.Table
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor5}

class PasswordRuleTest extends AnyFlatSpec with should.Matchers with OptionValues {

  val cases: TableFor5[String, String, () => PasswordRule, String, String] = Table(
    ("rule name", "test case", "instance", "value", "message"),
    (
      "MoreThanEightCharsPasswordRule",
      "inform if password has not more than eight characters",
      () => MoreThanEightCharsPasswordRule(),
      "less8",
      "Password must contain more than 8 characters."
    ),
    (
      "AtLeastOneUpperLetterPasswordRule",
      "inform if password has not at least an upper letter",
      () => AtLeastOneUpperLetterPasswordRule(),
      "noupperletter",
      "Password must contain at least an upper letter."
    ),
    (
      "AtLeastOneLowerLetterPasswordRule",
      "inform if password has not at least a lower letter",
      () => AtLeastOneLowerLetterPasswordRule(),
      "NOLOWERLETTER",
      "Password must contain at least a lower letter."
    ),
    (
      "AtLeastOneDigitPasswordRule",
      "inform if password has not at least a digit",
      () => AtLeastOneDigitPasswordRule(),
      "nodigit",
      "Password must contain at least a digit."
    ),
    (
      "AtLeastOneUnderscorePasswordRule",
      "should inform if password has not at least an underscore",
      () => AtLeastOneUnderscorePasswordRule(),
      "nounderscore",
      "Password must contain at least an underscore."
    )
  )

  TableDrivenPropertyChecks.forAll(cases) {
    (
        ruleName: String,
        testcase: String,
        instance: () => PasswordRule,
        value: String,
        message: String
    ) =>
      behavior of ruleName

      it should testcase in {
        val rule = instance()

        val maybeResult = rule.check(value)

        maybeResult.isDefined should be(true)
        maybeResult.get should be(Result(message))
      }
  }

}
