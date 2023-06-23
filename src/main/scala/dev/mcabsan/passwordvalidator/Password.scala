package dev.mcabsan.passwordvalidator

case class Password private (value: String)

object Password {
  def from(value: String, rules: Seq[PasswordRule] = defaultRules): Either[Result, Password] = {
    rules.flatMap(_.check(value)).headOption match
      case Some(result) => Left(result)
      case None         => Right(Password(value))
  }
  private val defaultRules: List[PasswordRule] = List(
    MoreThanEightCharsPasswordRule(),
    AtLeastOneUpperLetterPasswordRule(),
    AtLeastOneLowerLetterPasswordRule(),
    AtLeastOneDigitPasswordRule(),
    AtLeastOneUnderscorePasswordRule()
  )
}
