package dev.mcabsan.passwordvalidator

sealed trait PasswordRule {
  def check(value: String): Option[Result]
}

class MoreThanEightCharsPasswordRule extends PasswordRule {
  override def check(value: String): Option[Result] = if (value.length <= 8)
    Some(Result("Password must contain more than 8 characters."))
  else None
}

class AtLeastOneUpperLetterPasswordRule extends PasswordRule {
  override def check(value: String): Option[Result] = if (!value.exists(_.isUpper))
    Some(Result("Password must contain at least an upper letter."))
  else None
}

class AtLeastOneLowerLetterPasswordRule extends PasswordRule {
  override def check(value: String): Option[Result] = if (!value.exists(_.isLower))
    Some(Result("Password must contain at least a lower letter."))
  else None
}

class AtLeastOneDigitPasswordRule extends PasswordRule {
  override def check(value: String): Option[Result] = if (!value.exists(_.isDigit))
    Some(Result("Password must contain at least a digit."))
  else None
}

class AtLeastOneUnderscorePasswordRule extends PasswordRule {
  override def check(value: String): Option[Result] = if (!value.contains('_'))
    Some(Result("Password must contain at least an underscore."))
  else None
}
