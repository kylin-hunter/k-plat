package io.github.kylinhunter.plat.core.security.password;

import java.util.regex.Pattern;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-16 16:45
 */
public class WeakPassChecker {


  /*
   * basic check rule
   * 1 contains lowercase letters
   * 2, containing capital letters
   * 3, contains special symbols
   * 4, containing numbers
   * */
  private Pattern PATTERN_BASIC;


  /**
   * Duplicate character
   */
  private Pattern PATTERN_DUPLICATE_CHARACTER;


  /**
   * Continuous number  in ascending order
   */
  private Pattern PATTERN_CONTINUOUS_NUMBER_ASC;


  /**
   * Continuous number  in  ascending order
   */
  private Pattern PATTERN_CONTINUOUS_NUMBER_DESC;


  /**
   * Continuous letters  in ascending order
   */
  private Pattern PATTERN_CONTINUOUS_LETTERS_ASC;

  /**
   * Continuous letters  in descending order
   */
  private Pattern PATTERN_CONTINUOUS_LETTERS_DESC;
  /**
   * Keyboard horizontal  continuous letters  in ascending order
   */
  private Pattern PATTERN_KEYBOARD_HORIZONTAL_CONTINUOUS_LETTERS;

  /**
   * Keyboard vertical  continuous letters  in ascending order
   */
  private Pattern PATTERN_KEYBOARD_VERTICAL_CONTINUOUS_LETTERS;

  public WeakPassChecker(WeakPassOption weakPassOption) {
    PATTERN_BASIC = Pattern.compile(
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$!%*?&])[A-Za-z\\d@#$!%*?&]{"
            + weakPassOption.getMinLength() + "," + weakPassOption.getMaxLength() + "}$");

    PATTERN_DUPLICATE_CHARACTER = Pattern.compile(
        "([0-9a-zA-Z])\\1{" + (weakPassOption.getMinDuplicateChars() - 1) + "}");

    PATTERN_CONTINUOUS_NUMBER_ASC = Pattern.compile(
        "(?:0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){"
            + (weakPassOption.getMinContinuousChars() - 1) + "}");

    PATTERN_CONTINUOUS_NUMBER_DESC = Pattern.compile(
        "(?:9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){" + (
            weakPassOption.getMinContinuousChars() - 1) + "}");

    PATTERN_CONTINUOUS_LETTERS_ASC = Pattern.compile(
        "(?:a(?=b)|b(?=c)|c(?=d)|d(?=e)|e(?=f)|f(?=g)|g(?=h)|h(?=i)|i(?=j)|j(?=k)|k(?=l)|l(?=m)|m(?=n)|n(?=o)|o(?=p)|p(?=q)|q(?=r)|r(?=s)|s(?=t)|t(?=u)|u(?=v)|v(?=w)|w(?=x)|x(?=y)|y(?=z)){"
            + (weakPassOption.getMinContinuousChars() - 1) + "}");

    PATTERN_CONTINUOUS_LETTERS_DESC = Pattern.compile(
        "(?:z(?=y)|y(?=x)|x(?=w)|w(?=v)|v(?=u)|u(?=t)|t(?=s)|s(?=r)|r(?=q)|q(?=p)|p(?=o)|o(?=n)|n(?=m)|m(?=l)|l(?=k)|k(?=j)|j(?=i)|i(?=h)|h(?=g)|g(?=f)|f(?=e)|e(?=d)|d(?=c)|c(?=b)|b(?=a)){"
            + (weakPassOption.getMinContinuousChars() - 1) + "}");
    PATTERN_KEYBOARD_HORIZONTAL_CONTINUOUS_LETTERS = Pattern.compile(
        "(?:q(?=w)|w(?=e)|e(?=r)|r(?=t)|t(?=y)|y(?=u)|u(?=i)|i(?=o)|o(?=p)|a(?=s)|s(?=d)|d(?=f)|f(?=g)|g(?=h)|h(?=j)|j(?=k)|k(?=l)|z(?=x)|x(?=c)|c(?=v)|v(?=b)|b(?=n)|n(?=m)){"
            + (weakPassOption.getMinContinuousChars() - 1) + "}");

    PATTERN_KEYBOARD_VERTICAL_CONTINUOUS_LETTERS = Pattern.compile(
        "(?:1(?=q)|q(?=a)|a(?=z)|2(?=w)|w(?=s)|s(?=x)|3(?=e)e(?=d)|d(?=c)|4(?=r)|r(?=f)|f(?=v)|5(?=t)|t(?=g)|g(?=b)|6(?=y)|y(?=h)|h(?=n)|7(?=u)|u(?=j)|j(?=m)|8(?=i)|i(?=k)|k(?=,)|9(?=o)|o(?=l)|l(?=.)|0(?=p)|p(?=;)|;(?=/)){"
            + (weakPassOption.getMinContinuousChars() - 1) + "}");


  }

  /**
   * @param password password
   * @return boolean
   * @title isWeak
   * @description isWeak
   * @author BiJi'an
   * @date 2023-10-17 00:20
   */
  public boolean isWeak(String password) {

    if (PATTERN_BASIC.matcher(password).matches()) {
      String pattern = "([0-9a-zA-Z])\\1{2}";
      Pattern p = Pattern.compile(pattern);
      boolean isWeak = p.matcher(password).find();
      if (!isWeak) {
        isWeak = PATTERN_DUPLICATE_CHARACTER.matcher(password).find();
      }
      if (!isWeak) { //number asc
        isWeak = PATTERN_CONTINUOUS_NUMBER_ASC.matcher(password).find();

      }
      if (!isWeak) { // number desc
        isWeak = PATTERN_CONTINUOUS_NUMBER_DESC.matcher(password).find();

      }
      if (!isWeak) { // letter asc
        isWeak = PATTERN_CONTINUOUS_LETTERS_ASC.matcher(password).find();

      }
      if (!isWeak) { // letter desc
        isWeak = PATTERN_CONTINUOUS_LETTERS_DESC.matcher(password).find();

      }
      if (!isWeak) { //keyboard horizontal letters

        isWeak = PATTERN_KEYBOARD_HORIZONTAL_CONTINUOUS_LETTERS.matcher(password).find();
      }
      if (!isWeak) {  //keyboard vertical letters

        isWeak = PATTERN_KEYBOARD_VERTICAL_CONTINUOUS_LETTERS.matcher(password).find();
      }

      return isWeak;
    }
    return true;
  }


}